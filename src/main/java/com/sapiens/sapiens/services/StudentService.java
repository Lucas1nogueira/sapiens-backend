package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.ok(studentRepository.save(student));
    }

    public ResponseEntity<?> update(Student student) {
        return ResponseEntity.ok(studentRepository.save(student));
    }
    
    public ResponseEntity<?> findByMatriculation(String matriculation) {
        Student student = studentRepository.findByMatriculation(matriculation)
            .orElseThrow(() -> new BusinessException("Student not found"));
        
        return ResponseEntity.ok().body(student);
    }

    public ResponseEntity<?> findByEmail(String email) {
        Student student = studentRepository.findByEmail(email)
            .orElseThrow(() -> new BusinessException("Student not found"));
        
        return ResponseEntity.ok().body(student);
    }

}
