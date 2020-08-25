package aditi.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PredefinedActions {
	public WebDriver start(String url) {
		String osName = System.getProperty("os.name").toLowerCase();
		String path = osName.contains("windows") ? "./resources/windows/chromedriver.exe"
				: osName.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}
	
//	void navigateDemoTable() {
//		driver.findElement(By.linkText("Demo Tables")).click();
//		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
//	}
//	
//	void tearDown() {
//		driver.close();
//	}
	
}
