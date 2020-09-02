package aditi.exam3;

import java.io.*;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import aditi.base.PredefinedActions;



public class LogInTestCase extends PredefinedActions {

	WebDriver driver;
	WebDriverWait wait;
	Actions actions;

	@BeforeSuite
	void setUp() {
		driver = start("https://www.snapdeal.com/");
		wait = new WebDriverWait(driver, 15);
		actions = new Actions(driver);
	}

	public void validatePageTitle(String expectedTitleOfPage, String actualTitle) {
		System.out.println(actualTitle);
		Assert.assertEquals(expectedTitleOfPage, actualTitle);
		if (expectedTitleOfPage.equals(actualTitle))
			System.out.println("Test Pass - Page title matches - Title -> " + actualTitle);
	}

	@Test(dataProvider = "LoginDataProvider")
	void loginSnapDeal(String username, String otp, String signedInUserName) {
		String expectedHomePageTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		validatePageTitle(expectedHomePageTitle, driver.getTitle());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Sign In']")));
		actions.moveToElement(driver.findElement(By.xpath("//span[text()='Sign In']"))).build().perform();

		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'login')]")).isDisplayed());
		driver.findElement(By.xpath("//a[contains(text(),'login')]")).click();
		driver.switchTo().frame("loginIframe");

		Assert.assertTrue(driver.findElement(By.xpath("//header[text()='login/sign up on snapdeal']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='userName']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//button[@id='checkUser']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@id=\"fbUserLogin\"]/span[text()='Facebook'][1]")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@id=\"googleUserLogin\"]/span[text()='Google'][1]")).isDisplayed());

		driver.findElement(By.xpath("//input[@id='userName']")).sendKeys(username);
		driver.findElement(By.xpath("//button[@id='checkUser']")).click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//form[@id='loginOtpUC']//input[@placeholder='Code']")));
		driver.findElement(By.xpath("//form[@id='loginOtpUC']//input[@placeholder='Code']")).sendKeys(otp);
		driver.findElement(By.xpath("//button[@id='loginUsingOtp']")).click();

		String actualUserName = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")))
				.getText();

		Assert.assertEquals(actualUserName, signedInUserName);
	}

	// providing data of multiple users
	@DataProvider(name = "LoginDataProvider")
	public Object[][] loginData() {

		Object[][] data = new Object[1][3];

		data[0][0] = "7350058153";
		data[0][1] = "6154";
		data[0][2] = "Aditi Gajipara";

		// data[1][0] = "abc@gmail.com";
		// data[1][1] = "1122";
		// data[1][2] = "NA";

		return data;
	}
}