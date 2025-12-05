package com.saas.barbearia.security;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        // Stub de autenticação: gera um token simples. Integre com Keycloak em produção.
        String token = Base64.getEncoder()
                .encodeToString((request.username() + ":" + request.password()).getBytes(StandardCharsets.UTF_8));
        return new AuthResponse("demo-" + token, request.username(), "ADMIN");
    }
}
