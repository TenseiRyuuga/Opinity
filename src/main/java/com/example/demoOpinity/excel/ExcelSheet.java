package com.example.demoOpinity.excel;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelSheet {
	private ArrayList<ExcelItem> excelSheet = new ArrayList<>();

	public ExcelSheet(Sheet sheet) {
		// go through all available rows if you want information based on columns
		// you will need to make some changes here
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {

			// get the specific row of the sheet
			Row row = sheet.getRow(i);

			// create a ExcelItem based on the row 
			ExcelItem excelItem = new ExcelItem(row);

			excelSheet.add(excelItem);
		}
	}
}
