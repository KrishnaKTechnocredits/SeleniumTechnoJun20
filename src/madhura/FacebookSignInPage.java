package madhura;

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
public class FacebookSignInPage {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();

		if (driver.getTitle().equals("Facebook – log in or sign up")) {
			System.out.println("Login form page Title match --> Pass");
		} else {
			System.out.println("Login form page Title match --> Fail");
		}
		// login 
		driver.findElement(By.name("email")).sendKeys("madhurah02@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("facebook@123");
		driver.findElement(By.name("login")).click();
		Thread.sleep(1000);
		// verify page title after logged in
		if (driver.getTitle().equals("Facebook")) {
			System.out.println("After logged in Title match --> Pass");
		} else {
			System.out.println("After logged in Title match --> Fail");
		}

		// Navigation commands
		driver.navigate().to("https://www.google.com/");
		Thread.sleep(300);

		driver.navigate().back();
		Thread.sleep(300);
		if (driver.getTitle().equals("Facebook"))
			System.out.println("Navigate Back --> Passed");

		driver.navigate().forward();
		Thread.sleep(300);
		if (driver.getTitle().equals("Google"))
			System.out.println("Navigate forward --> Passed");

		driver.navigate().refresh();
		Thread.sleep(300);
		if (driver.getTitle().equals("Google"))
			System.out.println("Refresh navigated page --> Passed");

		driver.quit();
	}
}