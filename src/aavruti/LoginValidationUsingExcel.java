package aavruti;

import java.io.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


import aavruti.base.PredefinedActions;
import aavruti.utility.PropertyFileOperation;

public class LoginValidationUsingExcel extends PredefinedActions{

	WebDriver driver;
	WebDriverWait wait;

	String registrationTab, loginHeader, userNameInput, passwordInput, submitButton;

	@BeforeSuite
	void readProperty() throws IOException {		
		PropertyFileOperation prop = new PropertyFileOperation(".//src//aavruti//config//RegistrationLocator.properties");

		registrationTab = prop.propRead("registrationTab");
		loginHeader = prop.propRead("loginHeader");
		userNameInput = prop.propRead("userNameInput");
		passwordInput = prop.propRead("passwordInput");
		submitButton = prop.propRead("submitButton");
	}

	@BeforeTest
	void setUp() {
		driver = start();
		wait = driverWait();
	}

	@BeforeClass
	void navigateToRegistration() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(registrationTab))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loginHeader))).isDisplayed();
	}

	@Test(dataProvider="loginDataProvider")
	void validateLogin(String userName, String password, String alertMessage) {

		if(userName != null && password != null && alertMessage != null ) {
			driver.findElement(By.xpath(userNameInput)).sendKeys(userName);
			driver.findElement(By.xpath(passwordInput)).sendKeys(password);
			driver.findElement(By.xpath(submitButton)).click();

			Alert alert = driver.switchTo().alert();		
			Assert.assertEquals(alert.getText(), alertMessage);
			alert.accept();

			driver.findElement(By.xpath(userNameInput)).clear();
			driver.findElement(By.xpath(passwordInput)).clear();
		}
	}

	@DataProvider(name="loginDataProvider")
	String[][] loginDataProvider() throws IOException{

		String fileName = ".\\resources\\testdata\\Demo.xlsx";
		File file = new File(fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(inputStream);

		Sheet sheet = wb.getSheet("Data");

		int totalRows = sheet.getLastRowNum()+1;
		int totalCols = sheet.getRow(0).getLastCellNum();

		String[][] loginData = new String[totalRows][totalCols];

		for(int rowIndex=1;rowIndex<totalRows;rowIndex++) {
			for(int colIndex=0;colIndex<totalCols;colIndex++) {
				loginData[rowIndex][colIndex] = sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
			}
		}
		wb.close();
		return loginData;
	}	

	@AfterClass
	void closeBrowser() {
		driver.quit();
	}
}
