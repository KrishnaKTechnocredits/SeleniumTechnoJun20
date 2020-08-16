package aditi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoTest {

	public static void main(String[] args) {
		String os = System.getProperty("os.name");
		System.out.println("os : "+ os);
		String path = os.equalsIgnoreCase("window") ? "./resources/windows/chromedriver"
				: os.contains("Mac") ? "./resources/mac/chromedriver" : null;

		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com/");
		System.out.println("AutomationByKrishna website open successfully.");
		driver.quit();
	}
}
