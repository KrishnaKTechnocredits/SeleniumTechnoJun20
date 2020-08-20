package pooja;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment_2_facebook {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		//Navigate to facebook sign in page
		driver.navigate().to("https://www.facebook.com/");
		
		//verify page title is Facebook -log in or sign up
		if(driver.getTitle().equals("Facebook – log in or sign up"))
		{
			System.out.println("Test 1 pass");
		}
		else
		{
			System.out.println("Test 1 fail");
		}
		
		// enter emailid and password
		driver.findElement(By.id("email")).sendKeys("poojagatade9009@gamil.com");
		driver.findElement(By.id("pass")).sendKeys("pooja9009");
		driver.findElement(By.id("u_0_b")).click();

		//verify pagetitle is Facebook
		if(driver.getTitle().equals("Facebook"))
			System.out.println("Test 2 pass");
		else
			System.out.println("Test 2 fail");
		
		//using navigation command go to google
		driver.navigate().to("https://www.google.com");
		
		//verify navigate back at that time page title is "facebook"
	    driver.navigate().back();
	   if( driver.getTitle().equals("Facebook"))
	    System.out.println("Test 3 pass");
	   else
		   System.out.println("Test 3 fail");
	   //navigate forward
	   driver.navigate().forward();
	   if( driver.getTitle().equals("Google"))
	    System.out.println("Test 4 pass");
	   else
		   System.out.println("Test 4 fail");
	   
	    	
	    
		
		
	}

}
