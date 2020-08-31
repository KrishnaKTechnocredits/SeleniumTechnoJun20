package vaishnavi_CodingExams;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vaishnavi_Base.PredefinedAction;

public class SnapDeal extends PredefinedAction{
	
	WebDriver driver;
	WebDriverWait wait;
	Actions actions;
    @BeforeSuite
	void setUp() {
		driver = start("https://www.snapdeal.com/");
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
    
    //Test 2 and 3 - Verify User is able to Login/Sign In using mobile number and Email 
  	void verifySignUpBox(String UserName) throws InterruptedException {
  
   		wait = new WebDriverWait(driver, 5);

   		String mainWindow = driver.getWindowHandle();
   		
   		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='accountInner']"))); // Explicit wait																													 
   		actions.moveToElement(driver.findElement(By.xpath("//div[@class='accountInner']"))).build().perform();
   		
   		driver.findElement(By.xpath("//a[@href=\"https://www.snapdeal.com/login\"]")).isDisplayed();  		
   		driver.findElement(By.xpath("//a[@href=\"https://www.snapdeal.com/login\"]")).click();
   		
   		// Switch to another frame -login
   		driver.switchTo().frame("iframeLogin");
   		
   		//Verify if following elements are present in the frame
   		driver.findElement(By.xpath("//header[text()='login/sign up on snapdeal']")).isDisplayed();
		driver.findElement(By.id("userName")).isDisplayed();
		driver.findElement(By.id("checkUser")).isDisplayed();
		driver.findElement(By.id("fbUserLogin")).isDisplayed();
		driver.findElement(By.id("googleUserLogin")).isDisplayed();
		
   		//Enter basic details- to login
   		driver.findElement(By.id("userName")).sendKeys(UserName);
   		driver.findElement(By.id("checkUser")).click();
   		
   		//Validate if following elements are present in the sign-up form
   		driver.findElement(By.xpath("//input[@id='j_password_login_uc']")).isDisplayed();
		driver.findElement(By.xpath("//div/label[@for='keepLoginUC']")).isSelected();
		driver.findElement(By.xpath("//button[@id='submitLoginUC']")).isDisplayed();
		driver.findElement(By.xpath("//button[text()='Login Without Password']")).isDisplayed();
		driver.findElement(By.xpath("(//div[@id='fbUserLogin'])[3]")).isDisplayed();
		driver.findElement(By.xpath("(//div[@id='googleUserLogin'])[3]")).isDisplayed();
   	
   		driver.findElement(By.xpath("//input[@id='j_password_login_uc']")).sendKeys("Syntel23#"); // send password
   		//remove selection of keep in logedIn checkobox
   		driver.findElement(By.xpath("//div/label[@for='keepLoginUC']")).click();
   		
   		//click on login button
   		driver.findElement(By.xpath("//button[@id='submitLoginUC']")).click();
   		//Switch to main window
   		driver.switchTo().window(mainWindow);
   		//Get UserName
   		String ExpectedUserName = "vaishnavi";
   		
   		Thread.sleep(3000);
   		String ActualUserName = driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")).getText();	
   		assertEquals(ActualUserName, ExpectedUserName); // Validate Username with assertion
   		
   	}
    
    //Pre-requisite: User is a existing user and done few shopping on Snapdeal & no iteams are present in Cart.
   	// Test 5 -Verify existing user is able to see exisiting orders's list & 'Shopping Cart is empty!' text messages.
    @Test(priority=2)
    void verifyCartIsEmpty() {
    	try {
			verifySignUpBox("9545336556");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	actions.moveToElement(driver.findElement(By.xpath("//div[@class='accountInner']"))).build().perform();
		String[] options = { "Orders", "Shortlist", "SD Cash Rs. 0", "E-Gift Voucher", "LOGOUT" };
		ArrayList<WebElement> listofOptions = (ArrayList<WebElement>) driver.findElements(By.xpath("//div[@class='dropdownAccount']//a"));
		// Verifying options displayed after login
		for (int index = 0; index < options.length; index++) {
			assertEquals(listofOptions.get(index).getText(), options[index]);
		}
        // Click on orders from the list
		listofOptions.get(0).click();
		
		String expectedTitle = "Order History";
		String expectedUrl = "https://www.snapdeal.com/myorders";
		
		assertEquals(expectedTitle, driver.getTitle()); //Validating title
		assertEquals(expectedUrl, driver.getCurrentUrl()); //Validating url
		
		// OrderId
		String orderId = driver.findElement(By.xpath("//div[@class='orderId']")).getText();
		System.out.println(orderId);
		
		//Click on home button
		driver.findElement(By.xpath("//span[text()='Home']")).click();
		
		String homepageTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		assertEquals(homepageTitle, driver.getTitle()); //Validating home page title
		
		driver.findElement(By.xpath("//span[text()='Cart']")).click(); // Click on a cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart-categories-container']//p")));
		assertEquals(driver.findElement(By.xpath("//div//h3[text()='Shopping Cart is empty!']")).getText(), "Shopping Cart is empty!"); 
		
		List<WebElement> browserCategories = driver.findElements(By.xpath("//div[@class='cart-categories-list-wrapper row']//a"));
		assertEquals(driver.findElement(By.xpath("//div/p[text()='Browse Categories']")).getText(), "BROWSE CATEGORIES");
		assertEquals(browserCategories.size(), 20);
		
		driver.findElement(By.xpath("//div/a[text()='START SHOPPING NOW']")).isDisplayed();
		driver.findElement(By.xpath("//div/a[text()='START SHOPPING NOW']")).click();
		//Verify home page title
		assertEquals(homepageTitle, driver.getTitle());
    }
    
    @Test(dataProvider="loginDeatils" ,priority=1)
    void calling(String userName) {
    	try {
			verifySignUpBox(userName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	//LogOut from your account
   		actions.moveToElement(driver.findElement(By.xpath("//div[@class='accountInner']"))).build().perform();
		driver.findElement(By.xpath("//a[@class='accountBtn rippleWhite sign logout-account']")).click();
   
    }
    
    @AfterSuite
    void tearDown() {
    	driver.close();
    }
    
    @DataProvider(name="loginDeatils")
    public Object[][] dataProvider() {
    	Object[][] data = new Object[2][1];
    	data[0][0] = "9545336556";
    
    	data[1][0] = "VaishnaviVaidya0695@Yahoo.com";
    	
    	return data;
    }
}
