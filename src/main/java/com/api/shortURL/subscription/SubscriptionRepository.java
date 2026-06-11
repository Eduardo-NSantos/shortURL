package com.api.shortURL.subscription;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Integer> {
    boolean existsByUserId(Integer userId);
}
