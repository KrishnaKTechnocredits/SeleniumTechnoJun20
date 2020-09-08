package aavruti.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		return start("http://automationbykrishna.com");
	}
	
	public WebDriverWait driverWait() {
		return new WebDriverWait(driver,50);
	}
}
