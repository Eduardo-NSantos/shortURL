package com.api.shortURL.payment.dto;

public record PixQrCodeResponse(
        String encodedImage,
        String payload
)
{}
