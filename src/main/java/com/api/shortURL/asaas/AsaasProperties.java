package com.api.shortURL.payment;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "payment")
public record AsaasProperties(
        String apiKey,
        String apiUrl
)
{}
