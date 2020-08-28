package amita;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amita.base.PredefinedActions;

public class MouseHoverAction extends PredefinedActions{
	
	WebDriver driver;

	void setUp() {
		driver = start("https://www.amazon.in/");
	}
	
	void mouseHover() {
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//a[@id='nav-link-accountList']"));
		actions.moveToElement(element).build().perform();
		driver.findElement(By.xpath("//span[text()='Your Account']")).click();
		System.out.println("TEST PASS: Successfully Mouse Hovered.\nThe Title is : "+ driver.getTitle());		
	}
	
	void closeBrowser() {
		driver.quit();
	}
	public static void main(String[] args) {
		MouseHoverAction mouseHoverAction = new MouseHoverAction();
		mouseHoverAction.setUp();
		mouseHoverAction.mouseHover();
		mouseHoverAction.closeBrowser();	
	}
}