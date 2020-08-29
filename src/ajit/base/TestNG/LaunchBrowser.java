package ajit.base.TestNG;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LaunchBrowser {

	@Test
	public void Setup() {
		String expectedTitle="Login Signup Demo";
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : "+ os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;

		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com/");
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("AutomationByKrishna website open successfully.");
		System.out.println("Test Executed using TestNG XML File");
		driver.quit();
	}
	
	@Test
	void SecondTest() {
		System.out.println("2nd Test Executed");

	}
}
