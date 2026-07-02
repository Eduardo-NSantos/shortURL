package com.api.shortURL.asaas.payment.service;

import com.api.shortURL.asaas.payment.PaymentEntity;
import com.api.shortURL.asaas.payment.PaymentRepository;
import com.api.shortURL.asaas.client.AsaasClient;
import com.api.shortURL.asaas.customer.service.CustomerService;
import com.api.shortURL.asaas.payment.dto.CreatePaymentRequest;
import com.api.shortURL.asaas.payment.dto.CreatePaymentResponse;
import com.api.shortURL.asaas.payment.dto.PixQrCodeResponse;
import com.api.shortURL.asaas.payment.enums.BillingTypeEnum;
import com.api.shortURL.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final AsaasClient asaasClient;
    private final CustomerService customerService;
    private final PaymentRepository paymentRepository;

    public PaymentEntity createPixPayment(UserEntity user, BigDecimal value){
        String customerId = customerService.getOrCreateAsaasCustomer(user);

        CreatePaymentResponse response = asaasClient.createPayment(
                new CreatePaymentRequest(
                        customerId,
                        value,
                        BillingTypeEnum.PIX,
                        LocalDate.now().plusDays(7)
                )
        );

//        Tempoorário (espera o asaas criar a cobrança antes de pedir o qrCode)
        for (int i = 0; i < 5; i++) {
            try {
                PixQrCodeResponse qrCodeResponse =
                        asaasClient.getPixQrCode(response.id());

                if (qrCodeResponse != null && qrCodeResponse.payload() != null) {
                    break;
                }

                Thread.sleep(1000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        PixQrCodeResponse qrCodeResponse = asaasClient.getPixQrCode(response.id());

        PaymentEntity payment = new PaymentEntity();
        payment.setAsaasPaymentId(response.id());
        payment.setValue(response.value());
        payment.setStatus(response.status());
        payment.setDueDate(response.dueDate());
        payment.setBillingType("PIX");
        payment.setPixPayload(qrCodeResponse.payload());

        return paymentRepository.save(payment);
    }
}
