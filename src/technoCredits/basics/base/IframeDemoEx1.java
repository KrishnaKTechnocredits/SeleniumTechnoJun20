package technoCredits.basics.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

public class IframeDemoEx1 extends PredefinedActions{
	private WebDriver driver;
	
	void setUp() {
		driver = start();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	}
	
	
	void iframeDemo() {
		driver.findElement(By.linkText("IFrame Demo")).click();
		//driver.switchTo().frame(0); // 1 index
		driver.switchTo().frame("site1"); // 2 name/id
		//driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='site1']")));
		
		driver.findElement(By.xpath("//a[text()='MENU +']")).click();
	
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(2);
	}
	
	public static void main(String[] args) {
		IframeDemoEx1 ex1 = new IframeDemoEx1();
		ex1.setUp();
		ex1.iframeDemo();
	}
}
