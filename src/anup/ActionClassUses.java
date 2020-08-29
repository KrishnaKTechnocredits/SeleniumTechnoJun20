package anup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import anup.basics.PredefinedActions;

public class ActionClassUses extends PredefinedActions{
	
	WebDriver driver;	

	void setUp() {
		driver = start();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);	
	}
	
	void actionDoubleClick() {

		driver.findElement(By.id("basicelements")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver,15);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@ondblclick='doubleClick()']"))));
		js.executeScript("arguments[0].scrollIntoView()",element);
		
		String exp = "You successfully double clicked it";
		Actions actions = new Actions(driver);

		actions.doubleClick(element).build().perform();
		
		Alert alert = driver.switchTo().alert();
		String actual = alert.getText();
		alert.accept();
		
		if(actual.equals(exp)) {
			System.out.println("TEST PASS : Alert Message : "+actual);
		}else {
			System.out.println("TEST Fail");
		}			
	}
	
	void dragAndDrop() {
		
		driver.navigate().to("https://demos.telerik.com/kendo-ui/dragdrop/index");
		driver.findElement(By.xpath("//a[text()='Area']")).click();
		
		WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement destination = driver.findElement(By.xpath("//div[@class='test1']"));
		
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, destination).build().perform();
		System.out.println("BlueBox : "+driver.findElement(By.xpath("//div[@class='test1']")).getText());
		
		WebElement dest = driver.findElement(By.xpath("//div[@class='test2']"));
		
		actions.dragAndDrop(source, dest).build().perform();
		System.out.println("OrangeBox : "+driver.findElement(By.xpath("//div[@class='test2']")).getText());
		
	}
	
	void mouseHover() {
		
		driver.navigate().to("https://www.amazon.in/");
		
		WebElement element = driver.findElement(By.xpath("//a[@id='nav-link-accountList']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
		driver.findElement(By.xpath("//span[text()='Your Account']")).click();
		System.out.println("TEST PASS: "+ driver.getTitle());	
	}
	void closeBrowser() {
		driver.quit();
	}
	public static void main(String[] args) {
		ActionClassUses actionClassUses = new ActionClassUses();
		actionClassUses.setUp();
		actionClassUses.actionDoubleClick();
		actionClassUses.dragAndDrop();
		actionClassUses.mouseHover();
		actionClassUses.closeBrowser();		
	}
}