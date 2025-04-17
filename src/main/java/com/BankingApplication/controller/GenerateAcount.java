package com.BankingApplication.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.ui.Model;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.dto.GenerateDto;
import com.BankingApplication.service.AccountService;
import com.BankingApplication.service.GenerateService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/accounts")
@CrossOrigin(origins="http://localhost:4200")
public class GenerateAcount {

    
    private GenerateService generateService;
    public GenerateAcount(GenerateService generateService) {
		super();
		this.generateService = generateService;
	}

   @PostMapping("/generate")
   @PreAuthorize("hasRole('ADMIN')") 
	public ResponseEntity<GenerateDto> addAccount(@RequestBody GenerateDto generateDto){
		return new ResponseEntity<>(generateService.generateAccount(generateDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{accountNumber}/adminsearch")
	@PreAuthorize("hasRole('Admin')") 
	public ResponseEntity<List<GenerateDto>> getAccountByAccountNumberAdmin(@PathVariable int accountNumber) {
		List<GenerateDto> generateDto= generateService.getAccountByAccountNumberAdmin(accountNumber);
		if (generateDto != null) {
			return ResponseEntity.ok(generateDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/adminexport")
	@PreAuthorize("hasRole('Admin')") 
	public void exportToExcel(@RequestBody List<Map<String, Object>> data, HttpServletResponse response) throws IOException {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Data");

		// Create a CellStyle with a bold font
		CellStyle headerCellStyle = workbook.createCellStyle();
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);  // Set the font to bold
		headerCellStyle.setFont(headerFont); 
	

		String[] headers = { "ID", "account_holder_name","account_number","adhaar"};
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
		response.setHeader("Content-Disposition", "attachment; filename=dataAdmin.xlsx");
		workbook.write(response.getOutputStream());
		workbook.close();
	
	}
	

}
