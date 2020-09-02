package technoCredits.basics.testngdemo;

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

import technoCredits.basics.base.PredefinedActions;

public class DataProviderDemoWithExcel extends PredefinedActions{
	WebDriver driver;
	@BeforeClass
	public void setUp() {
		driver = start();
	}
	
	@Test(dataProvider="loginDataProvider")
	public void loginTest(String uname, String pwd, String expectedText) {
		//String expectedText = "Success!";
		driver.findElement(By.linkText("Registration")).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement usernameElement= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']")));
		usernameElement.sendKeys(uname);
		driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();
		Alert alert = driver.switchTo().alert();
		String actualText = alert.getText();
		alert.accept();
		Assert.assertEquals(actualText, expectedText);
	}
	
	@DataProvider(name="loginDataProvider")
	public String[][] dataprovider() throws IOException{
		
		String fileName = ".//resources//testdata//demo.xlsx";
		File file = new File(fileName);
		FileInputStream inputStream = new FileInputStream(file);
		
		Workbook wb = new XSSFWorkbook(inputStream);
		
		Sheet sheet = wb.getSheet("Data");
		int totalRows = sheet.getLastRowNum() +1;
		int totalCols = sheet.getRow(0).getLastCellNum();
		
		String[][] data = new String[totalRows][totalCols];
		
		for(int rowIndex=0;rowIndex<totalRows;rowIndex++) {
			for(int colIndex=0;colIndex<totalCols;colIndex++) {
				data[rowIndex][colIndex] = sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
			}
		}
		return data;
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
