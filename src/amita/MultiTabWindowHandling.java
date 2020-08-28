/*Assignment 13 :  28th Aug 2020

1) Print page title and then close that window & open next window for same operation.*/
package amita;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import amita.base.PredefinedActions;

public class MultiTabWindowHandling extends PredefinedActions{
	
	WebDriver driver;

	void setUp() {
		driver = start("file:///D:/JAVA-JUNE20/HTML%20Files/New%20Tab%20Open.html");
	}
	
	void multiTabWindow() {
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		int totalButton = driver.findElements(By.xpath("//button")).size();
		System.out.println("Total buttons in Main Page : "+totalButton);
		
		String mainWindow = driver.getWindowHandle();
		for(int index=1; index<=totalButton; index++) {
			driver.findElement(By.xpath("//button["+index+"]")).click();
			String buttonname = driver.findElement(By.xpath("//button["+index+"]")).getText();

			Set<String> multiTab = driver.getWindowHandles();
			Iterator<String> itr = multiTab.iterator();
			while(itr.hasNext()){
				String currentTab = itr.next();
				if(!currentTab.equals(mainWindow)){
					driver.switchTo().window(currentTab);
					System.out.println(buttonname+"-->  Title is : "+driver.getTitle());
				}				
			}
			driver.close();
			driver.switchTo().window(mainWindow);		
		}
		System.out.println("Main window Title is :"+driver.getTitle());
	}
		
	void closeBrowser() {
		driver.quit();
	}

	public static void main(String[] args) {
		MultiTabWindowHandling multiTabWindowHandling = new MultiTabWindowHandling();
		multiTabWindowHandling.setUp();
		multiTabWindowHandling.multiTabWindow();
		multiTabWindowHandling.closeBrowser();
	}
}