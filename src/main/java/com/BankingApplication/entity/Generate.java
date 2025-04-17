package com.BankingApplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="GenerateAcc")
public class Generate {
   @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="account_holder_name")
	private String accountHolderName;
	@Column(name="adhaar")
    private int adhaar;
    @Column(name="accountNumber")
    private int accountNumber;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAccountHolderName() {
        return accountHolderName;
    }
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
    public int getAdhaar() {
        return adhaar;
    }
    public void setAdhaar(int adhaar) {
        this.adhaar = adhaar;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public Generate() {
        super();
    }
    public Generate(Long id, String accountHolderName, int adhaar, int accountNumber) {
        super();
        this.id = id;
        this.accountHolderName = accountHolderName;
        this.adhaar = adhaar;
        this.accountNumber = accountNumber;
    }
    
    
}
