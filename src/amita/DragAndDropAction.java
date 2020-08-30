package amita;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amita.base.PredefinedActions;

public class DragAndDropAction extends PredefinedActions{
	
	WebDriver driver;

	void setUp() {
		driver = start("https://demos.telerik.com/kendo-ui/dragdrop/index");
	}
	
	void dragAndDrop() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		driver.findElement(By.xpath("//a[text()='Area']")).click();
		
		Actions action = new Actions(driver);
		WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement target = driver.findElement(By.xpath("//div[@class='test1']"));
		
		//drag and drop on BlueBox
		action.dragAndDrop(source, target).build().perform();
		System.out.println("Text on target BlueBox : "+driver.findElement(By.xpath("//div[@class='test1']")).getText());
		
		//Drag and Drop on OrangeBox
		WebElement secondTarget = driver.findElement(By.xpath("//div[@class='test2']"));
		action.dragAndDrop(source, secondTarget).build().perform();
		System.out.println("Text on secondTarget OrangeBox : "+driver.findElement(By.xpath("//div[@class='test2']")).getText());		
	}
	
	void closeBrowser() {
		driver.quit();
	}

	public static void main(String[] args) {
		DragAndDropAction dragAndDropAction = new DragAndDropAction();
		dragAndDropAction.setUp();
		dragAndDropAction.dragAndDrop();
		dragAndDropAction.closeBrowser();
	}
}
