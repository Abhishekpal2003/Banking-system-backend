package com.BankingApplication.service;

import java.util.List;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.dto.GenerateDto;

public interface GenerateService {

   GenerateDto generateAccount(GenerateDto generateDto);
   GenerateDto findByAccountNumberAndAdhaar(int accountNumber,int adhaar);
   List<GenerateDto> getAllAcounts();
   List<GenerateDto> getAccountByAccountNumberAdmin(int accountNumber);
    
}
