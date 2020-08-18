/*Selenium Assignment 2 : 17th Aug 2020

1. Navigate to facebook sign in page using url https://www.facebook.com/
2. Verify page title is “Facebook – log in or sign up”.
3. Enter email id & password, and click on ‘Log In’ button.
4. Verify page title is “Facebook”.
5. Now using navigation command go to https://www.google.com/
6. Verify when user navigate back at that time page title is “Facebook”.
7. Verify when user navigate forward at that time page title is “Google”
8. Verify when user refresh the page, title remains same. */
package amita;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FacebookLoginPageValidation {
	
	
	void facebookSignInValidation(WebDriver driver) throws InterruptedException {
		
		//1. Navigate to facebook sign in page using url https://www.facebook.com/
		System.out.println("Launch Chrome Browser");
		driver.navigate().to("https://www.facebook.com/");
		driver.manage().window().maximize();
		
		//2. Verify page title is “Facebook – log in or sign up”.
		System.out.println(driver.getTitle().equals("Facebook - Log In or Sign Up") ? "Test PASS : Navigated to facebook.com" : "Test FAIL : Navigation failed");
		
		//3. Enter email id & password, and click on ‘Log In’ button.
		driver.findElement(By.id("email")).sendKeys("email.com");
		driver.findElement(By.id("pass")).sendKeys("password");
		driver.findElement(By.name("login")).click();
		Thread.sleep(2000);
		
		//4. Verify page title is “Facebook”.
		if(driver.getTitle().startsWith("Facebook")) {
			System.out.println("Test PASS : Title is matching.Facebook Login Successfull " + "The Title is :" +driver.getTitle());
		}
		else {
			System.out.println("Test FAIL : Title is not matching.Facebook Login Unsuccessfull"+"The Title is :" + driver.getTitle());
		}
	}
	
	void navigationValidation(WebDriver driver) throws InterruptedException {
		//5. Now using navigation command go to https://www.google.com/
		driver.navigate().to("https://google.com/");
		Thread.sleep(2000);
		System.out.println(driver.getTitle().equals("Google") ? "Test PASS : Navigated to Google.com" : "Test FAIL : Navigation failed");
		
		//6. Verify when user navigate back at that time page title is “Facebook”.
		driver.navigate().back();
		Thread.sleep(2000);
		System.out.println(driver.getTitle().contains("Facebook") ? "Test PASS : Navigated back to Facebook.com" : "Test FAIL : Navigation failed");
		
		//7. Verify when user navigate forward at that time page title is “Google”
		driver.navigate().forward();
		Thread.sleep(2000);
		System.out.println(driver.getTitle().contains("Google") ? "Test PASS : Navigated forward to Google.com" : "Test FAIL : Navigation failed");
		
		//8. Verify when user refresh the page, title remains same. 
		driver.navigate().refresh();
		Thread.sleep(2000);
		System.out.println(driver.getTitle().contains("Google") ? "Test PASS : After refresh page title is Google.com" : "Test FAIL : Navigation failed");
		
		
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");	
		WebDriver driver = new ChromeDriver();
		
		FacebookLoginPageValidation facebookLogin = new FacebookLoginPageValidation();
		try {
			facebookLogin.facebookSignInValidation(driver);
			facebookLogin.navigationValidation(driver);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}finally {
			driver.close();
		}
	}
}
