package selenium.testng.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DemoTestNG {
	
	@Test
	public void firstTest() {
		String osName = System.getProperty("os.name").toLowerCase();
		String path = osName.contains("windows") ? "./resources/windows/chromedriver.exe"
				: osName.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
	}
	
	@Test
	public void secondTest() {
		String osName = System.getProperty("os.name").toLowerCase();
		String path = osName.contains("windows") ? "./resources/windows/chromedriver.exe"
				: osName.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
	}
}
