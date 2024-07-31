package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.domain.user.ChangePasswordRequest;
import com.sapiens.sapiens.domain.user.LoginRequest;
import com.sapiens.sapiens.domain.user.LoginResponse;
import com.sapiens.sapiens.domain.user.RegisterRequest;
import com.sapiens.sapiens.domain.user.User;
import com.sapiens.sapiens.infra.exceptions.AuthException;
import com.sapiens.sapiens.repositories.AuthRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private final AuthRepository authRepository;
    private final TokenService tokenService;

    public ResponseEntity<?> login(LoginRequest user) {
        var userDB = (User) authRepository.findByEmail(user.email());
        var token = tokenService.generateToken(user.email());

        return ResponseEntity.ok().body(
            new LoginResponse(user.email(), user.email(), token, userDB.getRole(), userDB.isFirstLogin()));
    }

    public ResponseEntity<?> register(RegisterRequest user) {
        if (authRepository.existsByEmail(user.email())) {
            throw new AuthException("Usuario já existe");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.password());
        authRepository.save(new User(user.name(), user.email(), encryptedPassword, user.role()));

        return ResponseEntity.ok().build();
    }

    // public ResponseEntity<?> refreshToken() {
    //     return ResponseEntity.ok().body(tokenService.refreshToken());
    // }

    public ResponseEntity<?> changePassword(ChangePasswordRequest user) {
        var userDB = authRepository.findUserByEmail(user.email())
                .orElseThrow(() -> new AuthException("Usuario nao encontrado"));
        
        var encrypt = new BCryptPasswordEncoder();

        if (!encrypt.matches(user.password(), userDB.getPassword())) {
            throw new AuthException("A sua senha antiga está incorreta");
        }

        if (!userDB.isFirstLogin() && user.password().equals(user.newPassword())) {
          throw new AuthException("A nova senha deve ser diferente da antiga");
        }

        var encryptedNewPassword = encrypt.encode(user.newPassword());
        userDB.setPassword(encryptedNewPassword);
        userDB.setFirstLogin(false);

        return ResponseEntity.ok().body(authRepository.save(userDB));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authRepository.findByEmail(username);
    }

}
