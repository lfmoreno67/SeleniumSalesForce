package com.DataDrivenExcel.Salesforce;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	static String path="./src/test/resources/DataDrivenExcel/Data.xlsx";
	static XSSFWorkbook workBook;
	static XSSFSheet sheet;
	
	public ExcelUtils(String sheetName) {
		
		try {
			workBook = new XSSFWorkbook(path);
			sheet = workBook.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public int getRowCount() {
		
		int rowCount=0;
		try {
			rowCount=sheet.getPhysicalNumberOfRows();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return rowCount;
	}

	public int getColCount() {
		
		int colCount=0;
		try {
			 colCount=sheet.getRow(0).getPhysicalNumberOfCells();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return colCount;
	}
	
	public String getCellDataString(int rowNum, int colNum) {
		
		String cellData=null;
		try {
			sheet.getRow(rowNum).getCell(colNum).setCellType(CellType.STRING);
			cellData=sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return cellData;
	}

}
