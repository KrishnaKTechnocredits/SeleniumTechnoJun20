package aashtha;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import aashtha.base.*;

public class SnapDealCodingExam3 extends PredefinedActions {
	WebDriver driver;
	Actions actions;
	WebDriverWait wait;
	String mainWindow;
	
	//Below method is triggered only once before the current suite - to open the chrome browser and initialize the actions and wait instances
	@BeforeSuite
	void setUp() {
		driver = start("https://www.snapdeal.com/");
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, 20);
	}
	
	//Below method is triggered only once after the current suite - to close the browser
	@AfterSuite
	void tearDown() {
		driver.close();
	}
	
	//below method logs in with given id and verifies the fields
	void snapdealLogin(String uname) {
		mainWindow = driver.getWindowHandle();
		verifyTitle();
		actions.moveToElement(driver.findElement(By.xpath("//div[@class='accountInner']"))).build().perform();
		driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
		WebElement username = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='userName']")));
		Assert.assertTrue(username.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//button[@id='checkUser']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-register-common']//div[@id='fbUserLogin']"))
				.isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='login-register-common']//div[@id='googleUserLogin']"))
						.isDisplayed());
		driver.findElement(By.xpath("//input[@id='userName']")).sendKeys(uname);
		driver.findElement(By.xpath("//button[@id='checkUser']")).click();
		driver.switchTo().window(mainWindow);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
		WebElement inputPassword = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='j_password_login_uc']")));
		Assert.assertTrue(inputPassword.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='keepLoginSignUp']")).isSelected());
		WebElement loginButton = driver.findElement(By.xpath("//button[@id='submitLoginUC']"));
		Assert.assertTrue(loginButton.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//button[@id='sendOtpUC']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='screen1']//div[@id='fbUserLogin']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='screen1']//div[@id='googleUserLogin']")).isDisplayed());
		driver.findElement(By.xpath("//label[@for='keepLoginUC']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='keepLoginSignUp']")).isSelected());
		inputPassword.sendKeys("abcdefg");
		Assert.assertNotEquals("abcdefg", "");
		loginButton.click();
		WebElement nameOnDashBoard = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")));
		Assert.assertEquals(nameOnDashBoard.getText(), "Kapil Kanojiya");
	}
	
	//below method verifies the title of the homepage
	void verifyTitle() {
		String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		Assert.assertEquals(driver.getTitle(), expectedTitle);
	}
	
	//below method is written to logout the user so that another login can be done on same browser window
	void logout() {
		actions.moveToElement(driver.findElement(By.xpath("//div[@class='accountInner']"))).build().perform();
		driver.findElement(By.xpath("//a[@class='accountBtn rippleWhite sign logout-account']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='accountInner']")));
	}
	
	//TestCase-2 : Verify User is able to Login/Sign In using mobile number 
	@Test(priority = 1)
	void verifyLoginUsingMobileNumber() {
		snapdealLogin("9876543210");
		logout();
	}
	
	//TestCase-3 : Verify User is able to Login/Sign In using Email address.
	@Test(priority = 2)
	void verifyLoginUsingEmail() {
		snapdealLogin("abcdefg@gmail.com");
		logout();
	}

	//TestCase-4 : Verify new user is able to see 'NO ORDERS AVAILABLE' & 'Shopping Cart is empty!' text messages.
	@Test(priority = 3)
	void noOrdersAndCartEmpty() {
		snapdealLogin("9876543210");
		actions.moveToElement(driver.findElement(By.xpath("//div[@class='accountInner']"))).build().perform();
		String[] optionsDisplayed = { "Orders", "Shortlist", "SD Cash Rs. 0", "E-Gift Voucher", "LOGOUT" };
		ArrayList<WebElement> listOfoptionsDisplayed = (ArrayList<WebElement>) driver
				.findElements(By.xpath("//div[@class='dropdownAccount']//a"));
		for (int index = 0; index < optionsDisplayed.length; index++) {
			Assert.assertEquals(listOfoptionsDisplayed.get(index).getText(), optionsDisplayed[index]);
		}
		listOfoptionsDisplayed.get(0).click();
		WebElement username = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='accountOrder']")));
		Assert.assertEquals(username.getText(), "MY ORDERS");
		Assert.assertEquals(driver.getTitle(), "Order History");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='noOrdersAvail']")).getText(),
				"NO ORDERS AVAILABLE");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.snapdeal.com/myorders");
		driver.findElement(By.xpath("//span[text()='Home']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[@data-pagename='HomePage']")));
		verifyTitle();
		driver.findElement(By.xpath("//div[@class='cartInner']")).click();
		WebElement browserCategoriesHeading = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='cart-categories-container']//p")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='cart-heading clearfix']/h3")).getText(),
				"Shopping Cart is empty!");
		List<WebElement> browserCategories = driver
				.findElements(By.xpath("//div[@class='cart-categories-list-wrapper row']//a"));
		Assert.assertEquals(browserCategoriesHeading.getText(), "BROWSE CATEGORIES");
		Assert.assertEquals(browserCategories.size(), 20);
		WebElement startShopping = driver.findElement(By.xpath("//div[@class='cart-buy-now-wrapper']/a"));
		Assert.assertTrue(startShopping.isDisplayed());
		startShopping.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[@data-pagename='HomePage']")));
		verifyTitle();
	}
}
