package mahesh;

import mahesh.base.PredefinedActions;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MultiWindowTest extends PredefinedActions {
	WebDriver driver;

	void setUp() {
		driver = start("file:///D:/Technocredit/Project/TechnoGitProject/SeleniumTechnoJun20/resources/forms/New%20Tab%20Open.html");
	}

	void multiWindowSwitch() {
		String mainWin = driver.getWindowHandle();
		List<WebElement> linksList = driver.findElements(By.xpath("//button/a"));
		for (WebElement link : linksList) {
			String buttonName = link.getText();
			link.click();
			Set<String> sessionIdList = driver.getWindowHandles();
			for (String browser : sessionIdList) {
				if (!browser.equals(mainWin)) {
					driver.switchTo().window(browser);
					WebDriverWait wait = new WebDriverWait(driver, 10);
					System.out.println("Button Name: '" + buttonName + "' Webpage Title: '" + driver.getTitle() + "'");
					driver.close();
					driver.switchTo().window(mainWin);
				}
			}
		}
	}

	public static void main(String[] args) {
		MultiWindowTest multiWindowTest = new MultiWindowTest();
		multiWindowTest.setUp();
		multiWindowTest.multiWindowSwitch();
		multiWindowTest.driver.close();
	}
}
