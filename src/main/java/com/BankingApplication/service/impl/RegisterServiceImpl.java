package com.BankingApplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BankingApplication.dto.LoginDto;
import com.BankingApplication.dto.RegisterDto;
import com.BankingApplication.entity.Register;
import com.BankingApplication.mapper.RegisterMapper;
import com.BankingApplication.repository.RegisterRepository;
import com.BankingApplication.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService{
    @Autowired
    private RegisterRepository registerRepository;
    @Override
    public RegisterDto registeruser(RegisterDto registerDto) {
        Register register= RegisterMapper.mapToRegister(registerDto);
        registerRepository.save(register);
        return registerDto;
    }
    @Override
    public String loginuser(LoginDto loginDto) {
        Register register = registerRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if(register !=null)
        {
            return "success";
        }
        return "unsess";
        

    }

    

    
}
