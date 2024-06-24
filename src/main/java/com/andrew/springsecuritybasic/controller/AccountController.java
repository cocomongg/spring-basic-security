package com.andrew.springsecuritybasic.controller;

import com.andrew.springsecuritybasic.mdoel.Accounts;
import com.andrew.springsecuritybasic.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountsRepository accountsRepository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam int id) {
        Accounts accounts = accountsRepository.findByCustomerId(id);
        if(accounts != null) {
            return accounts;
        } else {
            return null;
        }
    }
}
