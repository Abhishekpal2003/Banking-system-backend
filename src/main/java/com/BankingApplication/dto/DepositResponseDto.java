package com.BankingApplication.dto;

public class DepositResponseDto {
    private byte[] pdfBytes;
    private AccountDto entity;
    private String message;

    

    public DepositResponseDto(byte[] pdfBytes, AccountDto entity, String message) {
        this.pdfBytes = pdfBytes;
        this.entity = entity;
        this.message = message;
    }
    public byte[] getPdfBytes() {
        return pdfBytes;
    }
    public void setPdfBytes(byte[] pdfBytes) {
        this.pdfBytes = pdfBytes;
    }
    public AccountDto getEntity() {
        return entity;
    }
    public void setEntity(AccountDto entity) {
        this.entity = entity;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
