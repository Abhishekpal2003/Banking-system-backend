package com.BankingApplication.service;

import java.util.List;

import com.BankingApplication.dto.AccountDto;

public interface AccountService {

	AccountDto createAccount(AccountDto account);
	AccountDto getAccountById(Long id);
	List<AccountDto> getAccountByAccountNumber(int accountNumber);
	AccountDto deposit(Long id,double amount);
	AccountDto withdraw(Long id,double amount);
	List<AccountDto> getAllAcounts();
	void deleteAccount(Long id);
	byte[] generateReceipt(Long id, double amount,int type);
}
