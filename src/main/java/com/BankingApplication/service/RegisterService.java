package com.BankingApplication.service;

import org.springframework.stereotype.Component;

import com.BankingApplication.dto.LoginDto;
import com.BankingApplication.dto.RegisterDto;

@Component
public interface RegisterService {
    RegisterDto registeruser(RegisterDto registerDto);
    String loginuser(LoginDto loginDto);
}
