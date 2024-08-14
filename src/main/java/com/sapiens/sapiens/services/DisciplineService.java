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
        return ResponseEntity.ok().body("Disciplina excluída com sucesso.");
    }

    public ResponseEntity<?> findByName(String name) {
        Discipline discipline = disciplineRepository.findByName(name)
            .orElseThrow(() -> new BusinessException("Disciplina não encontrada."));
        
        return ResponseEntity.ok().body(discipline);
    }

    public ResponseEntity<?> findByCode(String code) {
        Discipline discipline = disciplineRepository.findByCode(code)
            .orElseThrow(() -> new BusinessException("Disciplina não encontrada."));
        
        return ResponseEntity.ok().body(discipline);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(disciplineRepository.findAll());
    }

    public ResponseEntity<?> findByTeacherId(Long id) {
        return ResponseEntity.ok().body(disciplineRepository.findByTeacherId(id));
    }

    public ResponseEntity<?> findBySchoolClassCode(String code) {
        return ResponseEntity.ok().body(disciplineRepository.findBySchoolClassCode(code));
    }

}
