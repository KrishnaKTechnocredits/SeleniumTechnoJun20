/*Assignment 13 :  28th Aug 2020

1) Print page title and then close that window & open next window for same operation.*/
package palak;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import palak.base.PredefinedActions;

public class MultiTabHandle extends PredefinedActions {
	WebDriver driver;
	void setUp(String url) {
		driver = start(url);
	}
	
	void handlingMultiTab() {
		String mainTab = driver.getWindowHandle();
		int buttonCount = driver.findElements(By.xpath("//button")).size();
		for(int index = 1; index <= buttonCount; index++) {
			driver.findElement(By.xpath("//button["+index+"]")).click();
			System.out.println("\nClicking on -> "+driver.findElement(By.xpath("//button["+index+"]")).getText());
			Set<String> multiTab =driver.getWindowHandles();
			Iterator<String>itr = multiTab.iterator();
			while (itr.hasNext()) {
				String currentTab = itr.next();
				if (!mainTab.equals(currentTab)) {
					driver.switchTo().window(currentTab);
					String text = "Current Title is : "+driver.getTitle();
					System.out.println(text);
					driver.close();
				}
			}
			driver.switchTo().window(mainTab);
		}
		System.out.println("Current Window ->"+driver.getTitle());
		}
		
	public static void main(String[] args) {
		MultiTabHandle multiTabHandle =  new MultiTabHandle();
		multiTabHandle.setUp("H:/TechnoCredits/New Tab Open.html");
		multiTabHandle.handlingMultiTab();
	}
}
