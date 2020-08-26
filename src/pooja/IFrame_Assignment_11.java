package pooja;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class IFrame_Assignment_11 {
	
    void test_iframe() {
    	System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com");
		driver.findElement(By.id("iframes")).click();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.switchTo().frame("site1");
		//System.out.println("hi");
	    driver.findElement(By.xpath("//a[@id='dropdownButton']")).click();
	    driver.findElement(By.xpath("//a[text()='Projects']")).click();
	    System.out.println(driver.findElement(By.xpath("//h1[text()='Selenium Projects']")).getText());
	   driver.switchTo().defaultContent();
	   
	   driver.switchTo().frame(driver.findElement(By.xpath("//iframe[3]")));
	  
	   driver.findElement(By.xpath("//li[@class='menuheader']//li[2]//a[text()='License']")).click();
	   driver.switchTo().defaultContent();
	  System.out.println( driver.findElement(By.xpath("//a[text()='Automation By Krishna']")).getText());
    }

	public static void main(String[] args) {
		IFrame_Assignment_11 iFrame_Assignment_11= new IFrame_Assignment_11();
		iFrame_Assignment_11.test_iframe();
		
	}

}
