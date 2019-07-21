package com.automationpractice.core.utils;


import java.io.IOException;
import java.util.HashMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.automationpractice.core.base.Globals;


	public class ExcelUtils extends FileUtility {   
		/**
		 * @Function  This method is designed to check for the type of the provided cell and return the cell value as Object type
		 */
		protected Object getCellValue(Cell cell) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:	
				return cell.getStringCellValue();
			case Cell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue();
			case Cell.CELL_TYPE_NUMERIC:
				return cell.getNumericCellValue();
			}    
			return null;
		}

		/**
		 * @Function  This method is designed to set the provided value into the specified cell as per the type of the value
		 */
		protected Cell setCellValue(Cell cell, Object value) 	{
			if (value.getClass() == Integer.class) {
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				cell.setCellValue(Integer.parseInt((String) value));
			} else if (value.getClass() == String.class) {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue((String) value);
			} else if (value.getClass() == Boolean.class) {
				cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
				cell.setCellValue(Boolean.parseBoolean((String) value));
			}
			return cell;
		}

		/**
		 * @Function  This method will create a new cell
		 */
		protected Cell createCell(Row row, int cellNo) {
			Cell cell = null;
			try {
				if(row.getCell(cellNo) == null)
					cell = row.createCell(cellNo);
			} catch (Exception e) {
				System.out.println("Error while creating new cell");
				e.printStackTrace();
			}
			return cell;
		}
		/**
		 * @Function  This method is designed to get the specified sheet of an excel file
		 */
		public Sheet getSheet(String excelFilePath, String sheetName) {
			Sheet sheet = null;
			try {
				Workbook workbook = this.getWorkbook(excelFilePath);
				sheet = workbook.getSheet(sheetName);
			} catch(Exception e) {
				System.out.println("Error while fetching specified sheet from the file. Sheet name = " + sheetName);
				e.printStackTrace();
			}   	
			return sheet;  	
		}

		/**
		 * @Function  This method is designed to check for the type of the excel file and accordingly to get the workbook of an excel file
		 */
		protected Workbook getWorkbook(String excelFilePath) {
			Workbook workbook = null;
			try {
				if (excelFilePath.endsWith("xlsx")) {
					workbook = new XSSFWorkbook(super.getFileInputStream(excelFilePath));
				} else if (excelFilePath.endsWith("xls")) {
					workbook = new HSSFWorkbook(super.getFileInputStream(excelFilePath));
				} else {
					throw new IllegalArgumentException("The specified file is not Excel file");
				}
			} catch(IOException eIO) {
				System.out.println("Error while fetching specified excel workbook. Excel Filepath = " + excelFilePath);
			}
			return workbook;
		}

		/**
		 * @Function  This method is designed to save the provided sheet to the excel file
		 */
		protected void  saveSheet(Sheet sheet, String excelFilePath) {
			try {
				if(excelFilePath.endsWith("xlsx") || excelFilePath.endsWith("xls")) {
					sheet.getWorkbook().write(super.getFileOutputStream(excelFilePath));    
				} else {
					throw new IllegalArgumentException("The specified file is not Excel file");
				}			
			} catch(IOException eIOException) {
				eIOException.printStackTrace();
			}
		}

		public synchronized HashMap<String, Object> getTestDataset(String sheetName) {    
			HashMap<String, Object> testDataset = new HashMap<String, Object>();       
			Sheet sheet = getSheet(Globals.TESTDATA_EXCEL_FILEPATH, sheetName);

			try {
				Row headerRow = sheet.getRow(0);

				for(int rowNo=1; rowNo<=sheet.getLastRowNum();) {
					Row currentRow = sheet.getRow(rowNo);

					//if(getCellValue(currentRow.getCell(0)).toString().equalsIgnoreCase(testDatasetID)) {

						for(int cellNo=0; cellNo<currentRow.getLastCellNum(); cellNo++) {
							Cell currentCell = currentRow.getCell(cellNo);
							String currentFieldName = getCellValue(headerRow.getCell(cellNo)).toString();
							Object testData =getCellValue(currentCell);	
							System.out.println(currentFieldName + "===" + testData);
							testDataset.put(currentFieldName, testData);
						}
						break;

				}
			} catch (Exception e) {
				System.out.println("Error while fetching test dataset");
				
			}
			return testDataset;
		}


}
