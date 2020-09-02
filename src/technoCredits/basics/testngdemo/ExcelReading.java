package technoCredits.basics.testngdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelReading {

	@Test
	public void readExcel() throws IOException {
		// open file
		String fileName = ".//resources//testdata//demo.xlsx";
		File file = new File(fileName);
		// enable read mode
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(inputStream);
		
		Sheet sheet = wb.getSheet("Data");
		/*String data1 = sheet.getRow(0).getCell(0).getStringCellValue();
		String data2 = sheet.getRow(0).getCell(1).getStringCellValue();
		String data3 = sheet.getRow(0).getCell(2).getStringCellValue();
	*/
		
		int totalRows = sheet.getLastRowNum()+1; // 2 ---- 1+1
		System.out.println(totalRows);
		
		int totalCols = sheet.getRow(0).getLastCellNum(); //3 --- 3
		System.out.println(totalCols);
		
		
		Object[][] data = new Object[totalRows][totalCols];
		
		for(int rowIndex=0;rowIndex<totalRows;rowIndex++) {
			int totalCurrentColNumber = sheet.getRow(rowIndex).getLastCellNum();
			for(int colIndex=0;colIndex<totalCurrentColNumber;colIndex++) {
				Cell cell = sheet.getRow(rowIndex).getCell(colIndex);
				
				if(cell.getCellType() == CellType.NUMERIC)
					data[rowIndex][colIndex] = sheet.getRow(rowIndex).getCell(colIndex).getNumericCellValue();
				else if(cell.getCellType() == CellType.STRING) {
					data[rowIndex][colIndex] = sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
				}
				
				System.out.print(data[rowIndex][colIndex] + " ");
				
			}
			System.out.println();
		}	
		
	}
}
