package com.BankingApplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="accounts")
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="account_holder_name")
	private String accountHolderName;
	
	@Column(name="balance")
	private double balance;
	@Column(name="contact")
	private int contact;
	@Column(name="pincode")
	private int pincode;
	
	@Column(name="address")
	private String address;
	@Column(name="adhaar")
	private int adhaar;
	@Column(name="accountNumber")
	private int accountNumber;
	public Account(Long id, String accountHolderName, double balance, int contact, int pincode, String address, int adhaar, int accountNumber) {
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
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	
	
}
