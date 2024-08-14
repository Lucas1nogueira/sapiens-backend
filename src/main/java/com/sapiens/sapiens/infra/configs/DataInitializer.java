package com.sapiens.sapiens.infra.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.sapiens.sapiens.domain.user.User;
import com.sapiens.sapiens.domain.user.UserRole;
import com.sapiens.sapiens.repositories.AuthRepository;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initializeData(AuthRepository authRepository, BCryptPasswordEncoder encoder) {
        return args -> {
            if (authRepository.findByRole(UserRole.ADMIN).isEmpty()) {
                var user = new User(
                    "Admin",
                    "admin@mail.com",
                    encoder.encode("admin123"),
                    UserRole.ADMIN
                );
                user.setFirstLogin(false);
                authRepository.save(user);
            }
        };
    }
}
