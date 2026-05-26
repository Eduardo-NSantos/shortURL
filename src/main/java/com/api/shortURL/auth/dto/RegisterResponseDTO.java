package com.api.shortURL.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record RegisterResponseDTO(
        @NotBlank
        Integer id,

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        LocalDateTime createdAt
)
{}
