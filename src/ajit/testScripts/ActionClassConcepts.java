/*
1. Drag And Drop
2. Double click 
3. Mouse Hover */
package ajit.testScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ajit.base.PredefinedActions;

public class ActionClassConcepts extends PredefinedActions {
	WebDriver driver;
	Actions actions;
	
	void setUp(String url) {
		driver = start(url);
	}

	void dragAndDrop() {
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droptarget"));
		actions = new Actions(driver);
		actions.dragAndDrop(source, target).build().perform();
		String expectedTargetText="You did great!";
		if (target.getText().equals(expectedTargetText))
			System.out.println("Drag and Drop operation Successfully Performed. Text Displayed after operation is: "
					+ target.getText());
		else
			System.out.println("Drag and Drop operation Failed");
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
		System.out.println(expectedText.equals(actualText) ? "Double Click-Successful-Test Passed"
				: "Double Click-UnSuccessful-Test Failed");
		alert.accept();

	}
	void mouseHover()  {
		driver.get("https://www.amazon.in/");
		actions.moveToElement(driver.findElement(By.id("nav-link-accountList"))).build().perform();
		driver.findElement(By.xpath("//div[@id='nav-al-your-account']/a[1]/span")).click();
		String expectedTitle ="Your Account";
		String actualTitle = driver.getTitle();
		String result = actualTitle.equals(expectedTitle)?"Title Matched. Click using MouseHover Successfully Performed." :
			"Title MisMatched. Click using MouseHover Failed";
		System.out.println(result);
	}
	
	void tearDown() {
		driver.close();
	}
	public static void main(String[] args) {
		ActionClassConcepts actionClassConcepts = new ActionClassConcepts();
		actionClassConcepts.setUp("https://demos.telerik.com/kendo-ui/dragdrop/events");
		actionClassConcepts.dragAndDrop();
		actionClassConcepts.doubleClick();
		actionClassConcepts.mouseHover();
		actionClassConcepts.tearDown();
	}
}