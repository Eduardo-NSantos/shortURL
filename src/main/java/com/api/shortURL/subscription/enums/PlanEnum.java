package com.api.shortURL.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public enum PlanEnum {
    FREE(
            "FREE",
            BigDecimal.ZERO,
            3
    ),

    PRO(
            "PRO",
            new BigDecimal("05.00"),
            null
    );

    private final String name;
    private final BigDecimal price;
    private final Integer maxLinks;
}
