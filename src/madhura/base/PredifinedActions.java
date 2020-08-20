package madhura.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PredifinedActions {
	public WebDriver start(String url) {
		String os = System.getProperty("os.name").toLowerCase();
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}
}
