package vaishnavi.TestNgDemo;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vaishnavi_Base.PredefinedAction;

public class AutomationByKrishna extends PredefinedAction {

	WebDriver driver;

	@BeforeTest
	void setUp() {
		driver = start();
	}

	@Test(dataProvider = "loginData")
	void loginUsingDataProvider(String uName, String pwd, String expectedText) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 3);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Registration']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']"))).sendKeys(uName);
			driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(pwd);
			driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();

			Alert alert = driver.switchTo().alert();			
			Assert.assertEquals(alert.getText(), expectedText);
			alert.accept();

		} catch (NoSuchElementException ne) {
			ne.printStackTrace();
		}
	}

	@DataProvider(name = "loginData")
	public Object[][] dataProvider() {
		Object[][] data = new Object[2][3];

		data[0][0] = "Vaishnavi";
		data[0][1] = "ABC";
		data[0][2] = "Failed! please enter strong password";

		data[1][0] = "Vaishnavi";
		data[1][1] = "ABC1254879";
		data[1][2] = "Success!";

		return data;
	}

	@AfterTest
	void tearDown() {
		driver.close();
	}

}
