package pranita;
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

import pranita.basic.PredefinedFunctions;

public class Assignment_15_ReadDataFromExcel extends PredefinedFunctions {
	WebDriver driver;

	@BeforeClass
	void setUp() {
		driver = start();
	}
	
	@Test(dataProvider = "loginDataProviderUsingExcelSheet")
	public void loginUsingDataProvider(String uname,String password,String expectedText) {
		driver.findElement(By.linkText("Registration")).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement userNameElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']")));
		userNameElement.sendKeys(uname);
		driver.findElement(By.id("pwdSignin")).sendKeys(password);
		driver.findElement(By.id("btnsubmitdetails")).click();
	
		Alert alert = driver.switchTo().alert();
		String actualText = alert.getText();
		alert.accept();

		Assert.assertEquals(actualText, expectedText);

	}
	@DataProvider(name = "loginDataProviderUsingExcelSheet")
	public Object[][] loginDataProvider() throws IOException {
		File file = new File(".//resources//testdata//TestData.xlsx");	
		FileInputStream input = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(input);
		Sheet sheet = workbook.getSheet("Data");
		int totalRows = sheet.getLastRowNum()+1;
		int totalColumns = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[totalRows][totalColumns];
		for (int rowIndex=0;rowIndex<totalRows;rowIndex++) {
			for (int colIndex=0;colIndex<totalColumns;colIndex++) {
				data[rowIndex][colIndex] = sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
			}
		}
		return data;
	}
	
	@AfterClass
	void tearDown() {
		driver.close();
	}

}
