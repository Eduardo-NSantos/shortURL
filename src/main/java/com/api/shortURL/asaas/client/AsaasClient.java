package com.api.shortURL.asaas.client;

import com.api.shortURL.asaas.AsaasProperties;
import com.api.shortURL.asaas.customer.dto.CreateCustomerRequest;
import com.api.shortURL.asaas.customer.dto.CreateCustomerResponse;
import com.api.shortURL.asaas.payment.dto.CreatePaymentRequest;
import com.api.shortURL.asaas.payment.dto.CreatePaymentResponse;
import com.api.shortURL.asaas.payment.dto.PixQrCodeResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestClient;

@Component
@Validated
public class AsaasClient {
    private final RestClient restClient;

    public AsaasClient(AsaasProperties properties){
        this.restClient = RestClient.builder()
                .baseUrl(properties.apiUrl())
                .defaultHeader("access_token", properties.apiKey())
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("User-Agent", "shortUrl/1.0")
                .build();
    }

    public CreateCustomerResponse CreateCustomer(@Valid CreateCustomerRequest request){
        return restClient.post()
                .uri("/customers")
                .body(request)
                .retrieve()
                .body(CreateCustomerResponse.class);
    }

    public CreatePaymentResponse createPayment(@Valid CreatePaymentRequest request){
        return restClient.post()
                .uri("/payments")
                .body(request)
                .retrieve()
                .body(CreatePaymentResponse.class);
    }

    public PixQrCodeResponse getPixQrCode(String paymentId){
        return restClient.get()
                .uri("/payments/{id}/pixQrCode", paymentId)
                .retrieve()
                .body(PixQrCodeResponse.class);
    }
}
