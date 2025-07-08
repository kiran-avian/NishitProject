package com.dataProviders;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDPMethods {
	public static String[][] selectiveData(String path, String SheetName, int FirstRow, int LastRow)
			throws IOException {

		FileInputStream file = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(SheetName);

		// int row = sheet.getLastRowNum();
		int row = LastRow - FirstRow + 1;
		int col = sheet.getRow(1).getLastCellNum();
		String[][] data = new String[row][col];
		DataFormatter df = new DataFormatter();
		for (int i = 0; i < row; i++) {
			XSSFRow cnRow = sheet.getRow(i + FirstRow + 1);
			for (int j = 0; j < col; j++) {
				data[i][j] = df.formatCellValue(cnRow.getCell(j));
			}
		}
		workbook.close();
		return data;

	}

	public static String[][] allData(String path) throws IOException {

		FileInputStream file = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet1");

		int row = sheet.getLastRowNum();

		int col = sheet.getRow(1).getLastCellNum();
		String[][] data = new String[row][col];
		DataFormatter df = new DataFormatter();
		for (int i = 0; i < row; i++) {
			XSSFRow cnRow = sheet.getRow(i + 1);
			for (int j = 0; j < col; j++) {
				data[i][j] = df.formatCellValue(cnRow.getCell(j));
			}
		}
		workbook.close();
		return data;
	}

	public static ArrayList<String> allDataWithoutGreenBackgrndColor(String path) throws IOException {
		ArrayList<String> al = new ArrayList<String>();
		FileInputStream file = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int row = sheet.getLastRowNum();
		System.out.println("row" + row);
		int col = sheet.getRow(0).getLastCellNum();
		String[][] data = new String[row + 1][col];
		DataFormatter df = new DataFormatter();
		for (int i = 0; i < row + 1; i++) {
			XSSFRow cnRow = sheet.getRow(i);
			for (int j = 0; j < col; j++) {
				data[i][j] = df.formatCellValue(cnRow.getCell(j));

				XSSFCell cell = cnRow.getCell(j);
				// Get the cell's style to check the background color
				XSSFCellStyle cellStyle = cell.getCellStyle();

				// Get the background color (Indexed Colors are typically used in Excel)
				XSSFColor color = cellStyle.getFillForegroundColorColor();

				XSSFColor xssfColor = (XSSFColor) color;
				String colorcod = xssfColor.getARGBHex();
				String colorFinal;
				String error;
				switch (colorcod) {
				case "FF70AD47":
					colorFinal = "Green";
					break;
				case "FFED7D31":
					colorFinal = "Orange";
					error = "Row " + (i) + ": " + sheet.getRow(0).getCell(j);
					// System.out.println(error);
					al.add(error);
					break;

				case "FF5B9BD5":
					colorFinal = "Blue Broken-URL";
					error = "Row " + (i) + ": " + sheet.getRow(0).getCell(j);
					// System.out.println(error);
					al.add(error);
					break;
				case "FFFFC000":
					colorFinal = "Yellow Dropdown-Error";
					error = "Row " + (i) + ": " + sheet.getRow(0).getCell(j);
					// System.out.println(error);
					al.add(error);
					break;
				default:
					colorFinal = "No colour";
					break;
				}
				// System.out.println(data[i][j] + " " + colorcod + " " + colorFinal);

			}
		}
		workbook.close();
		return al;
	}

	public static void editExcel2Cell(String excelFilePath, int rowIndex1, int colIndex1, String val1, int rowIndex2,
			int colIndex2, String val2) {

		try (FileInputStream fis = new FileInputStream(excelFilePath); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			// Get the first sheet
			XSSFSheet sheet = workbook.getSheet("Sheet1");

			// Update the first specified cell
			XSSFRow row = sheet.getRow(rowIndex1);
			if (row == null) {
				row = sheet.createRow(rowIndex1);
			}
			XSSFCell cell = row.getCell(colIndex1);
			if (cell == null) {
				cell = row.createCell(colIndex1);
			}
			cell.setCellValue(val1);

			// Update the second specified cell
			row = sheet.getRow(rowIndex2);
			if (row == null) {
				row = sheet.createRow(rowIndex2);
			}
			cell = row.getCell(colIndex2);
			if (cell == null) {
				cell = row.createCell(colIndex2);
			}
			cell.setCellValue(val2);

			// Write the changes back to the file
			try (FileOutputStream fos = new FileOutputStream(excelFilePath)) {
				workbook.write(fos);
			}

			System.out.println("Excel file updated successfully!");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void editExcel1Cell(String excelFilePath, int rowIndex1, int colIndex1, String val1) {

		try (FileInputStream fis = new FileInputStream(excelFilePath); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			// Get the first sheet
			XSSFSheet sheet = workbook.getSheet("Sheet1");

			// Update the first specified cell
			XSSFRow row = sheet.getRow(rowIndex1);
			if (row == null) {
				row = sheet.createRow(rowIndex1);
			}
			XSSFCell cell = row.getCell(colIndex1);
			if (cell == null) {
				cell = row.createCell(colIndex1);
			}
			cell.setCellValue(val1);
			
			// Write the changes back to the file
			try (FileOutputStream fos = new FileOutputStream(excelFilePath)) {
				workbook.write(fos);
			}

			System.out.println("Excel file updated successfully!");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
