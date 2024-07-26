package com.sapiens.sapiens.domain.user;

public record LoginResponse(String name, String email, String token, UserRole role, boolean firstLogin) {
}
