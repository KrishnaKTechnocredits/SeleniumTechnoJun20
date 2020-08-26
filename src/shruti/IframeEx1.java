package shruti;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import shruti.predefinedActionspkg.PtrdefinedActions;

public class IframeEx1 extends PtrdefinedActions {
	
	WebDriver driver;
	
	void setUp(){
		driver = start();
	}
	
	void iframeOps(){
		
	//	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div/ul/li/a[@id='iframes']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,15);
		driver.switchTo().frame("site1");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='MENU +']")));
		
		driver.findElement(By.xpath("//a[text()='MENU +']")).click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		String actualTitle = driver.findElement(By.xpath("//h1[text()='Selenium Projects']")).getText();
		
		if(actualTitle.equals("Selenium Projects"))
			System.out.println("you are on Selenium Projects page");
		else
			System.out.println("something went wrong,Unable to click on 'Project'");
		
		driver.switchTo().parentFrame();
		driver.switchTo().frame(2);
		driver.findElement(By.xpath("//html/body/div[4]/div/ul/li/ul/li/a[text()='License']")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[text()='Automation By Krishna']")).click();
		String actualText= driver.findElement(By.xpath("//div[@class='wrapper']")).getText();
		//System.out.println(actualText);
		
		if(actualText.equals("This is Maulik."))
			System.out.println("testcase passed. Text on the page is "+actualText);
		else
			System.out.println("testcase failed. Text on the page is not "+actualText);
	}

	public static void main(String[] args) {
		IframeEx1 iframeEx1 = new IframeEx1();
		iframeEx1.setUp();
		iframeEx1.iframeOps();
	}
}
