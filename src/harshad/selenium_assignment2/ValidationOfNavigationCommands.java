package harshad.selenium_assignment2;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ValidationOfNavigationCommands {
	WebDriver driver = new ChromeDriver();
	WebElement emailID,password,loginBtn;
	void openBrowserAndAccessURL() {
		driver.navigate().to("https://www.facebook.com/");
		
		if(driver.getTitle().equals("Facebook – log in or sign up")) {
			System.out.println("Verified page title is “Facebook – log in or sign up”");
			emailID = driver.findElement(By.id("email"));
			emailID.sendKeys("harshad@facebook.com");
			password = driver.findElement(By.id("pass"));
			password.sendKeys("abc123456");
			loginBtn = driver.findElement(By.name("login"));
			loginBtn.click();
			System.out.println("Facebook Login successful");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(driver.getTitle().equals("Facebook")) {
				
				System.out.println("Verified current page title is Facebook after login");
				
				driver.navigate().to("https://www.google.com/");
				System.out.println("Navigation from facebook.com to google.com successful");
				
				driver.navigate().back();
				if(driver.getTitle().equals("Facebook")) {
					System.out.println("Navigating back to facebook.com successful using back history");
					System.out.println("Verified current page title is Facebook");
				}else {
					System.out.println("Current page title is not Facebook it is "+driver.getTitle());
				}
				
				driver.navigate().forward();
				if(driver.getTitle().equals("Google")) {
					System.out.println("Navigating forward to google.com successful using forward history");
					System.out.println("Verified current page title is Google");
				}else {
					System.out.println("Current page title is not Google it is "+driver.getTitle());
				}
				
				driver.navigate().refresh();
				
				if(driver.getTitle().equals("Google")) {
					System.out.println("After Page Refresh same page of google.com is opened successfully");
					System.out.println("Verified current page title remains same as Google after page refresh");
				}else {
					System.out.println("Current page title is not Google after page refresh it is "+driver.getTitle());
				}
				
			}else {
				System.out.println("Current page title is not Facebook");
			}
		
		}else {
			System.out.println("Verified title of current page is not “Facebook – log in or sign up” it is "+driver.getTitle());
		}
		driver.close();
	}
	public static void main(String[] args) {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : "+ os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;

		System.setProperty("webdriver.chrome.driver", path);
		//System.setProperty("webdriver.chrome.driver", ".//chromedriver.exe");
		ValidationOfNavigationCommands validationOfNavigationCommands = new ValidationOfNavigationCommands();
		validationOfNavigationCommands.openBrowserAndAccessURL();
	}
}
