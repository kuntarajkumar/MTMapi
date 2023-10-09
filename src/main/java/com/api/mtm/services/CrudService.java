package com.api.mtm.services;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.mtm.commen.services.ExcelService;
import com.api.mtm.entitys.User;
import com.api.mtm.repositorys.UserRepository;

@Service
public class CrudService {
	@Autowired
	ExcelService excelService;
	@Autowired
	UserRepository userRepository;
	
	Logger logger = LoggerFactory.getLogger(CrudService.class);
	
	public ResponseEntity<?> saveExcelFile(MultipartFile excelFile) {
		try {
			if (excelService.checkExcelFileFormat(excelFile)) {
				List<User> list = excelService.ConvertExcelToList(excelFile.getInputStream());
				userRepository.saveAll(list);
				logger.info("excel file save successfully!!");
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(Map.of("status", 0, "message", "Excel file data successfully uploaded."));
			} else {
				logger.warn("excel file extention is wrong!!");
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(Map.of("status", 1, "message", "only excel file supported."));
			}
		} catch (Exception e) {
			logger.error("ERROR : "+e.getMessage());
			e.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("status", -1, "message", e.getMessage()));
		}
	}

	
	public ResponseEntity<?> getData() {
		try {
			List<User> users = userRepository.findAll();
			logger.info("user fetching data.");
			return ResponseEntity.status(HttpStatus.OK).body(Map.of("status",0,"data",users));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("status",-1,"message",e.getMessage()));
		}
	}
}
