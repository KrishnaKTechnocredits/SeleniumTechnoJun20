package aashtha;

import aashtha.base.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginUsingDataFromExcel extends PredefinedActions{
	WebDriver driver;
	WebDriverWait wait;
	Workbook workbook;
	Sheet sheet;
	Alert alert;

	//opens the Chrome
	@BeforeClass
	void setUp() {
		driver = start("http://automationbykrishna.com/");
		wait = new WebDriverWait(driver, 30);
	}
	
	//closes the browser window
	@AfterClass
	void tearDown() {
		driver.close();
	}
	
	void openFile(String filePath) throws IOException {
		File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);
		workbook = new XSSFWorkbook(inputStream);
		sheet = workbook.getSheet("Data");
	}
	
	@DataProvider(name = "loginData")
	String[][] dataProvider() throws IOException {
		openFile(".\\resources\\testdata\\Demo.xlsx");
		int totalRows = sheet.getLastRowNum() + 1;
		int totalColumns = sheet.getRow(0).getLastCellNum();
		String[][] data = new String[totalRows][totalColumns];
		for(int rowIndex = 0; rowIndex < totalRows; rowIndex++) {
			for(int columnIndex = 0; columnIndex < totalColumns; columnIndex++) {
				data[rowIndex][columnIndex] = sheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();
			}
		}
		return data;
	}
	
	@Test(dataProvider = "loginData")
	void verifyLogin(String uname, String password, String expectedMessage) {
		driver.findElement(By.xpath("//a[@id='registration2']")).click();
		WebElement username = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']")));
		if(!uname.equals("uname")) {
			username.sendKeys(uname);
			driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(password);
			driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();
			alert = driver.switchTo().alert();
			String actualMessage = alert.getText();
			Assert.assertEquals(actualMessage, expectedMessage);
			alert.accept();
		}
	}
}
