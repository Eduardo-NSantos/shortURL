package com.api.shortURL.asaas.webhook.dto;

import lombok.Data;

@Data
public class WebhookDTO {
    String event;
    PaymentDTO payment;
}

