package aavruti;

import java.io.*;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import aavruti.base.PredefinedActions;

public class CodingExam3 extends PredefinedActions{

	WebDriver driver;
	WebDriverWait wait;
	Actions actions;
	
	String signInMouseHover, loginButton, loginFrameId, loginUserName, continueButton, facebookButton, googleButton, 
	loginPassword, keepLoggedInChecbox, signInLoginButton, loginWithOutPwdButton, loggedInUserName, dropDownAccount,
	ordersLink, orderHeader, noOrderAvail, bCrumHome, cartButton, shoppingCartEmptyHeader, totalBrowsingCategories,
	startShoppingNowButton, logoutButton, totalOrders, totalOrderIds;

	
	//Launching Browser
	@BeforeSuite
	void launchBrowser() {
		driver = start("https://www.snapdeal.com/");
		wait = new WebDriverWait(driver, 50);
		actions = new Actions(driver);
	}
	
	//Assigning Locators to variables from properties files
	@BeforeSuite
	void propertiesLoad() throws IOException {
		File file = new File(".//src/aavruti/config/SnapDealLocators.properties");
		FileInputStream inputStream = new FileInputStream(file);
		
		Properties properties = new Properties();
		properties.load(inputStream);
		
		signInMouseHover = properties.getProperty("signInMouseHover");
		loginButton = properties.getProperty("loginButton");
		loginFrameId = properties.getProperty("loginFrameId");
		loginUserName = properties.getProperty("loginUserName");
		facebookButton = properties.getProperty("facebookButton");
		googleButton = properties.getProperty("googleButton");
		loginPassword = properties.getProperty("loginPassword");
		keepLoggedInChecbox = properties.getProperty("keepLoggedInChecbox");
		signInLoginButton = properties.getProperty("signInLoginButton");
		loginWithOutPwdButton = properties.getProperty("loginWithOutPwdButton");
		loggedInUserName = properties.getProperty("loggedInUserName");
		continueButton = properties.getProperty("continueButton");
		dropDownAccount = properties.getProperty("dropDownAccount");
		ordersLink = properties.getProperty("ordersLink");
		orderHeader = properties.getProperty("orderHeader");
		noOrderAvail = properties.getProperty("noOrderAvail");
		bCrumHome = properties.getProperty("bCrumHome");
		cartButton = properties.getProperty("cartButton");
		shoppingCartEmptyHeader = properties.getProperty("shoppingCartEmptyHeader");
		totalBrowsingCategories = properties.getProperty("totalBrowsingCategories");
		startShoppingNowButton = properties.getProperty("startShoppingNowButton");
		logoutButton = properties.getProperty("logoutButton");
		totalOrders = properties.getProperty("totalOrders");
		totalOrderIds = properties.getProperty("totalOrderIds");
	}
	
	/*Login user using Mobile Number/Email-Id & Passwords and also validating fields 'Password' text field, 
	'Keep me logged in' checkbox-checked, 'Login' & 'Login Without Password' button and 'Facebook' & 'Google' options.*/
	@Test(dataProvider="LoginDataProvider")
	void loginSnapDeal(String username, String password, String signedInUserName, String newUserOrNot) {
				
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(signInMouseHover)));
		actions.moveToElement(driver.findElement(By.xpath(signInMouseHover))).build().perform();
		
		driver.findElement(By.xpath(loginButton)).isDisplayed();
		driver.findElement(By.xpath(loginButton)).click();
		driver.switchTo().frame(driver.findElement(By.xpath(loginFrameId)));
		
		driver.findElement(By.xpath("//header[text()='login/sign up on snapdeal']")).isDisplayed();
		driver.findElement(By.xpath(loginUserName)).isDisplayed();
		driver.findElement(By.xpath(continueButton)).isDisplayed();
		driver.findElement(By.xpath(facebookButton)).isDisplayed();
		driver.findElement(By.xpath(googleButton)).isDisplayed();
		
		driver.findElement(By.xpath(loginUserName)).sendKeys(username);
		driver.findElement(By.xpath(continueButton)).click();
		
		driver.findElement(By.xpath(loginPassword)).isDisplayed();
		driver.findElement(By.xpath(keepLoggedInChecbox)).isSelected();
		driver.findElement(By.xpath(signInLoginButton)).isDisplayed();
		driver.findElement(By.xpath(loginWithOutPwdButton)).isDisplayed();
		driver.findElement(By.xpath(facebookButton)).isDisplayed();
		driver.findElement(By.xpath(googleButton)).isDisplayed();		
		
		driver.findElement(By.xpath(loginPassword)).sendKeys(password);
		driver.findElement(By.xpath(keepLoggedInChecbox + "//..//label")).click();
		driver.findElement(By.xpath(signInLoginButton)).click();
		
		String actualText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loggedInUserName))).getText();
		
		Assert.assertEquals(actualText, signedInUserName);
		ordersVerifications(newUserOrNot);
	}
	
	//Verifying Order History for New User and Old User, shopping cart, window titles
	void ordersVerifications(String newUserOrNot) {
		
		actions.moveToElement(driver.findElement(By.xpath(loggedInUserName))).build().perform();
		
		String[] dropDownValues = {"Orders", "Shortlist", "SD Cash Rs. 0", "E-Gift Voucher", "LOGOUT"};
		
		List<WebElement> usersOptionsList = driver.findElements(By.xpath(dropDownAccount));
		
		for(int index=0;index<usersOptionsList.size();index++) {
			usersOptionsList.get(index).getText().equals(dropDownValues[index]);
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ordersLink))).click();
		String orderPageHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(orderHeader))).getText();
		
		Assert.assertEquals(driver.getTitle(), "Order History");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.snapdeal.com/myorders");
		Assert.assertEquals(orderPageHeader, "MY ORDERS");
		
		if(newUserOrNot.equals("Yes")) {		
			Assert.assertEquals(driver.findElement(By.xpath(noOrderAvail)).getText(), "NO ORDERS AVAILABLE");
		}
		else {
			Assert.assertEquals(driver.findElements(By.xpath(totalOrders)).size(),driver.findElements(By.xpath(totalOrderIds)).size());			
		}		
		
		driver.findElement(By.xpath(bCrumHome)).click();
		Assert.assertEquals(driver.getTitle(), "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com");
		
		driver.findElement(By.xpath(cartButton)).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(shoppingCartEmptyHeader)));	
		Assert.assertEquals(driver.findElements(By.xpath(totalBrowsingCategories)).size(),20);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(startShoppingNowButton)));
		actions.doubleClick(driver.findElement(By.xpath(startShoppingNowButton)));
		
		Assert.assertEquals(driver.getTitle(), "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com");
		
		actions.moveToElement(driver.findElement(By.xpath(loggedInUserName))).build().perform();
		driver.findElement(By.xpath(logoutButton)).click();
	}
	
	//providing data of multiple users
	@DataProvider(name="LoginDataProvider")
	public Object[][] loginData(){
		
		Object[][] data = new Object[2][4];
		
		data[0][0] = "8485033276";
		data[0][1] = "viplove01";
		data[0][2] = "Aavruti";
		data[0][3] = "Yes";
		
		data[1][0] = "harshit.agrawal9109@gmail.com";
		data[1][1] = "helloworld123";
		data[1][2] = "Harshit Agrawal";
		data[1][3] = "No";
		
		return data;
	}
	
	//closing the browser
	@AfterSuite
	void closeBrowser() {
		driver.quit();
	}
}
