package aashtha;

import aashtha.base.*;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestMultiTabBrowsersHandle extends PredefinedActions {
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	void tearDown() {
		driver.close();
	}

	void printTitlesOfMultiTabs() {
		String mainWindow = driver.getWindowHandle();
		int totalButtons = driver.findElements(By.xpath("//button")).size();
		System.out.println("Below are the window titles:\n");
		for(int index = 1; index <= totalButtons; index++) {
			driver.findElement(By.xpath("//button[" + index + "]")).click();
			Set<String> multiWindows = driver.getWindowHandles();
			Iterator<String> iterator = multiWindows.iterator();
			while (iterator.hasNext()) {
				String currentBrowser = iterator.next();
				if(!currentBrowser.equals(mainWindow)) {
					driver.switchTo().window(currentBrowser);
					System.out.println(driver.getTitle());
					driver.close();
				}
			}
			driver.switchTo().window(mainWindow);
		}
	}

	public static void main(String[] args) {
		TestMultiTabBrowsersHandle testMultiTabBrowsersHandle = new TestMultiTabBrowsersHandle();
		testMultiTabBrowsersHandle.setUp("file:///D:/JavaBasics_JUN20/Class-Recordings/72-28Aug/New%20Tab%20Open.html");
		testMultiTabBrowsersHandle.printTitlesOfMultiTabs();
		testMultiTabBrowsersHandle.tearDown();
	}
}
