package barkha;

import java.util.Iterator;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import barkha_base.PredefinedFunctions;

public class SeleniumTest2 extends PredefinedFunctions {

	WebDriver driver;
	WebDriverWait wait;

	void setUp(String URL) {
		driver = start(URL);
		wait = new WebDriverWait(driver, 10);
	}

	void snapDeal() {
		// Verify Snapdeal page title
		String actualTitle = driver.getTitle();
		String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Test Case Pass:You are landed on Snapdeal site suceessfully.");
		} else
			System.out.println("Test Case FAIL.");

		// Mouse Hover to sign in
		Actions actions = new Actions(driver);
		actions.moveToElement(
				driver.findElement(By.xpath("//div[@class='myAccountTab accountHeaderClass col-xs-13 reset-padding']")))
				.build().perform();

		driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']")).click();

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class=\"login-register-common\"]/header")));

		// click on facebook button
		String mainWindow = driver.getWindowHandle();

		driver.findElement(By.xpath("//div[@class='social-button fbLogin col-xs-12']//span[text()='Facebook']"))
				.click();

		Set<String> newWindow = driver.getWindowHandles();
		Iterator<String> itr = newWindow.iterator();

		while (itr.hasNext()) {
			String tab = itr.next();
			if (!tab.equals(mainWindow)) {
				driver.switchTo().window(tab);
				if (driver.getTitle().equals("Log in to Facebook | Facebook")) {
					System.out.println("You are successfully landed on Facebook page");
				}else
					System.out.println("Test Case fail");
				
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys("barkhapatle18@gmail.com");
				driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("******");
				driver.findElement(By.xpath("//button[@id='loginbutton']")).click();
			}
		}
		driver.switchTo().window(mainWindow);

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='j_username_new']")));

		System.out.println(
				"Facebook Email ID is : " + driver.findElement(By.xpath("//input[@id='j_username_new']")).getAttribute("value"));
		System.out.println(
				"User Name  is: " + driver.findElement(By.xpath("//input[@id='j_name']")).getAttribute("value"));
		System.out.println("DOB  is : " + driver.findElement(By.xpath("//input[@id='j_dob']")).getAttribute("value"));

		driver.findElement(By.xpath("//input[@id='j_number']")).sendKeys("774*****5329");
		driver.findElement(By.xpath("//input[@id='j_password']")).sendKeys("*****");

		if (driver.findElement(By.xpath("//input[@id='keepLoginSignUp']")).isSelected()) {
			driver.findElement(By.xpath("//input[@id='keepLoginSignUp']/..//label")).click();
		}
	}

	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) {
		SeleniumTest2 test2 = new SeleniumTest2();
		test2.setUp("https://www.snapdeal.com/");
		test2.snapDeal();
		test2.tearDown();
	}
}