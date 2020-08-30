package barkha;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import barkha_base.PredefinedFunctions;

public class SeleniumTest3_TestCase1 extends PredefinedFunctions {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	void setUP() {
		driver = start("https://www.snapdeal.com/");
		wait = new WebDriverWait(driver, 20);
	}

	@AfterMethod
	void tearDown() {
		driver.close();
	}

	@Test
	void testCase1() {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//div[@class='accountInner']"))).build().perform();

		if (driver.findElement(By.xpath("//a[@href='https://www.snapdeal.com/login']")).isDisplayed()) {
			System.out.println("Login button is available.");
			driver.findElement(By.xpath("//a[@href='https://www.snapdeal.com/login']")).click();
		} else
			System.out.println("No Login button!");

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//iframe[@id='loginIframe']")));

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));

		if (driver.findElement(By.xpath("//div[@class='login-register-common']/header")).getText()
				.equals("Login/Sign Up On Snapdeal") && driver.findElement(By.id("userName")).isDisplayed()
				&& driver.findElement(By.xpath("//button[@id='checkUser']")).isDisplayed()
				&& driver.findElement(By.id("fbUserLogin")).isDisplayed()
				&& driver.findElement(By.id("googleUserLogin")).isDisplayed())
			System.out.println(
					"Login/Sign Up On Snapdeal form is opening with 'Mobile Number/ Email' textfield and 'continue' button, 'Facebook' & 'Google' options.");
		else
			System.out.println("Not landed on Login/Sign Up On Snapdeal form");

		driver.findElement(By.id("userName")).sendKeys("7745045329");

		driver.findElement(By.xpath("//button[@class='btn col-xs-24 btn-large btn-skyblue continueBtn marT20 marB30']"))
				.click();

		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//iframe[@id='loginIframe']")));

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));

		driver.findElement(By.xpath("//div[@class='loginCheckbox keepLoginUC']")).click();

		driver.findElement(By.xpath("//input[@id='j_password_login_uc']")).sendKeys("Barkha01");

		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='accountInner']/span")));
		System.out.println("User is successfully login to snapdeal.");
		
		System.out.println(driver.getCurrentUrl());
		if (driver.getCurrentUrl().contains("https://www.snapdeal.com/")) {
			System.out.println("User is on dashboard of Sanpdeal page and able to see name at right corner");
		}
	}
}