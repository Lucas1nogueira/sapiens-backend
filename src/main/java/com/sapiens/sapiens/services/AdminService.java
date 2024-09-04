package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.domain.admin.Admin;
import com.sapiens.sapiens.domain.user.UserRole;
import com.sapiens.sapiens.repositories.AdminRepository;
import com.sapiens.sapiens.repositories.AuthRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminService {

    private final AuthRepository authRepository;
    private final AdminRepository adminRepository;

    public ResponseEntity<?> save(Admin admin) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(admin.getPassword());

        admin.setPassword(encryptedPassword);
        admin.setFirstLogin(true);

        return ResponseEntity.ok().body(authRepository.save(admin));
    }

    public ResponseEntity<?> findAllAdmins() {
        return ResponseEntity.ok().body(authRepository.findByRole(UserRole.ADMIN));
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(authRepository.findAll());
    }

    public ResponseEntity<?> update(Admin admin) {
        return ResponseEntity.ok().body(adminRepository.save(admin));
    }

}
