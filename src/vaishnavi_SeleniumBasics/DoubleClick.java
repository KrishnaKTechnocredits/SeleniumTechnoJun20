package vaishnavi_SeleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import vaishnavi_Base.PredefinedAction;

public class DoubleClick extends PredefinedAction {
	private WebDriver driver;

	void setUp() {
		driver = start();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	void performDoubleClick() {
		try {
			driver.findElement(By.linkText("Basic Elements")).click();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement doubleClick = driver.findElement(By.linkText("Double-click on me"));
			js.executeScript("arguments[0].scrollIntoView();", doubleClick);
			Actions action = new Actions(driver);
			// double click
			action.doubleClick(doubleClick).build().perform();

			// handling alert
			Alert alert = driver.switchTo().alert();
			String validate = alert.getText();
			alert.accept();

			if (validate.equals("You successfully double clicked it"))
				System.out.println("Double Click action SuccessFull");
			else
				System.out.println("Double click action failed");
		} catch (NoSuchElementException ne) {
			ne.printStackTrace();
		} finally {
			driver.close();
		}
	}

	public static void main(String[] args) {
		DoubleClick doubleClick = new DoubleClick();
		doubleClick.setUp();
		doubleClick.performDoubleClick();
	}

}
