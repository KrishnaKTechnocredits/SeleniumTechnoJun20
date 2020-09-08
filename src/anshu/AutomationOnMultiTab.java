package anshu;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import anshu.base.PredefinedProperty;

public class AutomationOnMultiTab extends PredefinedProperty {
	WebDriver driver;

	void setUp() {
		driver = start("file:///C:/Users/hp/Downloads/New%20Tab%20Open.html");
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
	}

	void tearDown() {
		driver.quit();
	}

	void mutiTabNBrowser() {

		String mainWindow = driver.getWindowHandle();
		System.out.println(" Main window title is " + driver.getTitle());

		int buttonSize = driver.findElements(By.xpath("//button")).size();
		for (int index = 1; index < buttonSize; index++) {
			WebElement buttonName = driver.findElement(By.xpath("//button[" + index + "]"));
			String textOnButton = buttonName.getText();
			buttonName.click();

			Set<String> multiTab = driver.getWindowHandles();
			Iterator<String> itr = multiTab.iterator();
			while (itr.hasNext()) {
				String currentTab = itr.next();
				if (!currentTab.equals(mainWindow)) {
					driver.switchTo().window(currentTab);
					System.out.println( textOnButton + " Title is ----> " + driver.getTitle());
				}
			}
			driver.close();
			driver.switchTo().window(mainWindow);
		}
	}

	public static void main(String[] args) {
		AutomationOnMultiTab mutilTabHandle = new AutomationOnMultiTab();
		mutilTabHandle.setUp();
		mutilTabHandle.mutiTabNBrowser();
		mutilTabHandle.tearDown();
	}
}
