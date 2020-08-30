package nikhil;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import nikhil.base.PreDefinedActions;

public class MultiTabHandling extends PreDefinedActions {
	WebDriver driver;
	
	void setUp(String url) {
		driver = start(url);
	}
	
	void tearDown() {
		driver.close();
	}
	
	void tabHandling() {
		String mainWindow = driver.getWindowHandle();
		int buttonNos = driver.findElements(By.xpath("//button")).size();
		System.out.println("\nThe Names of the Buttons and Titles of the Tabs which are getting opened by clicking them:-\n");
		for(int index = 1; index <= buttonNos; index++) {
			WebElement btn = driver.findElement(By.xpath("//button["+index+"]"));
			String btnName = btn.getText();
			btn.click();
			Set<String> multiTab = driver.getWindowHandles();
			Iterator<String> itr = multiTab.iterator();
			while(itr.hasNext()) {
				String currentTab = itr.next();
				if(!currentTab.equals(mainWindow)) {
					driver.switchTo().window(currentTab);
					System.out.println(btnName+" ---> "+driver.getTitle());
				}
			}
			driver.close();
			driver.switchTo().window(mainWindow);
		}
	}
	
	public static void main(String[] args) {
		MultiTabHandling multiTabHandling = new MultiTabHandling();
		multiTabHandling.setUp("C:\\Users\\Nikhil.Nikhil-Universe.000\\Desktop\\New Tab Open.html");
		multiTabHandling.tabHandling();
		multiTabHandling.tearDown();
	}
}
