/*
Selenium Assignment 2 : 17th Aug 2020

1. Navigate to facebook sign in page using url https://www.facebook.com/
2. Verify page title is “Facebook – log in or sign up”.
3. Enter email id & password, and click on ‘Log In’ button.
4. Verify page title is “Facebook”.
5. Now using navigation command go to https://www.google.com/
6. Verify when user navigate back at that time page title is “Facebook”.
7. Verify when user navigate forward at that time page title is “Google”
8. Verify when user refresh the page, title remains same.
*/

package nikhil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FacebookToGoogle {
	void loginFacebook(WebDriver driver) throws InterruptedException {
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
		if(driver.getTitle().equals("Facebook – log in or sign up")) {
			System.out.println("Page title for Facebook sign-up/sign-in page is verified.");
		}else {
			System.out.println("Page title for Facebook sign-up/sign-in page is NOT verified.");
		}
		driver.findElement(By.id("email")).sendKeys("<user_email>");
		driver.findElement(By.id("pass")).sendKeys("<password>");
		driver.findElement(By.id("u_0_b")).click();
		Thread.sleep(2000);
		if(driver.getTitle().equals("Facebook")) {
			System.out.println("Page title for Facebook Main User page is verified.");
		}else {
			System.out.println("Page title for Facebook Main User page is NOT verified.");
		}
	}
	
	void goToGoogleBackAndForth(WebDriver driver) throws InterruptedException {
		driver.navigate().to("https://www.google.com/");
		Thread.sleep(2000);
		System.out.println("User is navigated to "+driver.getCurrentUrl());
		driver.navigate().back();
		Thread.sleep(2000);
		if(driver.getTitle().equals("Facebook")) {
			System.out.println("Page title for Facebook Main User page is verified after navigating back.");
		}else {
			System.out.println("Page title for Facebook Main User page is NOT verified after navigating back.");
		}
		driver.navigate().forward();
		Thread.sleep(2000);
		if(driver.getTitle().equals("Google")) {
			System.out.println("Page title for Google page is verified after navigating forward.");
		}else {
			System.out.println("Page title for Google page is NOT verified after navigating forward.");
		}
		driver.navigate().refresh();
		Thread.sleep(1000);
		if(driver.getTitle().equals("Google")) {
			System.out.println("Page title for Google page is verified after page refresh.");
		}else {
			System.out.println("Page title for Google page is NOT verified after page refresh.");
		}
	}
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		FacebookToGoogle facebookToGoogle = new FacebookToGoogle();
		try {
			//Login to Facebook
			facebookToGoogle.loginFacebook(driver);
			//Navigate to Google, back again to Facebook, again to Google and refresh
			facebookToGoogle.goToGoogleBackAndForth(driver);
			} catch (InterruptedException e) {
				System.out.println("Program terminated unexpectedly.\nPlease try after some time.");
				e.printStackTrace();
			}
		driver.close();
	}
}
