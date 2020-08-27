package aditi.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PredefinedActions {
	WebDriver driver;

	public WebDriver start(String url) {
		String osName = System.getProperty("os.name").toLowerCase();
		String path = osName.contains("windows") ? "./resources/windows/chromedriver.exe"
				: osName.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}

	public WebDriver start() {
		driver = start("http://automationbykrishna.com");
		return driver;
	}

	void navigateDemoTable() {
		driver.findElement(By.linkText("Demo Tables")).click();
	}

	void tearDown() {
		driver.close();
	}
}
