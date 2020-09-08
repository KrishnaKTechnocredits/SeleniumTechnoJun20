package ajit.testScripts;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import ajit.base.PredefinedActions;

public class MultipleBrowserHandling extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	void setUp() {
		driver = start("file:///D:/JAVA_LEARNING/Projects/TechnoGitProject/New_Tab_Open.html");
	}

	void multiBrowserSwitch() {
		String mainWindow = driver.getWindowHandle();
		System.out.println(" Main window title is " + driver.getTitle());

		List<WebElement> ListOfButtons = driver.findElements(By.xpath("//button/a"));
		for (WebElement buttonlink : ListOfButtons) {
			String buttonName = buttonlink.getText();
			buttonlink.click();
			Set<String> multiTab = driver.getWindowHandles();
			for (String browser : multiTab) {
				if (!browser.equals(mainWindow)) {
					driver.switchTo().window(browser);
					wait = new WebDriverWait(driver, 10);
					System.out.println(buttonName + "' Webpage Title: '" + driver.getTitle() + "'");
					driver.close();
					driver.switchTo().window(mainWindow);
				}
			}
		}
	}

	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) {
		MultipleBrowserHandling multipleBrowserHandling = new MultipleBrowserHandling();
		multipleBrowserHandling.setUp();
		multipleBrowserHandling.multiBrowserSwitch();
		multipleBrowserHandling.tearDown();
	}

}
