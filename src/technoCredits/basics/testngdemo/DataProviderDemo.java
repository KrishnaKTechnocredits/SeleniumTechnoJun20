package technoCredits.basics.testngdemo;

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
import technoCredits.basics.base.PredefinedActions;

public class DataProviderDemo extends PredefinedActions{
	WebDriver driver;
	@BeforeClass
	public void setUp() {
		driver = start();
	}
	
	@Test(dataProvider="loginDataProvider")
	public void loginTest(String uname, String pwd, String expectedText) {
		//String expectedText = "Success!";
		driver.findElement(By.linkText("Registration")).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement usernameElement= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']")));
		usernameElement.sendKeys(uname);
		driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();
		Alert alert = driver.switchTo().alert();
		String actualText = alert.getText();
		alert.accept();
		Assert.assertEquals(actualText, expectedText);
	}
	
	@DataProvider(name="loginDataProvider")
	public String[][] dataprovider(){
		
		String[][] data = new String[2][3];
		data[0][0] = "mkanani";
		data[0][1] = "mkanani122345";
		data[0][2] = "Success!";
		
		data[1][0] = "hvegada";
		data[1][1] = "hvg12";
		data[1][2] = "Failed! please enter strong password";
		
		return data;
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
