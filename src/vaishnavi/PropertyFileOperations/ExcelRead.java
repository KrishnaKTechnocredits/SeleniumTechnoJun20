package vaishnavi.PropertyFileOperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vaishnavi_Base.PredefinedAction;

public class ExcelRead extends PredefinedAction {

	WebDriver driver;

	@BeforeTest
	void setUp() {
		driver = start();
	}

	@Test(dataProvider = "loginData")
	void loginUsingDataProvider(String uName, String pwd, String expectedText) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 3);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Registration']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']")))
					.sendKeys(uName);
			driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(pwd);
			driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();

			Alert alert = driver.switchTo().alert();
			Assert.assertEquals(alert.getText(), expectedText);
			alert.accept();

		} catch (NoSuchElementException ne) {
			ne.printStackTrace();
		}
	}

	@DataProvider(name = "loginData")
	public Object[][] dataProvider() throws IOException {
		// Open File
		File file = new File(".//resources//testdata//Book1.xlsx");
		// Enable readable mode
		FileInputStream inputstream = new FileInputStream(file);

		// Create Object
		Workbook wb = new XSSFWorkbook(inputstream);
		Sheet sheet = wb.getSheet("Sheet1");

		int totalRows = sheet.getLastRowNum();
		int totalColumns = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[totalRows][totalColumns];

		for (int rowIndex = 1; rowIndex <= totalRows; rowIndex++) {
			for (int colIndex = 0; colIndex < totalColumns; colIndex++) {
				data[rowIndex-1][colIndex] = sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
			}
		}
		return data;
	}

	@AfterTest
	void tearDown() {
		driver.close();
	}

}
