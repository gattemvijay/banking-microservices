package com.mybank.account.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.account.model.Account;
import com.mybank.account.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
	
	private final AccountService service;
	
	@PostMapping
	public Account createAccount(@RequestParam Long userId) {
	    return service.createAccount(userId);
	}
	
    @PostMapping("/{id}/debit")
    public void debit(@PathVariable Long id,
                      @RequestParam BigDecimal amount) {
        service.debit(id, amount);
    }

    @PostMapping("/{id}/credit")
    public void credit(@PathVariable Long id,
                       @RequestParam BigDecimal amount) {
        service.credit(id, amount);
    }


}

