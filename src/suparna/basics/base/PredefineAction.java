package suparna.basics.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PredefineAction {

	public static  WebDriver driver;
	public void setDriver() {
		setDriver("http://automationbykrishna.com/");
		
	}
	public void setDriver(String URL) {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : " + os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("Webdriver.chrome.driver", path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
	}
	
	public void tearDown() {
		driver.close();
	}
	public void  pageScoll()
	{
		
	}
}
