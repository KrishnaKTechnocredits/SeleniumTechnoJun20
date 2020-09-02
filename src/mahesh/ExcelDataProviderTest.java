package mahesh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mahesh.base.PredefinedActions;
import org.apache.poi.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProviderTest extends PredefinedActions{
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	void setUp() {
		driver = start();
		wait = new WebDriverWait(driver, 20);
	}

	@AfterMethod
	void tearDown() {
		driver.close();
	}

	@Test(dataProvider = "loginDataProvider")
	public void loginWithMultipleData(String userName, String pass, String expectedMessage) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='registration2']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']"))).sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(pass);
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();
		Alert alert = driver.switchTo().alert();
		String actualMessage = alert.getText();
		alert.accept();
		Assert.assertEquals(actualMessage, expectedMessage);
	}
	
	@DataProvider(name = "loginDataProvider")
	public Object[][] loginDataProvider() throws IOException {
		String inputFilePath = ".//resources//testdata//Demo.xlsx";
		File file = new File(inputFilePath);	
		FileInputStream input = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(input);
		Sheet sheet = wb.getSheet("Data");
		int totalRows = sheet.getLastRowNum();
		int totalColumns = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[totalRows][totalColumns];
		for (int rowIndex=1;rowIndex<=totalRows;rowIndex++) {
			for (int colIndex=0;colIndex<totalColumns;colIndex++) {
				data[rowIndex-1][colIndex] = sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
			}
		}
		return data;
	}
}
