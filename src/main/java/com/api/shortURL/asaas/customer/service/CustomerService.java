package com.api.shortURL.payment.customer.service;

import com.api.shortURL.payment.client.AsaasClient;
import com.api.shortURL.payment.customer.dto.CreateCustomerRequest;
import com.api.shortURL.payment.customer.dto.CreateCustomerResponse;
import com.api.shortURL.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final AsaasClient asaasClient;

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

        return response.id();
    }
}
