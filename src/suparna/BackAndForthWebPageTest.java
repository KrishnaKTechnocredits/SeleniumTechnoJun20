package suparna;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*Selenium Assignment 2 : 17th Aug 2020
1. Navigate to facebook sign in page using url https://www.facebook.com/
2. Verify page title is “Facebook – log in or sign up”.
3. Enter email id & password, and click on ‘Log In’ button.
4. Verify page title is “Facebook”.
5. Now using navigation command go to https://www.google.com/
6. Verify when user navigate back at that time page title is “Facebook”.
7. Verify when user navigate forward at that time page title is “Google”
8. Verify when user refresh the page, title remains same. */
public class BackAndForthWebPageTest {
	public static void main(String[] args) {
		System.setProperty("Webdrive.chrome.driver",".a\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		
		driver.manage().window().maximize();
		if (driver.getTitle().equals("Facebook – log in or sign up"))
			System.out.println("Facebook – log in or sign up Page --> Title verification Test case Pass");
		else
			System.out.println("Facebook – log in or sign up Page --> Title verification Test case  failed");
		
		driver.findElement(By.id("email")).sendKeys("patil.suparna.c@gmail.com");
		System.out.println("User name enter successfully");
		driver.findElement(By.id("pass")).sendKeys("*****");
		System.out.println("Password enter successfully");
		
		driver.findElement(By.name("login")).click();
		/*try {
			Thread.sleep(100000);
		} catch (InterruptedException IE) {
			IE.printStackTrace();
		}*/
		if (driver.getTitle().equals("Facebook"))
			System.out.println("Redirected to Facebook Home Page ---> Home page Title  verifivation Test case pass");
		else
			System.out.println("Redirected to "+ driver.getCurrentUrl() +" Page ---> Home page Title  verifivation Test fail");
					
		driver.navigate().to("https://www.google.com/");
		if (driver.getTitle().equals("Google"))
			System.out.println("Successfully Navigated to google.com  --> Google page navigation test case pass ");
		else
			System.out.println("Navigated to "+ driver.getCurrentUrl() +"  to page  --> Google page navigation test case pass");
		driver.navigate().back();
		if (driver.getTitle().equals("Facebook"))
			System.out.println("Successfully Navigated back to Facebook --> back Navigation back test case pass ");
		else
			System.out.println("Navigated to "+ driver.getCurrentUrl() +" -->back  Navigation back test case fail");
		driver.navigate().forward();
		if (driver.getTitle().equals("Google"))
			System.out.println("Successfully Navigated forward to google.com --> forword Navigation back test case pass");
		else
			System.out.println("Navigated to "+ driver.getCurrentUrl() +" -->forword Navigation back test case fail");
		driver.navigate().refresh();
		if (driver.getTitle().equals("Google"))
			System.out.println("Page title is  Google after page refresh activity --> refresh page test case pass ");
		else
			System.out.println("Page title is " + driver.getTitle() + "after page refresh --> refresh page test case fail");
		driver.close();
	}
}