package com.andrew.springsecuritybasic.controller;

import com.andrew.springsecuritybasic.controller.dto.CustomerDto;
import com.andrew.springsecuritybasic.mdoel.Customer;
import com.andrew.springsecuritybasic.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LoginController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerDto.RegisterRequest request) {
        customerService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("register customer success");
    }

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        List<Customer> customers = customerService.getCustomersByEmail(authentication.getName());
        if (customers.size() > 0) {
            return customers.get(0);
        } else {
            return null;
        }
    }

}
