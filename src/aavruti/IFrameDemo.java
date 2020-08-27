package aavruti;

import aavruti.base.PredefinedActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IFrameDemo extends PredefinedActions{

	WebDriver driver;
	WebDriverWait wait;
	
	void launchBrowser() {
		driver = start();
		wait = new WebDriverWait(driver,15);
	}
	
	void iFrameVerifications() {
		driver.findElement(By.linkText("IFrame Demo")).click();
		
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//iframe"), 3));
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//a[@id='dropdownButton']")).click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='hero']/h1"))).getText());
		driver.switchTo().parentFrame();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='site3']")));
		driver.findElement(By.xpath("//a[text()='License']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='The Apache Software Foundation']")));
		driver.switchTo().defaultContent();
		
		driver.findElement(By.linkText("Automation By Krishna")).click();
		System.out.println(driver.findElement(By.xpath("//div[@id='indexBody']")).getText());
	}
	
	void closeBrowser() {
		driver.quit();
	}
	
	public static void main(String[] args) {
		IFrameDemo iFrameDemo = new IFrameDemo();
		iFrameDemo.launchBrowser();
		iFrameDemo.iFrameVerifications();
		iFrameDemo.closeBrowser();
	}
}
