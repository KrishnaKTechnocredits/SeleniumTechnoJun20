package kartikey;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import kartikey.base.PredDefindActions;

public class LoginUsingExcelReading extends PredDefindActions {
	WebDriver driver;
	
	void setup(String url) {
		driver=start(url);		
	}
	@BeforeTest
	void getUrl() {
		setup("http://www.automationbykrishna.com");
	}
	@AfterTest
	void tearDown() {
		driver.close();
	}
	void clickOnElement(String path) {
		driver.findElement(By.xpath(path)).click();
	}
	void sendKeysToElement(String path, String msg) {
		driver.findElement(By.xpath(path)).sendKeys(msg);
	}
	void waitexplicitly(String path) {
		WebDriverWait wait = new WebDriverWait(driver,25);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(path)));
	}
	String getAlertText() {
		return driver.switchTo().alert().getText();
	}
	void alertAccept() {
		driver.switchTo().alert().accept();
	}
	@Test(dataProvider="LoginTestData")
	void loginWithExcel(String uname, String pass, String expectedText) {
		clickOnElement("//a[@id='registration2']");
		waitexplicitly("//input[@id='unameSignin']");
		sendKeysToElement("//input[@id='unameSignin']", uname);
		sendKeysToElement("//input[@id='pwdSignin']", pass);
		clickOnElement("//button[@id='btnsubmitdetails']");
		String actualText=getAlertText();
		alertAccept();
		Assert.assertEquals(actualText, expectedText);
	}
	
	@DataProvider(name="LoginTestData")
	Object[][] dataProvider() throws IOException{
		File file = new File(".//resources//testdata//Demo.xlsx");
		FileInputStream inputSTream= new FileInputStream(file);
		
		Workbook wb= new XSSFWorkbook(inputSTream);
		Sheet sheet= wb.getSheet("Data");
		
		int totalRowsSize= sheet.getLastRowNum()+1;
		System.out.println(totalRowsSize);
		int totalColumnSize= sheet.getRow(1).getLastCellNum();
		System.out.println(totalColumnSize);
		
		Object[][] data= new Object[totalRowsSize][totalColumnSize];
		
		for(int indexRow=0; indexRow<totalRowsSize;indexRow++) {
			
			for(int indexColumn=0;indexColumn<totalColumnSize;indexColumn++) {
				data[indexRow][indexColumn]= sheet.getRow(indexRow).getCell(indexColumn).getStringCellValue();
				//System.out.println(data[indexRow][indexColumn]);
			}
			//System.out.println();
		}
		
		return data;
		
		
	}

}
