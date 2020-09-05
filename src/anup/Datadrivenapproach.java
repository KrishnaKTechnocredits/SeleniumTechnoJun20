package anup;

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

import anup.basics.PredefinedActions;


public class Datadrivenapproach extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;


	@BeforeClass
	void setUp() {
	driver = start();
	wait = new WebDriverWait(driver, 15);
	}


	@Test(dataProvider = "LoginDatavalidation")
	void datadrivenvalidation(String userName, String password, String expectedMsg) {
	driver.findElement(By.linkText("Registration")).click();;
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']"))).click();
	driver.findElement(By.xpath("//input[@id='unameSignin']")).sendKeys(userName);
	driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(password);
	driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();
	Alert alert = driver.switchTo().alert();
	String actualMessage = alert.getText();
	alert.accept();
	Assert.assertEquals(actualMessage, expectedMsg);
	}


	@DataProvider(name="LoginDatavalidation")
	public Object[][] dataprovider(){
	Object[][] data = new Object[3][3];


	data[0][0] = "Anup";
	data[0][1] = "Anup1234$";
	data[0][2] = "Success!";


	data[1][0] = "kumar";
	data[1][1] = "world123@";
	data[1][2] = "Success!";


	data[2][0] = "an";
	data[2][1] = "an1"; 
	data[2][2] = "Failed! please enter strong password";

	return data;
	
	}

	@AfterClass
	void tearDown() {
	driver.close();
	}
}
