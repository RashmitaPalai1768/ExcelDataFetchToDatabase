package com.txt.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.txt.entity.ExcelEntity;
import com.txt.helper.Helper;
import com.txt.repo.ExcelRepo;

@Service
public class ExcelServiceImpl{

	@Autowired
	private ExcelRepo excelRepo;
	
	public void save(MultipartFile file) {
		try {
			List<ExcelEntity> products= Helper.convertToList(file.getInputStream());
			excelRepo.saveAll(products);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<ExcelEntity> getAllExcel(){
		return excelRepo.findAll();
	}

}
