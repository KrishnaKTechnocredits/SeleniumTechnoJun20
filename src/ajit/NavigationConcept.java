/*
 Selenium Assignment 2 : 17th Aug 2020

1. Navigate to facebook sign in page using url https://www.facebook.com/
2. Verify page title is “Facebook – log in or sign up”.
3. Enter email id & password, and click on ‘Log In’ button.
4. Verify page title is “Facebook”.
5. Now using navigation command go to https://www.google.com/
6. Verify when user navigate back at that time page title is “Facebook”.
7. Verify when user navigate forward at that time page title is “Google”
8. Verify when user refresh the page, title remains same.
 */
package ajit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationConcept {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		
		//Verify Page Title
		if((driver.getTitle()).equals("Facebook – log in or sign up"))
			System.out.println("Title-LoginPage Matched Successfully");
		else
			System.out.println("Title-LoginPage Mismatched");
		
		//Enter EmailID, Password and Click on LogIn
		driver.findElement(By.id("email")).sendKeys("firkeeee1");
		driver.findElement(By.id("pass")).sendKeys("firkee@121212");
		driver.findElement(By.name("login")).click();
		Thread.sleep(8000);
		
		//Verify Page Title after login
		if((driver.getTitle()).equals("Facebook"))
			System.out.println("Title-AfterLogInPage Matched Successfully");
		else
			System.out.println("Title-AfterLogInPage Mismatched");
		
		driver.navigate().to("https://www.google.com/");
		Thread.sleep(8000);
		//Navigation Back
		driver.navigate().back();
		Thread.sleep(8000);
		if((driver.getTitle()).equals("Facebook"))
			System.out.println("Back Navigation Successful");
		else
			System.out.println("Back Navigation Unsuccessful");
		
		//Navigation Forward
		driver.navigate().forward();
		Thread.sleep(8000);
		if((driver.getTitle()).equals("Google"))
			System.out.println("Forward Navigation Successful");
		else
			System.out.println("Forward Navigation Unsuccessful");
		
		//Refresh Page
		driver.navigate().refresh();
		Thread.sleep(8000);
		if((driver.getTitle()).equals("Google"))
			System.out.println("Refresh Command Executed Successful/Didn't change the URL");
		else
			System.out.println("Refresh Command Execution UnSuccessful/It changed the URL");
		
		driver.close();
	}
}
