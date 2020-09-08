package barkha;

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

import barkha_base.PredefinedFunctions;

public class DataProvider_Login extends PredefinedFunctions {
	WebDriver driver;
	
	@BeforeClass
	void setUp() {
		driver=start();
	}
	@AfterClass
	void tearDown() {
		driver.close();
	}
	@Test(dataProvider="loginUsingDataProvider")
	void loginUsingDataProvider(String userName,String passWord,String expectedText) {
		driver.findElement(By.linkText("Registration")).click();
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("unameSignin")));
		
		driver.findElement(By.id("unameSignin")).sendKeys(userName);
		driver.findElement(By.id("pwdSignin")).sendKeys(passWord);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Alert alert=driver.switchTo().alert();
		String actualText=alert.getText();
		alert.accept();
		
		Assert.assertEquals(actualText, expectedText);
	}
	@DataProvider(name="loginUsingDataProvider")
	 Object[][] dataProvider(){
		 Object[][] data=new Object[2][3];                    //2 rows 3 columns
		 
		 data[0][0]="Barkha";
		 data[0][1]="Barkha123";
		 data[0][2]="Success!";
		 
		 data[1][0]="Piyush";
		 data[1][1]="1234";
		 data[1][2]="Failed! please enter strong password";
		
		 return data;
	}
}