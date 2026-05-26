package com.api.shortURL.auth;

import com.api.shortURL.auth.dto.LoginRequestDTO;
import com.api.shortURL.auth.dto.LoginResponseDTO;
import com.api.shortURL.auth.dto.RegisterRequestDTO;
import com.api.shortURL.auth.dto.RegisterResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody
            @Valid
            LoginRequestDTO request
    ){
        LoginResponseDTO login = service.login(request);
        return ResponseEntity.ok(login);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(
            @RequestBody
            @Valid
            RegisterRequestDTO request
    ){
        RegisterResponseDTO register = service.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(register);
    }
}
