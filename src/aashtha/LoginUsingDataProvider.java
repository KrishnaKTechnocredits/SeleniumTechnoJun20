package aashtha;

import aashtha.base.*;

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

public class LoginUsingDataProvider extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	Alert alert;

	@BeforeClass
	void setUp() {
		driver = start("http://automationbykrishna.com/");
		wait = new WebDriverWait(driver, 20);
	}

	@AfterClass
	void tearDown() {
		driver.close();
	}

	@DataProvider(name = "loginData")
	String[][] dataProvider() {
		String[][] data = new String[2][3];
		data[0][0] = "Aastha";
		data[0][1] = "aastha@123";
		data[0][2] = "Success!";
		data[1][0] = "Aastha";
		data[1][1] = "aj";
		data[1][2] = "Failed! please enter strong password";
		return data;
	}

	@Test(dataProvider = "loginData")
	void verifyLogin(String uname, String password, String expectedMessage) {
		driver.findElement(By.xpath("//a[@id='registration2']")).click();
		WebElement username = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']")));
		username.sendKeys(uname);
		driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();
		alert = driver.switchTo().alert();
		String actualMessage = alert.getText();
		Assert.assertEquals(actualMessage, expectedMessage);
		alert.accept();
	}
}
