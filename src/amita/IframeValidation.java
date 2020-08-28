package amita;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amita.base.PredefinedActions;

public class IframeValidation extends PredefinedActions{
	
	WebDriver driver;

	void setUp() {
		driver = start();	
	}
	
	void validateIframe(){
		//Click on IFrame Demo
		driver.findElement(By.linkText("IFrame Demo")).click();
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@src='http://www.seleniumhq.org']")));
		
		//switch to iframe1
		driver.switchTo().frame("site1");
		//Click on Menu
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='MENU +']")));
		driver.findElement(By.xpath("//a[text()='MENU +']")).click();
		System.out.println("Switched to iframe-1 and Clicked on Menu ");
		driver.findElement(By.xpath("//a[@href='/projects']")).click();
		System.out.println("Clicked on projects ");
		System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='hero']/h1"))).getText());
		
		//Switch to main page
		//driver.switchTo().defaultContent();
		driver.switchTo().parentFrame();
		
		//switch to iframe3
		driver.switchTo().frame(2);
		driver.findElement(By.xpath("//a[@href='https://www.apache.org/licenses/']")).click();
		System.out.println("Switched to iFrame-3 Clicked on license ");
		
		//switch to main page
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[@href='http://automationbykrishna.com']")).click();
		System.out.println("Switched to main page and Clicked on automationbykrishna link");
		System.out.println(driver.findElement(By.xpath("//div[@id='indexBody']")).getText());	
	}
	
	void closeBrowser() {
		driver.quit();
	}

	public static void main(String[] args) {
		IframeValidation iframeValidation = new IframeValidation();
		iframeValidation.setUp();
		iframeValidation.validateIframe();
		iframeValidation.closeBrowser();
	}
}
