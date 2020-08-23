package amita.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PredefinedActions {

	public WebDriver start(String url) {
	
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : "+ os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
			: os.contains("mac") ? "./resources/mac/chromedriver" : null;

		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		return driver;
	}
	
	public WebDriver start() {
		return start("http://automationbykrishna.com/");		
	}
}