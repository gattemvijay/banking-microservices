package com.mybank.transaction.controller;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.mybank.transaction.model.Transaction;
import com.mybank.transaction.service.TransactionService;
import com.mybank.transaction.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;
    private final JwtUtil jwtUtil;

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(
            @RequestParam Long fromId,
            @RequestParam Long toId,
            @RequestParam BigDecimal amount,
            HttpServletRequest request) {

    	String userIdFromToken = jwtUtil.extractUserFromRequest(request);
        service.transfer(fromId, toId, amount,userIdFromToken);
        return ResponseEntity.ok("Transfer Successful");
    }
    
    @GetMapping("/history")
    public List<Transaction> getAllTransactions() {
        return service.getAllTransactions();
    }

    @GetMapping("/history/{accountId}")
    public List<Transaction> getByAccount(@PathVariable Long accountId) {
        return service.getTransactionsByAccount(accountId);
    }
}
