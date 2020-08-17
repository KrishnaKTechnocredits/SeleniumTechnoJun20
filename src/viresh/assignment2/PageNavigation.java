package viresh.assignment2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageNavigation {

	void NavigatePage() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", ".//resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		System.out.println("Facebook Login Page Title: " + driver.getTitle());
		// adding valid credentials to facebook login page.
		driver.findElement(By.id("email")).sendKeys("viresh.jangam@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("V!re$h@1986");
		driver.findElement(By.id("u_0_b")).click();
		Thread.sleep(10000);
		// Verifying if facebook is logged in successful.
		if (driver.getTitle().equals("(3) Facebook")) {
			System.out.println("Page Title post login: " + driver.getTitle());
			System.out.println("Facebook Login Successful.");
		}
		// Navigating to Google from Facebook.
		driver.navigate().to("https://www.google.com/");
		System.out.println("Google page title: " + driver.getTitle());

		driver.navigate().back();
		Thread.sleep(5000);
		if (driver.getTitle().equals("(3) Facebook")) {
			System.out.println("Navigated back to Facebook.");
		}
		driver.navigate().forward();
		Thread.sleep(5000);
		String preRefreshTitle = driver.getTitle();
		if (preRefreshTitle.equals("Google")) {
			System.out.println("Navigated to Google again.");
		}

		driver.navigate().refresh();
		Thread.sleep(5000);
		System.out.println("Page refreshed.");
		String postRefreshTitle = driver.getTitle();
		if (postRefreshTitle.equals(preRefreshTitle)) {
			System.out.println("Page Title before refresh: " + preRefreshTitle);
			System.out.println("Page Title after refresh : " + postRefreshTitle);
		}
		driver.close();
	}

	public static void main(String[] args) throws InterruptedException {

		new PageNavigation().NavigatePage();
	}
}
