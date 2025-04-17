package com.BankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.BankingApplication.entity.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register,Long>{
    Register findByEmailAndPassword(String email,String password);
}
