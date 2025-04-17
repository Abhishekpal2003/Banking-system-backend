package com.BankingApplication.mapper;

import com.BankingApplication.dto.RegisterDto;
import com.BankingApplication.entity.Register;

public class RegisterMapper {

    public static Register mapToRegister(RegisterDto registerDto)
    {
        Register register = new Register(
            registerDto.getId(), 
            registerDto.getEmail(),
            registerDto.getName(),
            registerDto.getPassword(),
            registerDto.getGender()
        );
        return register;
    }

    public static RegisterDto mapToRegisterDto(Register register)
    {
        RegisterDto registerDto = new RegisterDto(
            register.getId(), 
            register.getEmail(),
            register.getName(),
            register.getPassword(),
            register.getGender()
        );
        return registerDto;
    }
}
