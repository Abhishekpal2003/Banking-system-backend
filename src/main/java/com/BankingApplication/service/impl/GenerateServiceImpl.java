package com.BankingApplication.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.dto.GenerateDto;
import com.BankingApplication.entity.Generate;
import com.BankingApplication.mapper.AccountMapper;
import com.BankingApplication.mapper.GenerateMapper;

import com.BankingApplication.repository.GenerateRepository;
import com.BankingApplication.service.GenerateService;

@Service
public class GenerateServiceImpl implements GenerateService {

    
    private GenerateRepository generateRepository;

    public GenerateServiceImpl(GenerateRepository generateRepository) {
		super();
		this.generateRepository = generateRepository;
	}

    @Override
    public GenerateDto generateAccount(GenerateDto generateDto) {
        Generate generate=GenerateMapper.mapToGenerate(generateDto);
		Generate savedAccount=generateRepository.save(generate);
		
		return GenerateMapper.mapToGenerateDto(savedAccount);
    }

    @Override
    public GenerateDto findByAccountNumberAndAdhaar(int accountNumber, int adhaar) {
    
        Optional<Generate> generateOptional = generateRepository.findByAccountNumberAndAdhaar(accountNumber, adhaar);
    if (generateOptional.isPresent()) {
        return GenerateMapper.mapToGenerateDto(generateOptional.get());
    } else {
        return null;  // Returning null if no result is found
    }
    }

    @Override
    public List<GenerateDto> getAllAcounts() {
        return generateRepository.findAll().stream().map((account)->GenerateMapper.mapToGenerateDto(account)).collect(Collectors.toList());
    }

    // public List<AccountDto> getAccountByAccountNumber(int accountNumber) {
	// 	// Account account=accountRepository.findByAccountNumber(accountNumber);
    //     return  accountRepository.findByAccountNumber(accountNumber).stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
    // }

    @Override
    public List<GenerateDto> getAccountByAccountNumberAdmin(int accountNumber) {
       return generateRepository.findByAccountNumber(accountNumber).stream().map((account)->GenerateMapper.mapToGenerateDto(account)).collect(Collectors.toList());
    }

    

    

}
