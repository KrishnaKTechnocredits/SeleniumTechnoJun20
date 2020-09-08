package harshad.selenium_assignment15;

/*Assignment-15 : AutomationByKrishna login using dataprovider, use demo.xlsx file as testdata.*/

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
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

import harshad.basepkg.PredefinedSetOfActions;


public class LoginWithDataProviderDemoUsingExcel extends PredefinedSetOfActions{
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setUp() {
		driver = start("http://automationbykrishna.com/");
	}

	@Test(dataProvider="loginDataProvider")
	public void loginTest(String uname, String pwd, String expectedText) {
		driver.findElement(By.linkText("Registration")).click();
		wait = new WebDriverWait(driver, 15);
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
	public String[][] checkLoginWithDataprovider() throws IOException{

		String fileName = ".//resources//testdata//demo.xlsx";
		File file = new File(fileName);
		FileInputStream inputStream = new FileInputStream(file);

		Workbook wb = new XSSFWorkbook(inputStream);

		Sheet sheet = wb.getSheet("Data");
		int totalRows = sheet.getLastRowNum();

		int totalCols = sheet.getRow(0).getLastCellNum();

		String[][] data = new String[totalRows][totalCols];

		for(int rowIndex=0;rowIndex<totalRows;rowIndex++) {
			Row row = sheet.getRow(rowIndex+1);
			for(int colIndex=0;colIndex<totalCols;colIndex++) {

				Cell cell= row.getCell(colIndex);

				if(cell == null) {
					data[rowIndex][colIndex] = "";
					//System.out.println(data[rowIndex][colIndex]+ "\t");
				}
				else if(cell.getCellType() == CellType.STRING) {
					data[rowIndex][colIndex] = cell.getStringCellValue();
					//System.out.println(data[rowIndex][colIndex]+ "\t");
				}
				else if(cell.getCellType() == CellType.NUMERIC) {
					data[rowIndex][colIndex] = String.valueOf(cell.getNumericCellValue());
					//System.out.println(data[rowIndex][colIndex]+ "\t");
					//data[rowIndex-1][colIndex] = String.valueOf(sheet.getRow(rowIndex).getCell(colIndex).getNumericCellValue()).toString();
				}
				else if(cell.getCellType() == CellType.BLANK) {
					data[rowIndex][colIndex] = "";
					//System.out.println(data[rowIndex][colIndex]+ "\t");
				}
			}
		}
		return data;
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
