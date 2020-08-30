package barkha;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import barkha_base.PredefinedFunctions;

public class DoubleClick extends PredefinedFunctions {

	WebDriver driver;
	WebDriverWait wait;

	void setUp() {
		driver = start();
	}

	void navigateToPage() {
		driver.findElement(By.xpath("//a[@id='basicelements']")).click();

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@name='ufname']")));
	}

	void doubleClick() {
		// Scroll down to double click element
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()",
				driver.findElement(By.xpath("//a[text()='Double-click on me']")));

		Actions actions = new Actions(driver);
		actions.doubleClick(driver.findElement(By.xpath("//a[text()='Double-click on me']"))).build().perform();
		
		//Handle Alert after double click
		Alert alert=driver.switchTo().alert();
		String actualText=alert.getText();
		alert.accept();
		
		if (actualText.equals("You successfully double clicked it"))
			System.out.println("Test case PASS: Successfully click on Double.");
		else
			System.out.println("Test case FAIL.");
	}

	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) {
		DoubleClick doubleClick = new DoubleClick();
		doubleClick.setUp();
		doubleClick.navigateToPage();
		doubleClick.doubleClick();
		doubleClick.tearDown();
	}
}