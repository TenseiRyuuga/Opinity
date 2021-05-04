package com.example.demoOpinity.excel;

import java.util.ArrayList;

public class ExcelObject {
	private ArrayList<String> worksheetNames = new ArrayList<>();
	private ArrayList<ExcelItem> worksheetItems = new ArrayList<>();

	public ExcelObject() {
		// the names of the sheets that we want to read in
		worksheetNames.add("Sheet1");

	}

	// getters and setters
	public ArrayList<String> getWorksheetNames() {
		return worksheetNames;
	}

	public ArrayList<ExcelItem> getWorksheetItems() {
		return worksheetItems;
	}
}
