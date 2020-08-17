/* 1. Navigate to facebook sign in page using url https://www.facebook.com/
2. Verify page title is “Facebook – log in or sign up”.
3. Enter email id & password, and click on ‘Log In’ button.
4. Verify page title is “Facebook”.
5. Now using navigation command go to https://www.google.com/
6. Verify when user navigate back at that time page title is “Facebook”.
7. Verify when user navigate forward at that time page title is “Google”
8. Verify when user refresh the page, title remains same. */

package raj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigateAndVerify {
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver" , "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//maximize browser
		driver.manage().window().maximize();
		
		//Get facebook page link and verify page title
		driver.navigate().to("https://www.facebook.com/");
	
		if((driver.getTitle()).equals("Facebook – log in or sign up"))
			System.out.println("Title verification passed...!!");
		else 
			System.out.println("Title verification failed...!!");
		
		//Find Elements of login page, Fill details and login
		driver.findElement(By.id("email")).sendKeys("email/number");
		driver.findElement(By.id("pass")).sendKeys("Pwd");
		driver.findElement(By.name("login")).click();
		Thread.sleep(3000);
		
		//verify Title of Page after login 
		if((driver.getTitle()).equals("Facebook"))
			System.out.println("After login, page title verification passed...!! ");
		else
			System.out.println("After login, page title verification failed...!!");
		
		//Navigate to google page
		driver.navigate().to("https://www.google.com/");
		Thread.sleep(3000);
		
		//Back Page Navigation
		driver.navigate().back();
		Thread.sleep(3000);
		
		//Verify title of back page 
		if((driver.getTitle()).equals("Facebook"))
			System.out.println("Back Navigation page title verification passed...!!");
		else
			System.out.println("Back Navigation page title verification failed...!!");

		//Navigation Forward
		driver.navigate().forward();
		Thread.sleep(3000);
		
		//Verify title of forward page 
		if((driver.getTitle()).equals("Google"))
			System.out.println("Forward Navigation page title verification passed...!!");
		else
			System.out.println("Forward Navigation page title verification failed...!!");

		//Refresh Page
		driver.navigate().refresh();
		Thread.sleep(3000);
		
		//Verify page title after page refresh
		if((driver.getTitle()).equals("Google"))
			System.out.println("Page refresh successful with same url...!!");
		else
			System.out.println("Page refresh changed url...!! ");

		driver.close();
	}

}

