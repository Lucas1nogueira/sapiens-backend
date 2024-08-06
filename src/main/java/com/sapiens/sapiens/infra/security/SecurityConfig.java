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
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/change-password").permitAll()
                        
                        .requestMatchers("/api/student/save").hasRole("ADMIN")
                        .requestMatchers("/api/student/**").hasAnyRole("ADMIN", "STUDENT")

                        .requestMatchers("/api/discipline/save").hasRole("ADMIN")
                        .requestMatchers("/api/discipline/delete").hasRole("ADMIN")
                        .requestMatchers("/api/discipline/all").hasAnyRole("ADMIN", "TEACHER", "STUDENT")

                        .requestMatchers("/api/groupCollege/teacher/**").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers("/api/group/all").hasAnyRole("ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/api/groupCollege/**").hasRole("ADMIN")
                        
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