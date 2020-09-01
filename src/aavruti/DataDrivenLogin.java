//Login Data validation using Data Driven Approach

package aavruti;

import aavruti.base.PredefinedActions;
import aavruti.utility.PropertyFileOperation;

import java.io.IOException;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class DataDrivenLogin extends PredefinedActions {

	WebDriver driver;
	WebDriverWait wait;
	
	String registrationTab, loginHeader, userNameInput, passwordInput, submitButton;
	
	@BeforeSuite
	void readProperty() throws IOException {		
		PropertyFileOperation prop = new PropertyFileOperation(".//src//aavruti//config//RegistrationLocator.properties");
		
		registrationTab = prop.propRead("registrationTab");
		loginHeader = prop.propRead("loginHeader");
		userNameInput = prop.propRead("userNameInput");
		passwordInput = prop.propRead("passwordInput");
		submitButton = prop.propRead("submitButton");
	}
	
	@BeforeTest
	void setUp() {
		driver = start();
		wait = driverWait();
	}	
	
	@BeforeClass
	void navigateToRegistration() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(registrationTab))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loginHeader))).isDisplayed();
	}
	
	//Login data alert message validation
	@Test(dataProvider="LoginCredentials")
	void validateLogin(String userName, String password, String alertMessage) {
		
		driver.findElement(By.xpath(userNameInput)).sendKeys(userName);
		driver.findElement(By.xpath(passwordInput)).sendKeys(password);
		driver.findElement(By.xpath(submitButton)).click();
		
		Alert alert = driver.switchTo().alert();		
		Assert.assertEquals(alert.getText(), alertMessage);
		alert.accept();
		
		driver.findElement(By.xpath(userNameInput)).clear();
		driver.findElement(By.xpath(passwordInput)).clear();
	}
	
	//Login data info
	@DataProvider(name="LoginCredentials")
	Object[][] loginInfo() {
		
		Object[][] loginData = new Object[4][3];
		
		loginData[0][0] = "Aavruti";
		loginData[0][1] = "aavruti";
		loginData[0][2] = "Failed! please enter strong password";
		
		loginData[1][0] = "Aavruti";
		loginData[1][1] = "";
		loginData[1][2] = "Failed! please enter password";
		
		loginData[2][0] = "";
		loginData[2][1] = "aavruti";
		loginData[2][2] = "Failed! please enter username";
		
		loginData[3][0] = "Aavruti";
		loginData[3][1] = "aavruti@123";
		loginData[3][2] = "Success!";
		
		return loginData;
	}
}