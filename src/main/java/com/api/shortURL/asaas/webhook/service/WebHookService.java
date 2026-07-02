package com.api.shortURL.asaas.webhook.service;

import com.api.shortURL.asaas.payment.PaymentEntity;
import com.api.shortURL.asaas.payment.PaymentRepository;
import com.api.shortURL.asaas.payment.enums.PaymentStatusEnum;
import com.api.shortURL.asaas.webhook.dto.WebhookDTO;
import com.api.shortURL.subscription.SubscriptionEntity;
import com.api.shortURL.subscription.SubscriptionRepository;
import com.api.shortURL.subscription.enums.SubscriptionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WebHookService {
    private final PaymentRepository paymentRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Transactional
    public void process(WebhookDTO payload){
        PaymentEntity payment = paymentRepository.findByAsaasPaymentId(
                payload.getPayment().id()
        ).orElseThrow(() ->
//                Temporário
                new RuntimeException(
                        "Pagamento não encontrado: " + payload.getPayment().id()
                )
        );

        if (payload.getEvent().equals("PAYMENT_RECEIVED")){
            payment.setStatus(PaymentStatusEnum.CONFIRMED);
            SubscriptionEntity subscription = subscriptionRepository
                    .findByPayment(payment)
                    .orElseThrow(() ->
//                            Temporário
                            new RuntimeException(
                                    "Assinatura não encontrada para pagamento "
                                            + payment.getAsaasPaymentId()
                            )
                    );

            subscription.setStatus(SubscriptionStatus.ACTIVE);
        }
    }
}
