package com.BankingApplication.mapper;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.dto.GenerateDto;
import com.BankingApplication.entity.Account;
import com.BankingApplication.entity.Generate;

public class GenerateMapper {

    public static Generate mapToGenerate(GenerateDto generateDto) {
		Generate generate =new Generate(
				generateDto.getId(),
				generateDto.getAccountHolderName(),
				
				generateDto.getAdhaar(),
				generateDto.getAccountNumber()
				);
		return generate;
	}
	
	public static GenerateDto mapToGenerateDto(Generate generate)
	{
		GenerateDto generateDto=new GenerateDto(
            generate.getId(),
            generate.getAccountHolderName(),
				
            generate.getAdhaar(),
            generate.getAccountNumber()
				);
		return generateDto;
	}

}
