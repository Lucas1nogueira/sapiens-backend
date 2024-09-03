package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.infra.exceptions.BusinessException;
import com.sapiens.sapiens.repositories.SchoolRepository;
import com.sapiens.sapiens.domain.school.School;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public ResponseEntity<?> save(School school) {
        return ResponseEntity.ok().body(schoolRepository.save(school));
    }

    public ResponseEntity<?> update(School school) {
        return ResponseEntity.ok().body(schoolRepository.save(school));
    }

    public ResponseEntity<?> findById(Long id) {
        School school = schoolRepository.getReferenceById(id);

        if (school == null) {
            throw new BusinessException("Escola não encontrada.");
        }

        return ResponseEntity.ok().body(school);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(schoolRepository.findAll());
    }

    public ResponseEntity<?> findByAdminId(Long id) {
        return ResponseEntity.ok().body(schoolRepository.findByAdminId(id));
    }

    public ResponseEntity<?> findBySecretariatId(Long id) {
        return ResponseEntity.ok().body(schoolRepository.findBySecretariatId(id));
    }

    public ResponseEntity<?> findByStudentsId(Long id) {
        return ResponseEntity.ok().body(schoolRepository.findByStudentsId(id));
    }

    public ResponseEntity<?> findByTeachersId(Long id) {
        return ResponseEntity.ok().body(schoolRepository.findByTeachersId(id));
    }

    public ResponseEntity<?> findByDisciplinesCode(String code) {
        return ResponseEntity.ok().body(schoolRepository.findByDisciplinesCode(code));
    }

    public ResponseEntity<?> findBySchoolClassesCode(String code) {
        return ResponseEntity.ok().body(schoolRepository.findBySchoolClassesCode(code));
    }

}
