package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.domain.student.Student;
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
}
