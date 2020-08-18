/*
 * Selenium Assignment 2 : 17th Aug 2020

1. Navigate to facebook sign in page using url https://www.facebook.com/
2. Verify page title is “Facebook – log in or sign up”.
3. Enter email id & password, and click on ‘Log In’ button.
4. Verify page title is “Facebook”.
5. Now using navigation command go to https://www.google.com/
6. Verify when user navigate back at that time page title is “Facebook”.
7. Verify when user navigate forward at that time page title is “Google”
8. Verify when user refresh the page, title remains same. 
 */
package pranita;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignmnt2_FBLogin {

	void loginFBPage(WebDriver driver) throws InterruptedException {
		String expectedTitle = "Facebook - Log In or Sign Up";
		
		// Navigate to facebook sign in page using url https://www.facebook.com/
		driver.get("https://www.facebook.com/");
		
		// Verify page title is “Facebook – log in or sign up”.
		if (driver.getTitle().equals(expectedTitle)) {
			System.out.println("Page Title is as expected-Facebook login Page opened");
		} else {
			System.out.println("Incorrect Page Title." + driver.getTitle());
		}
		
		// Enter email id & password, and click on ‘Log In’ button.
		driver.findElement(By.id("email")).sendKeys("pranita.mini@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("*LoveForCats*");
		driver.findElement(By.id("u_0_b")).click();
		Thread.sleep(5000);
		
		// Verify page title is “Facebook”.
		String loginTitle = driver.getTitle();
		System.out.println(loginTitle);
		if (driver.getTitle().equals(loginTitle)) {
			System.out.println("Page Title is as expected-logged in to Facebook");
		} else {
			System.out.println("Failed to login" + driver.getTitle());
		}
		
		// Now using navigation command go to https://www.google.com/
		driver.navigate().to("https://www.google.com/");

		// Verify when user navigate back at that time page title is “Facebook”.
		driver.navigate().back();
		Thread.sleep(3000);
		if (driver.getTitle().equals(loginTitle)) {
			System.out.println("Page Title is as expected-navigated back to Facebook");
		} else {
			System.out.println("Failed to navigate back");
		}

		// Verify when user navigate forward at that time page title is “Google”
		driver.navigate().forward();
		Thread.sleep(2000);
		if (driver.getTitle().equals("Google"))
			System.out.println("Google page successfully opens");
		else
			System.out.println("You failed to navigate forward");

		// Verify when user refresh the page, title remains same.
		driver.navigate().refresh();
		Thread.sleep(2000);
		if (driver.getTitle().equals("Google"))
			System.out.println("You are still on the same page- Google");
		else
			System.out.println("You failed to refresh the page");

	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Assignmnt2_FBLogin assignmnt2_FBLogin = new Assignmnt2_FBLogin();
		try {
			assignmnt2_FBLogin.loginFBPage(driver);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.close();
	}

}
