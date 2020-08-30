package aashtha.testNGDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTestNG {
	WebDriver driver;

	void setUp() {
		String os = System.getProperty("os.name").toLowerCase();
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com/");
	}

	void tearDown() {
		driver.close();
	}
	
	@Test(priority=1)
	void verifyTitle() {
		setUp();
		String actualTitle = driver.getTitle();
		String expectedTitle = "Login Signup Demo";
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Test(priority=2)
	void verifyTextOnPage() {
		String expectedText = "This is Maulik.";
		String actualText = driver.findElement(By.xpath("//div[@id='indexBody']")).getText().trim();
		Assert.assertEquals(actualText, expectedText);
		tearDown();
	}
}
