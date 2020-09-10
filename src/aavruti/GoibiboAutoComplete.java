package aavruti;

import java.io.*;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class GoibiboAutoComplete {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeTest
	void setup() {
		driver = GoibiboInit.getDriver();
		wait = GoibiboInit.getDriverWait();
	}
	/*	1)Enter 'Pun' in From field, Print all the option from given auto-suggestions and Select Pune.
		2) Enter 'Del' in Destination field, Print all the option from given auto-suggestions and select Delhi.
	 */
	@Test(dataProvider="autoSuggestionData")
	void autoSuggestion(String sourceInput, String sourceValue, String expectedValue, String printMsg, String inputBoxLocator) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='"+inputBoxLocator+"']"))).sendKeys(sourceInput);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='react-autosuggest-1']")));

		int totalAutoSuggestion = driver.findElements(By.xpath("//ul[@id='react-autosuggest-1']/li")).size();

		System.out.println("\n**"+printMsg+": ");
		for(int index=1;index<=totalAutoSuggestion;index++){
			System.out.println(driver.findElement(By.xpath("(//ul[@id='react-autosuggest-1']//child::div[@class='mainTxt clearfix'])["+index+"]/span")).getText());
		}

		driver.findElement(By.xpath("//ul[@id='react-autosuggest-1']//child::div[@class='mainTxt clearfix']/span[text()='"+sourceValue+"']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='"+inputBoxLocator+"']")).getAttribute("value"),expectedValue);
		System.out.println();
	}

	@DataProvider(name="autoSuggestionData")
	Object[][] autoSuggestionData() throws IOException{

		File file = new File(".\\src\\aavruti\\testdata\\Goibibo.xlsx");
		FileInputStream inputStream = new FileInputStream(file);

		Workbook wb = new XSSFWorkbook(inputStream);		
		Sheet sheet = wb.getSheet("AutoSuggestion");

		int totalRows = sheet.getLastRowNum();
		int totalCols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[totalRows][totalCols];

		for(int rowIndex=1;rowIndex<=totalRows;rowIndex++) {
			for(int colIndex=0;colIndex<totalCols;colIndex++) {
				data[rowIndex-1][colIndex] = sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
			}
		}
		wb.close();
		return data;
	}
}
