package mahesh;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import mahesh.base.PredefinedActions;

public class ActionTest extends PredefinedActions{
	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	JavascriptExecutor js;
	
	void setUp() {
		driver = start();
		wait = new WebDriverWait(driver,10);
		action = new Actions(driver);
		js = (JavascriptExecutor) driver;
	}
	
	void dragAndDrop() {
		driver.navigate().to("https://demos.telerik.com/kendo-ui/dragdrop/events");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/kendo-ui/dragdrop/events']")));
		WebElement des = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='droptarget']")));
		WebElement src = driver.findElement(By.xpath("//div[@id='draggable']"));
//		WebElement des = driver.findElement();
		js.executeScript("arguments[0].scrollIntoView()", src);
		action.dragAndDrop(src,des).build().perform();
		System.out.println(driver.findElement(By.xpath("//div[@id='droptarget']")).getText());
	}
	
	void doubleClick() {
		driver.findElement(By.linkText("Basic Elements")).click();
		WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@ondblclick='doubleClick()']")));
		js.executeScript("arguments[0].scrollIntoView()", link);
		action.doubleClick(driver.findElement(By.xpath("//a[@ondblclick='doubleClick()']"))).build().perform();
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
	}
	
	void mouseHover() {
		driver.navigate().to("https://www.amazon.in/");
		action.moveToElement(driver.findElement(By.xpath("//a[@id='nav-link-accountList']"))).build().perform();
		driver.findElement(By.xpath("//a[@class='nav-link nav-item']/span[text()='Your Account']")).click();
		System.out.println(driver.getTitle());
	}
	
	public static void main(String[] args) {
		ActionTest actionTest = new ActionTest();
		actionTest.setUp();
		actionTest.doubleClick();
		actionTest.dragAndDrop();
		actionTest.mouseHover();
		actionTest.driver.close();
	}


}
