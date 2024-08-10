package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.domain.schoolClass.SchoolClass;
import com.sapiens.sapiens.infra.exceptions.BusinessException;
import com.sapiens.sapiens.repositories.SchoolClassRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SchoolClassService {
    
    private final SchoolClassRepository schoolClassRepository;

    public ResponseEntity<?> save(SchoolClass schoolClass) {
        if (schoolClassRepository.existsByCode(schoolClass.getCode())) {
            throw new BusinessException("Turma já registrada.");
        }

        return ResponseEntity.ok().body(schoolClassRepository.save(schoolClass));
    }

    public ResponseEntity<?> update(SchoolClass schoolClass) {
        return ResponseEntity.ok().body(schoolClassRepository.save(schoolClass));
    }
    
    public ResponseEntity<?> findByGroupCode(String code) {
        SchoolClass schoolClass = schoolClassRepository.findByCode(code)
            .orElseThrow(() -> new BusinessException("Turma não encontrada."));
        
        return ResponseEntity.ok().body(schoolClass);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(schoolClassRepository.findAll());
    }

    public ResponseEntity<?> findByTeacherId(Long id) {
        return ResponseEntity.ok().body(schoolClassRepository.findByTeachersId(id));
    }

    public ResponseEntity<?> findByStudentId(Long id) {
        return ResponseEntity.ok().body(schoolClassRepository.findByStudentsId(id));
    }
}
