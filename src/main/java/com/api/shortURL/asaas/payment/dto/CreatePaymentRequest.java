package com.api.shortURL.asaas.payment.dto;

import com.api.shortURL.asaas.payment.enums.BillingTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreatePaymentRequest(
        @NotBlank
        String customer,

        @NotNull
        BigDecimal value,

        @NotNull
        BillingTypeEnum billingType,

        @NotNull
        LocalDate dueDate
)
{}
