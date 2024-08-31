package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.domain.secretariat.Secretariat;
import com.sapiens.sapiens.infra.exceptions.BusinessException;
import com.sapiens.sapiens.repositories.SecretariatRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SecretariatService {

    private final SecretariatRepository secretariatRepository;

    public ResponseEntity<?> save(Secretariat secretariat) {
        if (secretariatRepository.existsById(secretariat.getId())) {
            throw new BusinessException("Secretaria já registrada.");
        }

        return ResponseEntity.ok().body(secretariatRepository.save(secretariat));
    }

    public ResponseEntity<?> update(Secretariat secretariat) {
        return ResponseEntity.ok().body(secretariatRepository.save(secretariat));
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(secretariatRepository.findAll());
    }

    public ResponseEntity<?> findByName(String name) {
        return ResponseEntity.ok().body(secretariatRepository.findByName(name));
    }

    public ResponseEntity<?> findBySchoolsId(Long id) {
        return ResponseEntity.ok().body(secretariatRepository.findBySchoolsId(id));
    }

}
