package aditi;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import aditi.base.PredefinedActions;

public class LoginUsingDataProvider extends PredefinedActions {
	WebDriver driver;

	@BeforeClass
	void setUp() {
		this.driver = start();
	}

	@Test(dataProvider = "loginDataProvider")
	public void loginText(String uname, String pwd, String expectedText) {
		driver.findElement(By.linkText("Registration")).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement userName = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']")));
		userName.sendKeys(uname);
		driver.findElement(By.id("pwdSignin")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();
		Alert alert = driver.switchTo().alert();
		String actualText = alert.getText();
		alert.accept();
		Assert.assertEquals(actualText, expectedText);
	}

	@DataProvider(name = "loginDataProvider")
	public Object[][] dataProvider() {
		Object[][] data = new Object[2][3];
		data[0][0] = "Aditi";
		data[0][1] = "BerryAllen1234";
		data[0][2] = "Success!";

		data[1][0] = "Oliver Queen";
		data[1][1] = "1234";
		data[1][2] = "Failed! please enter strong password";

		return data;
	}

	@AfterClass
	public void closeBrowser() {
		driver.close();
	}
}