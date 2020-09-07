package aavruti;

import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import aavruti.base.PredefinedActions;

public class NumbersOfEmployeeMapping extends PredefinedActions{

	WebDriver driver;
	WebDriverWait wait;

	@BeforeTest
	void launchBrowser() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
		wait = new WebDriverWait(driver,100);
	}
	
	/*
	   1) Office & Number of employees mapping
	   2) Position and Number of Employees mapping
	*/
	@Test(dataProvider="tableData")
	void noOfEmpMapping(String columnName,Double columnNo) {
		if(columnName!= null && columnNo!=null) {
			boolean isNextButtonEnabled = true;
			HashMap<String,Integer> empMap = new HashMap<>();

			while(isNextButtonEnabled) {
				try {
					int totalRows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='example']/tbody/tr"))).size();

					for(int index=1;index<=totalRows;index++) {
						String columnValue = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + index + "]/td[" + columnNo + "]")).getText();
						if(empMap.containsKey(columnValue))
							empMap.put(columnValue, empMap.get(columnValue)+1);
						else
							empMap.put(columnValue, 1);	
					}				
					driver.findElement(By.xpath("//a[@class='paginate_button next']")).click();
				}
				catch(NoSuchElementException ne) {
					isNextButtonEnabled = false;
				}
			}

			driver.findElement(By.xpath("//a[text()='1' and contains(@class,'paginate_button')]")).click();
			System.out.println("--------------------------\n**" + columnName + " --> No Of Employees**");
			for(String keyValue : empMap.keySet()) {
				System.out.println(keyValue + " --> " + empMap.get(keyValue));
			}
		}
	}

	@DataProvider(name="tableData")
	Object[][] columnData() throws IOException{
		File file = new File(".\\src\\aavruti\\testdata\\DataTables.xlsx");
		FileInputStream inputStream = new FileInputStream(file);

		Workbook wb = new XSSFWorkbook(inputStream);
		Sheet sheet = wb.getSheet("Data");

		int totalRows = sheet.getLastRowNum()+1;
		int totalCols = sheet.getRow(0).getLastCellNum();

		Object[][] columnData = new Object[totalRows][totalCols];

		for(int rowIndex=1;rowIndex<totalRows;rowIndex++) {
			for(int colIndex=0;colIndex<totalCols;colIndex++) {
				Cell cell = sheet.getRow(rowIndex).getCell(colIndex);

				if(cell.getCellType() == CellType.STRING)
					columnData[rowIndex][colIndex] = cell.getStringCellValue();
				else if(cell.getCellType() == CellType.NUMERIC)
					columnData[rowIndex][colIndex] = cell.getNumericCellValue();
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

/* Alternate solution using Search box :
 if(!empMap.containsKey(columnValue)) {
	String currentPage = driver.findElement(By.xpath("//a[@class='paginate_button current']")).getText();
	driver.findElement(By.xpath(searchTextBox)).sendKeys(columnValue);
	String entriesText = driver.findElement(By.xpath("//div[@id='example_info']")).getText();
	empMap.put(columnValue, Integer.parseInt(entriesText.substring(entriesText.indexOf("of ") + "of ".length(),entriesText.indexOf(" entries"))));						
	driver.findElement(By.xpath(searchTextBox)).clear();
	driver.findElement(By.xpath(searchTextBox)).sendKeys(Keys.ENTER);
	driver.findElement(By.xpath("//a[text()='"+currentPage+"']")).click();
}*/
