package com.api.shortURL.payment.customer.dto;

public record CreateCustomerResponse(
        String id,
        String name,
        String cpfCnpj
)
{}
