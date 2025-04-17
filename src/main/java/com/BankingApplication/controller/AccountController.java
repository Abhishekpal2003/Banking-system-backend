package com.BankingApplication.controller;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.dto.CheckDto;
import com.BankingApplication.dto.DepositResponseDto;
import com.BankingApplication.dto.GenerateDto;
import com.BankingApplication.entity.Account;
import com.BankingApplication.service.AccountService;
import com.BankingApplication.service.GenerateService;
import com.BankingApplication.service.impl.GenerateServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins="http://localhost:4200")
public class AccountController {
	
	private AccountService accountService;
	private GenerateServiceImpl genaretServiceImpl;
	private GenerateService generateService;
	
	public AccountController(AccountService accountService, GenerateServiceImpl genaretServiceImpl, GenerateService generateService) {
		super();
		this.accountService = accountService;
		this.genaretServiceImpl = genaretServiceImpl;
		this.generateService = generateService;
	}

	//add account rest api
	@PostMapping
	@PreAuthorize("hasRole('USER')") 
	public ResponseEntity<?> addAccount(@RequestBody AccountDto accountDto){
		AccountDto accountDto1 = accountService.createAccount(accountDto);
		if(accountDto1 != null){
			return new ResponseEntity<>(accountDto1,HttpStatus.CREATED);
		}
		return new ResponseEntity<>(accountDto,HttpStatus.NOT_FOUND);
		
	}
	
	//get single account detail
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER')") 
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id)
	{
		AccountDto accountDto=accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}

	
	
	
	
	@PutMapping("/{id}/deposit")
	@PreAuthorize("hasRole('USER')") 
	public ResponseEntity<byte[]> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) throws JsonProcessingException {
		Double amount = request.get("amount");
		AccountDto accountDto = accountService.deposit(id, amount);
		
		// Generate the PDF bytes
		byte[] pdfBytes = accountService.generateReceipt(id, amount,1);
		
		// Convert AccountDto to JSON for sending in header
		ObjectMapper objectMapper = new ObjectMapper();
		String accountJson = objectMapper.writeValueAsString(accountDto);
		
		// Prepare the DepositResponseDto or response headers
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=receipt.pdf");
		headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
		headers.add("entity", accountJson);
		headers.add("message", "Successfully done");

		// Return the response with the PDF file and headers
		return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	}

	@PutMapping("/{id}/withdraw")
	@PreAuthorize("hasRole('USER')") 
	public ResponseEntity<byte[]> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) throws JsonProcessingException {
		Double amount = request.get("amount");
		AccountDto accountDto = accountService.withdraw(id, amount);
		
		// Generate the PDF bytes
		byte[] pdfBytes = accountService.generateReceipt(id, amount,2);
		
		// Convert AccountDto to JSON for sending in header
		ObjectMapper objectMapper = new ObjectMapper();
		String accountJson = objectMapper.writeValueAsString(accountDto);
		
		// Prepare the DepositResponseDto or response headers
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=receipt.pdf");
		headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
		headers.add("entity", accountJson);
		headers.add("message", "Successfully done");

		// Return the response with the PDF file and headers
		return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	}


	
	

	
	@GetMapping
	@PreAuthorize("hasRole('USER')") 
	public ResponseEntity<List<AccountDto>>getAllAccounts(){
		List<AccountDto> accountDto=accountService.getAllAcounts();
		return ResponseEntity.ok(accountDto);
	}

	@GetMapping("/getAdminList")
	@PreAuthorize("hasRole('ADMIN')") 
	public ResponseEntity<List<GenerateDto>>getAllAdminAccounts(){
		List<GenerateDto> generateDto=generateService.getAllAcounts();
		return ResponseEntity.ok(generateDto);
	}

	@GetMapping("/{accountNumber}/search")
	@PreAuthorize("hasRole('USER')") 
	public ResponseEntity<List<AccountDto>> getAccountByAccountNumber(@PathVariable int accountNumber) {
		List<AccountDto> accountDto= accountService.getAccountByAccountNumber(accountNumber);
		if (accountDto != null) {
			return ResponseEntity.ok(accountDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('USER')") 
	public ResponseEntity<Map<String,Boolean>> deleteAccount(@PathVariable Long id)
	{
		accountService.deleteAccount(id);
		Map<String,Boolean>response=new HashMap<String,Boolean>();
		response.put("delete",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/export")
	@PreAuthorize("hasRole('USER')") 
	public void exportToExcel(@RequestBody List<Map<String, Object>> data, HttpServletResponse response) throws IOException {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Data");

		// Create a CellStyle with a bold font
		CellStyle headerCellStyle = workbook.createCellStyle();
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);  // Set the font to bold
		headerCellStyle.setFont(headerFont); 
	

		String[] headers = { "ID", "account_holder_name","balance(RS)","contact","address ","adhaar","pincode","account_number"};
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(headerCellStyle); 
		}


		int rowIndex = 1;
		for (Map<String, Object> rowData : data) {
		Row row = sheet.createRow(rowIndex++);
		int cellIndex = 0;
		for (Map.Entry<String, Object> entry : rowData.entrySet()) {
			Cell cell = row.createCell(cellIndex++);
			cell.setCellValue(entry.getValue().toString());
		}
		}

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=data.xlsx");
		workbook.write(response.getOutputStream());
		workbook.close();
	
	}

	

}