package com.BankingApplication.dto;

public class AccountDto {

	private Long id;
	private String accountHolderName;
	private double balance;
	private int contact;
	private int pincode;
	private String address;
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
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public int getContact() {
		return contact;
	}
	public void setContact(int contact) {
		this.contact = contact;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public AccountDto(Long id, String accountHolderName, double balance, int contact, int pincode, 
	String address, int adhaar, int accountNumber) {
		super();
		this.id = id;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
		this.contact=contact;
		this.pincode=pincode;
		this.address=address;
		this.adhaar=adhaar;
		this.accountNumber=accountNumber;
	}
	public AccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
