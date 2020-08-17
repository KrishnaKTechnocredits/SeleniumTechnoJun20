package kartikey;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FacebookGoogleNavigateAndNavigateBack {

	WebDriver getDriver() {
		String os = System.getProperty("os.name").toLowerCase();
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;

		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	void verifyNavigationToAndFroFromFacebookAndGoogle(WebDriver driver) {
		driver.manage().window().maximize();
		driver.navigate().to("https://www.facebook.com/");
		if (driver.getTitle().equals("Facebook – log in or sign up"))
			System.out.println("Navigation to Facebook is successfull");
		else
			System.out.println("Navigation to Facebook is unsuccessfull");

		driver.findElement(By.id("email")).sendKeys("AutomationStarted@gamil.com");
		driver.findElement(By.id("pass")).sendKeys("SeleniumStarted");
		driver.findElement(By.id("u_0_b")).click();

		if (driver.getTitle().equals("Log in to Facebook | Facebook"))
			System.out.println("Title is verified after login details provided");
		else
			System.out.println("Title is not verified after Login details provided");
		// Navigate to google and back
		driver.navigate().to("https://www.google.com/");
		driver.navigate().back();

		if (driver.getTitle().equals("Log in to Facebook | Facebook"))
			System.out.println("Navigate to google and back is successfull");
		else
			System.out.println("Navigate to google and back is unsuccessfull");

		// Navigate forward to google
		driver.navigate().forward();
		if (driver.getTitle().equals("Google"))
			System.out.println("Navigate forward to google is successfull");
		else
			System.out.println("Navigate forward to google is unsuccessfull");

		// Refresh
		driver.navigate().refresh();
		if (driver.getTitle().equals("Google"))
			System.out.println("After refresh Title is same");
		else
			System.out.println("After refresh Title is not same");
	}

	public static void main(String[] args) {
		FacebookGoogleNavigateAndNavigateBack facebookGoogleNavigateAndNavigateBack = new FacebookGoogleNavigateAndNavigateBack();
		WebDriver driver = facebookGoogleNavigateAndNavigateBack.getDriver();
		facebookGoogleNavigateAndNavigateBack.verifyNavigationToAndFroFromFacebookAndGoogle(driver);
		driver.close();
	}

}
