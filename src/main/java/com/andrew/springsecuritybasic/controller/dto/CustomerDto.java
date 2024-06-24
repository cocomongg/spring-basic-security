package com.andrew.springsecuritybasic.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CustomerDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class RegisterRequest {
        private String name;
        private String email;
        private String mobileNumber;
        private String pwd;
        private String role;
    }
}
