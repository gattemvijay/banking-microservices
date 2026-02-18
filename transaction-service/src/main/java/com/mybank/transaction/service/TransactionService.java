package com.mybank.transaction.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybank.transaction.client.AccountClient;
import com.mybank.transaction.model.Transaction;
import com.mybank.transaction.repository.TransactionRepository;
import com.mybank.transaction.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

	private final TransactionRepository repository;
	private final AccountClient accountClient;

	@Transactional
	public void transfer(Long fromId, Long toId, BigDecimal amount, String userId) {

		if (userId == null) {
			throw new RuntimeException("Unauthorized");
		}

		Long loggedInUserId = Long.parseLong(userId);
		if (!loggedInUserId.equals(fromId)) {
			throw new RuntimeException("You cannot transfer from another user's account");
		}

		accountClient.debit(fromId, amount);
		accountClient.credit(toId, amount);

		Transaction txn = new Transaction();
		txn.setFromAccountId(fromId);
		txn.setToAccountId(toId);
		txn.setAmount(amount);
		txn.setTransactionType("TRANSFER");
		txn.setTimestamp(LocalDateTime.now());

		repository.save(txn);
	}

	public List<Transaction> getAllTransactions() {
		return repository.findAll();
	}

	public List<Transaction> getTransactionsByAccount(Long accountId) {
		return repository.findByFromAccountIdOrToAccountId(accountId, accountId);
	}

}
