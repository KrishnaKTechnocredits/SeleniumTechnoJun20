package anup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import anup.basics.PredefinedActions;

public class IFrameValidations extends PredefinedActions{
	
	WebDriver driver;

	void setUp() {
		driver = start();
	}
	
	void validateIframe(){
		
		driver.findElement(By.linkText("IFrame Demo")).click();
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@src='http://www.seleniumhq.org']")));
		
		driver.switchTo().frame("site1");
		driver.findElement(By.xpath("//a[text()='MENU +']")).click();
		driver.findElement(By.xpath("//a[@href='/projects']")).click();
		System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='hero']/h1"))).getText());
		
		//driver.switchTo().defaultContent();
		driver.switchTo().parentFrame();
		
		driver.switchTo().frame(2);
		driver.findElement(By.xpath("//a[@href='https://www.apache.org/licenses/']")).click();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[@href='http://automationbykrishna.com']")).click();
		System.out.println(driver.findElement(By.xpath("//div[@id='indexBody']")).getText());	
	}
	
	void closeBrowser() {
		driver.quit();
	}
	
	public static void main(String[] args) {
		IFrameValidations iFrameValidations = new IFrameValidations();
		iFrameValidations.setUp();
		iFrameValidations.validateIframe();
		iFrameValidations.closeBrowser();	
	}
}
