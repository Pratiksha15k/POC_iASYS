package com.exportxlfile.example;

import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExportXlFile {
	public static void main(String args[]){
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("User Import");
		Object userData[][] = {
				{"Pratiksha","Datir","pratiksha.datir@iasys.co.in",23},
				{"Komal","Shevane","komal.shevane@iasys.co.in",24},
				{"Swapnali","Suntusare","swpnali.suntusare@iasys.co.in",22}
		}; 
		int rowCount = 0;
		for (Object[] aBook : userData) {
			XSSFRow row = sheet.createRow(++rowCount);

			int columnCount = 0;

			for (Object field : aBook) {
				XSSFCell cell = row.createCell(++columnCount);
				cell.setCellValue((String) field);
			}
		}
		try{
			FileOutputStream outputStream = new FileOutputStream("UserImport.xlsx");
			workbook.write(outputStream);
		}catch(Exception e){
			
		}
	}
}
