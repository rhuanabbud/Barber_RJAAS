package com.saas.barbearia.security;

public record AuthResponse(String token, String user, String role) {
}
