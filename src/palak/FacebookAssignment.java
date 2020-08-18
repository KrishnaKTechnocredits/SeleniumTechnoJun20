/*Selenium Assignment 2 : 17th Aug 2020

1. Navigate to facebook sign in page using url https://www.facebook.com/
2. Verify page title is “Facebook – log in or sign up”.
3. Enter email id & password, and click on ‘Log In’ button.
4. Verify page title is “Facebook”.
5. Now using navigation command go to https://www.google.com/
6. Verify when user navigate back at that time page title is “Facebook”.
7. Verify when user navigate forward at that time page title is “Google”
8. Verify when user refresh the page, title remains same.*/
package palak;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FacebookAssignment {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//1. Navigate to facebook sign in page using url https://www.facebook.com/
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		
		//2. Verify page title is “Facebook – log in or sign up”.
		if(driver.getTitle().equals("Facebook – log in or sign up"))
			System.out.println("2.Test Pass : Title is -> " +driver.getTitle());
		else
			System.out.println("2.Test Fail : Title is not -> Facebook – log in or sign up");
		
		//3. Enter email id & password, and click on ‘Log In’ button.
		driver.findElement(By.name("email")).sendKeys("<@gmail.com>");
		driver.findElement(By.name("pass")).sendKeys("<Password>");
		driver.findElement(By.name("login")).click();
		Thread.sleep(2000);
		
		//4. Verify page title is “Facebook”.
		if(driver.getTitle().equals("Facebook"))
			System.out.println("4.Test Pass : Login Successfully & Title is -> "+driver.getTitle());
		else
			System.out.println("4.Test Fail: Login Failed");
		
		//5. Now using navigation command go to https://www.google.com/
		driver.navigate().to("https://www.google.com/"); //add if conditon
		driver.navigate().back();
		
		//6. Verify when user navigate back at that time page title is “Facebook”.
		if(driver.getTitle().equals("Facebook"))
			System.out.println("6.Test Pass: Navigate back to the Facebook -> "+driver.getTitle());
		else
			System.out.println("6.Test Fail: Navigation failed to the Title Facebook");
				
		//7. Verify when user navigate forward at that time page title is “Google”
		driver.navigate().forward();
		if(driver.getTitle().equals("Google"))
			System.out.println("7.Test Pass : Navigate forward to Google -> "+driver.getTitle());
		else
			System.out.println("7.Test Fail : Navigation failed to the google ");
		
		//8. Verify when user refresh the page, title remains same.
		driver.navigate().refresh();
		if(driver.getTitle().equals("Google"))
			System.out.println("8.Test Pass : After Referesh Title is -> "+driver.getTitle());
		else
			System.out.println("8.Test Fail : Navigation failed to the google ");
	}

}
