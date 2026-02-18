package com.mybank.account.service;

import org.springframework.stereotype.Service;

import com.mybank.account.model.Account;
import com.mybank.account.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AccountService {
	

	private final AccountRepository repository;
	
	public Account createAccount(Long userId) {
		 Account account = new Account();
		    account.setUserId(userId);
		    account.setAccountNumber(String.valueOf(new Random().nextLong(1000000000L)));
		    account.setBalance(BigDecimal.ZERO);
		    return repository.save(account);
	}
	
	public void debit(Long id, BigDecimal amount) {

        Account account = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance().subtract(amount));
        repository.save(account);
    }

    public void credit(Long id, BigDecimal amount) {

        Account account = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        account.setBalance(account.getBalance().add(amount));
        repository.save(account);
    } 

}

