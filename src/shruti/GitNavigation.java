/*
1. Navigate to facebook sign in page using url https://www.facebook.com/
2. Verify page title is “Facebook – log in or sign up”.
3. Enter email id & password, and click on ‘Log In’ button.
4. Verify page title is “Facebook”.
5. Now using navigation command go to https://www.google.com/
6. Verify when user navigate back at that time page title is “Facebook”.
7. Verify when user navigate forward at that time page title is “Google”
8. Verify when user refresh the page, title remains same. 
 */
package shruti;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GitNavigation {
	
	
	void navigation() throws InterruptedException{
		
		WebDriver driver;
		
		System.setProperty("webdriver.chrome.driver",".\\Resources\\windows\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//Login to Git
		driver.get("https://github.com/login");
		System.out.println(driver.getTitle());
		if(driver.getTitle().equals("Sign in to GitHub · GitHub"))
			System.out.println("You are on GIT login page, plese Sign in");
		else 
		System.out.println("Incorrect url entered");
		
		driver.findElement(By.id("login_field")).sendKeys("shrutidubey@gmail.com");
		driver.findElement(By.id("password")).sendKeys("password@123");
		driver.findElement(By.name("commit")).click();
		
		if(driver.getTitle().equals("Sign in to GitHub · GitHub"))
		System.out.println("Incorrect Credential entered. Login Failed");
		else
			System.out.println("Hi Shruti,Welcome to GIT");
		
		//Navigate to google
		driver.navigate().to("https://www.google.com/");
		System.out.println("Navigated to Google Home Page");
		Thread.sleep(1000);
		
		//Navigate back to GIT
		driver.navigate().back();
		Thread.sleep(1000);
		
		if(driver.getTitle().equals("Sign in to GitHub · GitHub"))
			System.out.println("Navigated back to GIT login Page");
		else
			System.out.println("Something went wrong- navigation to GIT login failed");
		
		//Navigate forward to google
		driver.navigate().forward();
		Thread.sleep(1000);
		if(driver.getTitle().equals("Google"))
			System.out.println("Navigated back to Google Page");
		else
			System.out.println("Something went wrong- navigation to google failed");
		
		//Page Refresh
		driver.navigate().refresh();
		Thread.sleep(1000);
		if(driver.getTitle().equals("Google"))
			System.out.println("Page Refreshed Successfully");
		else
			System.out.println("Something went wrong- Refresh failed");
		
	}
	
	public static void main(String[] args) {
		GitNavigation gitNavigation = new GitNavigation();
		try {
			gitNavigation.navigation();
		} catch (InterruptedException e) {
			System.out.println("Something Went Wrong");
		}
		
	}

}
