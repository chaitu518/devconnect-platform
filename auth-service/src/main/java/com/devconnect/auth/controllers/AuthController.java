package com.devconnect.auth.controller;

import com.devconnect.auth.dtos.*;
import com.devconnect.auth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        String token = authService.register(request.getUsername(),request.getEmail(),request.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getEmail(),request.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }
    @GetMapping("/test")
    public ResponseEntity<String> testToken() {
        return ResponseEntity.ok("You are authenticated!");
    }

}
