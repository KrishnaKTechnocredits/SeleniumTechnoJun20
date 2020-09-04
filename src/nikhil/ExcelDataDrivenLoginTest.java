package nikhil;

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

import nikhil.base.PreDefinedActions;

public class ExcelDataDrivenLoginTest extends PreDefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	void setUp() {
		driver=start();
		wait = new WebDriverWait(driver, 15);
	}
	
	@Test(dataProvider="loginDataProvider")
	void loginTest(String userName, String password, String expectedResult) {
		driver.findElement(By.linkText("Registration")).click();
		WebElement uName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']")));
			
		uName.sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();
					
		Alert alert = driver.switchTo().alert();
		String actualResult = alert.getText();
		alert.accept();

		Assert.assertEquals(expectedResult, actualResult);
	}
	
	@DataProvider(name="loginDataProvider")
	public String[][] dataProvider() throws IOException {
		String fileName = ".\\resources\\testdata\\Demo.xlsx";
		File file = new File(fileName);
		FileInputStream fileInputStream = new FileInputStream(file);
		
		Workbook wb = new XSSFWorkbook(fileInputStream);		
		Sheet sheet = wb.getSheet("Data");
		int totalRows = sheet.getLastRowNum();
		int totalColumns = sheet.getRow(0).getLastCellNum();
		
		String[][] data = new String[totalRows][totalColumns];
		for(int rowIndex=1; rowIndex<=totalRows; rowIndex++) {
			for(int columnIndex=0; columnIndex<totalColumns; columnIndex++) {
				data[rowIndex-1][columnIndex] = sheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();
			}
		}
		wb.close();
		return data;
	}
	
	@AfterClass
	void tearDown() {
		driver.close();
	}
}
