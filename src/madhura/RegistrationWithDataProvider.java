package madhura;

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

import madhura.base.PredefinedActions;

public class RegistrationWithDataProvider extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	void setUp() {
		driver = start();
		wait = new WebDriverWait(driver, 15);
	}

	void navigateToRegistrationPage() {
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Registration"))).click();
	}

	@Test(dataProvider = "loginDataProvider")
	void loginUsingDataProvider(String userName, String password, String expectedText) {
		navigateToRegistrationPage();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']"))).sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();
		
		Alert alert = driver.switchTo().alert();
		String actualMessage = alert.getText();
		Assert.assertEquals(actualMessage, expectedText);
		alert.accept();
	}

	@DataProvider(name = "loginDataProvider")
	public Object[][] dataProviders() {
		Object[][] data = new Object[2][3];
		data[0][0] = "Madhura";
		data[0][1] = "madhura456";
		data[0][2] = "Success!";

		data[1][0] = "MadhuraM";
		data[1][1] = "m123";
		data[1][2] = "Failed! please enter strong password";
		return data;
	}

	@AfterClass
	void tearDown() {
		driver.quit();
	}
}