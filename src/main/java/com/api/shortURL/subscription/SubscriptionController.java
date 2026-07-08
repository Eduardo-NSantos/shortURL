package com.api.shortURL.subscription;

import com.api.shortURL.asaas.payment.PaymentEntity;
import com.api.shortURL.secutiry.CustomUserDetails;
import com.api.shortURL.subscription.dto.SubscriptionRequestDTO;
import com.api.shortURL.subscription.enums.PlanEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<PaymentEntity> createSubscription(
            @RequestBody PlanEnum plan,
            @AuthenticationPrincipal CustomUserDetails user
    ){
        PaymentEntity payment = subscriptionService.create(
                new SubscriptionRequestDTO(
                        user.getId(),
                        plan
                )
        );

        return ResponseEntity.ok(payment);
    }
}
