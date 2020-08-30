package shruti;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import shruti.predefinedActionspkg.PtrdefinedActions;

public class DoubleClick extends PtrdefinedActions {
	
	WebDriver driver;
	WebDriverWait wait;
	
	
	void setUp(){
		driver = start("http://automationbykrishna.com/");
	}
	
	void doubleClickops() throws Exception{
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("basicelements")).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/section/header[text()=' Alert Demo ']")));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement link = driver.findElement(By.xpath("//a[text()='Double-click on me']"));
		js.executeScript("arguments[0].scrollIntoView()",link);
		Actions actions = new Actions(driver);
		actions.doubleClick(link).build().perform();
		Alert alert = driver.switchTo().alert();
		String expectedText = alert.getText();
		if(expectedText.equals("You successfully double clicked it"))
				System.out.println("You have successfully Double clicked -  Test passed");
		else
			System.out.println("Double click operation failed");
		alert.accept();
		
	}

	public static void main(String[] args) throws Exception {
		DoubleClick doubleClick = new DoubleClick();
		doubleClick.setUp();
		doubleClick.doubleClickops();
		
		
	}
}
