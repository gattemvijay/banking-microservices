package com.mybank.transaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.transaction.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	List<Transaction> findByFromAccountIdOrToAccountId(Long from, Long to);

    List<Transaction> findByFromAccountId(Long fromAccountId);

    List<Transaction> findByToAccountId(Long toAccountId);
    
}
