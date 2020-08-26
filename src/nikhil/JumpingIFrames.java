package nikhil;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import nikhil.base.PreDefinedActions;

public class JumpingIFrames extends PreDefinedActions {
	WebDriver driver;
	
	void setUp() {
		driver = start();
		//I have purposefully used implicit wait in this code as due to slow Internet, each web-element was taking a very long time to load. By using implicit wait code becomes simpler. 
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
	}
	
	void tearDown() {
		driver.close();
	}
	
	void navigateTo() {
		driver.findElement(By.linkText("IFrame Demo")).click();
	}
	
	void iFrameOps() {
		//Switching to first iFrame
		driver.switchTo().frame("site1");
		driver.findElement(By.xpath("//a[text()='MENU +']")).click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		System.out.println("Text from iFrame 1 ---> "+driver.findElement(By.xpath("//section[@class='hero']/h1")).getText());
		
		//Switching back to default/main page 
		driver.switchTo().defaultContent();
		
		//Switching to third iFrame
		driver.switchTo().frame(2);
		driver.findElement(By.xpath("//a[text()='License']")).click();
		
		//Switching back to default/main page 
		driver.switchTo().defaultContent();
		
		//Clicking the link and validating navigation to home page
		driver.findElement(By.xpath("//a[text()='Automation By Krishna']")).click();
		System.out.println("\nNavigated to Home Page & Visible text is ---> "+driver.findElement(By.xpath("//div[@id='indexBody']")).getText());
	}
	
	public static void main(String[] args) {
		JumpingIFrames jumpingIFrames = new JumpingIFrames();
		jumpingIFrames.setUp();
		jumpingIFrames.navigateTo();
		jumpingIFrames.iFrameOps();
		jumpingIFrames.tearDown();
	}
}
