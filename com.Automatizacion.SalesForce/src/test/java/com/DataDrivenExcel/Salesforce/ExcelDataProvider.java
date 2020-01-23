package com.DataDrivenExcel.Salesforce;

public class ExcelDataProvider {	
	
	ExcelUtils excel;
	
	public Object[][] testData(String sheetName) {
		
		excel = new ExcelUtils(sheetName);
		int colCoutn=excel.getColCount();
		int rowCount=excel.getRowCount();
		
		Object data[][] = new Object[rowCount][colCoutn];
		
		for(int i=0; i<rowCount; i++) {
			for(int j=0; j<colCoutn; j++) {
				String cellData=excel.getCellDataString(i, j);
				data[i][j]=cellData;
			}
		}
		
		return data;
	
	}
	
	
}
