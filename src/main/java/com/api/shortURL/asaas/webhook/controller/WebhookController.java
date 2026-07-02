package com.api.shortURL.asaas.webhook.controller;

import com.api.shortURL.asaas.webhook.dto.WebhookDTO;
import com.api.shortURL.asaas.webhook.service.WebHookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhooks")
@RequiredArgsConstructor
public class WebhookController {
    private final WebHookService webHookService;

    @PostMapping("/asaas")
    public ResponseEntity<Void> receiveWebhook(@RequestBody WebhookDTO payload){
        webHookService.process(payload);

        return ResponseEntity.ok().build();
    };
}
