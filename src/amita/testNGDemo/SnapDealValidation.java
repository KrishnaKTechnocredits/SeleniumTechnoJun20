package amita.testNGDemo;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import amita.base.PredefinedActions;

public class SnapDealValidation extends PredefinedActions{
	
	WebDriver driver;
	WebDriverWait wait;
	Actions actions;
	String mainPageTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
	
	@BeforeClass
	public void setUp() {
		driver = start("https://www.snapdeal.com/");
		wait = new WebDriverWait(driver, 15);
		actions = new Actions(driver);
	}
	
	//verify user is able to login snapdeal with email or mobile number 
	@Test (dataProvider = "loginDataProvider")
	public void loginUsingMobile(String userName,String password) {
		
		String mainWindow = driver.getWindowHandle();
		
		// Mouse hover on "Sign In" option.
		Assert.assertEquals(driver.getTitle(), mainPageTitle);		
		actions.moveToElement(driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']"))).build().perform();
		
		//Login option displayed and click on Login button
		driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']")).isDisplayed();
		driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iFrame[@id='loginIframe']")));
		driver.switchTo().frame(driver.findElement(By.xpath("//iFrame[@id='loginIframe']")));
		
		//verify "Login/Sign Up On Snapdeal", 'Mobile Number/ Email' textfield and 'continue' button, 'Facebook' & 'Google' options are displayed
		driver.findElement(By.xpath("//header[text()='login/sign up on snapdeal']")).isDisplayed();
		driver.findElement(By.xpath("//input[@id='userName']")).isDisplayed();
		driver.findElement(By.xpath("//button[@id='checkUser']")).isDisplayed();
		driver.findElement(By.xpath("(//div[@id='fbUserLogin']//span[text()='Facebook'])[1]")).isDisplayed();
		driver.findElement(By.xpath("(//div[@id='googleUserLogin']//span[text()='Google'])[1]")).isDisplayed();
		
		// enter email address and Click on 'continue' button.
		driver.findElement(By.xpath("//input[@id='userName']")).sendKeys(userName);
		driver.findElement(By.xpath("//button[@id='checkUser']")).click();
		
		//verify 'Password' textfield,'Keep me logged in' checkbox-checked,'Login' & 'Login Without Password' button and 'Facebook'&'Google' options are displayed.
		driver.findElement(By.xpath("//input[@id='j_password_login_uc']")).isDisplayed();	
		driver.findElement(By.xpath("//button[@id='submitLoginUC']")).isDisplayed();
		driver.findElement(By.xpath("//button[text()='Login Without Password']")).isDisplayed();
		driver.findElement(By.id("fbUserLogin")).isDisplayed();
		driver.findElement(By.id("googleUserLogin")).isDisplayed();
		
		//Unchecked "Keep me logged in" checkbox and enter 'password' and click on Login
		driver.findElement(By.xpath("//div/label[@for='keepLoginUC']")).click();
		driver.findElement(By.xpath("//input[@id='j_password_login_uc']")).sendKeys(password); 		
		driver.findElement(By.xpath("//button[@id='submitLoginUC']")).click();
		
		
		//verify User name on dashboard right corner.
		String expectedUserName = "Amita Rout";
		String actualUserName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']"))).getText();
		assertEquals(actualUserName, expectedUserName);
				
		verifyCart();
	}
	
	public void verifyCart() {
		
		//2. Mouse hover on 'User' name.
		actions.moveToElement(driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']"))).build().perform();
		
		String[] dropDownOptions = {"Orders", "Shortlist", "SD Cash", "E-Gift Voucher", "LOGOUT"};
		//2. User should be able to see above option on mentioned in dropDownOptions on hover :
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='dropdownAccount']//a"));
		System.out.println(list);
		for(int index=0; index<list.size(); index++) {
			list.get(index).getText().contains((dropDownOptions[index]));
		}
		
		//Click on 'Orders' option.Page title should be "Order History".
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dropdownAccount']//a[text()='Orders']"))).click();
		String expectedTitle = "Order History";
		assertEquals(expectedTitle, driver.getTitle());
		
		String expectedUrl = "https://www.snapdeal.com/myorders";
		assertEquals(expectedUrl, driver.getCurrentUrl());
		
		// click on 'Home' option.
		driver.findElement(By.xpath("//span[text()='Home']")).click();
		String homepageTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		assertEquals(homepageTitle, driver.getTitle());
		
		//Click on 'Cart' from menu heading.
		driver.findElement(By.xpath("//span[text()='Cart']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//h3[text()='Shopping Cart is empty!']")));
		assertEquals(driver.findElement(By.xpath("//div//h3[text()='Shopping Cart is empty!']")).getText(), "Shopping Cart is empty!");
		//Under 'BROWSE CATEGORIES' totle 20 items should be present.
		assertEquals(driver.findElements(By.xpath("//div[@class='cart-categories-list-wrapper row']//li")).size(),20);
		
		// Click on "START SHOPPING NOW" option.
		driver.findElement(By.xpath("//div/a[text()='START SHOPPING NOW']")).isDisplayed();
		driver.findElement(By.xpath("//div/a[text()='START SHOPPING NOW']")).click();
		assertEquals(homepageTitle, driver.getTitle());
		
		//Logout from signed in user
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Amita Rout']")));
		WebElement logoutelement = driver.findElement(By.xpath("//span[text() = 'Amita Rout']"));
		actions.moveToElement(logoutelement).build().perform();
		driver.findElement(By.xpath("//a[text() = 'Logout']")).click();		
	}
	
	@DataProvider(name = "loginDataProvider")
	public Object[][] loginDetails(){
		Object[][] data = new Object[2][2];
		
		data[0][0] = "amitarout84@gmail.com";
		data[0][1] = "**********";
		
		
		data[1][0] = "8981077775";
		data[1][1] = "*********";
		
		return data;
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.close();
	}
}
