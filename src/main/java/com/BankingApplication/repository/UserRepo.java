package com.BankingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BankingApplication.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{

    User findFirstByEmail(String email);

}
