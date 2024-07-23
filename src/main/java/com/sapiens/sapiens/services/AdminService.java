package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.repositories.AuthRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminService {

    private final AuthRepository authRepository;

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(authRepository.findAll());
    }
}
