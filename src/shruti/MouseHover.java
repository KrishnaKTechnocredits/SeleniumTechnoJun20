package shruti;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import shruti.predefinedActionspkg.PtrdefinedActions;

public class MouseHover extends PtrdefinedActions {
	
	WebDriver driver;
	
	void setUp(){
		driver = start("https://www.amazon.com/");
	}
	void myAccount(){
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//span[@class ='nav-line-2 nav-long-width']"))).build().perform();
		String title = driver.findElement(By.xpath("//span[text()='Your Account']")).getText();
		if(title.equals("Your Account"))
			System.out.println("Test Passed. Title is :"+ title);
		else
			System.out.println("Test Failed. Title is :"+ title);
	}
	public static void main(String[] args) {
		MouseHover mouseHover = new MouseHover();
		mouseHover.setUp();
		mouseHover.myAccount();
	}

}
