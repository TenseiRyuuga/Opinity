package com.example.demoOpinity.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelWorkbook {
	// get the configuration for the excel files
	ExcelConfig excelConfig = new ExcelConfig().getBasicExcelConfig();

	ArrayList<ExcelSheet> excelWorkbook = new ArrayList<>();

	public ExcelWorkbook(File inputExcelFile) {
		// we're assuming that if the file exists we can read it since we haven't placed
		// any restrictions on the file while copying it onto the server 
		// we also check if the filename ends with .xlsx or .xls which should mean it's a excel file
		if (inputExcelFile.exists() && (inputExcelFile.getName().toLowerCase().endsWith(".xlsx") || inputExcelFile.getName().toLowerCase().endsWith(".xls"))) {
			try {
				FileInputStream file = new FileInputStream(inputExcelFile);

				inputExcelFile.exists();

				// Create Workbook instance holding reference to .xlsx file
				Workbook workbook = new WorkbookFactory().create(file);

				// retrieve the names of the sheets we want to use while creating the Excel
				// Object/Workbook
				ArrayList<String> worksheetNames = excelConfig.getWorksheetNames();

				// Get desired sheet(s) from the workbook
				if (worksheetNames.size() > 0) {
					for (int i = 0; i < worksheetNames.size(); i++) {
						Sheet sheet = workbook.getSheet(worksheetNames.get(i));
						ExcelSheet excelSheet = new ExcelSheet(sheet);
						excelWorkbook.add(excelSheet);
					}
				} else {
					// in case there are no worksheetnames specified we will simply try to get all
					// sheets available in the workbook
					for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
						Sheet sheet = workbook.getSheet(worksheetNames.get(i));
						ExcelSheet excelSheet = new ExcelSheet(sheet);
						excelWorkbook.add(excelSheet);
					}
				}

				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
