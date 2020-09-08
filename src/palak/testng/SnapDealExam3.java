package palak.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import palak.base.PredefinedActions;

public class SnapDealExam3 extends PredefinedActions {
	WebDriver driver ; 
	WebDriverWait wait;
	String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
	WebElement emailOrNumber;
	Actions actions;
	
	@BeforeTest
	void setUp() {
		driver = start("https://www.snapdeal.com/");
	}
	
	
	public void login() {
		String expectedLoginText = "LOGIN";
		Assert.assertEquals(driver.getTitle(), expectedTitle);
	
		WebElement signInElement = driver.findElement(By.xpath("//div[@class='accountInner']"));
		actions = new Actions(driver);
		actions.moveToElement(signInElement).build().perform();
		
		WebElement loginElement = driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']/a"));
		String LoginText = loginElement.getText();
		Assert.assertEquals(LoginText, expectedLoginText);
		
		loginElement.click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
	}
	
	@Test(priority = 0, dataProvider="loginDataProvider")
	public void loginViaNumberOrEmail(String userName, String pass, String loggedInUserName) {
		login();
		driver.findElement(By.xpath("//div[@class='iframeSignin']/div[@class='userAuthIcons']/div/div[6]/form/div/input[@id='userName']")).sendKeys(userName);
		driver.findElement(By.xpath("//button[@id='checkUser']")).click();
		wait = new WebDriverWait(driver, 10);
		WebElement password = driver.findElement(By.xpath("//form[@id='loginUC']/input[@name='j_password']"));
		wait.until(ExpectedConditions.visibilityOf(password));
		
		WebElement loginButton = driver.findElement(By.xpath("//button[@id='submitLoginUC']"));
		
		//passwrd
		Assert.assertTrue(password.isDisplayed());
		
		//checkbox
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='keepLoginUC']")).isSelected());
		//Login
		Assert.assertTrue(loginButton.isDisplayed());
		//Login without Password 
		Assert.assertTrue(driver.findElement(By.xpath("//button[@id='sendOtpUC']")).isDisplayed());
		
		//Facebook 
		WebElement facebookButton = driver.findElement(By.xpath("//div[@class='iframeSignin']//div[6]//div[2]"));
		facebookButton.isDisplayed();
		
		//Google
		driver.findElement(By.xpath("(//div[@id='googleUserLogin']//span[text()='Google'])[1]")).isDisplayed();
		
		//Uncheck
		driver.findElement(By.xpath("//input[@id='keepLoginUC']/../label")).click();
		
		password.sendKeys(pass);
		
		Assert.assertFalse(driver.findElement(By.xpath("//div/input[@id='keepLoginUC']")).isSelected());
		loginButton.click();
		Assert.assertEquals(driver.getTitle(), expectedTitle);
		
		//Verifying Signin User
		String actualLoggedInUserNameText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']"))).getText();
		actions.moveToElement(driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']"))).build().perform();
		Assert.assertEquals(actualLoggedInUserNameText, loggedInUserName);
		
		//Logout for checking with Email id 
		driver.findElement(By.xpath("//a[@class='accountBtn rippleWhite sign logout-account']")).click();
	}
	
	@DataProvider(name="loginDataProvider")
	public Object[][] loginData(){
		Object[][] data = new Object[2][3];
		data[0][0] = "8109575444";
		data[0][1] = "SnapDeal@1234";
		data[0][2] = "Palak Soni";
		
		data[1][0] = "palaksoni1907@gmail.com";
		data[1][1] = "SnapDeal@1234";
		data[1][2] = "Palak Soni";
		return data;
	}
}
