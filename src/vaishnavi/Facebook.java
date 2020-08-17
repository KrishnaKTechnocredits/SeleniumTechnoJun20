package vaishnavi;

import org.openqa.selenium.By;

/* Selenium Assignment 2 : 17th Aug 2020

1. Navigate to facebook sign in page using url https://www.facebook.com/
2. Verify page title is “Facebook – log in or sign up”.
3. Enter email id & password, and click on ‘Log In’ button.
4. Verify page title is “Facebook”.
5. Now using navigation command go to https://www.google.com/
6. Verify when user navigate back at that time page title is “Facebook”.
7. Verify when user navigate forward at that time page title is “Google”
8. Verify when user refresh the page, title remains same.  */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Facebook {

	void verifyFacebookPage() throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://www.facebook.com/");
		driver.manage().window().maximize(); // Maximizing the window

		if (driver.getTitle().equals("Facebook – log in or sign up"))
			System.out.println("You are on a correct page");
		else
			System.out.println("You are on a wrong page with name " + driver.getCurrentUrl());

		driver.findElement(By.id("email")).sendKeys("9545336556");
		driver.findElement(By.id("pass")).sendKeys("vaish@78#");
		driver.findElement(By.name("login")).click();

		// Verify Page title
		Thread.sleep(3000);
		if (driver.getTitle().equals("(2) Facebook"))
			System.out.println("Title for Facebook is verified");
		else
			System.out.println("You failed to logIn" + driver.getTitle());

		// Navigate to Google
		driver.navigate().to("https://www.google.com/");
		// Navigate back
		driver.navigate().back();

		// Verifying again if title is Facebook
		Thread.sleep(3000);
		if (driver.getTitle().equals("(2) Facebook"))
			System.out.println("Title for Facebook is verified - After navigating");
		else
			System.out.println("You failed to navigate back" + driver.getTitle());

		// Navigate forward
		driver.navigate().forward();
		Thread.sleep(2000);
		if (driver.getTitle().equals("Google"))
			System.out.println("Google page successfully opens");
		else
			System.out.println("You failed to navigate forward");

		// Refresh the page
		driver.navigate().refresh();
		Thread.sleep(2000);
		if (driver.getTitle().equals("Google"))
			System.out.println("You are still on the same page- Google");
		else
			System.out.println("You failed to refresh the page");

		driver.close();

	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".\\resources//windows//chromedriver.exe");
		try {
			new Facebook().verifyFacebookPage();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
