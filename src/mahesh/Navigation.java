package mahesh;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Navigation {

	void fbLogin(WebDriver driver) {
		driver.get("https://www.facebook.com");
		if (driver.getTitle().equals("Facebook – log in or sign up")) {
			System.out.println("Facebook page is successfully loaded");
			driver.findElement(By.id("email")).sendKeys("mahesh@yahoo.com");
			driver.findElement(By.id("pass")).sendKeys("Test@123");
			driver.findElement(By.name("login")).click();
			System.out.println(driver.getTitle());
			if (driver.getTitle().equals("Facebook")) {
				System.out.println("User successfully logged in");
			} else {
				System.out.println("Login failed. Please try again");
			}
		}else {
			System.out.println("Incorrect URL");
		}
	}

	void nagivateWebPages(WebDriver driver) throws InterruptedException{
		driver.navigate().to("https://www.google.com");
		Thread.sleep(5000);
		verifyTitle(driver, "Google");
		driver.navigate().back();
		Thread.sleep(5000);
		verifyTitle(driver, "Facebook");
		driver.navigate().forward();
		Thread.sleep(5000);
		verifyTitle(driver, "Google");
		driver.navigate().refresh();
		Thread.sleep(5000);
		verifyTitle(driver, "Google");
	}
	
	void verifyTitle(WebDriver driver, String title) {
		if (driver.getTitle().equals(title)) {
			System.out.println("User is on webpage " + title);
		} else {
			System.out.println("User is not on webpage " + title);
		}
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Navigation navigation = new Navigation();
		navigation.fbLogin(driver);
		try {
		navigation.nagivateWebPages(driver);
		}catch(InterruptedException ie) {
			System.out.println("Interrupted Exception encountered");
		}finally {
			driver.close();
		}
	}
}
