package shruti;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import shruti.predefinedActionspkg.PtrdefinedActions;

public class RegistrationDataProvider extends PtrdefinedActions {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeTest
	void setUp(){
		driver = start();
		wait = new WebDriverWait(driver, 15);
	}
	@DataProvider(name="registrationData")
	public String[][] data(){
		String[][] data = new String[2][3];
		data[0][0]= "Shruti";
		data[0][1]= "Shruti1205";
		data[0][2] = "Success!";
		
		data[1][0]= "Ankit";
		data[1][1]= "1qaz";
		data[1][2] = "Failed! please enter strong password";
		return data;
	}
	@Test(dataProvider = "registrationData")
	public void registration(String name, String pwd,String msgText){
		driver.findElement(By.linkText("Registration")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='unameSignin']")));
		driver.findElement(By.xpath("//input[@id='unameSignin']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();

		Alert alert = driver.switchTo().alert();
		String actualMsgText = alert.getText();
		Assert.assertEquals(actualMsgText, msgText);
		alert.accept();
		
	}

}
