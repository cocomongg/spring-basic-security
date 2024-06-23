package com.andrew.springsecuritybasic.service;

import com.andrew.springsecuritybasic.controller.dto.CustomerDto;
import com.andrew.springsecuritybasic.mdoel.Customer;
import com.andrew.springsecuritybasic.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Customer register(CustomerDto.RegisterRequest request) {
        String encodedPasswd = passwordEncoder.encode(request.getPwd());

        Customer customer = Customer.builder()
                .email(request.getEmail())
                .pwd(encodedPasswd)
                .role(request.getRole())
                .build();

        return customerRepository.save(customer);
    }
}
