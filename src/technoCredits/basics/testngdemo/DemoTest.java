package technoCredits.basics.testngdemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTest {

	@Test
	public void firstTest() {
		String expectedTitle = "Login Signup Demo1";
		
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : "+ os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;

		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com/");
		String actualTitle = driver.getTitle();
		System.out.println("------Before");
		
		System.out.println("AutomationByKrishna website open successfully.");
		driver.quit();
		Assert.assertEquals(actualTitle, expectedTitle); // Hard Assert
	}
}
