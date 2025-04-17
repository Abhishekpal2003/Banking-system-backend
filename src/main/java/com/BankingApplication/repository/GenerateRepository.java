package com.BankingApplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.BankingApplication.entity.Generate;

@Repository
public interface GenerateRepository extends JpaRepository<Generate,Long>{
    Optional<Generate> findByAccountNumberAndAdhaar(int accountNumber,int adhaar);
    List<Generate> findByAccountNumber(int accountNumber);
}
