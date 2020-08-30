package raj;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import raj.selenium.base.PredefinedActions;

public class ActionDoubleClick extends PredefinedActions{
	WebDriver driver;
	
	void setup() {
		driver = start("http://automationbykrishna.com/");
	}
	
	void performDoubleClickAction() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		driver.findElement(By.xpath("//a[@id='basicelements']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text() = 'Double-click on me']"))));
		js.executeScript("arguments[0].scrollIntoView()",element);

		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();

		Alert alert = driver.switchTo().alert();
		String actualText = alert.getText();
		alert.accept();
		
		String expectedText = "You successfully double clicked it";

		if(actualText.equals(expectedText)) {
			System.out.println("Text on alert for double click link is matched : \n"+actualText);
		}else {
			System.out.println("Text on alert doesn't matched...!!");
		}	
	}

	void closeBrowser() {
		driver.quit();
	}
	
	public static void main(String[] args) {
		ActionDoubleClick dblClick = new ActionDoubleClick();
		dblClick.setup();
		dblClick.performDoubleClickAction();
		dblClick.closeBrowser();
	}
}
