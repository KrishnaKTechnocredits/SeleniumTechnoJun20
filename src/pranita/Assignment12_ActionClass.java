package pranita;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import pranita.basic.PredefinedFunctions;

public class Assignment12_ActionClass extends PredefinedFunctions {
	public static void main(String[] args) {
		Assignment12_ActionClass Assignment12_ActionClass = new Assignment12_ActionClass();
		Assignment12_ActionClass.setUp();
		Assignment12_ActionClass.doubleClick();
		Assignment12_ActionClass.MouseHover();
		Assignment12_ActionClass.dragAndDrop();
		Assignment12_ActionClass.tearDown();
		
	}
	
	WebDriver driver;
	public void setUp() {
		driver = start();
	}
	
	public void tearDown() {
		driver.close();
	}
	
	private void doubleClick() {
		System.out.println("\n------------------Double Click--------------");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Basic Elements")).click();
		WebElement element = driver.findElement(By.xpath("//div[@class='col-lg-12']/div/a"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",element);
		
		Actions actions = new Actions(driver);
		actions.doubleClick(element).build().perform();
		
		Alert alert = driver.switchTo().alert();
		String expectedAlertMsg = "You successfully double clicked it";
		String actualAlertMsg = alert.getText();
		alert.accept();
		if(actualAlertMsg.equals(expectedAlertMsg))
			System.out.println("Test Pass: User successfully double clicked on link");
		else
			System.out.println("Test Fail");
		
	}

	private void MouseHover() {
		System.out.println("\n------------------Mouse Hover--------------");
		driver.navigate().to("https://demos.telerik.com/kendo-ui/dragdrop/index");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		WebElement element = driver.findElement(By.xpath("//ul[@class='TK-Context-Menu TK-Menu']/li[2]/button[@class='TK-Menu-Item-Button']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
		int size = driver.findElements(By.xpath("//ul[@class='TK-Context-Menu TK-Menu']/li[2]/ul/li")).size();
		System.out.println("Total options available under Framework dropdown are: "+size);
		
	}

	private void dragAndDrop() {
		System.out.println("\n------------------Drag And Drop--------------");
		driver.navigate().to("https://demos.telerik.com/kendo-ui/dragdrop/index");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebElement sourceElement = driver.findElement(By.id("draggable"));
		WebElement targetElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
		Actions actions = new Actions(driver);
		actions.dragAndDrop(sourceElement, targetElement).build().perform();
		System.out.println("Test Case passed.Successfully done drag and drop operation");
		
	}

}
