package anshu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import anshu.base.PredefinedProperty;

public class LoginExcelDataProvider extends PredefinedProperty {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setUp() {
		driver = start();
		wait = new WebDriverWait(driver,15);
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

	@Test(dataProvider = "logindataprovider")
	public void loginValidation(String userName, String passWord, String exptdResult) {
		try {
			driver.findElement(By.xpath("//a[text()='Registration']")).click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='unameSignin']"))))
					.sendKeys(userName);
			driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(passWord);
			driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();

			Alert alert = driver.switchTo().alert();
			String actualText = alert.getText();
			alert.accept();

			Assert.assertEquals(actualText, exptdResult);
		} catch (NoSuchElementException ne) {
			ne.printStackTrace();
		}

	}

	@DataProvider(name = "logindataprovider")
	public Object[][] dataProvide() throws IOException {
		String fileName = ".//resources//testdata//Demo.xlsx";
		File file = new File(fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(inputStream);
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

}
