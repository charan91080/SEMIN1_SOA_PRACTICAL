package com.example.jwt.controller;

import com.example.jwt.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jwt")
public class JwtController {
    private final JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest request) {
        if (request == null || request.username() == null || request.username().isBlank()
                || request.role() == null || request.role().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("username and role are required"));
        }

        String token = jwtService.generateToken(request.username().trim(), request.role().trim());
        return ResponseEntity.ok(new JwtResponse("JWT generated successfully", token, "Bearer"));
    }

    public record JwtRequest(String username, String role) {}
    public record JwtResponse(String message, String token, String tokenType) {}
    public record ErrorResponse(String error) {}
}
