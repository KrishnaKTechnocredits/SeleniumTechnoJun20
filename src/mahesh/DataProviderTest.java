package mahesh;

import mahesh.base.PredefinedActions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class DataProviderTest extends PredefinedActions{
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeMethod
	void setUp() {
		driver = start();
		wait = new WebDriverWait(driver, 20);
	}
	
	@AfterMethod
	void tearDown() {
		driver.close();
	}
	
	@Test(dataProvider = "LoginData")
	void loginWithMultipleData(String userName, String pass, String expectedMessage) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='registration2']"))).click();
		driver.findElement(By.xpath("//input[@id='unameSignin']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(pass);
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();
		Alert alert = driver.switchTo().alert();
		String actualMessage = alert.getText();
		alert.accept();
		Assert.assertEquals(actualMessage, expectedMessage);
	}
	
	@DataProvider(name="LoginData")
	public Object[][] loginData(){
		Object[][] data = new Object[3][3];
		data[0][0] = "Mahesh";
		data[0][1] = "Mahesh@123";
		data[0][2] = "Success!";
		data[1][0] = "Kumavat";
		data[1][1] = "Kumavat@123";
		data[1][2] = "Success!";
		data[2][0] = "123";
		data[2][1] = "123";		
		data[2][2] = "Failed! please enter strong password";
		return data;
	}

}
