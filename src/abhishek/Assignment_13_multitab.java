package abhishek;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import abhishek.base.PredefinedActions;

public class Assignment_13_multitab extends PredefinedActions {

	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	void handlingMultiTab() {
		String mainTab = driver.getWindowHandle();
		int buttonCount = driver.findElements(By.xpath("//button")).size();
		for (int index = 1; index <= buttonCount; index++) {
			driver.findElement(By.xpath("//button[" + index + "]")).click();
			
			System.out.println("Clicking on -> " + driver.findElement(By.xpath("//button[" + index + "]")).getText());
			Set<String> multiTab = driver.getWindowHandles();
			Iterator<String> itr = multiTab.iterator();
			while (itr.hasNext()) {
				String currentTab = itr.next();
				if (!mainTab.equals(currentTab)) {
					driver.switchTo().window(currentTab);
					String text = "Current Title is : " + driver.getTitle();
					System.out.println(text);
					//driver.close();
				}
			}
			driver.close();
			driver.switchTo().window(mainTab);
		}
		System.out.println("Current Window ->" + driver.getTitle());
	}

	void closeBrowser() {
		driver.close();
	}
	public static void main(String[] args) {
		Assignment_13_multitab multiTabHandle = new Assignment_13_multitab();
		multiTabHandle.setUp("file:///Users/adityashivankar/Downloads/New%20Tab%20Open.html");
		multiTabHandle.handlingMultiTab();
		multiTabHandle.closeBrowser();
	}

}
