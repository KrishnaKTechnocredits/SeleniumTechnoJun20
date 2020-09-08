package madhura;

//Ass15 = automationbykrishna page registration using data from excel sheet
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

import madhura.base.PredefinedActions;

public class RegistrationWithXLSheet extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	void setUp() {
		driver = start();
		wait = new WebDriverWait(driver, 15);
	}

	void navigateToRegistrationPage() {
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Registration"))).click();
	}

	@Test(dataProvider = "loginDataProvider")
	void registerUsingXLsheetData(String userName, String password, String expectedText) {
		navigateToRegistrationPage();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']")))
				.sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();

		Alert alert = driver.switchTo().alert();
		String actualMessage = alert.getText();
		Assert.assertEquals(actualMessage, expectedText);
		alert.accept();
	}

	@DataProvider(name = "loginDataProvider")
	public Object[][] xlSheetData() throws IOException {
		String fileName = ".//src//madhura//RegistrationData.xlsx";
		File file = new File(fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(inputStream);
		Sheet sheet = wb.getSheet("LoginData");
		int totalRows = sheet.getLastRowNum() + 1;
		int totalCols = sheet.getRow(0).getLastCellNum();
		String[][] data = new String[totalRows][totalCols];
		for (int rowIndex = 0; rowIndex < totalRows; rowIndex++) {
			for (int colIndex = 0; colIndex < totalCols; colIndex++) {
				data[rowIndex][colIndex] = sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
				System.out.println(data[rowIndex][colIndex] + " ");
			}
			System.out.println();
		}
		return data;
	}

	@AfterClass
	void tearDown() {
		driver.quit();
	}
}