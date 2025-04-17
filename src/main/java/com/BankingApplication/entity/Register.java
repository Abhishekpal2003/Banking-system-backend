package com.BankingApplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="register_data")
public class Register {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    @Column
    private String email;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String gender;
    
    public Register() {
        super();
    }
    public Register(Long id, String email, String name, String password, String gender) {
        super();
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.gender = gender;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    
}
