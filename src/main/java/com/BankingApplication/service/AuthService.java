package com.BankingApplication.service;

import com.BankingApplication.dto.SignupRequest;
import com.BankingApplication.dto.UserDTO;

public interface AuthService {

    UserDTO createUser(SignupRequest signuprequest);

    
}
