package com.api.mtm.commen.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.mtm.entitys.User;


@Service
public class ExcelService {

	public boolean checkExcelFileFormat(MultipartFile file) {
		return file.getContentType().equals("application/vnd.ms-excel") || file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}

	public List<User> ConvertExcelToList(InputStream is) {
		List<User> list = new ArrayList<>();

		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheet("user_Template");
			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cells = row.iterator();
				// int cid=0;
				User user = new User();
				user.setCreatedDtTime(new Date());

				while (cells.hasNext()) {
					Cell cell = cells.next();
					switch (cell.getAddress().getColumn()) {
					case 0:
						user.setUserName(cell.getStringCellValue());
						break;
					case 1:
						//user.setUserContact(""+cell.getNumericCellValue());
						user.setUserContact(cell.getStringCellValue());
						break;
					case 2:
						user.setUserEmail(cell.getStringCellValue());
						break;
					case 3:
						user.setUserAddress(cell.getStringCellValue());;
						break;
					case 4:
						user.setUserSalary((float)(cell.getNumericCellValue()));
						break;
					case 5:
						user.setUserAge((int)cell.getNumericCellValue());
						break;
					case 6:
						user.setUserGender(cell.getStringCellValue());
						break;
					default:
						break;
					}
				}
				list.add(user);
			}
			workbook.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
	}
}
