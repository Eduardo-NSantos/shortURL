package com.api.shortURL.asaas.payment.dto;

import com.api.shortURL.asaas.payment.enums.PaymentStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreatePaymentResponse(
        String id,
        BigDecimal value,
        PaymentStatusEnum status,
        LocalDate dueDate,
        PixQrCodeResponse qrCodeResponse
)
{}
