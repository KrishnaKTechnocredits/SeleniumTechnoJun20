package pooja;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsClass_Assignment_12 {
	void actionsClassDemo()
	{
		
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Actions actions=new Actions(driver);
		
		//Drag and drop
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//a[@class=' active']")).click();
		WebElement src=driver.findElement(By.id("draggable"));
		WebElement des=driver.findElement(By.id("droptarget"));
		actions.dragAndDrop(src, des).build().perform();
		
		//double click
		driver.navigate().to("http://automationbykrishna.com");
		driver.manage().window().maximize();
		driver.findElement(By.id("basicelements")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//a[text()='Double-click on me']")));
		
		actions.doubleClick(driver.findElement(By.xpath("//a[text()='Double-click on me']"))).build().perform();
		Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		
		//clickAndHold
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		actions.moveToElement(driver.findElement(By.xpath("//span[text()='Hello, Sign in']"))).build().perform();
		
	}

	public static void main(String[] args) {
		ActionsClass_Assignment_12 actionsClass_Assignment_12= new ActionsClass_Assignment_12();
		actionsClass_Assignment_12.actionsClassDemo();
	
	}

}
