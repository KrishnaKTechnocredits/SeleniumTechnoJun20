package barkha;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import barkha_base.PredefinedFunctions;

public class MouseHover extends PredefinedFunctions {
	
	WebDriver driver;
	
	void setUp(String URL) {
		driver=start(URL);
	}
	
	void mouseHoverOnElement() {
		Actions actions=new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//div[@id='nav-tools']/a[2]"))).build().perform();
		
		driver.findElement(By.xpath("//span[text()='Your Account']")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='a-row a-spacing-base']/h1")));
		
		String actualText=driver.findElement(By.xpath("//div[@class='a-row a-spacing-base']/h1")).getText();
		
		if (actualText.equals("Your Account"))
			System.out.println("Test Case PASS: Successfully landed to Account Screen (Mouse Hover Successfully).");
		else
			System.out.println("Test Case FAIL.");
	}
	
	void tearDown() {
		driver.close();
	}
	
	public static void main(String[] args) {
		MouseHover mouseHover=new MouseHover();
		mouseHover.setUp("https://www.amazon.in/");
		mouseHover.mouseHoverOnElement();
		mouseHover.tearDown();
	}
}