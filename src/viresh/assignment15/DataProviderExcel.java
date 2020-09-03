package viresh.assignment15;

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

import viresh.utility.SetUp;

public class DataProviderExcel extends SetUp {

	WebDriver driver;

	@BeforeClass
	public WebDriver setUp() {
		return driver = start();
	}

	@Test(dataProvider = "loginDataProvider")
	public void loginTest(String uName, String pwd, String expectedAlertText) {
		if (uName!=null && pwd != null && expectedAlertText!=null) {
			
			driver.findElement(By.xpath("//a[@id= 'registration2']")).click();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement userName = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']")));
			userName.sendKeys(uName);
			driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(pwd);
			driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();

			Alert alert = driver.switchTo().alert();
			String ActualAlertText = alert.getText();
			alert.accept();
		
			Assert.assertEquals(expectedAlertText, ActualAlertText);
		}
	}

	@DataProvider(name = "loginDataProvider")
	public String[][] dataProvider() throws IOException {

		File file = new File(".//resources//testdata//demo.xlsx");
		FileInputStream inputStream = new FileInputStream(file);

		Workbook wb = new XSSFWorkbook(inputStream);

		Sheet sheet = wb.getSheet("Data");

		int totalRows = sheet.getLastRowNum()+1;
		int totalCols = sheet.getRow(0).getLastCellNum();

		String[][] data = new String[totalRows][totalCols];

		for (int outerIndex = 1; outerIndex < totalRows; outerIndex++) {
			for (int innerIndex = 0; innerIndex < totalCols; innerIndex++) {
				data[outerIndex][innerIndex] = sheet.getRow(outerIndex).getCell(innerIndex).getStringCellValue();
			}
		}
		return data;
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
