package vaishnavi_SeleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import vaishnavi_Base.PredefinedAction;

public class MouseHover extends PredefinedAction {
	private WebDriver driver;

	void setUp() {
		driver = start("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	void performMouseHover() {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(By.xpath("//a[@id='nav-link-accountList']"))).build().perform();
			driver.findElement(By.xpath("//span[text()='Your Account']")).click();

		} catch (NoSuchElementException ne) {
			ne.printStackTrace();
		} finally {
			driver.close();
		}
	}

	public static void main(String[] args) {
		MouseHover mouseHover = new MouseHover();
		mouseHover.setUp();
		mouseHover.performMouseHover();
	}

}
