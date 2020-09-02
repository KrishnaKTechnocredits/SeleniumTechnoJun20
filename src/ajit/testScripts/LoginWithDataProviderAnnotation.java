package ajit.testScripts;

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

import ajit.base.PredefinedActions;

public class LoginWithDataProviderAnnotation extends PredefinedActions {
	WebDriver driver;

	@BeforeClass
	void setUp() {
		this.driver = start();
	}

	@Test(dataProvider = "loginDataProvider")
	public void loginText(String userName, String passwod, String expectedText) {
		driver.findElement(By.linkText("Registration")).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement userNameElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']")));
		userNameElement.sendKeys(userName);
		driver.findElement(By.id("pwdSignin")).sendKeys(passwod);
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();
		Alert alert = driver.switchTo().alert();
		String actualText = alert.getText();
		alert.accept();
		Assert.assertEquals(actualText, expectedText);
	}

	@DataProvider(name = "loginDataProvider")
	public String[][] dataProvider() {
		String[][] data = new String[2][3];
		data[0][0] = "Ajit";
		data[0][1] = "Ajit@123456";
		data[0][2] = "Success!";
		
		data[1][0] = "KumarAjju";
		data[1][1] = "Ajit1";
		data[1][2] = "Failed! please enter strong password";

		return data;
	}

	@AfterClass
	public void closeBrowser() {
		driver.close();
	}

}
