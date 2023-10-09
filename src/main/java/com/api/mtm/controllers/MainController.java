package com.api.mtm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.mtm.services.CrudService;

@RestController
@CrossOrigin("*")
public class MainController {
	@Autowired
	CrudService crudService;
	
	@PostMapping("/uploadExcelFile")
	private ResponseEntity<?> saveExcelFileInDb(@RequestParam("excelFile") MultipartFile excelFile) {
		return crudService.saveExcelFile(excelFile);
	}
	
	@GetMapping("/getData")
	private ResponseEntity<?> getData(){
		return crudService.getData();
	}
}
