package com.example.demoOpinity.excel;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public ArrayList<ExcelObject> read(File inputExcelFile) {
		ArrayList<ExcelObject> excelObjectList = new ArrayList<>();

		try {
			FileInputStream file = new FileInputStream(inputExcelFile);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			ExcelObject excelObject = new ExcelObject();
			ArrayList<String> worksheetNames = excelObject.getWorksheetNames();

			if (worksheetNames.size() > 0) {
				for (int i = 0; i < worksheetNames.size(); i++) {
					XSSFSheet sheet = workbook.getSheet(worksheetNames.get(i));
					mapExcelItems(sheet, excelObject);
				}
			} else {
				for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
					XSSFSheet sheet = workbook.getSheet(worksheetNames.get(i));
					mapExcelItems(sheet, excelObject);
				}
			}

			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return excelObjectList;
	}

	private void mapExcelItems(XSSFSheet sheet, ExcelObject excelObject) {

		// go through all available rows
		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
			ExcelItem excelItem = new ExcelItem();

			// get the specific row of the sheet
			Row row = sheet.getRow(i);
			
			// go through the available cells in this specific row
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				// get the specific cell in the row and apply other business logic
				Cell cel = row.getCell(j);
				CellType celType = cel.getCellType();
				
				// If we know a specific cel in a row contains certain information we can set this information to a specific field in the object.
				if (j == 0) {
					excelItem.setRowwName(cel.getStringCellValue());
				}
				
				// logic for adding data based on the cellType in this case number or string
				else if (celType.equals(CellType.NUMERIC)) {
					excelItem.getExcelItemNumbers().add(cel.getNumericCellValue());
				}
				else if (celType.equals(CellType.STRING)) {
					excelItem.getExcelItemStrings().add(cel.getStringCellValue());
				}
			}
			excelObject.getWorksheetItems();
		}
	}
}
