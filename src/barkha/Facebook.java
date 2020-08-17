/*  1. Navigate to facebook sign in page using url https://www.facebook.com/
	2. Verify page title is “Facebook – log in or sign up”.
	3. Enter email id & password, and click on ‘Log In’ button.
	4. Verify page title is “(1) Facebook”.
	5. Now using navigation command go to https://www.google.com/
	6. Verify when user navigate back at that time page title is “(1) Facebook”.
	7. Verify when user navigate forward at that time page title is “Google”
	8. Verify when user refresh the page, title remains same. */
	
package barkha;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Facebook {
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		//Navigate to facebook sign in page using url https://www.facebook.com/
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		
		//Navigate to facebook sign in page using url https://www.facebook.com/
		if (driver.getTitle().equals("Facebook – log in or sign up")) 
			System.out.println("URL is redirectig to a page having title Facebook – log in or sign up.");
		else
			System.out.println("You are on Wrong Page.");
		
		//Enter email id & password, and click on ‘Log In’ button.
		driver.findElement(By.name("email")).sendKeys("barkhapatle18@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("******");
		driver.findElement(By.id("u_0_b")).click();
		Thread.sleep(1000);
		
		//Verify page title is “(1) Facebook”
		if (driver.getTitle().equals("(1) Facebook")) 
			System.out.println("After clickig on loging button, Facebook page is opening having title (1) Facebook.");
		else
			System.out.println("You are on Wrong Page.");
		
		//Now using navigation command go to https://www.google.com/
		driver.navigate().to("https://www.google.com/");
		Thread.sleep(1000);
		if (driver.getCurrentUrl().equals("https://www.google.com/"))
			System.out.println("You are navigated to Google page.");
		else
			System.out.println("You are on Wrong Page.");
		
		//Verify when user navigate back at that time page title is “(1) Facebook”
		driver.navigate().back();
		Thread.sleep(1000);
		
		if (driver.getTitle().equals("(1) Facebook")) 
			System.out.println("Backword Navigation Successful(Google->Facebook):You are on Facebook page having title is (1) Facebook.");
		else
			System.out.println("Backword Navigation Unsuccessful.");
		
		//Verify when user navigate forward at that time page title is “Google”
		driver.navigate().forward();
		Thread.sleep(1000);
		
		if (driver.getTitle().equals("Google")) 
			System.out.println("Forword Navigation Successful(Facebook->Google):You are on Google page having title is Google.");
		else
			System.out.println("Forword Navigation Unsuccessful.");
		
		//Verify when user refresh the page, title remains same
		driver.navigate().refresh();
		
		if (driver.getTitle().equals("Google"))
			System.out.println("You are on same page (Google) after refreshing.");
		else
			System.out.println("Page is not the same: Fail");
		
		driver.close(); 
	}	
}		