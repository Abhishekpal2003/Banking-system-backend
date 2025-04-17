package com.BankingApplication.service.impl;


import java.util.List;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.dto.GenerateDto;
import com.BankingApplication.entity.Account;
import com.BankingApplication.mapper.AccountMapper;
import com.BankingApplication.repository.AccountRepository;
import com.BankingApplication.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountRepository accountRepository;
	private GenerateServiceImpl genaretServiceImpl;
	
	
	public AccountServiceImpl(AccountRepository accountRepository, GenerateServiceImpl genaretServiceImpl) {
		super();
		this.accountRepository = accountRepository;
		this.genaretServiceImpl = genaretServiceImpl;
	}



	public AccountDto createAccount(AccountDto accountDto) {
		
		GenerateDto generateDto = genaretServiceImpl.findByAccountNumberAndAdhaar(accountDto.getAccountNumber(), accountDto.getAdhaar());
		
		if(generateDto !=null){
		Account account=AccountMapper.mapToAccount(accountDto);
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}
		return null;
	}



	
	public AccountDto getAccountById(Long id) {
		Account account=accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
		return AccountMapper.mapToAccountDto(account);
	}

	
	public AccountDto deposit(Long id, double amount) {
		Account account=accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
		double totalBalance=account.getBalance()+amount;
		account.setBalance(totalBalance);
		Account savedAccount =accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account=accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
		if(account.getBalance()<amount)
		{
			throw new RuntimeException("Insufficient Amount");
		}
		double totalBalance=account.getBalance()-amount;
		account.setBalance(totalBalance);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public List<AccountDto> getAllAcounts() {
		return accountRepository.findAll().stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
	}


	public List<AccountDto> getAccountByAccountNumber(int accountNumber) {
		// Account account=accountRepository.findByAccountNumber(accountNumber);
        return  accountRepository.findByAccountNumber(accountNumber).stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
    }



	@Override
	public void deleteAccount(Long id) {

		Account account=accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
		accountRepository.delete(account);
	}

	public byte[] generateReceipt( Long id,double amount,int type) {
        try {
			String htmlTemplate;
            // Load the HTML template
			if(type == 1)
			{
				 htmlTemplate = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/depositreceipt.html")));
			}
			else{
				 htmlTemplate = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/withdrawreceipt.html")));
			}
            
			Account account=accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
            // Replace placeholders with actual data
            String htmlContent = htmlTemplate
			.replace("{{Date}}",  java.time.LocalDate.now().toString())
			.replace("{{Name}}", account.getAccountHolderName())
			.replace("{{TransactionAmount}}", String.valueOf(amount))
			.replace("{{AccountNumber}}", String.valueOf(account.getAccountNumber()));

            // Convert HTML to PDF
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocumentFromString(htmlContent);
                renderer.layout();
                renderer.createPDF(outputStream);
                return outputStream.toByteArray();
            }

        } catch (Exception e) {
            throw new RuntimeException("Error while generating PDF", e);
        }
    }

}
