package com.api.shortURL.subscription;

import com.api.shortURL.subscription.enums.SubscriptionStatus;
import com.api.shortURL.subscription.enums.PlanEnum;
import com.api.shortURL.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
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

    @Column(nullable = false)
    private String gateway_subscription_id;

    private LocalDateTime expires_at;
}
