/*
1. Drag And Drop
2. Double click 
3. Mouse Hover*/
package palak;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import palak.base.PredefinedActions;

public class ActionClassAssignment extends PredefinedActions {
	WebDriver driver;
	Actions actions;
	void setUp(String url) {
		driver = start(url);
	}
	
	void dragAndDrop() {
		WebElement draggable = driver.findElement(By.id("draggable"));
		WebElement dropTarget = driver.findElement(By.id("droptarget"));
		actions = new Actions(driver);
		actions.dragAndDrop(draggable, dropTarget).build().perform();
		String exptectedText="You did great!";
		if (dropTarget.getText().equals(exptectedText))
			System.out.println("Dragged Successfully Text is -> "+dropTarget.getText());
		else
			System.out.println("Not Dragged");
	}
	
	void doubleClick() {
		String actualText="You successfully double clicked it";
		driver.navigate().to("http://automationbykrishna.com/");
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		driver.findElement(By.linkText("Basic Elements")).click();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement element = driver.findElement(By.xpath("//div[@class='col-lg-12']/div/a"));
		js.executeScript("arguments[0].scrollIntoView()", element);
		
		actions.doubleClick(element).build().perform();
		Alert alert = driver.switchTo().alert();
		String expectedText = alert.getText();
		System.out.println(expectedText.equals(actualText)?"Test Pass : Text is -> "+expectedText:"Test Failed");
		alert.accept();
		
	}
	void mouseHover()  {
		driver.get("https://www.amazon.in/");
		actions.moveToElement(driver.findElement(By.id("nav-link-accountList"))).build().perform();
		driver.findElement(By.xpath("//div[@id='nav-al-your-account']/a[1]/span")).click();
		String expectedTitle ="Your Account";
		String actualTitle = driver.getTitle();
		//System.out.println(actualTitle);
		String result = actualTitle.equals(expectedTitle)?"Test pass : Title is -> "+actualTitle :
			"Test Failed Title is "+ actualTitle;
		System.out.println(result);
	}
	public static void main(String[] args) {
		ActionClassAssignment actionClassAssignment = new ActionClassAssignment();
		actionClassAssignment.setUp("https://demos.telerik.com/kendo-ui/dragdrop/events");
		actionClassAssignment.dragAndDrop();
		actionClassAssignment.doubleClick();
		actionClassAssignment.mouseHover();
	}
}
