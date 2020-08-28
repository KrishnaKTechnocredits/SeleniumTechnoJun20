package raj;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import raj.selenium.base.PredefinedActions;

public class ActionDragAndDrop extends PredefinedActions {
	WebDriver driver;

	void setUp() {
		driver = start("https://demos.telerik.com/kendo-ui/dragdrop/index");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	void performDragAndDropAction() {

		//driver.findElement(By.xpath("//a[text()='Area']")).click();Area
		driver.findElement(By.linkText("Area")).click();

		WebElement source = driver.findElement(By.xpath("//div[@id ='draggable']"));
		WebElement firstTarget = driver.findElement(By.xpath("//div[@class='test1']"));

		Actions action = new Actions(driver);

		//drag and drop action on Blue Box(firstTarget)
		action.dragAndDrop(source, firstTarget).build().perform();
		System.out.println("Text of Blue Box is : " + driver.findElement(By.xpath("//div[@class ='test1']")).getText());
		
		WebElement secondTarget = driver.findElement(By.xpath("//div[@class='test2']"));
		//Drag and Drop on action Orange Box()
		action.dragAndDrop(source, secondTarget).build().perform();
		System.out.println("Text of Orange Box is : " + driver.findElement(By.xpath("//div[@class='test2']")).getText());		
	}

	void closeBrowser() {
		driver.quit();
	}

	public static void main(String[] args) {
		ActionDragAndDrop dragAndDropAction = new ActionDragAndDrop();
		dragAndDropAction.setUp();
		dragAndDropAction.performDragAndDropAction();
		dragAndDropAction.closeBrowser();
	}
}
