package com.api.shortURL.asaas.customer.service;

import com.api.shortURL.asaas.client.AsaasClient;
import com.api.shortURL.asaas.customer.dto.CreateCustomerRequest;
import com.api.shortURL.asaas.customer.dto.CreateCustomerResponse;
import com.api.shortURL.user.UserEntity;
import com.api.shortURL.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final AsaasClient asaasClient;
    private final UserRepository userRepository;

    public String getOrCreateAsaasCustomer(UserEntity user){
        if (user.getAsaasCustomerId() != null){
            return user.getAsaasCustomerId();
        }

        CreateCustomerResponse response = asaasClient.CreateCustomer(
                new CreateCustomerRequest(
                        user.getName(),
                        user.getCpf()
                )
        );

        user.setAsaasCustomerId(response.id());
        userRepository.save(user);

        return response.id();
    }
}
