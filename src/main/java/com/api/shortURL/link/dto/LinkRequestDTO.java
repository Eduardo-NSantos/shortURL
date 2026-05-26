package com.api.shortURL.link.dto;

import jakarta.validation.constraints.NotBlank;

public record LinkRequestDTO(
        @NotBlank
        String originalURL
)
{}
