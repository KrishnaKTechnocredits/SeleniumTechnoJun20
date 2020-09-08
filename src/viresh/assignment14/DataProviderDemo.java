package viresh.assignment14;

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

import viresh.utility.SetUp;

public class DataProviderDemo extends SetUp {

	WebDriver driver;

	@BeforeClass
	public WebDriver setUp() {
		return driver = start();
	}

	@Test(dataProvider = "loginDataProvider")
	public void loginTest(String uName, String pwd, String expectedAlertText) {
		driver.findElement(By.xpath("//a[@id= 'registration2']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement userName = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']")));
		userName.sendKeys(uName);
		driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();

		Alert alert = driver.switchTo().alert();
		String ActualAlertText = alert.getText();
		alert.accept();

		Assert.assertEquals(expectedAlertText, ActualAlertText);
	}

	@DataProvider(name = "loginDataProvider")
	public Object[][] dataProvider() {
		Object[][] data = new Object[2][3];
		data[0][0] = "vjangam";
		data[0][1] = "abdc@1234";
		data[0][2] = "Success!";

		data[1][0] = "technocredits";
		data[1][1] = "a@1";
		data[1][2] = "Failed! please enter strong password";

		return data;
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
