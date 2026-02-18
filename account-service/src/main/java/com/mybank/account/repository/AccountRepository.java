package com.mybank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.account.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}