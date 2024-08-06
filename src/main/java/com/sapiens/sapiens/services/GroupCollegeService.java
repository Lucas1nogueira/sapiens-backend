package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.domain.groupCollege.GroupCollege;
import com.sapiens.sapiens.infra.exceptions.BusinessException;
import com.sapiens.sapiens.repositories.GroupCollegeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GroupCollegeService {
    
    private final GroupCollegeRepository groupCollegeRepository;

    public ResponseEntity<?> save(GroupCollege groupCollege) {
        if (groupCollegeRepository.existsByGroupCode(groupCollege.getGroupCode())) {
            throw new BusinessException("Turma já registrada.");
        }

        return ResponseEntity.ok().body(groupCollegeRepository.save(groupCollege));
    }

    public ResponseEntity<?> update(GroupCollege groupCollege) {
        return ResponseEntity.ok().body(groupCollegeRepository.save(groupCollege));
    }
    
    public ResponseEntity<?> findByGroupCode(String groupCode) {
        GroupCollege groupCollege = groupCollegeRepository.findByGroupCode(groupCode)
            .orElseThrow(() -> new BusinessException("Turma não encontrada."));
        
        return ResponseEntity.ok().body(groupCollege);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(groupCollegeRepository.findAll());
    }

    public ResponseEntity<?> findByTeacherId(Long id) {
        return ResponseEntity.ok().body(groupCollegeRepository.findByTeachersId(id));
    }

}
