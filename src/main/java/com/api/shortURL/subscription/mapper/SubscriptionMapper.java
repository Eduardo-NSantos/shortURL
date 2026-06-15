package com.api.shortURL.subscription.mapper;

import com.api.shortURL.subscription.SubscriptionEntity;
import com.api.shortURL.subscription.dto.SubscriptionRequestDTO;
import com.api.shortURL.user.UserEntity;
import com.api.shortURL.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionMapper {
    private final UserService userService;

    public SubscriptionEntity toEntity(SubscriptionRequestDTO request){
        SubscriptionEntity subscription = new SubscriptionEntity();
        UserEntity user = userService.findEntity(request.userId());

        subscription.setUser(user);
        subscription.setPlan(request.plan());
        subscription.setGateway_subscription_id(request.gateway_subscription_id());

        return subscription;
    }
}
