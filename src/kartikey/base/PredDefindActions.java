package kartikey.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PredDefindActions {
	
	public final WebDriver start(String url) {
		String os= System.getProperty("os.name").toLowerCase();
		String path= os.contains("windows")? ".//resources//windows//chromedriver.exe" :os.contains("mac")? ".//resources//mac//chromedriver": null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver= new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		return driver;
	}

}
