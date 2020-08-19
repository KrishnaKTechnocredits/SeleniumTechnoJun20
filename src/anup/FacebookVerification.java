package anup;
/*Selenium Assignment 2 : 17th Aug 2020

1. Navigate to facebook sign in page using url https://www.facebook.com/
2. Verify page title is “Facebook – log in or sign up”.
3. Enter email id & password, and click on ‘Log In’ button.
4. Verify page title is “Facebook”.
5. Now using navigation command go to https://www.google.com/
6. Verify when user navigate back at that time page title is “Facebook”.
7. Verify when user navigate forward at that time page title is “Google”
8. Verify when user refresh the page, title remains same. */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FacebookVerification {
	
	WebDriver driver = new ChromeDriver();
	void facebookValidation() throws InterruptedException{
		
		
		//1. Navigate to facebook sign in page using url https://www.facebook.com/
		driver.navigate().to("https://www.facebook.com/");
		Thread.sleep(3000);
		driver.manage().window().maximize();
		
		//2. Verify page title is “Facebook – log in or sign up”.
		if(driver.getTitle().equals("Facebook - Log In or Sign Up")) {
			System.out.println("Test Pass - The Title is matching "+ driver.getTitle());
		}
		else {
			System.out.println("Test Fail - The Title is not matching "+driver.getTitle());
		}
		
		//3. Enter email id & password, and click on ‘Log In’ button.
		driver.findElement(By.id("email")).sendKeys("email");
		driver.findElement(By.id("pass")).sendKeys("password");
		driver.findElement(By.id("u_0_b")).click();
		Thread.sleep(3000);
		
		
		//4. Verify page title is “Facebook”.
		if(driver.getTitle().startsWith("Facebook")) {
			System.out.println("Test Pass - The Title is matching " + driver.getTitle());
		}
		else {
			System.out.println("Test Fail - The Title is not matching "+ driver.getTitle());
		}	
		
		//5. Now using navigation command go to https://www.google.com/
		driver.navigate().to("https://www.google.com/");
		Thread.sleep(3000);
		if(driver.getTitle().startsWith("Google")) {
			System.out.println("Test Pass - The Title is matching " + driver.getTitle());
		}
		else {
			System.out.println("Test Fail - The Title is not matching "+ driver.getTitle());
		}	
		
		//6. Verify when user navigate back at that time page title is “Facebook”.
		driver.navigate().back();
		Thread.sleep(3000);
		if(driver.getTitle().startsWith("Facebook")) {
			System.out.println("Test Pass - The Title is matching " + driver.getTitle());
		}
		else {
			System.out.println("Test Fail - The Title is not matching "+ driver.getTitle());
		}	
		
		//7. Verify when user navigate forward at that time page title is “Google”
		driver.navigate().forward();
		Thread.sleep(3000);
		if(driver.getTitle().startsWith("Google")) {
			System.out.println("Test Pass - The Title is matching " + driver.getTitle());
		}
		else {
			System.out.println("Test Fail - The Title is not matching "+ driver.getTitle());
		}	
		
		//8. Verify when user refresh the page, title remains same.
		driver.navigate().refresh();
		Thread.sleep(3000);
		if(driver.getTitle().startsWith("Google")) {
			System.out.println("Test Pass - The Title is matching " + driver.getTitle());
		}
		else {
			System.out.println("Test Fail - The Title is not matching "+ driver.getTitle());
		}	
		driver.close();
	}
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".//resources//mac//chromedriver");
		FacebookVerification facebokverification = new FacebookVerification();
		try {
			facebokverification.facebookValidation();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
