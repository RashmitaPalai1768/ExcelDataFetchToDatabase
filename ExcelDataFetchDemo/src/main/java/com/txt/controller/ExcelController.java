package com.txt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.txt.entity.ExcelEntity;
import com.txt.helper.Helper;
import com.txt.repo.ExcelRepo;
import com.txt.service.ExcelServiceImpl;

@RestController
public class ExcelController {

	@Autowired
	ExcelRepo excelRepo;
	@Autowired
	ExcelServiceImpl excelService;
	@PostMapping("/")
	public ResponseEntity<?> fetchExcel(@RequestParam("file") MultipartFile file) {
		if(Helper.checkExcelFormat(file)) {
			excelService.save(file);
			return ResponseEntity.ok(Map.of("Message","File is Uploaded Successfully into Database"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Upload Excel File Only");
		
	}
	@GetMapping("/product")
	public java.util.List<ExcelEntity> getAll(){
		return excelService.getAllExcel();
	}
}
