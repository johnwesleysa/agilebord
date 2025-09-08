package com.alencar.agileboard.controller;

import com.alencar.agileboard.dto.AuthResponseDTO;
import com.alencar.agileboard.dto.LoginDTO;
import com.alencar.agileboard.dto.RegisterDTO;
import com.alencar.agileboard.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO){
        authService.register(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthResponseDTO response = authService.login(loginDTO);
        return ResponseEntity.ok(response);
    }
}
