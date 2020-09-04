package rachana;

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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rachana.base.PredefinedActions;

public class DataProviderUsingExcel extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	void SetUp() {
		driver = start();
		wait = new WebDriverWait(driver, 15);
	}
	
	void navigateToMenu() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("registration2")));
		driver.findElement(By.id("registration2")).click();
	}

	@Test(dataProvider = "loginTest")
	void loginTest(String uname, String pwd, String expectedText) {
		navigateToMenu();
		//String expectedtext = "Success!";
		driver.findElement(By.linkText("Registration")).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement usernameElement= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']")));
		usernameElement.sendKeys(uname);
		driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();
		Alert alert = driver.switchTo().alert();
		String actualtext = alert.getText();
		alert.accept();
		Assert.assertEquals(actualtext, expectedText);
//		navigateToMenu();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("unameSignin")));
	}
	
	@DataProvider(name ="loginTest")
	String[][] dataProvider() throws IOException{
		String fileName = ".//resources//testdata//demo.xlsx";
		File file = new File(fileName);
		FileInputStream inputstream = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(inputstream);
		Sheet sheet = wb.getSheet("Data");
		int totalrows = sheet.getLastRowNum();
		int totalcols = sheet.getRow(0).getLastCellNum();
		System.out.println(totalrows);
		System.out.println(totalcols);
		
		String[][] data = new String [totalrows][totalcols];
		for(int rowIndex=1;rowIndex<=totalrows;rowIndex++) {
			for(int colIndex=0;colIndex < totalcols; colIndex++) {
				data[rowIndex-1][colIndex] = sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
			}
		}
		
		return data;
	}

	@AfterClass
	void tearDown() {
		driver.close();
	}

}
