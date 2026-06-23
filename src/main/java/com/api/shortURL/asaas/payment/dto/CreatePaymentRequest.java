package com.api.shortURL.payment.dto;

import com.api.shortURL.payment.enums.BillingTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreatePaymentRequest(
        @NotBlank
        String customerId,

        @NotNull
        BigDecimal value,

        @NotNull
        BillingTypeEnum billingType,

        @NotNull
        LocalDate dueDate
)
{}
