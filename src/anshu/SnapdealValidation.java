package anshu;

import java.util.ArrayList;
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
import org.testng.annotations.Test;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import anshu.base.PredefinedProperty;

public class SnapdealValidation extends PredefinedProperty {
    WebDriver driver;
	@BeforeMethod
	public void startUp() {
		driver = start("https://www.snapdeal.com");
	}
		@ Test(priority=4)
   public void newUserValidation() {
    	// verify title 
			String actualTitle = driver.getTitle();
			String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
			Assert.assertEquals(actualTitle, expectedTitle);
			

			// 2.  mover hover on signin
			WebDriverWait wait = new WebDriverWait(driver, 15);
			Actions actions = new Actions(driver);
			WebElement element = driver.findElement(By.xpath("//span[text()='Sign In']"));
			System.out.println(" 2. User are able to see Sign in Option");
			actions.moveToElement(element).build().perform();
			driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']")).click();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
			
			//3 validation of mobile/ continue /facebook /google.
			String mobileNText = driver.findElement(By.xpath("//input[@placeholder='Mobile Number/ Email']")).getAttribute("placeholder");
			String continueButtonText = driver.findElement(By.xpath("//button[text()='continue']")).getText();
			String faceBookText = driver.findElement(By.xpath("//div[@id='fbUserLogin']")).getText();
			String googleText =  driver.findElement(By.xpath("//div[@id='googleUserLogin']")).getText();
			
			List<String> visibleExpectedText = new ArrayList<String>();
			visibleExpectedText.add(mobileNText);
			//visibleExpectedText.add(continueButtonText);
			visibleExpectedText.add(faceBookText);
			visibleExpectedText.add(googleText);
			
			
			String ActualMbText = "Mobile Number/ Email" ;
			//String cntdText = "CONTINUE";
			String ActualFbText = "Facebook";
			String ActualGoogleText = "Google";
			
			List<String> visibleActualText = new ArrayList<String>();
			visibleActualText.add(ActualMbText);
			//visibleActualText.add(cntdText);
			visibleActualText.add(ActualFbText);
			visibleActualText.add(ActualGoogleText);
			Assert.assertEquals(visibleActualText, visibleExpectedText);
			
			System.out.println("User successfuly switch to login page.");
			
			// users should able to fill mobile no
			driver.findElement(By.xpath("//input[@placeholder='Mobile Number/ Email']")).sendKeys("8340359932");
			driver.findElement(By.xpath("//button[text()='continue']")).click();
			driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
			driver.findElement(By.xpath("//button[@id='loginUsingOtp']")).click();
			
			driver.switchTo().defaultContent();
			System.out.println(driver.getCurrentUrl());
			
			WebElement signin = driver.findElement(By.xpath("//i[@class='sd-icon sd-icon-user']"));
			actions.moveToElement(signin).build().perform();
			
			
			String usernameText = driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")).getText();
			System.out.println(usernameText);
			String ActualText = "Anshu";
			if(usernameText.contains(ActualText)) {
				System.out.println("Username found : TestPass");
			}else {
				System.out.println("Test Fail : Not found Username");
			}
			System.out.println(usernameText);
			
			// get all option name in login page
			int row = driver.findElements(By.xpath("//div[@class='dropdownAccount']/div[@class='accountList']/ul/li")).size();
		List<WebElement> list  = 	driver.findElements(By.xpath("//div[@class='dropdownAccount']/div[@class='accountList']/ul/li"));
			for(int index = 1 ; index <=row ; index++ ) {
				String currentText = driver.findElement(By.xpath("//div[@class='dropdownAccount']/div[@class='accountList']/ul/li["+index+"]/a")).getText();
				 if(currentText.contains("Orders")) {
					System.out.println(" user are able to see orders");
				 }else if(currentText.contains("shortlist")) {
					System.out.println(" users are able to see shoertlist");
				 }else if(currentText.contains("SD Cash")) {
					 System.out.println("users are able to see SDCash");
				 }else if(currentText.contains("E-Gift")) {
					System.out.println("users are able to see E-Gift voucher");
					 
				 }else if(currentText.contains("Logout")) {
					 System.out.println("Users are able to see logout");
				 }else {
					  System.out.println(" users are not able to see these items.");
				 }
			}
			// go on order 
			driver.findElement(By.xpath("//div[@class='dropdownAccount']/div[@class='accountList']/ul/li[\"+index+\"]/a")).click();
			String exptText = driver.findElement(By.xpath("//span[@class='accountOrder']")).getText();
			if(exptText.contains("No Orders are available")) {
				System.out.println(" users are able to see no No orders are avaialble");
			}else {
				System.out.println("Users are not able to see order page.");
			}
			if(driver.getCurrentUrl().contains("https://www.snapdeal.com/myorders")) {
				System.out.println("Users on orders page.");
			}else {
				System.out.println(" TestFail : user are not navigate to orders page.");
			}
			// go back to dashboard
			driver.findElement(By.xpath("//span[text()='Home']")).click();
			if(driver.getCurrentUrl().equals("https://www.snapdeal.com")){
				System.out.println(" Users return to DashBoard.");
			}else {
				System.out.println(" Users are not on same page");
			}
			
			// go to cart
			
			driver.findElement(By.xpath("//span[text()='Cart']")).click();
			
			String cartText = driver.findElement(By.xpath("//p[@class='text-center browse-category-text']")).getText();
			if(cartText.contains("BROWSE CATEGORIES")) {
					System.out.println(" Users is on cart page. ");
			}
			
			 int count = driver.findElements(By.xpath("//div[@class='cart-categories-list-wrapper row']/ul/li[@class='col-xs-6']/a")).size();
			 System.out.println(" Total "+count+" items are present in cart page.");
			 
			 driver.findElement(By.xpath("//a[@class='btn btn-xl rippleWhite cart-shopping-button col-xs-7']")).click();
			 if(driver.getCurrentUrl().equals("https://www.snapdeal.com/")) {
				 System.out.println(" users are enjoying current shopping");
			 }
			
			
		
			
			
    }
	//	@ Test(priority =1)
		public void byEmailIdValidation() {
			// verify page title
			String actualTitle = driver.getTitle();
			String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
			Assert.assertEquals(actualTitle, expectedTitle);

			// mover hover on signin
			WebDriverWait wait = new WebDriverWait(driver, 15);
			Actions actions = new Actions(driver);
			WebElement element = driver.findElement(By.xpath("//span[text()='Sign In']"));
			System.out.println(" 2. User are able to see Sign in Option");
			actions.moveToElement(element).build().perform();
			driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']")).click();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
			
			//2 validation of mobile/ continue /facebook /google.
			String mobileNText = driver.findElement(By.xpath("//input[@placeholder='Mobile Number/ Email']")).getAttribute("placeholder");
			String continueButtonText = driver.findElement(By.xpath("//button[text()='continue']")).getText();
			String faceBookText = driver.findElement(By.xpath("//div[@id='fbUserLogin']")).getText();
			String googleText =  driver.findElement(By.xpath("//div[@id='googleUserLogin']")).getText();
			
			List<String> visibleExpectedText = new ArrayList<String>();
			visibleExpectedText.add(mobileNText);
			//visibleExpectedText.add(continueButtonText);
			visibleExpectedText.add(faceBookText);
			visibleExpectedText.add(googleText);
			
			
			String ActualMbText = "Mobile Number/ Email" ;
			//String cntdText = "CONTINUE";
			String ActualFbText = "Facebook";
			String ActualGoogleText = "Google";
			
			List<String> visibleActualText = new ArrayList<String>();
			visibleActualText.add(ActualMbText);
			//visibleActualText.add(cntdText);
			visibleActualText.add(ActualFbText);
			visibleActualText.add(ActualGoogleText);
			Assert.assertEquals(visibleActualText, visibleExpectedText);
			
			System.out.println(" 3 .User successfuly switch to login page.");
			
			// users should able to fill mobile no
			driver.findElement(By.xpath("//input[@placeholder='Mobile Number/ Email']")).sendKeys("ysuditellu@gmail.com");
			driver.findElement(By.xpath("//button[text()='continue']")).click();
			driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
			WebElement password= driver.findElement(By.xpath("//input[@id='j_password_login_uc']"));
			if(password.getAttribute("placeholder").contains("Password")) {
				System.out.println("Users are able to see password");
			}else {
				System.out.println("users are not able to see password");
			}
			password.sendKeys("Looser123#");
			
			WebElement loggedin = driver.findElement(By.xpath("//label[@for='keepLoginUC']/span"));
			if(loggedin.getText().contains("Keep me logged in")) {
				System.out.println(" users are able to see Keep me checkbox");
			}else {
				System.out.println(" users are not able to see Checkbox");
			}
			loggedin.click();
			
			WebElement login = driver.findElement(By.xpath("//button[@id='submitLoginUC']"));
			if(login.getText().contains("LOGIN")) {
				System.out.println(" Users are able to see loginbutton");
			}else {
				System.out.println(" users are not able to see login button");
			}
			WebElement loginPass = driver.findElement(By.xpath("//button[@id='sendOtpUC']"));
			if(loginPass.getText().contains("LOGIN WITHOUT PASSWORD")) {
				System.out.println(" Users are able tro see login without password");
			}else {
				System.out.println("users are not able to see login without password");
			}
			
			login.click();
			driver.switchTo().defaultContent(); 
			System.out.println(" users are succesffully login snapdeal account");
			
			System.out.println(driver.getCurrentUrl());
			if(driver.getCurrentUrl().contains("https://www.snapdeal.com/")) {
				System.out.println(" Users comes to dashboard of Sanpdeal page.");
			}

		}
	//	@ Test(priority = 2)
		// verify title
		public void byMobileNoValidation() {
			String actualTitle = driver.getTitle();
			String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
			Assert.assertEquals(actualTitle, expectedTitle);

			// mover hover on signin
			WebDriverWait wait = new WebDriverWait(driver, 15);
			Actions actions = new Actions(driver);
			WebElement element = driver.findElement(By.xpath("//span[text()='Sign In']"));
			System.out.println(" 2. User are able to see Sign in Option");
			actions.moveToElement(element).build().perform();
			driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']")).click();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
			
			//2 validation of mobile/ continue /facebook /google.
			String mobileNText = driver.findElement(By.xpath("//input[@placeholder='Mobile Number/ Email']")).getAttribute("placeholder");
			String continueButtonText = driver.findElement(By.xpath("//button[text()='continue']")).getText();
			String faceBookText = driver.findElement(By.xpath("//div[@id='fbUserLogin']")).getText();
			String googleText =  driver.findElement(By.xpath("//div[@id='googleUserLogin']")).getText();
			
			List<String> visibleExpectedText = new ArrayList<String>();
			visibleExpectedText.add(mobileNText);
			//visibleExpectedText.add(continueButtonText);
			visibleExpectedText.add(faceBookText);
			visibleExpectedText.add(googleText);
			
			
			String ActualMbText = "Mobile Number/ Email" ;
			//String cntdText = "CONTINUE";
			String ActualFbText = "Facebook";
			String ActualGoogleText = "Google";
			
			List<String> visibleActualText = new ArrayList<String>();
			visibleActualText.add(ActualMbText);
			//visibleActualText.add(cntdText);
			visibleActualText.add(ActualFbText);
			visibleActualText.add(ActualGoogleText);
			Assert.assertEquals(visibleActualText, visibleExpectedText);
			
			System.out.println(" 3 .User successfuly switch to login page.");
			
			// users should able to fill mobile no
			driver.findElement(By.xpath("//input[@placeholder='Mobile Number/ Email']")).sendKeys("9769476916");
			driver.findElement(By.xpath("//button[text()='continue']")).click();
			driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
			
			WebElement password= driver.findElement(By.xpath("//input[@id='j_password_login_uc']"));
			if(password.getAttribute("placeholder").contains("Password")) {
				System.out.println("Users are able to see password");
			}else {
				System.out.println(" users are not able to see password.");
			}
			password.sendKeys("Looser123#");
			
			WebElement loggedin = driver.findElement(By.xpath("//label[@for='keepLoginUC']/span"));
			if(loggedin.getText().contains("Keep me logged in")) {
				System.out.println(" users are able to see Keep me checkbox");
			}else {
				System.out.println(" userts are not able to see checkbox");
			}
			loggedin.click();
			
			WebElement login = driver.findElement(By.xpath("//button[@id='submitLoginUC']"));
			if(login.getText().contains("LOGIN")) {
				System.out.println(" Users are able to see loginbutton");
			}else {
				System.out.println("users are not able to see login button");
			}
			WebElement loginPass = driver.findElement(By.xpath("//button[@id='sendOtpUC']"));
			if(loginPass.getText().contains("LOGIN WITHOUT PASSWORD")) {
				System.out.println(" Users are able tro see login without password");
			}else {
				System.out.println(" users are not able to see password.");
			}
			
			login.click();
			driver.switchTo().defaultContent();
			
			System.out.println(driver.getCurrentUrl());
			if(driver.getCurrentUrl().contains("https://www.snapdeal.com/")) {
				System.out.println(" Users comes to dashboard of Sanpdeal page.");
			}else {
				System.out.println(" not able to return dashboard");
			}

		}
		//@Test(priority =3)
		public void byExistingUser() {
			// verify title
			String actualTitle = driver.getTitle();
			String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
			Assert.assertEquals(actualTitle, expectedTitle);

			// mover hover on signin
			WebDriverWait wait = new WebDriverWait(driver, 15);
			Actions actions = new Actions(driver);
			WebElement element = driver.findElement(By.xpath("//span[text()='Sign In']"));
			System.out.println(" 2. User are able to see Sign in Option");
			actions.moveToElement(element).build().perform();
			driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']")).click();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
			
			//2 validation of mobile/ continue /facebook /google.
			String mobileNText = driver.findElement(By.xpath("//input[@placeholder='Mobile Number/ Email']")).getAttribute("placeholder");
			String continueButtonText = driver.findElement(By.xpath("//button[text()='continue']")).getText();
			String faceBookText = driver.findElement(By.xpath("//div[@id='fbUserLogin']")).getText();
			String googleText =  driver.findElement(By.xpath("//div[@id='googleUserLogin']")).getText();
			
			List<String> visibleExpectedText = new ArrayList<String>();
			visibleExpectedText.add(mobileNText);
			//visibleExpectedText.add(continueButtonText);
			visibleExpectedText.add(faceBookText);
			visibleExpectedText.add(googleText);
			
			
			String ActualMbText = "Mobile Number/ Email" ;
			//String cntdText = "CONTINUE";
			String ActualFbText = "Facebook";
			String ActualGoogleText = "Google";
			
			List<String> visibleActualText = new ArrayList<String>();
			visibleActualText.add(ActualMbText);
			//visibleActualText.add(cntdText);
			visibleActualText.add(ActualFbText);
			visibleActualText.add(ActualGoogleText);
			Assert.assertEquals(visibleActualText, visibleExpectedText);
			
			System.out.println(" 3 .User successfuly switch to login page.");
			
			// users should able to fill mobile no
			driver.findElement(By.xpath("//input[@placeholder='Mobile Number/ Email']")).sendKeys("ysuditellu@gmail.com");
			driver.findElement(By.xpath("//button[text()='continue']")).click();
			driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
			WebElement password= driver.findElement(By.xpath("//input[@id='j_password_login_uc']"));
			if(password.getAttribute("placeholder").contains("Password")) {
				System.out.println("Users are able to see password");
			}else {
				System.out.println(" not able to see password");
			}
			password.sendKeys("Looser123#");
			
			WebElement loggedin = driver.findElement(By.xpath("//label[@for='keepLoginUC']/span"));
			if(loggedin.getText().contains("Keep me logged in")) {
				System.out.println(" users are able to see Keep me checkbox");
			}else {
				System.out.println(" not able to see checkbox.");
			}
			loggedin.click();
			
			WebElement login = driver.findElement(By.xpath("//button[@id='submitLoginUC']"));
			if(login.getText().contains("LOGIN")) {
				System.out.println(" Users are able to see loginbutton");
			}else {
				System.out.println("users are not able to see login");
			}
			WebElement loginPass = driver.findElement(By.xpath("//button[@id='sendOtpUC']"));
			if(loginPass.getText().contains("LOGIN WITHOUT PASSWORD")) {
				System.out.println(" Users are able tro see login without password");
			}else {
				System.out.println("users are not able to see login without password");
			}
			
			login.click();
			driver.switchTo().defaultContent();
			
			System.out.println(driver.getCurrentUrl());
			if(driver.getCurrentUrl().contains("https://www.snapdeal.com/")) {
				System.out.println(" Users comes to dashboard of Sanpdeal page.");
			}else {
				System.out.println(" usets are not able to back to snapdeal page.");
			}

			
			
			
			WebElement signin = driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']"));
			System.out.println("  User are able to see Sign in Option");
			actions.moveToElement(signin).build().perform();
			
			
			// get all optin on mouse hover
			int row = driver.findElements(By.xpath("//div[@class='dropdownAccount']/div[@class='accountList']/ul/li")).size();
			List<WebElement> list  = 	driver.findElements(By.xpath("//div[@class='dropdownAccount']/div[@class='accountList']/ul/li"));
				for(int index = 1 ; index <=row ; index++ ) {
					String currentText = driver.findElement(By.xpath("//div[@class='dropdownAccount']/div[@class='accountList']/ul/li["+index+"]/a")).getText();
					 if(currentText.contains("Orders")) {
						System.out.println(" user are able to see orders");
					 }else if(currentText.contains("shortlist")) {
						System.out.println(" users are able to see shoertlist");
					 }else if(currentText.contains("SD Cash")) {
						 System.out.println("users are able to see SDCash");
					 }else if(currentText.contains("E-Gift")) {
						System.out.println("users are able to see E-Gift voucher");
						 
					 }else if(currentText.contains("Logout")) {
						 System.out.println("Users are able to see logout");
					 }else {
						  System.out.println(" users are not able to see these items.");
					 }
				}
				//click on order 
				driver.findElement(By.xpath("//div[@class='dropdownAccount']/div[@class='accountList']/ul/li[\"+index+\"]/a")).click();
				 
				// click on order detail
				driver.findElement(By.xpath("//span[@class='OrdDetails sd-tour']")).click();
				String orderId = driver.findElement(By.xpath("//span[@class='orderNo subOrdId']")).getText();
				System.out.println(orderId);
				
				// back to home page
				driver.findElement(By.xpath("//span[@itemprop='title']")).click();
				System.out.println("users are able to naviogate back to dashboard");
				
				// go top cart
				driver.findElement(By.xpath("//span[text()='Cart']")).click();
				
				String cartText = driver.findElement(By.xpath("//p[@class='text-center browse-category-text']")).getText();
				if(cartText.contains("BROWSE CATEGORIES")) {
						System.out.println(" Users is on cart page. ");
				}
				
				 int count = driver.findElements(By.xpath("//div[@class='cart-categories-list-wrapper row']/ul/li[@class='col-xs-6']/a")).size();
				 System.out.println(" Total "+count+" items are present in cart page.");
				 
				 driver.findElement(By.xpath("//a[@class='btn btn-xl rippleWhite cart-shopping-button col-xs-7']")).click();
				 if(driver.getCurrentUrl().equals("https://www.snapdeal.com/")) {
					 System.out.println(" users are enjoying current shopping");
				 }
			
			
		}
	@AfterMethod
	public void tearDown() {
			driver.close();
		}

}