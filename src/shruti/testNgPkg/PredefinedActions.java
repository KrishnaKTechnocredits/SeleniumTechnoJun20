package shruti.testNgPkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PredefinedActions {

	@Test
	public void firstTest() {
		String expectedTitle = "Login Signup Demo1";
		String osName = System.getProperty("os.name").toLowerCase();
		String path = osName.contains("windows") ? "./resources/windows/chromedriver.exe": osName.contains("mac") ? "./resources/mac/chromedriver": null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationbykrishna.com/");
		String actualTitle = driver.getTitle();

		Assert.assertEquals(actualTitle, expectedTitle);// Assert-TestNg class static method
		//System.out.println("Website open successfully");
	}
}
