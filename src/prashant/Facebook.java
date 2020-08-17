package prashant;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Facebook {
	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//Facebook opening ad verification
		driver.get("https://www.facebook.com/");		
		if (driver.getTitle().equals("Facebook – log in or sign up")){
		System.out.println("You are on facebook page");
		}
		else {
			System.out.println("Check your url");
		}
			
		driver.findElement(By.id("email")).sendKeys("Chipadeprashant@yahoo.com");	
		driver.findElement(By.id("pass")).sendKeys("9890129502");		
		driver.findElement(By.id("u_0_b")).click();
		
		driver.navigate().refresh();
		Thread.sleep(1000);
		//Navigation to google page and verification
		
		driver.navigate().to("https://www.google.com/");			
		if(driver.getTitle().equals("Google")) {
			System.out.println("You are on Google page");
		}
		else
		{
			System.out.println("You are still on same page");
		}
		
		//Navigation back to facebook page
		driver.navigate().back();		
		Thread.sleep(1000);
		if(driver.getTitle().equals("Facebook")) {
			System.out.println("You are on Facebook page");
		}
		else
		{
			System.out.println("You are still on same page");
		}		
		
		//Confirming the page is Facebook after refresh
		driver.navigate().refresh();
		if(driver.getTitle().equals("Facebook")) {
			System.out.println("You are on Facebook page");
		}
		else
		{
			System.out.println("You are still on same page");
		}		
		driver.navigate().refresh();
		driver.close();
	}
}
