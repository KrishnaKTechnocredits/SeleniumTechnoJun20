package anup.TestNGDemo;
/*Verify User is able to Login/Sign In using mobile number.*/

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import anup.basics.PredefinedActions;

public class SnapDealAExam3 extends PredefinedActions{
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void setup() {
		driver = start("https://www.snapdeal.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,15);
	}
	
	@Test(dataProvider = "SnapDealLogin")
	public void SnapDealValidations(String userName,String pwd,String expectedTitle) {
		//1. User should successfully navigated to snapdeal website.
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		
		//2. User should be able to see "LogIn" option.
		
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//span[text() = 'Sign In']"));
		actions.moveToElement(element).build().perform(); // Mouse hovering
		driver.findElement(By.xpath("//span[@class = 'accountBtn btn rippleWhite']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iFrame[@id='loginIframe']")));
		//switch to iFrame
		driver.switchTo().frame(driver.findElement(By.xpath("//iFrame[@id='loginIframe']")));
		
		//Entering username
		driver.findElement(By.xpath("//input[@id='userName']")).sendKeys(userName);
		//Click on login button
		driver.findElement(By.xpath("//button[@id='checkUser']")).click();
		//Enter password
		driver.findElement(By.xpath("//input[@id='j_password_login_uc']")).sendKeys(pwd);
		//Uncheck the Checkbox
		driver.findElement(By.xpath("//div/label[@for='keepLoginUC']")).click();
		//Click on Submit button
		driver.findElement(By.xpath("//button[@id='submitLoginUC']")).click();
		
		String expectedUserName = "Anup Kumar Sahoo";
		
		String actualUserName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']"))).getText();
		//Validating the name which is signed in
		Assert.assertEquals(actualUserName, expectedUserName);
		
		actions.moveToElement(driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']"))).build().perform();
		
		String[] dropDownOptions = {"Orders", "Shortlist", "SD Cash", "E-Gift Voucher", "LOGOUT"};
		
		List<WebElement> listofelements = driver.findElements(By.xpath("//div[@class='dropdownAccount']//li/q"));
		System.out.println(listofelements);
		for(int index=0; index<listofelements.size(); index++) {
			listofelements.get(index).getText().contains((dropDownOptions[index]));
			
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dropdownAccount']//a[text()='Orders']"))).click();
		
		String expectedoption = "Order History";
		Assert.assertEquals(expectedoption, driver.getTitle());
		Assert.assertEquals(3, driver.findElements(By.xpath("//div[@class='orderId']")).size());
		
		
		String expectedUrl = "https://www.snapdeal.com/myorders";
		Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
		driver.findElement(By.xpath("//span[text()='Home']")).click();
		String homepageTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		Assert.assertEquals(homepageTitle, driver.getTitle());
		
		driver.findElement(By.xpath("//span[text()='Cart']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//h3[text()='Shopping Cart is empty!']")));
		Assert.assertEquals(driver.findElement(By.xpath("//div//h3[text()='Shopping Cart is empty!']")).getText(), "Shopping Cart is empty!"); 
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='cart-categories-list-wrapper row']//li")).size(),20);
		
		driver.findElement(By.xpath("//div/a[text()='START SHOPPING NOW']")).isDisplayed();
		driver.findElement(By.xpath("//div/a[text()='START SHOPPING NOW']")).click();
		Assert.assertEquals(homepageTitle, driver.getTitle());
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Anup Kumar Sahoo']")));
		WebElement logoutelement = driver.findElement(By.xpath("//span[text() = 'Anup Kumar Sahoo']"));
		actions.moveToElement(logoutelement).build().perform();
		driver.findElement(By.xpath("//a[text() = 'Logout']")).click();		
	
	}
	
	@DataProvider(name = "SnapDealLogin")
	public Object[][] dataProvider(){
		Object[][] data = new Object[2][3];
		data[0][0] = "8981077774";
		data[0][1] = "********";
		data[0][2] = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		
		data[1][0] = "sahoo.anupkumar@gmail.com";
		data[1][1] = "********";
		data[1][2] = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		return data;
	}
	
	@AfterClass
	public void teardown() {
		driver.close();
	}
	
	

}
