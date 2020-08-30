package amita;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amita.base.PredefinedActions;

public class DoubleClickAction extends PredefinedActions{
	
	WebDriver driver;

	void setUp() {
		driver = start();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	void dragAndDrop() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		driver.findElement(By.xpath("//a[@id='basicelements']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@ondblclick='doubleClick()']"))));
		js.executeScript("arguments[0].scrollIntoView()",element);
		
		String expText = "You successfully double clicked it";
		
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
		
		Alert alert = driver.switchTo().alert();
		String actualText = alert.getText();
		alert.accept();
		
		if(actualText.equals(expText)) {
			System.out.println("TEST PASS : "+actualText);
		}else {
			System.out.println("TEST Fail");
		}	
	}
	
	void closeBrowser() {
		driver.quit();
	}
	
	public static void main(String[] args) {
		DoubleClickAction dragAndDropAction = new DoubleClickAction();
		dragAndDropAction.setUp();
		dragAndDropAction.dragAndDrop();
		dragAndDropAction.closeBrowser();
	}
}
