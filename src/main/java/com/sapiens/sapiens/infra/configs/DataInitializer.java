package com.sapiens.sapiens.infra.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.sapiens.sapiens.domain.secretariat.Secretariat;
import com.sapiens.sapiens.domain.superadmin.SuperAdmin;
import com.sapiens.sapiens.domain.user.UserRole;
import com.sapiens.sapiens.repositories.AuthRepository;
import com.sapiens.sapiens.repositories.SecretariatRepository;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initializeData(AuthRepository authRepository, SecretariatRepository secretariatRepository,
            BCryptPasswordEncoder encoder) {
        return args -> {
            if (authRepository.findByRole(UserRole.SUPERADMIN).isEmpty()) {

                var user = new SuperAdmin(
                        "Super Admin",
                        "super@mail.com",
                        encoder.encode("super123"),
                        UserRole.SUPERADMIN);
                user.setFirstLogin(false);
                var superAdmin = authRepository.save(user);

                var secretariat = new Secretariat();
                secretariat.setName("Sapiens Educação");
                secretariat.setSuperAdmin(superAdmin);

                secretariatRepository.save(secretariat);
            }
        };
    }
}
