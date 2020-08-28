package raj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import raj.selenium.base.PredefinedActions;

public class ActionMouseHover extends PredefinedActions {
	WebDriver driver;

	void setUp() {
		driver = start("https://www.amazon.in/");
		driver.manage().window().maximize();
	}

	void mouseHover() {

		//WebDriverWait wait = new WebDriverWait(driver, 15);
		
		Actions actions = new Actions(driver);
		WebElement targetElement = driver.findElement(By.xpath("//a[@id='nav-link-accountList']"));
		actions.moveToElement(targetElement).build().perform();
		driver.findElement(By.xpath("//span[text()='Your Account']")).click();
		System.out.println("Mouse Hover Test Passed...!!");
		System.out.println("Title is : " + driver.getTitle());
	}

	void closeBrowser() {
		driver.quit();
	}
	public static void main(String[] args) {
		ActionMouseHover mouseHover = new ActionMouseHover();
		mouseHover.setUp();
		mouseHover.mouseHover();
		mouseHover.closeBrowser();	
	}

}
