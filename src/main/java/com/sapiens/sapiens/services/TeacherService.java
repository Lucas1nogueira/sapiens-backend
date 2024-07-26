package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.domain.teacher.Teacher;
import com.sapiens.sapiens.infra.exceptions.BusinessException;
import com.sapiens.sapiens.repositories.TeacherRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeacherService {
    
    private final TeacherRepository teacherRepository;

    public ResponseEntity<?> save(Teacher teacher) {
        if (teacherRepository.existsByEmail(teacher.getEmail())) {
            throw new BusinessException("Email already registered");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(teacher.getPassword());
        teacher.setPassword(encryptedPassword);
        teacher.setFirstLogin(true);

        return ResponseEntity.ok().body(teacherRepository.save(teacher));
    }

    public ResponseEntity<?> update(Teacher teacher) {
        return ResponseEntity.ok().body(teacherRepository.save(teacher));
    }
    
    public ResponseEntity<?> findByTeacherCode(String teacherCode) {
        Teacher teacher = teacherRepository.findByTeacherCode(teacherCode)
            .orElseThrow(() -> new BusinessException("Teacher not found"));
        
        return ResponseEntity.ok().body(teacher);
    }

    public ResponseEntity<?> findByEmail(String email) {
        Teacher teacher = teacherRepository.findByEmail(email)
            .orElseThrow(() -> new BusinessException("Teacher not found"));
        
        return ResponseEntity.ok().body(teacher);
    }

}