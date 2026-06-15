package com.api.shortURL.subscription.dto;

import com.api.shortURL.subscription.enums.PlanEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubscriptionRequestDTO(
        @NotNull
        Integer userId,

        @NotNull
        PlanEnum plan,

        @NotBlank
        String gateway_subscription_id
)
{}
