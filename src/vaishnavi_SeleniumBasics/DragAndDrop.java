package vaishnavi_SeleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import vaishnavi_Base.PredefinedAction;

public class DragAndDrop extends PredefinedAction {

	private WebDriver driver;

	void setUp() {
		driver = start("https://demos.telerik.com/kendo-ui/dragdrop/index");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	void performDragAndDrop() {
		try {
			driver.findElement(By.xpath("//a[@href='/kendo-ui/dragdrop/area']")).click();
			Actions action = new Actions(driver);
			WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
			WebElement destination = driver.findElement(By.xpath("//div[@class='test1']"));
			// perform drag and drop using actions
			action.dragAndDrop(source, destination).build().perform();
			System.out.println("First Box Message:- " + driver.findElement(By.xpath("//div[@class='test1']")).getText());

			WebElement box2 = driver.findElement(By.xpath("//div[@class='test2']"));
			action.dragAndDrop(source, box2).build().perform();
			System.out.println("Second Box Message:- " + driver.findElement(By.xpath("//div[@class='test2']")).getText());
		} catch (NoSuchElementException ne) {
			ne.printStackTrace();
		} finally {
			driver.close();
		}
	}

	public static void main(String[] args) {
		DragAndDrop dragAndDrop = new DragAndDrop();
		dragAndDrop.setUp();
		dragAndDrop.performDragAndDrop();
	}

}
