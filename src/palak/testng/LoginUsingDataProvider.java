/*Assignment 14 - Login in AuotmationbyKrishna using Data provider*/
package palak.testng;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import palak.base.PredefinedActions;

public class LoginUsingDataProvider extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	@BeforeTest
	void setUp() {
		driver = start("http://automationbykrishna.com/");
	}

	@Test(dataProvider="loginDataProvider")
	public void login(String uname, String pwd , String msg) {
		driver.findElement(By.linkText("Registration")).click();	
		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@id='unameSignin']")));
		driver.findElement(By.xpath("//input[@id='unameSignin']")).sendKeys(uname);
		driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();
		
		Alert alert = driver.switchTo().alert();
		String actualMsg = alert.getText();
		Assert.assertEquals(actualMsg, msg);
		alert.accept();
	}
	
	@DataProvider(name="loginDataProvider")
	public Object[][] data(){
		Object[][] data = new Object[2][3];
		data[0][0] = "Palak";
		data[0][1] = "TomCruise1589";
		data[0][2] = "Success!";
		
		data[1][0] = "Palash";
		data[1][1] = "123";
		data[1][2] = "Failed! please enter strong password";
		return data;
	}
}
