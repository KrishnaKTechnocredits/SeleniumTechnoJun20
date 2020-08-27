package aashtha;

import aashtha.base.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestActionClass extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	Actions actions;

	void setUp(String url) {
		driver = start(url);
		wait = new WebDriverWait(driver, 60);
		actions = new Actions(driver);
	}

	void tearDown() {
		driver.close();
	}

	void testDoubleClick() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		driver.findElement(By.id("basicelements")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='firstRow']/div[1]/section/header")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()",
				driver.findElement(By.xpath("//a[text()='Double-click on me']")));
		actions.doubleClick(driver.findElement(By.xpath("//a[text()='Double-click on me']"))).build().perform();
		Alert alert = driver.switchTo().alert();
		System.out.println("On double click, the alert message shows : " + alert.getText());
		alert.accept();
	}

	void testDragAndDrop() {
		driver.navigate().to("https://demos.telerik.com/kendo-ui/dragdrop/index");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='draggable']")));
		WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement target = driver.findElement(By.xpath("//div[@id='droptarget']"));
		actions.dragAndDrop(source, target).build().perform();
		System.out.println(
				"Text after drag and drop : " + driver.findElement(By.xpath("//div[@id='droptarget']")).getText());
	}

	void testMouseHover() {
		driver.navigate().to("https://www.amazon.in/");
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id=\"nav-link-accountList\"]/span[1]")));
		WebElement target = driver.findElement(By.xpath("//a[@id=\"nav-link-accountList\"]/span[1]"));
		actions.moveToElement(target).build().perform();
		driver.findElement(By.xpath("//span[text()='Your Account']")).click();
		System.out.println("Successfully hovered over and clicked on menu");
	}

	public static void main(String[] args) {
		TestActionClass testActionClass = new TestActionClass();
		testActionClass.setUp("http://automationbykrishna.com/");
		testActionClass.testDoubleClick();
		testActionClass.testDragAndDrop();
		testActionClass.testMouseHover();
		testActionClass.tearDown();
	}

}
