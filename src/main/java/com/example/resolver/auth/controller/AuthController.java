package com.example.resolver.auth.controller;

import com.example.resolver.auth.dto.AuthRequestDto;
import com.example.resolver.auth.dto.AuthResponseDto;
import com.example.resolver.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public void signup(
            @RequestBody AuthRequestDto request
    ) {
        authService.signup(request);
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<AuthResponseDto> signin(
            @RequestBody AuthRequestDto request
    ) {
        return ResponseEntity.ok(authService.signin(request));
    }
}
