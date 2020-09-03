/*Assignment-15 : AutomationByKrishna login using dataprovider, use demo.xlsx file as testdata*/
package palak.testng;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import palak.base.PredefinedActions;

public class LoginAssignment15 extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeTest
	void setUp() {
		driver = start("http://automationbykrishna.com/");
	}

	@Test(dataProvider = "loginDataProvider")
	public void login(String uname, String pwd, String msg) {
		driver.findElement(By.linkText("Registration")).click();
		wait = new WebDriverWait(driver, 5);

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@id='unameSignin']")));
		if (uname != null && pwd != null && msg != null) {
			driver.findElement(By.xpath("//input[@id='unameSignin']")).sendKeys(uname);
			driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(pwd);
			driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();

			Alert alert = driver.switchTo().alert();
			String actualMsg = alert.getText();
			Assert.assertEquals(actualMsg, msg);
			alert.accept();

		}
	}

	@DataProvider(name = "loginDataProvider")
	public Object[][] loginData() throws IOException {
		// Open File
		File file = new File("./resources/testdata/Demo.xlsx");
		// Read File
		FileInputStream fileInputStream = new FileInputStream(file);
		// Load File
		Workbook wb = new XSSFWorkbook(fileInputStream);

		Sheet sheet = wb.getSheet("Data");
		int totalRowSize = sheet.getLastRowNum() + 1;
		int totalColSize = sheet.getRow(0).getLastCellNum();
		String[][] data = new String[totalRowSize][totalColSize];
		for (int rowIndex = 1; rowIndex < totalRowSize; rowIndex++) {
			for (int colIndex = 0; colIndex < totalColSize; colIndex++) {
				data[rowIndex][colIndex] = sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
			}
		}

		return data;
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
