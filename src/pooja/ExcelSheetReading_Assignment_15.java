package pooja;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.corba.se.spi.orbutil.fsm.Input;

public class ExcelSheetReading_Assignment_15 {
	
	@Test(dataProvider="logindetails")
	public void start(String uname,String psw,String result ) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com");
		driver.findElement(By.id("registration2")).click();
		driver.manage().window().maximize();
		driver.findElement(By.id("unameSignin")).sendKeys(uname);
		driver.findElement(By.id("pwdSignin")).sendKeys(psw);
		driver.findElement(By.id("btnsubmitdetails")).click();
		
		String expected=driver.switchTo().alert().getText();
		Assert.assertEquals(result, expected);
	}

	@DataProvider(name = "logindetails")
	public String[][] dataprovider() throws IOException {
		
		File file=new File(".//resources//testdata//demo.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
        Workbook wb= new XSSFWorkbook(inputStream);
        Sheet sheet=wb.getSheet("Data");
        int totalRows = sheet.getLastRowNum();
        System.out.println(totalRows);
		int totalCols = sheet.getRow(0).getLastCellNum();
		System.out.println(totalCols);
		String[][] data = new String[totalRows][totalCols];
		
		for(int rowIndex=0;rowIndex<totalRows;rowIndex++) {
			for(int colIndex=0;colIndex<totalCols;colIndex++) {
				data[rowIndex][colIndex] = sheet.getRow(rowIndex+1).getCell(colIndex).getStringCellValue();
			}
		}
        
        		
		return data;

	}


}
