package com.api.shortURL.plans.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public enum PlanType {
    FREE(
            0,
            "FREE",
            BigDecimal.ZERO,
            1
    ),

    SIMPLE(
            1,
            "SIMPLE",
            new BigDecimal("00.05"),
            5
    ),

    PRO(
            2,
            "PRO",
            new BigDecimal("01.00"),
            Integer.MAX_VALUE
    );

    private final Integer id;
    private final String name;
    private final BigDecimal price;
    private final Integer maxLinks;
}
