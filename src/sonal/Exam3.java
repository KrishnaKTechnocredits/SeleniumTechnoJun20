package sonal;

public class Exam3 {
	package TestNgBasics;

	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	public class Exam3 {

		WebDriver driver;

		public WebDriver Start(String url) {

			System.setProperty("webdriver.chrome.driver", "E:/NewWorkSpace/SeleniumBasics1/chromedriver.exe");

			WebDriver driver = new ChromeDriver();

			driver.get(url);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

			driver.manage().window().maximize();

			return driver;
		}

		@BeforeClass
		public void setUp() {

			driver = Start("https://www.snapdeal.com/");
		}

		@AfterClass
		public void tearDown() {

			driver.close();
		}

		@Test
		public void logInVerificationCellNum() throws InterruptedException {
			String expTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
			String actTitle = driver.getTitle();

			// verify titke of snapdeal
			if (actTitle.equals(expTitle)) {
				System.out.println("Snapdeal.com successfully opened");
			} else
				System.out.println("It is not Snapdeal.com");

			WebElement element = driver.findElement(By.xpath("//div[@class='accountInner']"));

			// action class for mousehover
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();

			if (driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']/a")).isDisplayed()) {
				System.out.println("Login button is displayed");
			} else
				System.out.println("Check if you are logged in");

			driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']/a")).click();

			Thread.sleep(6000);

			// Navigate to iframe
			loginScreenVerification();
			driver.findElement(By.xpath("//input[@id='userName']")).sendKeys("9075166208");
			driver.findElement(By.xpath("//button[@id='checkUser']")).click();

			SignUpscreenverification();
			driver.findElement(By.xpath("//form[@id='userSignupForm']/input[@name='j_password']")).sendKeys("A67Gyuyu");
			driver.findElement(By.xpath("//div[@class='loginCheckbox keepLoginSignUp']")).click();
			driver.navigate().to("https://www.snapdeal.com");
		

		}
		
		@Test
		public void logInVerificationEmail() throws InterruptedException {
			String expTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
			String actTitle = driver.getTitle();

			// verify titke of snapdeal
			if (actTitle.equals(expTitle)) {
				System.out.println("Snapdeal.com successfully opened");
			} else
				System.out.println("It is not Snapdeal.com");

			WebElement element = driver.findElement(By.xpath("//div[@class='accountInner']"));

			// action class for mousehover
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();

			if (driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']/a")).isDisplayed()) {
				System.out.println("Login button is displayed");
			} else
				System.out.println("Check if you are logged in");

			driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']/a")).click();

			Thread.sleep(6000);

			// Navigate to iframe
			loginScreenVerification();
			driver.findElement(By.xpath("//input[@id='userName']")).sendKeys("aditiina@gmail.com");
			driver.findElement(By.xpath("//button[@id='checkUser']")).click();

			SignUpscreenverification();
			driver.findElement(By.xpath("//form[@id='userSignupForm']/input[@name='j_password']")).sendKeys("A67Gyuyu");
			driver.findElement(By.xpath("//div[@class='loginCheckbox keepLoginSignUp']")).click();

		}
		
		

		public void loginScreenVerification() {
			driver.switchTo().frame("loginIframe");
			// Verify form text

			String formText = driver.findElement(By.xpath("//div[@class='login-register-common']")).getText();
			System.out.println(formText);
			String expformText = "Please provide your Mobile Number or Email to Login/ Sign Up on SnapdealCONTINUEor Login UsingFacebookGoogle";
			// Assert.assertEquals(formText, expformText);
			if (driver.findElement(By.xpath("//input[@id='userName']")).isDisplayed()) {
				System.out.println("Enter Mobile number/Email field is displayed");
			} else
				System.out.println("Its a wrong window");
			if (driver.findElement(By.xpath("//button[@id='checkUser']")).isDisplayed()) {
				System.out.println("Continue button is displayed");
			} else
				System.out.println("Its a wrong window");
			if (driver
					.findElement(By
							.xpath("//div[@class='userAuth-card']/div[@class='login-register-common']/div[@class='loginSocialBtn col-xs-24 reset-padding marT20 marB20']/div[@id='fbUserLogin']"))
					.isDisplayed()) {
				System.out.println("Facebook button is displayed");
			} else
				System.out.println("Its a wrong window");
			if (driver
					.findElement(By
							.xpath("//div[@class='userAuth-card']/div[@class='login-register-common']/div[@class='loginSocialBtn col-xs-24 reset-padding marT20 marB20']/div[@id='googleUserLogin']"))
					.isDisplayed()) {
				System.out.println("Google button is displayed");
			} else
				System.out.println("Its a wrong window");

		}

		public void SignUpscreenverification() {
			// driver.switchTo().frame("loginIframe");
			
			System.out.println(driver.findElement(By.xpath("//div[@class='screen1']")).getText());

			if (driver.findElement(By.xpath("//form[@id='userSignupForm']/input[@name='j_password']")).isDisplayed()) {
				System.out.println("Password field is displayed");
			} else
				System.out.println("Password Field not present.Its a wrong window");

			if (driver.findElement(By.xpath("//input[@id='keepLoginSignUp']")).isSelected()) {
				System.out.println("KeepmeLoggedin checkbox is selected");
			} else
				System.out.println("Its a wrong window");

			if (driver.findElement(By.xpath("//button[@id='userSignup']")).isDisplayed()) {
				System.out.println("Continue button is displayed");
			} else
				System.out.println("Its a wrong window");

			if (driver.findElement(By.xpath("//div[@class='login-register-common hidden']/div[@class='loginSocialBtn col-xs-24 reset-padding marT20 marB20']/div[@id='googleUserLogin']"))
					.isDisplayed())
			{
				System.out.println("Google button is displayed");
			} else
				System.out.println("Google Button is not present.Its a wrong window");

			if (driver.findElement(By.xpath("//div[@class='login-register-common hidden']/div[@class='loginSocialBtn col-xs-24 reset-padding marT20 marB20']/div[@class='social-button fbLogin col-xs-12']"))
					.isDisplayed())
			
				System.out.println("Facebook button is displayed");
			else
				System.out.println("Facebook button is not displayed.Its a wrong window");

		}

	}


}
