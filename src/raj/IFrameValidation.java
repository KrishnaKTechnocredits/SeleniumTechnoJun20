package raj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import raj.selenium.base.PredefinedActions;

public class IFrameValidation extends PredefinedActions{
	WebDriver driver;
	WebDriverWait wait;
	
	void setUp() {
		driver = start("http://automationbykrishna.com/");
		 wait = new WebDriverWait(driver, 15);
	}
	
	void validateIframe() {
		
		driver.findElement(By.linkText("IFrame Demo")).click();
		
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[text() = 'MENU +']")));
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//iframe"), 3));
		//Switch to frame 1
		driver.switchTo().frame(0);
		
		//Click on menu and then click on Project Link
		driver.findElement(By.xpath("//a[text() = 'MENU +']")).click();
		driver.findElement(By.xpath("//a[text() = 'Projects']")).click();
		
		String element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='hero']/h1"))).getText();
		// Print the Header text
		System.out.println(element);
		//Switch to main screen
		driver.switchTo().defaultContent();
		
		//Switch to frame 3
		driver.switchTo().frame(2);
		//Click on license
		driver.findElement(By.linkText("License")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt = 'The Apache Software Foundation']")));
		//Switch to main page
		driver.switchTo().defaultContent();

		driver.findElement(By.linkText("Automation By Krishna")).click();
		System.out.println(driver.findElement(By.xpath("//div[@id = 'indexBody']")).getText());
		
		driver.close();
	}
	
	public static void main(String[] args) {
		IFrameValidation iframe = new IFrameValidation();
		iframe.setUp();
		iframe.validateIframe();
	}
}

