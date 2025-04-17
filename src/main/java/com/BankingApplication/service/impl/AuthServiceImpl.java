package com.BankingApplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.BankingApplication.service.AccountService;
import com.BankingApplication.dto.SignupRequest;
import com.BankingApplication.dto.UserDTO;
import com.BankingApplication.entity.User;
import com.BankingApplication.repository.UserRepo;
import com.BankingApplication.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService  {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDTO createUser(SignupRequest signuprequest) {
        User user=new User();
        user.setEmail(signuprequest.getEmail());
        user.setName(signuprequest.getName());
        user.setGender(signuprequest.getGender());
        user.setPassword(new BCryptPasswordEncoder().encode(signuprequest.getPassword()));
        user.setRole("USER");
        User createdUser=userRepo.save(user);
        UserDTO userDTO=new UserDTO();
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setName(createdUser.getName());
        userDTO.setGender(createdUser.getGender());
        userDTO.setRole(createdUser.getRole());
        return userDTO;


    }
    

}
