package com.BankingApplication.controller;

import org.springframework.web.bind.annotation.RestController;

import com.BankingApplication.dto.AuthenticationRequest;
import com.BankingApplication.dto.AuthenticationResponse;
import com.BankingApplication.dto.LoginDto;
import com.BankingApplication.dto.RegisterDto;
import com.BankingApplication.dto.SignupRequest;
import com.BankingApplication.dto.UserDTO;
import com.BankingApplication.service.AuthService;
import com.BankingApplication.service.RegisterService;
import com.BankingApplication.service.jwt.UserDetailsServiceImpl;
import com.BankingApplication.utils.JwtUtil;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ObjectInputFilter.Status;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins="http://localhost:4200")
public class RegisterController {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthService authService;
    
    @PostMapping("/register1")
    public ResponseEntity<String> registerUserNew(@RequestBody RegisterDto registerDto) {
   
        RegisterDto registerDto1 =registerService.registeruser(registerDto);
        if(registerDto1 != null){
        return ResponseEntity.ok("Register Successfully");
        }
        else
        {
            return ResponseEntity.ok("Register Unsuccessfully");
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> getAllData(@RequestBody LoginDto loginDto){
        String mess = registerService.loginuser(loginDto);
        if(mess.equals("success")){
            return ResponseEntity.ok("Login Successfully");
            }else{
            
                return ResponseEntity.ok("Login Unsuccessfully");
            }
    }
    // For Login Purpose
    @PostMapping("/authentication")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response) throws BadCredentialsException, DisabledException,UsernameNotFoundException,IOException, java.io.IOException{
       
        
        try {
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())); 
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect Usernmae or password");
        }catch(DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"User is not created. Register User first.");
            return null;
        }
        final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        
        String role = userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(", "));
        final String jwt=jwtUtil.generateToken(userDetails.getUsername(),role);
        return new AuthenticationResponse(jwt);
    }    

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody SignupRequest signuprequest){
        UserDTO createdUser=authService.createUser(signuprequest);
        if(createdUser==null)
        {
            return new ResponseEntity<>("User is not created, try again later", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }

}
