package aditi;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import aditi.base.PredefinedActions;

public class ActionDoubleClick extends PredefinedActions {

	WebDriver driver;
	WebDriverWait wait;

	void setUp() {
		driver = start();
		// wait = new WebDriverWait(driver,15);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@id='basicelements']")).click();
	}

	void performDoubleClick() {
		wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@ondblclick='doubleClick()']"))));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
		String expectedText = "You successfully double clicked it";
		Actions actions = new Actions(driver);
		actions.doubleClick(element).build().perform();
		Alert alert = driver.switchTo().alert();
		String actualText = alert.getText();
		alert.accept();
		System.out.println(actualText.equals(expectedText)
				? "Test Pass -> Successfully double clicked \nAlertBox Text -  " + actualText
				: "Test Fail ");
	}

	void closeBrowser() {
		driver.quit();
	}

	public static void main(String[] args) {
		ActionDoubleClick dragAndDropAction = new ActionDoubleClick();
		dragAndDropAction.setUp();
		dragAndDropAction.performDoubleClick();
		dragAndDropAction.closeBrowser();
	}
}