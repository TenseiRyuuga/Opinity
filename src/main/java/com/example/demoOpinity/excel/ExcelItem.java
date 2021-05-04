package com.example.demoOpinity.excel;

import java.util.ArrayList;

// a excelObject can contain multiples of a certain group of information for example employee
public class ExcelItem {
	
	String rowwName;
	ArrayList<String> excelItemStrings = new ArrayList<>();
	ArrayList<Double> excelItemNumbers = new ArrayList<>();
	
	// getters and setters
	public String getRowwName() {
		return rowwName;
	}
	public void setRowwName(String rowwName) {
		this.rowwName = rowwName;
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
