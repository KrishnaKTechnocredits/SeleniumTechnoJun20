package nikhil.testNG;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import nikhil.base.PreDefinedActions;

public class DataDrivenLoginTest extends PreDefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	void setUp() {
		driver = start();
		wait = new WebDriverWait(driver, 15);
	}
	
	@Test(dataProvider="loginDataProvider")
	void loginTest(String userName, String password, String expectedResult) {
		driver.findElement(By.linkText("Registration")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Login']")));
		driver.findElement(By.xpath("//input[@id='unameSignin']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();
		
		Alert alert = driver.switchTo().alert();
		String actualResult = alert.getText();
		alert.accept();
		
		Assert.assertEquals(expectedResult, actualResult);
	}
	
	@DataProvider(name="loginDataProvider")
	public String[][] dataProvider(){
		String[][] data = new String[3][3];
		
		data[0][0]="nikhilm";
		data[0][1]="nikhilm@123";
		data[0][2]="Success!";
		
		data[1][0]="nik";
		data[1][1]="hi";
		data[1][2]="Failed! please enter strong password";
		
		data[2][0]="ironman";
		data[2][1]="tonystark123";
		data[2][2]="Success!";
		
		return data;
	}
	
	@AfterClass
	void tearDown() {
		driver.close();
	}
}
