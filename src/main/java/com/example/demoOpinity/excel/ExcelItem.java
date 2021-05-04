package com.example.demoOpinity.excel;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

// a excelObject can contain multiples of a certain group of information for example employee
public class ExcelItem {

	String rowName;
	ArrayList<String> excelItemStrings = new ArrayList<>();
	ArrayList<Double> excelItemNumbers = new ArrayList<>();

	public ExcelItem(Row row) {
		for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
			// get the specific cell in the row and apply other business logic
			Cell cel = row.getCell(j);
			if (cel != null) {
				CellType celType = cel.getCellType();

				// If we know a specific cel in a row contains certain information we can set
				// this information to a specific field in the object.
				if (j == 0) {
					rowName = cel.getStringCellValue();
				}

				// logic for adding data based on the cellType in this case number or string
				else if (celType.equals(CellType.NUMERIC)) {
					excelItemNumbers.add(cel.getNumericCellValue());
				} else if (celType.equals(CellType.STRING)) {
					excelItemStrings.add(cel.getStringCellValue());
				}
			}
		}
	}

	// getters and setters in case we want to do anything with the information
	public String getRowName() {
		return rowName;
	}

	public void setRowName(String rowName) {
		this.rowName = rowName;
	}

	public ArrayList<String> getExcelItemStrings() {
		return excelItemStrings;
	}

	public void setExcelItemStrings(ArrayList<String> excelItemStrings) {
		this.excelItemStrings = excelItemStrings;
	}

	public ArrayList<Double> getExcelItemNumbers() {
		return excelItemNumbers;
	}

	public void setExcelItemNumbers(ArrayList<Double> excelItemNumbers) {
		this.excelItemNumbers = excelItemNumbers;
	}
}
