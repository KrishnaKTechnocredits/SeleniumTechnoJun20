package barkha;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import barkha_base.PredefinedFunctions;

public class DataProviderLogin_ReadExcelFile extends PredefinedFunctions {

	WebDriver driver;

	@BeforeClass
	void setUp() {
		driver = start();
	}

	@AfterClass
	void tearDown() {
		driver.close();
	}

	@Test(dataProvider = "DataProviderLogin")
	void login(String username, String password, String expectedText) {

		if (username != null && password != null && expectedText != null) {
			driver.findElement(By.linkText("Registration")).click();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("unameSignin")));

			driver.findElement(By.id("unameSignin")).sendKeys(username);
			driver.findElement(By.id("pwdSignin")).sendKeys(password);
			driver.findElement(By.xpath("//button[@type='submit']")).click();

			Alert alert = driver.switchTo().alert();
			String actualText = alert.getText();
			alert.accept();

			Assert.assertEquals(actualText, expectedText);
		}
	}

	@DataProvider(name = "DataProviderLogin")
	Object[][] dataProviderList() throws IOException {

		Workbook wb;

		// open file
		String fileName = ".//resources//testdata//demo.xlsx";
		File file = new File(fileName);
		FileInputStream inputStream = new FileInputStream(file);

		// enable read mode
		if (fileName.contains(".xlsx"))
			wb = new XSSFWorkbook(inputStream);                      //To open .xlsx file
		else
			wb = new HSSFWorkbook(inputStream);					     //To open .xls file

		Sheet sheet = wb.getSheet("Data");                           // Data is file name(sheet name) we saved

		// count total rows and total columns in the sheet
		int colCount = 0;
		int rowCount = sheet.getLastRowNum() + 1;                    // always do +1 because it gives 1 less count
		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
			colCount = sheet.getRow(rowIndex).getLastCellNum();      // cell means column and here we get exact col count
		}

		Object[][] givenData = new Object[rowCount][colCount];

		for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {    // at rowIndex=0... there is header only .. no data to test
																	 

			for (int colIndex = 0; colIndex < colCount; colIndex++) {
				Cell cell = sheet.getRow(rowIndex).getCell(colIndex);

				if (cell.getCellType() == CellType.NUMERIC) {
					givenData[rowIndex][colIndex] = sheet.getRow(rowIndex).getCell(colIndex).getNumericCellValue();

				} else if (cell.getCellType() == CellType.STRING) {
					givenData[rowIndex][colIndex] = sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();

				}
			}
		}
		wb.close();
		return givenData;
	}
}