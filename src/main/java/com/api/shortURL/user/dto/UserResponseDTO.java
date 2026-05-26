package com.api.shortURL.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record UserResponseDTO (
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
