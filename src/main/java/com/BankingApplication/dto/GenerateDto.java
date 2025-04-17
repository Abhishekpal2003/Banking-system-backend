package com.BankingApplication.dto;

public class GenerateDto {

    private Long id;
	private String accountHolderName;
    private int adhaar;
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
    
    public GenerateDto() {
        super();
    }
    public GenerateDto(Long id, String accountHolderName, int adhaar, int accountNumber) {
        super();
        this.id = id;
        this.accountHolderName = accountHolderName;
        this.adhaar = adhaar;
        this.accountNumber = accountNumber;
    }
    
}
