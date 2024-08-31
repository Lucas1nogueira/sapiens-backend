package com.sapiens.sapiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.user.ChangePasswordRequest;
import com.sapiens.sapiens.domain.user.LoginRequest;
import com.sapiens.sapiens.domain.user.RegisterRequest;
import com.sapiens.sapiens.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest user) {
        return authService.login(user, authenticationManager);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest user) {
        return authService.register(user);
    }

    // @PostMapping("/refresh")
    // public ResponseEntity<?> refreshToken() {
    // return authService.refreshToken();
    // }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest user) {
        return authService.changePassword(user);
    }

}
