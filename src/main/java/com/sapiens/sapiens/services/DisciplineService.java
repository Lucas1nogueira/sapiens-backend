package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.domain.discipline.Discipline;
import com.sapiens.sapiens.infra.exceptions.BusinessException;
import com.sapiens.sapiens.repositories.DisciplineRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DisciplineService {
    
    private final DisciplineRepository disciplineRepository;

    public ResponseEntity<?> save(Discipline discipline) {
        return ResponseEntity.ok().body(disciplineRepository.save(discipline));
    }

    public ResponseEntity<?> update(Discipline discipline) {
        return ResponseEntity.ok().body(disciplineRepository.save(discipline));
    }

    public ResponseEntity<?> delete(Discipline discipline) {
        disciplineRepository.delete(discipline);
        return ResponseEntity.ok().body("Discipline was deleted");
    }

    public ResponseEntity<?> findByName(String name) {
        Discipline discipline = disciplineRepository.findByName(name)
            .orElseThrow(() -> new BusinessException("Discipline not found"));
        
        return ResponseEntity.ok().body(discipline);
    }

    public ResponseEntity<?> findByDisciplineCode(String disciplineCode) {
        Discipline discipline = disciplineRepository.findByDisciplineCode(disciplineCode)
            .orElseThrow(() -> new BusinessException("Discipline not found"));
        
        return ResponseEntity.ok().body(discipline);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(disciplineRepository.findAll());
    }

}