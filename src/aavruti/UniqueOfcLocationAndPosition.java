package aavruti;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.io.*;
import java.util.*;

import aavruti.base.PredefinedActions;

public class UniqueOfcLocationAndPosition extends PredefinedActions{

	WebDriver driver;
	WebDriverWait wait;

	@BeforeSuite
	void launchBrowser() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
		wait = new WebDriverWait(driver,30);
	}

	
	@Test(dataProvider="columnNumber")
	void UniqueValues(String columnName, String columnNo) {

		if(columnName!=null && columnNo!=null) {

			boolean flag = true;
			HashSet<String> tableValueSet = new HashSet<String>();

			System.out.println("\n----------------------------------------");
			System.out.println("Unique Value of " + columnName + " is :");

			while(flag) {			
				try {				
					int totalRows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='example']/tbody/tr"))).size();

					for(int index=1;index<=totalRows;index++) {
						String tableValue = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + index + "]/td[" + columnNo + "]")).getText();
						if(tableValueSet.add(tableValue)) {
							System.out.println(tableValue);
						}
					}
					driver.findElement(By.xpath("//a[@class='paginate_button next']")).click();
				}
				catch(NoSuchElementException ne) {
					flag = false;
				}
			}
			driver.findElement(By.xpath("//a[text()='1' and contains(@class,'paginate_button')]")).click();
		}
	}

	@DataProvider(name="columnNumber")
	String[][] columnNumber() throws IOException{

		String fileName = ".\\src\\aavruti\\testdata\\DataTables.xlsx";
		File file = new File(fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(inputStream);

		Sheet sheet = wb.getSheet("Data");

		int totalRows = sheet.getLastRowNum()+1;
		int totalColumns = sheet.getRow(0).getLastCellNum();

		String[][] columnData = new String[totalRows][totalColumns];

		for(int rowIndex=1;rowIndex<totalRows;rowIndex++) {
			for(int colIndex=0;colIndex<totalColumns;colIndex++) {
				Cell cell = sheet.getRow(rowIndex).getCell(colIndex);

				if(cell.getCellType() == CellType.STRING) {
					columnData[rowIndex][colIndex] = cell.getStringCellValue();
				}
				else if(cell.getCellType() == CellType.NUMERIC) {
					columnData[rowIndex][colIndex] = String.valueOf(cell.getNumericCellValue());
				}
			}
		}
		wb.close();
		return columnData;
	}
	
	@AfterTest
	void closeBrowser() {
		driver.quit();
	}
}