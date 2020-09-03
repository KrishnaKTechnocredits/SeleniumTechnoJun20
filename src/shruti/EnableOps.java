package shruti;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import shruti.predefinedActionspkg.PtrdefinedActions;

public class EnableOps extends PtrdefinedActions {
	
	WebDriver driver;
	
	void setUp(){
		driver = start();
	}
	
	void amazonMouseAction(){
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//div/input[@id='UserName']")).sendKeys("fake_nikhil.bansode@silicus.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Pa55w0rd");
		driver.findElement(By.xpath("//button[@title='Sign in']")).click();
		driver.findElement(By.xpath("//div/ul/li[@class='dropdown active-navbg']")).click();
		driver.findElement(By.xpath("//div/ul/li[2]/ul/li[4]")).click();
		
		//Add ticket for access change
		
		driver.findElement(By.xpath("//div/button[@id='btnAddTickets']")).click();
		driver.findElement(By.xpath("//input[@id='Title']")).sendKeys("TEST Ticket 1");
		driver.findElement(By.xpath("//input[@id='ClientAutoComplete']")).sendKeys("SHRUTI SLA TEST");
		Select envSelect = new Select(driver.findElement(By.xpath("//select[@id='ddlEnvironment']")));
		envSelect.selectByValue("750");
	//	Select planSelect = new Select(driver.findElement(By.xpath("//span[text()='Select Engagement Plan']")));
		//plan

	}
	
	public static void main(String[] args) {
		EnableOps o = new EnableOps();
		o.setUp();
		o.amazonMouseAction();
	}
	
	

}
