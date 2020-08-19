package anshu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FacebookNavigation {

	void FacebookNavigationTestCase(WebDriver driver) throws InterruptedException {

		// 1 Navigate Facebook sign
		driver.navigate().to("https://www.facebook.com/");
		driver.manage().window().maximize();

		// . Verify page title is “Facebook – log in or sign up”.
		String title = driver.getTitle();
		Thread.sleep(100);
		System.out.println(
				title.equals("Facebook - Log In or Sign Up") ? "TestPass: Got Facebook - Log In or Sign Up Title"
						: "TestFail : Unable to get Facebook - Log In or Sign Up  Title");

		// 3. Enter email id & password, and click on ‘Log In’ button.
		driver.findElement(By.id("email")).sendKeys("anshu.v.kumari02@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("Losser123#");
		driver.findElement(By.name("login")).click();
		Thread.sleep(1000);

		// 4 . Verify page title is “Facebook”.
		if (driver.getTitle().contains("Facebook")) {
			System.out.println("TestPass : Got  Facebook Title");
		} else {
			System.out.println("TestFail: Unable to get Facebook Title");
		}

		// 5 Now using navigation command go to https://www.google.com/
		driver.navigate().to("https://www.google.com");
		System.out.println(driver.getTitle().startsWith("Google") ? "TestPass : Got google title "
				: "TestFail: Unable to get google title");

		// 6. Verify when user navigate back at that time page title is “Facebook”.
		driver.navigate().back();
		Thread.sleep(1000);
		System.out.println(driver.getTitle().contains("Facebook") ? "Test PASS : Navigated back to Facebook.com"
				: "Test FAIL : Navigation Failed");

		// 7. Verify when user navigate forward at that time page title is “Google”
		driver.navigate().forward();
		Thread.sleep(1000);
		System.out.println(driver.getTitle().contains("Google") ? "Test PASS : Navigated forward to Google.com"
				: "Test FAIL : Navigation failed");

		// 8. Verify when user refresh the page, title remains same.
		driver.navigate().refresh();
		Thread.sleep(1000);
		System.out.println(driver.getTitle().contains("Google") ? "Test PASS : Got google.com after refresh "
				: "Test FAIL : Navigation failed");

	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		FacebookNavigation facebookNavigation = new FacebookNavigation();
		try {
			facebookNavigation.FacebookNavigationTestCase(driver);
		} catch (InterruptedException ie) {
			System.out.println("Exception Handle");
		} finally {
			driver.close();
		}

	}

}
