package com.example.authservice.controller;

import com.example.authservice.dto.*;
import com.example.authservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        return ResponseEntity.ok(
                authService.register(request)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfile(
            Authentication authentication) {

        return ResponseEntity.ok(
                authService.getProfile(authentication.getName())
        );
    }

    @PutMapping("/profileupdate")
    public ResponseEntity<ProfileResponse> updateProfile(
            @RequestBody UpdateProfileRequest request,
            Authentication authentication
    ) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                authService.updateProfile(email, request)
        );
    }
}
