package com.api.shortURL.subscription;

import com.api.shortURL.subscription.dto.SubscriptionRequestDTO;
import com.api.shortURL.subscription.enums.SubscriptionStatus;
import com.api.shortURL.subscription.mapper.SubscriptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository repository;
    private final SubscriptionMapper mapper;

    public void create(SubscriptionRequestDTO request){
        if (repository.existsByUserId(request.userId())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "User already has an active subscription"
            );
        }

        SubscriptionEntity subscription = mapper.toEntity(request);

        subscription.setStatus(SubscriptionStatus.PENDING);
        subscription.setExpires_at(LocalDateTime.now().plusMonths(1));

        repository.save(subscription);
    }
}
