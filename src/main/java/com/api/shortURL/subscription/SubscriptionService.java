package com.api.shortURL.subscription;

import com.api.shortURL.asaas.payment.PaymentEntity;
import com.api.shortURL.asaas.payment.service.PaymentService;
import com.api.shortURL.subscription.dto.SubscriptionRequestDTO;
import com.api.shortURL.subscription.enums.PlanEnum;
import com.api.shortURL.subscription.enums.SubscriptionStatus;
import com.api.shortURL.subscription.mapper.SubscriptionMapper;
import com.api.shortURL.user.UserEntity;
import com.api.shortURL.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Validated
public class SubscriptionService {
    private final SubscriptionRepository repository;
    private final UserService userService;
    private final PaymentService paymentService;
    private final SubscriptionMapper mapper;

    public PaymentEntity create(@Valid SubscriptionRequestDTO request){
        if (repository.existsByUserId(request.userId())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "User already has an active subscription"
            );
        }

        UserEntity user = userService.findEntity(request.userId());
        PaymentEntity payment = paymentService.createPixPayment(
                user,
                request.plan().getPrice()
        );

        SubscriptionEntity subscription = mapper.toEntity(request, payment);

        subscription.setStatus(SubscriptionStatus.PENDING);
        subscription.setExpiresAt(LocalDateTime.now().plusMonths(1));

        repository.save(subscription);

        return payment;
    }

    public PlanEnum getPlan(Integer userId){
        return repository.findByUserId(userId)
                .filter(subscription -> subscription.getStatus() == SubscriptionStatus.ACTIVE)
                .map(SubscriptionEntity::getPlan)
                .orElse(PlanEnum.FREE);
    }
}
