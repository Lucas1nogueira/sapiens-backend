package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sapiens.sapiens.domain.admin.Admin;
import com.sapiens.sapiens.repositories.AdminRepository;
import com.sapiens.sapiens.repositories.AuthRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminService {

    private final AuthRepository authRepository;
    private final AdminRepository adminRepository;

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(authRepository.findAll());
    }

    public ResponseEntity<?> update(Admin admin) {
        return ResponseEntity.ok().body(adminRepository.save(admin));
    }
}
