package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.infra.exceptions.BusinessException;
import com.sapiens.sapiens.repositories.SchoolRepository;
import com.sapiens.sapiens.domain.school.School;
import com.sapiens.sapiens.domain.user.RegisterRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final AuthService authService;

    public ResponseEntity<?> save(School school) {
        var admin = school.getAdmin();

        authService.register(
                new RegisterRequest(admin.getName(), admin.getEmail(), admin.getPassword(), admin.getRole()));

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

    public ResponseEntity<?> findBySecretariatId(Long id) {
        return ResponseEntity.ok().body(schoolRepository.findBySecretariatId(id));
    }

}
