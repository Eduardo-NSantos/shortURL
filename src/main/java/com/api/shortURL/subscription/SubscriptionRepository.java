package com.api.shortURL.subscription;

import com.api.shortURL.asaas.payment.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Integer> {
    boolean existsByUserId(Integer userId);
    Optional<SubscriptionEntity> findByPayment(PaymentEntity payment);
}
