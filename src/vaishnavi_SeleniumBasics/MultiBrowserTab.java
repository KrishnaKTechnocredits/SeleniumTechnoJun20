package vaishnavi_SeleniumBasics;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import vaishnavi_Base.PredefinedAction;

public class MultiBrowserTab extends PredefinedAction {
	WebDriver driver;

	void setUp() {
		driver = start("file:///C:/Users/vaish/Desktop/Selenium_Recordings/New%20Tab%20Open.html");
	}

	void handleMultiBrowsers() {
		try {
			String mainWindow = driver.getWindowHandle(); // will return session Id for main window

			driver.findElement(By.xpath("//a[@value='Google']")).click();
			driver.findElement(By.xpath("//a[@value='ThoughtWorks']")).click();
			driver.findElement(By.xpath("//a[@value='FB']")).click();
			driver.findElement(By.xpath("//a[@value='Amazon']")).click();
			driver.findElement(By.xpath("//a[@value='Flipkart']")).click();
			driver.findElement(By.xpath("//a[@value='Nvidia']")).click();
			driver.findElement(By.xpath("//a[@value='VMware']")).click();

			Set<String> multiWindow = driver.getWindowHandles(); // return set of string
			System.out.println("Please find below all the titles of windows open: ");

			Iterator<String> itr = multiWindow.iterator();

			while (itr.hasNext()) {
				String currentBrowser = itr.next();
				if (!currentBrowser.equals(mainWindow)) {
					driver.switchTo().window(currentBrowser);
					System.out.println(driver.getTitle());
					driver.close();
				}
			}
			driver.switchTo().window(mainWindow);
		} catch (NoSuchElementException ne) {
			ne.printStackTrace();
		} finally {
			driver.close();
		}
	}

	public static void main(String[] args) {
		MultiBrowserTab multiBrowserTab = new MultiBrowserTab();
		multiBrowserTab.setUp();
		multiBrowserTab.handleMultiBrowsers();
	}

}
