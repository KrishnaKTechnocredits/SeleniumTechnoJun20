package sonal;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assignment12 {

		WebDriver driver;
		public  WebDriver Start(String url){
			
			System.setProperty("webdriver.chrome.driver","E:/NewWorkSpace/SeleniumBasics1/chromedriver.exe"); // Method to initiate web driver and launch url
			
			WebDriver driver = new ChromeDriver();
			
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
			driver.manage().window().maximize();
			
			return driver;
		}
		
		public void setUp (String url)
		{
			driver=Start(url);
		}
	
		//method for drag and drop functionality
	public void dragAndDrop()
	{ 
		//Launch URL
	
		
		
		driver.findElement(By.xpath("//div[@id='example-nav']//a[text()='Area']")).click();
		
		WebElement drag = driver.findElement(By.id("draggable"));		
		WebElement drop = driver.findElement(By.xpath("//div[@id='droptarget']"));
		
		Actions action = new Actions(driver);
		
		action.dragAndDrop(drag, drop).build().perform();
		
		System.out.println("Text after drag and drop is: "+driver.findElement(By.xpath("//div[@id='droptarget']")).getText());
		
			
	}
	
	//method for doubleclick
	public void doubleClick(){
		driver.navigate().to("http://automationbykrishna.com");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("basicelements")).click();
		WebDriverWait wait = new WebDriverWait(driver,15);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserFirstName")));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//div[@class='form-group']/a"))); //scroll down the page
		WebElement element = driver.findElement(By.xpath("//div[@class='form-group']/a"));
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
		
		Alert alert = driver.switchTo().alert();
		String acttext = alert.getText();
		alert.accept();
		String exptext = "You successfully double clicked it";
		if(acttext.equals(exptext))
			System.out.println("Test is Pass:Doublickick is successful");
		else
			System.out.println("Test is Fail");
		
		
	}
	//mousehover
	public void mouseHover() throws InterruptedException
	{
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
		WebElement element1 = driver.findElement(By.xpath("//a[@id='nav-link-accountList']"));
		
		Actions action = new Actions(driver);
		
		action.moveToElement(element1).build().perform();
	
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//a[@id='nav_prefetch_yourorders']")).click();
		System.out.println("Mousehover is successful");
		driver.close();	
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		
		Assignment12 a1 = new Assignment12();
		a1.setUp("https://demos.telerik.com/kendo-ui/dragdrop/index");
		a1.dragAndDrop();
		a1.doubleClick();
		a1.mouseHover();
}
}

