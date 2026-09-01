package com.example.authservice.service;

import com.example.authservice.dto.LoginRequest;
import com.example.authservice.dto.LoginResponse;
import com.example.authservice.dto.RegisterRequest;
import com.example.authservice.entity.User;
import com.example.authservice.exception.EmailAlreadyExistException;
import com.example.authservice.exception.InvalidEmailPassException;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.security.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Value("${app.admin-email}")
    private String adminEmail;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistException();
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .number(request.getNumber())
                .password(
                        passwordEncoder.encode(request.getPassword())
                )
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new EmailAlreadyExistException());

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new InvalidEmailPassException();
        }

        String token = jwtService.generateToken(user.getEmail());

        boolean isAdmin =
                user.getEmail().equalsIgnoreCase(adminEmail);

        return new LoginResponse(
                token,
                user.getEmail(),
                isAdmin
        );
    }


}
