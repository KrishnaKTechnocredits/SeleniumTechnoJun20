package deavina;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment2 {
	public static void main(String[] args) throws InterruptedException {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : " + os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.facebook.com/");
		if ((driver.getTitle()).equals("Facebook – log in or sign up")) {
			System.out.println("Title of the page is Facebook-log in or sign up");
		}
		driver.findElement(By.id("email")).sendKeys("neetu2856@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("Jan@2019");
		driver.findElement(By.name("login")).click();
		Thread.sleep(10000);
		if ((driver.getTitle()).equals("Facebook")) {
			System.out.println("Login successfully on Facebook");
		}
		driver.navigate().to("http://www.google.com");
		driver.navigate().back();
		if ((driver.getTitle()).equals("Facebook")) {
			System.out.println("We are back on Facebook login page");
		}
		driver.navigate().forward();
		if ((driver.getTitle()).equals("Google")) {
			System.out.println("We are back on Google login page");
		}
		driver.navigate().refresh();
		if ((driver.getTitle()).equals("Google")) {
			System.out.println("we are on same page after refresh ");
		}

		Thread.sleep(4000);
		driver.close();
	}
}