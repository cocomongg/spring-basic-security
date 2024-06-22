package com.andrew.springsecuritybasic.controller;

import com.andrew.springsecuritybasic.controller.dto.CustomerDto;
import com.andrew.springsecuritybasic.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
