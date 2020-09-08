package mahesh.Exam.CodingExam3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import mahesh.base.PredefinedActions;

public class CommonValidations extends PredefinedActions{
	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	String snapDealPageTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";

	@BeforeMethod
	void setUp() {
		driver = start("https://www.snapdeal.com/");
		wait = new WebDriverWait(driver, 20);
		action = new Actions(driver);
	}

	@AfterMethod
	void tearDown() {
		driver.close();
	}
	
	void goToLoginPage() {
		Assert.assertEquals(driver.getTitle(),snapDealPageTitle);
		action.moveToElement(driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")))
				.build().perform();
		driver.findElement(By.xpath("//span/a[@href='https://www.snapdeal.com/login']")).click();
	}
	
	void validateSnapdealHomePage() {
		driver.switchTo().defaultContent();
		Assert.assertEquals(driver.getTitle(),snapDealPageTitle);
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Mahesh Kumavat']"))).isDisplayed());
	}
	
	void validateLoginPage() {
		driver.switchTo().frame(
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@id='loginIframe']"))));
		Assert.assertTrue(driver.findElement(By.xpath(
				"//div[@class='login-register-common']/div/div[@class='social-button fbLogin col-xs-12']/span[@class='social-content']"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath(
				"//div[@class='login-register-common']/div/div[@class='social-button gmLogin rfloat col-xs-11']/span[@class='social-content']"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//button[@id='checkUser']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='userName']")).isDisplayed());

	}

	void validateLoginPage1() {
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//div[@class='loginEmailUpgrade-card']/div/div/div[@id='fbUserLogin']/span[@class='social-content']"))).isDisplayed());		Assert.assertTrue(driver.findElement(By.xpath(
				"//div[@class='loginEmailUpgrade-card']/div/div/div[@id='googleUserLogin']/span[@class='social-content']"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//button[@id='submitLoginUC']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//button[@id='sendOtpUC']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='j_password_login_uc']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='j_username_uc']")).isDisplayed());
	}
	
	void login(String userId, String password) {
		goToLoginPage();
		validateLoginPage();
		driver.findElement(By.xpath("//input[@id='userName']")).sendKeys(userId);
		driver.findElement(By.xpath("//button[@id='checkUser']")).click();
		validateLoginPage1();
		driver.findElement(By.xpath("//input[@id='j_password_login_uc']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='submitLoginUC']")).click();
		validateSnapdealHomePage();
	}
}
