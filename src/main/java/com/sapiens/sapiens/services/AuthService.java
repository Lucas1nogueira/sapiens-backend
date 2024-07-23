package com.sapiens.sapiens.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
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

    private final AuthRepository userRepository;
    private final TokenService tokenService;

    public ResponseEntity<?> login(LoginRequest user) {
        var userDB = (User) userRepository.findByEmail(user.email());
        var token = tokenService.generateToken(user.email());

        return ResponseEntity.ok().body(new LoginResponse(user.email(), user.email(), token, userDB.getRole()));
    }

    public ResponseEntity<?> register(RegisterRequest user) {
        if (userRepository.existsByEmail(user.email())) {
            throw new AuthException("User already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.password());
        userRepository.save(new User(user.name(), user.email(), encryptedPassword, user.role()));

        return ResponseEntity.ok().build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

}
