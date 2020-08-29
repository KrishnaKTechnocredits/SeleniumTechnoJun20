package aavruti;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import aavruti.base.PredefinedActions;

public class BrowserHandling extends PredefinedActions{

	WebDriver driver;
	WebDriverWait wait;

	void launchBrowser() {
		driver = start("file:///G:/SeleniumVideos/Assignment/New%20Tab%20Open.html");
		wait = new WebDriverWait(driver,15);
	}

	//Print page title and then close that window & open next window for same operation.
	void windowHandling() {

		int buttonCount = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button"))).size();

		for(int index=1;index<=buttonCount;index++) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[" + index + "]/a"))).click();

			String mainWindow = driver.getWindowHandle();			
			String buttonName = driver.findElement(By.xpath("//button[" + index + "]/a")).getText();

			Set<String> windowSessionIds = driver.getWindowHandles();		
			Iterator<String> itr = windowSessionIds.iterator();

			while(itr.hasNext()) {
				String newTab = itr.next();
				if(!newTab.equals(mainWindow)) {
					driver.switchTo().window(newTab);
					System.out.println(buttonName + " title --> " + driver.getTitle());
				}
			}
			driver.close();
			driver.switchTo().window(mainWindow);
		}
		System.out.println("Main Window --> " + driver.getTitle());
	}

	public static void main(String[] args) {
		BrowserHandling browserHandling = new BrowserHandling();
		
		browserHandling.launchBrowser();
		browserHandling.windowHandling();		
		browserHandling.closeBrowser();
	}

	void closeBrowser() {
		driver.close();
	}
}