package mahesh.Exam;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import mahesh.base.PredefinedActions;

public class CodingExam_2 extends PredefinedActions{
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	void setup() {
		driver = start("https://www.snapdeal.com/");
		wait = new WebDriverWait(driver, 20);
	}

	@AfterMethod
	void tearDown() {
		driver.close();
	}

	@Test
	public void snapdealSignUp() {
		String snapdealExpectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		Assert.assertEquals(driver.getTitle(), snapdealExpectedTitle);
		System.out.println("Snapdeal website successfully loaded and Title of page is: " + snapdealExpectedTitle);
		String mainWin = driver.getWindowHandle();

		// hover to Singin menu and click on login
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[@class='accountInner']"))).build().perform();
		driver.findElement(By.xpath("//span/a[@href='https://www.snapdeal.com/login']")).click();

		// switch to iframe and select loging using Facebook options
		driver.switchTo().frame(
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@id='loginIframe']"))));
		driver.findElement(By.xpath(
				"//div[@class='login-register-common']/div/div[@class='social-button fbLogin col-xs-12']/span[@class='social-content']"))
				.click();

		// switch to facebook login window and login with username and password
		Set<String> sessionIdList = driver.getWindowHandles();
		for (String browser : sessionIdList) {
			if (!browser.equals(mainWin)) {
				driver.switchTo().window(browser);
				String fbExpectedTile = "Log in to Facebook | Facebook";
				Assert.assertEquals(driver.getTitle(), fbExpectedTile);
				System.out.println("Switched to Facebook loging window and the Title is: " + fbExpectedTile);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")))
						.sendKeys("mkumavat31@yahoo.in");
				driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Quinnox$123");
				driver.findElement(By.xpath("//button[@id='loginbutton']")).click();

				// switch back to Snapdeal browser and enter user details for signing up
				driver.switchTo().window(mainWin);
				System.out.println("Switched back to Snapdeal browser");
				driver.switchTo().frame(wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@id='loginIframe']"))));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='j_username_new']")));
				driver.findElement(By.xpath("//input[@name='j_number']")).sendKeys("9167755341");
				driver.findElement(By.xpath("//input[@id='j_password']")).sendKeys("Sample@123");
				driver.findElement(By.xpath("//label[@for='keepLoginSignUp']")).click();
			}
		}
	}
}
