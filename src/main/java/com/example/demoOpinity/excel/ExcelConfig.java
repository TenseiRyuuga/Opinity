package com.example.demoOpinity.excel;

import java.util.ArrayList;

public class ExcelConfig {
	private ArrayList<String> worksheetNames = new ArrayList<>();

	// a simple way to make a prest config in case we repeatedly need the same
	// configuration
	public ExcelConfig getBasicExcelConfig() {
		// the names of the sheets that we want to read in
		worksheetNames.add("Sheet1");
		return this;
	}
	
	// getters and setters
	public ArrayList<String> getWorksheetNames() {
		return worksheetNames;
	}

}
