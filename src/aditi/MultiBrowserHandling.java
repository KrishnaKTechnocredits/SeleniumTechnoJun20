package aditi;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import aditi.base.PredefinedActions;

public class MultiBrowserHandling extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	void setUp(String url) {
		driver = start(url);
		wait = new WebDriverWait(driver, 15);
	}

	void displayTitleOfTabs() {
		try {
			int buttonCount = driver.findElements(By.xpath("//button")).size();
			String mainWindow = driver.getWindowHandle();
			for (int index = 1; index <= buttonCount; index++) {
				WebElement button = driver.findElement(By.xpath("//button[" + index + "]/a"));
				String buttonName = button.getText();
				button.click();
				Set<String> multiTab = driver.getWindowHandles();
				Iterator<String> itr = multiTab.iterator();
				while (itr.hasNext()) {
					String currentTab = itr.next();
					if (!currentTab.equals(mainWindow)) {
						driver.switchTo().window(currentTab);
						System.out.println(buttonName + " title ->  " + driver.getTitle());
						driver.close();
					}
				}
				driver.switchTo().window(mainWindow);
			}
		} catch (NoSuchElementException noSuchElementException) {
			noSuchElementException.printStackTrace();
		} finally {
			driver.close();
		}
	}

	public static void main(String[] args) {
		MultiBrowserHandling multiBrowserHandling = new MultiBrowserHandling();
		multiBrowserHandling
				.setUp("file:///Users/apple/Documents/Class_Sessions/PracticeWebPages/New%20Tab%20Open.html");
		multiBrowserHandling.displayTitleOfTabs();
	}
}
