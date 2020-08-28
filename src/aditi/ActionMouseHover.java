package aditi;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import aditi.base.PredefinedActions;

public class ActionMouseHover extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	void setUp(String url) {
		driver = start(url);
		wait = new WebDriverWait(driver, 5);
	}

	void performMouseHover() {
		System.out.println("Mouse Hover");
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//a[@id='nav-link-accountList']"))).build().perform();
		driver.findElement(By.xpath("//span[text()='Your Account']")).click();
		System.out.println(driver.getTitle());
	}

	void closeBrowser() {
		driver.quit();
	}

	public static void main(String[] args) {
		ActionMouseHover actionMouseHover = new ActionMouseHover();
		actionMouseHover.setUp("https://www.amazon.com/");
		actionMouseHover.performMouseHover();
		actionMouseHover.closeBrowser();
	}
}
