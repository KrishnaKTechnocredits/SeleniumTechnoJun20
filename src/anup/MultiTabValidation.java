package anup;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import anup.basics.PredefinedActions;

public class MultiTabValidation extends PredefinedActions{
	
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}
	
	void multiTab() {
		
		int buttonCount = driver.findElements(By.xpath("//button")).size();
		String mainWindow = driver.getWindowHandle();
		
		System.out.println("Button Names And The Titles of the Tabs are : ");
		for(int index=1; index<=buttonCount; index++) {
			driver.findElement(By.xpath("//button["+index+"]")).click();
			String buttonname = driver.findElement(By.xpath("//button["+index+"]")).getText();

			Set<String> multiTab = driver.getWindowHandles();
			Iterator<String> itr = multiTab.iterator();
			while(itr.hasNext()){
				String currentTab = itr.next();
				if(!currentTab.equals(mainWindow)){
					driver.switchTo().window(currentTab);
					System.out.println(buttonname+" and  Title : "+driver.getTitle());
				}				
			}
			driver.close();
			driver.switchTo().window(mainWindow);		
		}
	}
	void closeBrowser() {
		driver.quit();
	}
	
	public static void main(String[] args) {
		MultiTabValidation multiTabValidation = new MultiTabValidation();
		multiTabValidation.setUp("file:///Users/amitarout/Desktop/TechnoGitProject/html/New%20Tab%20Open.html");
		multiTabValidation.multiTab();
		multiTabValidation.closeBrowser();
	}
}
