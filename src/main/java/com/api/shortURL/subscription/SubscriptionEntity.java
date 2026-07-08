package com.api.shortURL.subscription;

import com.api.shortURL.asaas.payment.PaymentEntity;
import com.api.shortURL.subscription.enums.SubscriptionStatus;
import com.api.shortURL.subscription.enums.PlanEnum;
import com.api.shortURL.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlanEnum plan;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private PaymentEntity payment;

    private LocalDateTime expiresAt;
}
