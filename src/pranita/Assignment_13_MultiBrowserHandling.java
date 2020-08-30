package pranita;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pranita.basic.PredefinedFunctions;

public class Assignment_13_MultiBrowserHandling extends PredefinedFunctions {
	public static void main(String[] args) {
		Assignment_13_MultiBrowserHandling assignment_13_MultiBrowserHandling = new Assignment_13_MultiBrowserHandling();
		assignment_13_MultiBrowserHandling.setUp("file:///C:/Users/Shashank/Downloads/New%20Tab%20Open.html");
		assignment_13_MultiBrowserHandling.multiTabHandling();
		assignment_13_MultiBrowserHandling.tearDown();
	}

	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	void tearDown() {
		driver.quit();
	}

	void multiTabHandling() {

		int buttonCount = driver.findElements(By.xpath("//button")).size();
		String mainWindow = driver.getWindowHandle();

		System.out.println("Button Names And The Titles of the Tabs are : ");
		for (int index = 1; index <= buttonCount; index++) {
			driver.findElement(By.xpath("//button[" + index + "]")).click();
			String buttonname = driver.findElement(By.xpath("//button[" + index + "]")).getText();

			Set<String> multiTab = driver.getWindowHandles();
			Iterator<String> itr = multiTab.iterator();
			while (itr.hasNext()) {
				String currentTab = itr.next();
				if (!currentTab.equals(mainWindow)) {
					driver.switchTo().window(currentTab);
					System.out.println(buttonname + " and  Title : " + driver.getTitle());
				}
			}
			driver.close();
			driver.switchTo().window(mainWindow);
		}
	}

}
