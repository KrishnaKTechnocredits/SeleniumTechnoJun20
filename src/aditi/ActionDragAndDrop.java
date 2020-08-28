package aditi;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import aditi.base.PredefinedActions;

public class ActionDragAndDrop extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	Actions actions;

	void setUp(String url) {
		driver = start(url);
		wait = new WebDriverWait(driver, 10);
	}

	void performDragAndDrop() {
		driver.findElement(By.xpath("//a[@href='/kendo-ui/dragdrop/area']")).click();
		actions = new Actions(driver);
		WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement destination = driver.findElement(By.xpath("//div[@class='test1']"));
		// perform drag and drop using actions
		actions.dragAndDrop(source, destination).build().perform();
		System.out.println(
				"Test Pass:\nBox-1 Message:- " + driver.findElement(By.xpath("//div[@class='test1']")).getText());

		WebElement box2 = driver.findElement(By.xpath("//div[@class='test2']"));
		actions.dragAndDrop(source, box2).build().perform();
		System.out.println(
				"Test Pass:\nBox-2 Message:- " + driver.findElement(By.xpath("//div[@class='test2']")).getText());
	}

	void closeBrowser() {
		driver.quit();
	}

	public static void main(String[] args) {
		ActionDragAndDrop actionDragAndDrop = new ActionDragAndDrop();
		actionDragAndDrop.setUp("https://demos.telerik.com/kendo-ui/dragdrop/events");
		actionDragAndDrop.performDragAndDrop();
		actionDragAndDrop.closeBrowser();
	}

}
