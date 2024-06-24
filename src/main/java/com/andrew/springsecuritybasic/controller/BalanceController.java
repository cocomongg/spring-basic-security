package com.andrew.springsecuritybasic.controller;

import com.andrew.springsecuritybasic.mdoel.AccountTransactions;
import com.andrew.springsecuritybasic.repository.AccountTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BalanceController {

    private final AccountTransactionRepository accountTransactionsRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam int id) {
        List<AccountTransactions> accountTransactions = accountTransactionsRepository.
                findByCustomerIdOrderByTransactionDtDesc(id);
        if (accountTransactions != null ) {
            return accountTransactions;
        }else {
            return null;
        }
    }
}
