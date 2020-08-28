package aavruti;

import aavruti.base.PredefinedActions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionsDemo extends PredefinedActions{

	WebDriver driver;
	WebDriverWait wait;
	Actions actions;

	void launchBrowser() {
		driver = start();
	}

	//Double Click
	void doubleClickDemo() {

		System.out.println("\n---- DOUBLE-CLICK ----");

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Basic Elements")))).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@ondblclick='doubleClick()']")))));

		actions.doubleClick(driver.findElement(By.xpath("//a[@ondblclick='doubleClick()']"))).build().perform();

		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert Message after Double Click --> " + alert.getText());
		alert.accept();
	}

	//Drag and Drop
	void dragAndDropDemo() {
		driver.navigate().to("https://demos.telerik.com/kendo-ui/dragdrop/events");
		
		System.out.println("\n---- DRAG AND DROP ----");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='onetrust-accept-btn-handler' and text()='Accept Cookies']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Area']"))).click();

		WebElement sourceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='draggable']")));
		WebElement destinationElement = driver.findElement(By.xpath("//div[@class='test1']"));

		actions.dragAndDrop(sourceElement, destinationElement).build().perform();
		System.out.println("Blue Box Message --> " + driver.findElement(By.xpath("//div[@class='test1']")).getText());

		destinationElement = driver.findElement(By.xpath("//div[@class='test2']"));

		actions.dragAndDrop(sourceElement, destinationElement).build().perform();
		System.out.println("Orange Box Message --> " + driver.findElement(By.xpath("//div[@class='test2']")).getText());
	}

	//Mouse Hover
	void onMouseHoverDemo() {
		driver.navigate().to("https://www.amazon.in/");
		
		System.out.println("\n---- Mouse Over ----");
		
		actions.moveToElement(driver.findElement(By.xpath("//a[@id='nav-link-accountList']"))).build().perform();
		driver.findElement(By.xpath("//a[@class='nav-link nav-item']/span[text()='Your Account']")).click();
		System.out.println(driver.getTitle());
	}
	
	public static void main(String[] args) {
		ActionsDemo actionsDemo = new ActionsDemo();

		actionsDemo.launchBrowser();
		actionsDemo.wait = new WebDriverWait(actionsDemo.driver,30);
		actionsDemo.actions = new Actions(actionsDemo.driver);		

		actionsDemo.doubleClickDemo();
		actionsDemo.dragAndDropDemo();
		actionsDemo.onMouseHoverDemo();
		actionsDemo.closeBrowser();
	}

	void closeBrowser() {
		driver.quit();
	}
}
