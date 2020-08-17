/*
 * 1. Navigate to facebook sign in page using url https://www.facebook.com/
 * 2. Verify page title is “Facebook – log in or sign up”.
 * 3. Enter email id & password, and click on ‘Log In’ button.
 * 4. Verify page title is “Facebook”.
 * 5. Now using navigation command go to https://www.google.com/
 * 6. Verify when user navigate back at that time page title is “Facebook”.
 * 7. Verify when user navigate forward at that time page title is “Google”
 * 8. Verify when user refresh the page, title remains same. 
 */

package aditi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FBGoogleNavigationBackAndForth {
	void faceBookLogin(WebDriver driver) throws InterruptedException {
		String expectedTitle = "Facebook - Log In or Sign Up";
		driver.manage().window().maximize();
		driver.navigate().to("https://www.facebook.com/");

		// Validation to check FB Page opened or not
		if (driver.getTitle().equals(expectedTitle))
			System.out.println("Test Pass - FaceBook page opened");
		else
			System.out.println("Test Failed - Current page URL is: " + driver.getCurrentUrl());

		// Login
		driver.findElement(By.id("email")).sendKeys("<EMAIL_ID>");
		driver.findElement(By.id("pass")).sendKeys("<PASSWORD>");
		driver.findElement(By.id("u_0_b")).click();
		Thread.sleep(5000);
		// Validation - (Later add logic to check logout button/link to check user is logged in or not. )
		System.out.println(driver.getTitle().equals("(1) Facebook") ? "Test Pass - User is logged in"
				: "Test Failed - User Unable to login");
	}

	void googlePageNavigationBackNForth(WebDriver driver) throws InterruptedException {
		driver.navigate().to("https://www.google.com/");
		System.out.println("User navigated to -> " + driver.getCurrentUrl());
		// History page
		driver.navigate().back();
		Thread.sleep(3000);
		System.out.println(driver.getTitle().equals("(1) Facebook")
				? "Test Pass - Back page works - History Page loaded successfully - " + driver.getCurrentUrl()
				: "Test Fail - Issue while navigating back to  History Page");
		// Forward navigation
		driver.navigate().forward();
		Thread.sleep(3000);
		System.out.println(driver.getTitle().equals("Google") ? "Test Pass - Google Search Page loaded successfully"
				: "Test Failed - Issue while loading page");
		// Page Refresh
		driver.navigate().refresh();
		Thread.sleep(3000);
		System.out.println(driver.getTitle().equals("Google") ? "Test Pass - Page refresh working as expected"
				: "Page refreash not working");

	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./resources/mac/chromedriver");
		WebDriver driver = new ChromeDriver();
		FBGoogleNavigationBackAndForth fbGoogleNavigationBackAndForth = new FBGoogleNavigationBackAndForth();
		try {
			fbGoogleNavigationBackAndForth.faceBookLogin(driver);
			fbGoogleNavigationBackAndForth.googlePageNavigationBackNForth(driver);
		} catch (InterruptedException interruptedException) {
			System.out.println("Whoops, something went wrong...");
			interruptedException.printStackTrace();
		}
		driver.quit();

	}

}
