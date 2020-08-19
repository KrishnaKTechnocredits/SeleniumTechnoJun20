package vaishnavi_SeleniumBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/* Selenium Assignment -3 : 18th Aug 2020

1. Navigate to facebook sign in page using url https://www.facebook.com/r.php
2. Verify page title is “Sign up for Facebook | Facebook”.
3. Enter Firstname, surname.
4. Enter wrong mobile number (like 788898989hjhj88)
5. Enter any combination of password
6. Using Select class select Date, Month and Year using sequentially selectByValue(), selectByIndex() and selectByVisibleText() 
7. Select gender from given radio button options.
8. Click on SignUp button.
(No need to validate error occurred for not entering a valid phone number.)

Sign up for Facebook
https://www.facebook.com */

public class FacebookSignUp {

	void verifySignUpPage() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://www.facebook.com/r.php");

		// Maximize
		driver.manage().window().maximize();

		// Verify the title
		Thread.sleep(2000);
		if (driver.getTitle().equals("Sign up for Facebook | Facebook"))
			System.out.println("Page title Verified: It is- " + driver.getTitle());
		else
			System.out.println("Page title verification failed: " + driver.getTitle());

		try {
			// Enter signup details
			driver.findElement(By.name("firstname")).sendKeys("Vaishnavi");
			driver.findElement(By.name("lastname")).sendKeys("Vaidya");
			driver.findElement(By.name("reg_email__")).sendKeys("75686655645287");
			driver.findElement(By.name("reg_passwd__")).sendKeys("Techno123");

			// Selecting from dropDown
			WebElement date = driver.findElement(By.name("birthday_day"));
			Select oSelect = new Select(date);
			oSelect.selectByValue("6");

			Select oSelect1 = new Select(driver.findElement(By.id("month")));
			oSelect1.selectByIndex(12);

			Select oSelect2 = new Select(driver.findElement(By.id("year")));
			oSelect2.selectByVisibleText("1995");

			// Select gender
			driver.findElement(By.xpath("//div//span//span//input[@value='1']")).click();

			driver.findElement(By.name("websubmit")).click();

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows//chromedriver.exe");
		try {
			new FacebookSignUp().verifySignUpPage();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
