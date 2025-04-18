package com.BankingApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
	

  List<Account> findByAccountNumber(int accountNumber);

}
