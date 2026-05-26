package com.api.shortURL.link.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LinkResponseDTO (
    @NotNull
    Integer id,

    @NotBlank
    String originalURL,

    @NotBlank
    String shortCode,

    @NotNull
    Integer userId
)
{}