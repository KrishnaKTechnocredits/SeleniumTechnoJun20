package pranita;

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

import pranita.basic.PredefinedFunctions;

public class Assignment_14_DataProvider_Login extends PredefinedFunctions {
	WebDriver driver;

	@BeforeClass
	void setUp() {
		driver = start();
	}

	@Test(dataProvider = "loginDataProvider")
	public void loginUsingDataProvider(String uname,String password,String expectedText) {
		driver.findElement(By.linkText("Registration")).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement userNameElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']")));
		userNameElement.sendKeys(uname);
		driver.findElement(By.id("pwdSignin")).sendKeys(password);
		driver.findElement(By.id("btnsubmitdetails")).click();
		// Failed! please enter password
		// Success!
		Alert alert = driver.switchTo().alert();
		String actualText = alert.getText();
		alert.accept();

		Assert.assertEquals(actualText, expectedText);

	}
	
	@DataProvider(name="loginDataProvider")
	public Object[][]dataprovider(){
		
		Object[][] data = new Object[2][3];
		
		data[0][0]= "mkanani";
		data[0][1]="mkanani122345";
		data[0][2]= "Success!";
		
		data[1][0]= "ppuran";
		data[1][1]= "psp1234";
		data[1][2]= "Failed! please enter password";
		
		return data;
	}

	@AfterClass
	void tearDown() {
		driver.close();
	}

}
