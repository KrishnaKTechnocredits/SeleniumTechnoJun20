/*
 Assignment-11
 Switching between Frames
 */
package ajit.testScripts;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ajit.base.PredefinedActions;

public class iFrameConcept extends PredefinedActions {
	
	private WebDriver driver;
	
	void setUp(String url) {
		driver = start(url);
	}	

	void iFrameSwitching() {
		driver.findElement(By.linkText("IFrame Demo")).click();
		WebDriverWait wait=new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@name='site1']")));
		
		//Switch to First Frame
		driver.switchTo().frame(0);
		System.out.println("Successfully Switched to 1st Frame");
		driver.findElement(By.xpath("//a[text() ='MENU +']")).click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='hero']/h1")));
		System.out.println("Header of Project under First Frame: "+(driver.findElement(By.xpath("//section[@class='hero']/h1")).getText()));
		
		//switch to Second Frame
		driver.switchTo().parentFrame();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='site3']")));
		System.out.println("Successfully Switched to 2nd Frame");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/a[text()='License']")));
		driver.findElement(By.xpath("//li/a[text()='License']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='The Apache Software Foundation']")));
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[text()='Automation By Krishna']")).click();
		System.out.println("Successfully Switched to default Page");
		
		//Verify Content of Default Page
		System.out.println("Content Of Main Page: "+(driver.findElement(By.xpath("//div[@id='indexBody']")).getText()));		
		
	}
	
	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) {
		iFrameConcept iframeConcept=new iFrameConcept();
		iframeConcept.setUp("http://automationbykrishna.com/");
		iframeConcept.iFrameSwitching();
		iframeConcept.tearDown();

	}

}
