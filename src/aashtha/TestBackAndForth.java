package aashtha;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*Selenium Assignment 2 : 17th Aug 2020

1. Navigate to facebook sign in page using url https://www.facebook.com/
2. Verify page title is “Facebook – log in or sign up”.
3. Enter email id & password, and click on ‘Log In’ button.
4. Verify page title is “Facebook”.
5. Now using navigation command go to https://www.google.com/
6. Verify when user navigate back at that time page title is “Facebook”.
7. Verify when user navigate forward at that time page title is “Google”
8. Verify when user refresh the page, title remains same. */

public class TestBackAndForth {
	WebDriver driver = new ChromeDriver();

	void testBrowserHistoryButtons() {
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		if (driver.getTitle().equals("Facebook – log in or sign up"))
			System.out.println("Facebook – log in or sign up Page - Title verified");
		else
			System.out.println("Facebook – log in or sign up Page - Title verification failed");
		System.out.println("User name entered successfully");
		driver.findElement(By.id("email")).sendKeys("jaiswal.aastha1691@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("abcd1234");
		System.out.println("Password entered successfully");
		driver.findElement(By.name("login")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (driver.getTitle().equals("Facebook"))
			System.out.println("Facebook Home Page - Title verified");
		else
			System.out.println("Facebook Home Page - Title verification failed");
		driver.navigate().to("https://www.google.com/");
		if (driver.getTitle().equals("Google"))
			System.out.println("Successfully Navigated to google.com");
		else
			System.out.println("Navigation to google.com failed");
		driver.navigate().back();
		if (driver.getTitle().equals("Facebook"))
			System.out.println("Successfully Navigated back to Facebook");
		else
			System.out.println("Back Navigation to Facebook failed");
		driver.navigate().forward();
		if (driver.getTitle().equals("Google"))
			System.out.println("Successfully Navigated forward to google.com");
		else
			System.out.println("Forward Navigation to google.com failed");
		driver.navigate().refresh();
		if (driver.getTitle().equals("Google"))
			System.out.println("Page title is Google after page refresh");
		else
			System.out.println("Page title is " + driver.getTitle() + "after page refresh");
		driver.close();
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		new TestBackAndForth().testBrowserHistoryButtons();
	}
}
