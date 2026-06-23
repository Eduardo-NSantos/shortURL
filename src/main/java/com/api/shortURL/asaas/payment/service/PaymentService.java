package com.api.shortURL.payment.service;

import com.api.shortURL.payment.PaymentEntity;
import com.api.shortURL.payment.PaymentRepository;
import com.api.shortURL.payment.client.AsaasClient;
import com.api.shortURL.payment.customer.service.CustomerService;
import com.api.shortURL.payment.dto.CreatePaymentRequest;
import com.api.shortURL.payment.dto.CreatePaymentResponse;
import com.api.shortURL.payment.dto.PixQrCodeResponse;
import com.api.shortURL.payment.enums.BillingTypeEnum;
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

        PixQrCodeResponse qrCodeResponse = asaasClient.getPixQrCode(response.id());

        PaymentEntity payment = new PaymentEntity();
        payment.setAsaasPaymentId(response.id());
        payment.setValue(response.value());
        payment.setStatus(response.status());
        payment.setDueDate(response.dueDate());
        payment.setBillingType("PIX");
        payment.setPixEncodedImage(qrCodeResponse.encodedImage());
        payment.setPixPayload(qrCodeResponse.payload());

        return paymentRepository.save(payment);
    }
}
