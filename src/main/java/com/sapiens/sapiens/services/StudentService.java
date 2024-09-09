package com.sapiens.sapiens.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.domain.attendance.Attendance;
import com.sapiens.sapiens.domain.discipline.Discipline;
import com.sapiens.sapiens.domain.lesson.Lesson;
import com.sapiens.sapiens.domain.student.Report;
import com.sapiens.sapiens.domain.student.Student;
import com.sapiens.sapiens.domain.student.Subject;
import com.sapiens.sapiens.infra.exceptions.BusinessException;
import com.sapiens.sapiens.repositories.StudentRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public ResponseEntity<?> save(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new BusinessException("E-mail já registrado.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(student.getPassword());

        student.setPassword(encryptedPassword);
        student.setFirstLogin(true);

        return ResponseEntity.ok().body(studentRepository.save(student));
    }

    public ResponseEntity<?> update(Student student) {
        return ResponseEntity.ok().body(studentRepository.save(student));
    }

    public ResponseEntity<?> findByMatriculation(String matriculation) {
        Student student = studentRepository.findByMatriculation(matriculation)
                .orElseThrow(() -> new BusinessException("Estudante não encontrado."));

        return ResponseEntity.ok().body(student);
    }

    public ResponseEntity<?> findByEmail(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Estudante não encontrado."));

        return ResponseEntity.ok().body(student);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(studentRepository.findAll());
    }

    public ResponseEntity<?> findBySchoolClassCode(String code) {
        return ResponseEntity.ok().body(studentRepository.findBySchoolClassCode(code));
    }

    public ResponseEntity<?> findBySchoolId(Long id) {
        return ResponseEntity.ok().body(studentRepository.findBySchoolId(id));
    }

    private int getLessonsAttended(Discipline discipline, Student student) {
        int lessonsAttended = 0;

        List<Lesson> allLessons = discipline.getLessons();

        Map<Long, Integer> studentAttendancesMap = new HashMap<>();
        for (Attendance attendance : student.getAttendances()) {
            studentAttendancesMap.put(attendance.getLesson().getId(), attendance.getAttendedCount());
        }

        for (Lesson lesson : allLessons) {
            Integer attendanceCount = studentAttendancesMap.get(lesson.getId());

            if (attendanceCount != null) {
                lessonsAttended += attendanceCount;
            }
        }

        return lessonsAttended;
    }

    public ResponseEntity<?> report(Long id) {
        Student student = studentRepository.getReferenceById(id);

        if (student == null) {
            throw new BusinessException("Estudante não encontrado.");
        }

        var disciplines = student.getSchoolClass().getDisciplines();

        var subjects = disciplines.stream().map(discipline -> {
            int manyLessons = discipline.getManyLessons();
            int lessonsAttended = getLessonsAttended(discipline, student);
            int lessonsMissed = manyLessons - lessonsAttended;
            double attendancePercentage = (double) lessonsAttended / manyLessons * 100.0;
            double finalGrade = 0.0;
            String status = "EM DESENVOLVIMENTO";

            return new Subject(
                    discipline.getCode(),
                    discipline.getName(),
                    manyLessons,
                    lessonsAttended,
                    lessonsMissed,
                    attendancePercentage,
                    status,
                    finalGrade,
                    student.getGrades());
        }).toList();

        var report = new Report(student.getName(), student.getMatriculation(), subjects);

        return ResponseEntity.ok().body(report);
    }

}
