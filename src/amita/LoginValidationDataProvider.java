package amita;

import java.util.concurrent.TimeUnit;

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

import amita.base.PredefinedActions;

public class LoginValidationDataProvider extends PredefinedActions {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	void setUp() {
		driver = start();
		wait = new WebDriverWait(driver, 15);
	}

	@Test(dataProvider = "LoginData")
	void datadrivenvalidation(String userName, String password, String alertMsg) {
		driver.findElement(By.linkText("Registration")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']"))).click();
		driver.findElement(By.xpath("//input[@id='unameSignin']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();
		Alert alert = driver.switchTo().alert();
		String actualMessage = alert.getText();
		alert.accept();
		Assert.assertEquals(actualMessage, alertMsg);
	}

	@DataProvider(name = "LoginData")
	public Object[][] dataprovider() {
		Object[][] data = new Object[3][3];

		data[0][0] = "Amita";
		data[0][1] = "password##12";
		data[0][2] = "Success!";

		data[1][0] = "Alia";
		data[1][1] = "hello@@world";
		data[1][2] = "Success!";

		data[2][0] = "abc";
		data[2][1] = "psd";
		data[2][2] = "Failed! please enter strong password";

		return data;
	}

	@AfterClass
	void tearDown() {
		driver.close();
	}
}