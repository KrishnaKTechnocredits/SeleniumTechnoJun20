package ajit.testScripts;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ajit.base.PredefinedActions;

public class CodingExam_3 extends PredefinedActions {
	WebDriver driver;
	Actions actions;
	WebDriverWait wait;

	@BeforeTest
	public void setUp() {
		// driver = start();
		driver = start("https://www.snapdeal.com/");
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "SnapdealLoginData")
	public void LoginUsingMobileAndEmailID(String userName, String password) throws InterruptedException {
		actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")))
				.build().perform();
		driver.findElement(By.xpath("//a[text()='login']")).click();
		Thread.sleep(3000);

		// driver.switchTo().frame(0);
		// driver.switchTo().frame(driver.findElement(By.xpath("//div/iframe[@onload='onloadSignupIframe()']")));
		// driver.switchTo().frame("iframeLogin");
		driver.switchTo().frame(driver.findElement(By.cssSelector("#loginIframe")));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//header[text()='login/sign up on snapdeal']")));
		verifyLoginSignUpPageElements();
		driver.findElement(By.xpath("//input[@id='userName']")).sendKeys(userName);
		driver.findElement(By.xpath("//button[@id='checkUser']")).click();
		verifyPasswordPageElements();
		driver.findElement(By.xpath("//input[@id='j_password_login_uc']")).sendKeys(password);

		// Uncheck Keep me loggedIn checkBox
		driver.findElement(By.xpath("//div/label[@for='keepLoginUC']")).click();
		driver.findElement(By.xpath("//button[@id='submitLoginUC']")).click();
		System.out.println("Clicked on Submit button");

		String mainWindow = driver.getWindowHandle();
		driver.switchTo().window(mainWindow);
		String expectedUserName = "AJIT SINGH";
		Thread.sleep(7000);
		String actualUserName = driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']"))
				.getText();
		Assert.assertEquals(actualUserName, expectedUserName);
		System.out.println("Execution Completed");
		VerifyCartIsEmpty();
		logOutFromAccount();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")));
	}

	// Verify Elements on Login Page
	void verifyLoginSignUpPageElements() {
		Assert.assertEquals((driver.findElement(By.xpath("//div[@class='login-register-common']/header")).getText()),
				"Login/Sign Up On Snapdeal");
		String expectedHeaderText = "Login/Sign Up On Snapdeal";
		String actualHeaderText = driver.findElement(By.xpath("//div[@class='login-register-common']/header"))
				.getText();
		Assert.assertEquals(actualHeaderText, expectedHeaderText);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='userName']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//button[@id='checkUser']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-register-common']//div[@id='fbUserLogin']"))
				.isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='login-register-common']//div[@id='googleUserLogin']"))
						.isDisplayed());
		System.out.println("verifyLoginSignUpPageElements Method Executed");
	}

	// Verify Elements on Password Page
	void verifyPasswordPageElements() {
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//div[@class='loginEmailUpgrade-card']/div/div/div[@id='fbUserLogin']/span[@class='social-content']")))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath(
				"//div[@class='loginEmailUpgrade-card']/div/div/div[@id='googleUserLogin']/span[@class='social-content']"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='j_username_uc']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='j_password_login_uc']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//button[@id='submitLoginUC']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//button[@id='sendOtpUC']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//button[@id='sendOtpUC']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//button[@id='sendOtpUC']")).isDisplayed());
		System.out.println("verifyPasswordPageElements Method Executed");
	}

	void VerifyCartIsEmpty() throws InterruptedException {
		actions.moveToElement(driver.findElement(By.xpath("//div[@class='accountInner']"))).build().perform();
		String[] options = { "Orders", "Shortlist", "SD Cash", "E-Gift Voucher", "LOGOUT" };
		ArrayList<WebElement> listofOptions = (ArrayList<WebElement>) driver
				.findElements(By.xpath("//div[@class='dropdownAccount']//a"));
		// Verifying List of options displayed after loginHover
		for (int index = 0; index < options.length; index++) {
			Assert.assertEquals(listofOptions.get(index).getText(), options[index]);
		}

		// Click on orders from the list
		listofOptions.get(0).click();
		String expectedTitle = "Order History";
		String expectedUrl = "https://www.snapdeal.com/myorders";
		Assert.assertEquals(driver.getTitle(), expectedTitle); // Validating title
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl); // Validating url

		// OrderId
		String actualOrderId = driver.findElement(By.xpath("//div[@class='noOrdersAvail']")).getText();
		String expectedOrderID = "NO ORDERS AVAILABLE";
		Assert.assertEquals(actualOrderId, expectedOrderID);
		System.out.println(actualOrderId);

		// Click on home button
		driver.findElement(By.xpath("//span[text()='Home']")).click();
		String homepageTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		Assert.assertEquals(driver.getTitle(), homepageTitle); // Validating home page title
		driver.findElement(By.xpath("//span[text()='Cart']")).click(); // Click on a cart
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='cart-categories-container']//p")));
		Assert.assertEquals(driver.findElement(By.xpath("//div//h3[text()='Shopping Cart is empty!']")).getText(),
				"Shopping Cart is empty!");
		Assert.assertEquals(driver.findElement(By.xpath("//div/p[text()='Browse Categories']")).getText(),
				"BROWSE CATEGORIES");
		System.out.println("5");
		Assert.assertEquals(
				driver.findElements(By.xpath("//div[@class='cart-categories-list-wrapper row']//li")).size(), 20);

		Assert.assertTrue(driver.findElement(By.xpath("//div/a[text()='START SHOPPING NOW']")).isDisplayed());
		// Verify home page title
		driver.findElement(By.xpath("//div/a[text()='START SHOPPING NOW']")).click();
		Assert.assertEquals(driver.getTitle(), homepageTitle);

	}

	void logOutFromAccount() {
		actions.moveToElement(driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")))
				.build().perform();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
	}

	@DataProvider(name = "SnapdealLoginData")
	public String[][] dataprovider() {

		String[][] data = new String[1][2];

		data[0][0] = "ajitkusi21@gmail.com";
		data[0][1] = "XXXXXXXX";
		data[1][0] = "7299212566";
		data[1][1] = "XXXXXXXXX";

		return data;
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
