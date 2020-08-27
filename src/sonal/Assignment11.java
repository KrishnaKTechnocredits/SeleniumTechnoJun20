package sonal;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assignment11 {

	
		WebDriver driver;
		
		public  WebDriver Start(String url){
				
				System.setProperty("webdriver.chrome.driver","E:/NewWorkSpace/SeleniumBasics1/chromedriver.exe"); // Method to initiate web driver and launch url
				
				WebDriver driver = new ChromeDriver();
				
				driver.get(url);
				
				driver.manage().window().maximize();
				
				return driver;
			}
		
		public void testiFrame() throws InterruptedException
		{ 
			//Launch URL
			WebDriver driver = Start("http://automationbykrishna.com");
			//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.linkText("IFrame Demo")).click();
		
			Thread.sleep(6000);
			//switch to first iframe
			driver.switchTo().frame("site1");
			driver.findElement(By.xpath("//a[@id='dropdownButton']")).click();
			driver.findElement(By.xpath("//nav[@id='navbar']/a[@class='nav-item dropdown-active'][2]")).click();
		
			System.out.println("Text is: "+driver.findElement(By.xpath("//section[@class='hero']")).getText());
			
			//switch to default page
			driver.switchTo().defaultContent();
			Thread.sleep(6000);
			
			//Navigate to third iframe
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[3]")));
			driver.findElement(By.xpath("//li[@class='menuheader'][1]/ul/li[2]/a")).click();
			
			//switch to default page
			driver.switchTo().defaultContent();
			Thread.sleep(6000);
		
			
			//click on link
			driver.findElement(By.xpath("//div[@class='lg-col-12']/a")).click();
			
			
		String chktext = 	driver.findElement(By.xpath("//body[@class='horizontal-menu-page']//div[@id='indexBody']")).getText();
		
		System.out.println(chktext);
		
		driver.close();
			
		}
		
		public static void main(String[] args) throws InterruptedException {
		
			Assignment11 a1 = new Assignment11();
			
			a1.testiFrame();

	}

}

