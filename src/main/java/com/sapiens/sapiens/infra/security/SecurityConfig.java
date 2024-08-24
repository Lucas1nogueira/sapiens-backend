package com.sapiens.sapiens.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                    // Auth Controller
                    .requestMatchers(HttpMethod.POST, "/api/auth/register").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/auth/change-password").permitAll()
                    
                    // Admin Controller
                    .requestMatchers("/api/admin/**").hasRole("ADMIN")
    
                    // Attendance Controller
                    .requestMatchers("/api/attendance/save-one").hasAnyRole("ADMIN", "TEACHER")
                    .requestMatchers("/api/attendance/save-many").hasAnyRole("ADMIN", "TEACHER")
                    .requestMatchers("/api/attendance/update").hasAnyRole("ADMIN", "TEACHER")
                    .requestMatchers("/api/attendance/delete").hasAnyRole("ADMIN", "TEACHER")
                    .requestMatchers("/api/attendance/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT")

                    // Discipline Controller
                    .requestMatchers("/api/discipline/save").hasRole("ADMIN")
                    .requestMatchers("/api/discipline/delete").hasRole("ADMIN")
                    .requestMatchers("/api/discipline/teacher/**").hasAnyRole("ADMIN", "TEACHER")
                    .requestMatchers("/api/discipline/class/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT")
                    .requestMatchers("/api/discipline/all").hasAnyRole("ADMIN", "TEACHER", "STUDENT")

                    // Evaluation Controller
                    .requestMatchers("/api/evaluation/save").hasAnyRole("ADMIN", "TEACHER")
                    .requestMatchers("/api/evaluation/update").hasAnyRole("ADMIN", "TEACHER")
                    .requestMatchers("/api/evaluation/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT")

                    // Grade Controller
                    .requestMatchers("/api/grade/save-one").hasAnyRole("ADMIN", "TEACHER")
                    .requestMatchers("/api/grade/save-many").hasAnyRole("ADMIN", "TEACHER")
                    .requestMatchers("/api/grade/update").hasAnyRole("ADMIN", "TEACHER")
                    .requestMatchers("/api/grade/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT")

                    // Lesson Controller
                    .requestMatchers("/api/lesson/save").hasAnyRole("ADMIN", "TEACHER")
                    .requestMatchers("/api/lesson/update").hasAnyRole("ADMIN", "TEACHER")
                    .requestMatchers("/api/lesson/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT")
    
                    // Schedule Controller
                    .requestMatchers("/api/schedule/save-one").hasRole("ADMIN")
                    .requestMatchers("/api/schedule/save-many").hasRole("ADMIN")
                    .requestMatchers("/api/schedule/save-many/discipline/{code}").hasRole("ADMIN")
                    .requestMatchers("/api/schedule/update").hasRole("ADMIN")
                    .requestMatchers("/api/schedule/delete").hasRole("ADMIN")
                    .requestMatchers("/api/schedule/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT")
    
                    // School Class Controller
                    .requestMatchers("/api/school-class/save").hasRole("ADMIN")
                    .requestMatchers("/api/school-class/update").hasRole("ADMIN")
                    .requestMatchers("/api/school-class/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT")

                    // Student Controller
                    .requestMatchers("/api/student/save").hasRole("ADMIN")
                    .requestMatchers("/api/student/update").hasAnyRole("ADMIN", "STUDENT")
                    .requestMatchers("/api/student/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER")

                    // Teacher Controller
                    .requestMatchers("/api/teacher/**").hasAnyRole("ADMIN", "TEACHER")
                        
                    .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authManager(AuthenticationConfiguration authConfiguration)
            throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

}