package madhura;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import madhura.base.PredefinedActions;

/*Assignment 13 :  28th Aug 2020

1) Print page title and then close that window & open next window for same operation.*/
public class MultipleWindows extends PredefinedActions {
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	void multiWindowHandling() {
		String mainWin = driver.getWindowHandle();
		System.out.println("Main window is : " + driver.getTitle());
		int totalButtons = driver.findElements(By.xpath("//button")).size();
		//for loop for all buttons to be clicked
		for (int index = 1; index <= totalButtons; index++) {
			driver.findElement(By.xpath("//button[" + index + "]")).click();
			Set<String> multiWin = driver.getWindowHandles();
		//iterator to switch the windows and get title of the new window
			Iterator<String> itr = multiWin.iterator();
			while (itr.hasNext()) {
				String currentBrowser = itr.next();
				if (!currentBrowser.equals(mainWin)) {
					driver.switchTo().window(currentBrowser);
					System.out.println("\n" + driver.getTitle());
					driver.close();
				}
			}
		//switching back to main window
			driver.switchTo().window(mainWin);
		}
	}

	public static void main(String[] args) {
		MultipleWindows multipleWindows = new MultipleWindows();
		multipleWindows.setUp("file:///E:/TechnoCredits/Notes/New%20Tab%20Open.html");
		multipleWindows.multiWindowHandling();
	}
}