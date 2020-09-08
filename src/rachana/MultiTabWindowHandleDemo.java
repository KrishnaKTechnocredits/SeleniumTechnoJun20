package rachana;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import rachana.base.PredefinedActions;

public class MultiTabWindowHandleDemo extends PredefinedActions{
	WebDriver driver;
	WebDriverWait wait;

	void SetUp() {
		driver = start("file:///G:/Technocredits/TechnoGitSeleniumProject/Assignment13.html");
		wait = new WebDriverWait(driver,15);
	}
	
	void tearDown() {
		driver.close();
	}
	void verifyMultiTabWindow() {
		
		String mainwindow = driver.getWindowHandle();
		int totalbtns = driver.findElements(By.xpath("//button")).size();
		
		for(int index=1;index<=totalbtns;index++) {
			String btnName = driver.findElement(By.xpath("//button["+index+"]")).getText();
			driver.findElement(By.xpath("//button["+index+"]")).click();
			Set<String> multiTab = driver.getWindowHandles();
			Iterator<String> itr = multiTab.iterator();
			
			while(itr.hasNext()){
				String currentTab = itr.next();
				if(!currentTab.equals(mainwindow)){
					driver.switchTo().window(currentTab);
					System.out.println("For "+btnName+" Title is: "+driver.getTitle());
				}				
			}
			driver.close();
			driver.switchTo().window(mainwindow);		
		}
		System.out.println("Main window Title is :"+driver.getTitle());
	}
		
	
	public static void main(String[] args) {
		MultiTabWindowHandleDemo multiwindow = new MultiTabWindowHandleDemo();
		multiwindow.SetUp();
		multiwindow.verifyMultiTabWindow();
		multiwindow.tearDown();
	}

}
