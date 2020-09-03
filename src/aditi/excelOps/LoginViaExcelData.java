package aditi.excelOps;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import aditi.base.PredefinedActions;


public class LoginViaExcelData extends PredefinedActions{
	WebDriver driver;
	WebDriverWait wait;

	@BeforeTest
	public void setUp() {
		driver = start();
		wait = new WebDriverWait(driver, 15);
	}

	@Test(dataProvider = "loginData")
	void loginViaExcelData(String userName, String password, String expectedMessage) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='registration2']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']")))
					.sendKeys(userName);
			driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(password);
			driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();
			Alert alert = driver.switchTo().alert();
			String actualMessage = alert.getText();
			alert.accept();
			Assert.assertEquals(actualMessage, expectedMessage);
		} catch (NoSuchElementException ne) {
			ne.printStackTrace();
		}
	}

	@DataProvider(name = "loginData")
	public Object[][] loginDataProvider() throws IOException {
		// Open file using FilePath
		String inputFilePath = ".//resources//testdata//Demo.xlsx";
		
		// Open file in read mode
		File file = new File(inputFilePath);
		FileInputStream input = new FileInputStream(file);
		
		// Create object and read excel data
		Workbook wb = new XSSFWorkbook(input);
		Sheet sheet = wb.getSheet("Data");
		int totalRows = sheet.getLastRowNum();
		int totalColumns = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[totalRows][totalColumns];
		for (int rowIndex = 1; rowIndex <= totalRows; rowIndex++) {
			for (int colIndex = 0; colIndex < totalColumns; colIndex++) {
				data[rowIndex - 1][colIndex] = sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
			}
		}
		return data;
	}

	@AfterTest
	void tearDown() {
		driver.close();
	}
}
