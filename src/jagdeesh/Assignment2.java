package jagdeesh;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class Assignment2 {
	// 2. Verify title
	void verifyTitle(WebDriver driver) {
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		
		if(driver.getTitle().equals("Facebook – log in or sign up"))
			System.out.println("Navigated to Facebook login page successfully - Test Case 1 - Pass");
		else
			System.out.println("Not able to go to Facebook login page --> Test Case 1 - Fail");
		System.out.println("Facebook title is : "+driver.getTitle() +" --> Test Case 2 - Pass");
	}
	//3 & 4. Enter email id & password, and click on ‘Log In’ button & page title
	void login(WebDriver driver) {
		driver.findElement(By.name("email")).sendKeys("m_jagadeesh_99@yahoo.com");
		driver.findElement(By.name("pass")).sendKeys("*******");
		driver.findElement(By.name("login")).click();
		if (driver.getTitle().contains("Facebook"))
			System.out.println("Facebook login page title is : "+driver.getTitle() +" --> Test Case  4 - Pass");
		else
			System.out.println("not able to login to facebook --> Test Case  4 - Fail");		
	}
	
	//5 & 6 &7 . Navigate to google and back to Facebook
	void navigation(WebDriver driver) throws InterruptedException {
		driver.navigate().to("https://www.google.com/");
		Thread.sleep(1000);
		if(driver.getTitle().equals("Google"))
			System.out.println("Navigated to Google, page title is : "+driver.getTitle()+" --> Test Case 5 - Pass ");
		else
			System.out.println("Unable to go to Google using navigate commands --> Test Case 5 - Fail");
		driver.navigate().back();
		Thread.sleep(1000);
		if(driver.getTitle().equals("Facebook"))
			System.out.println("Navigated to Facebook, page title is : "+driver.getTitle()+" --> Test Case 6 - Pass");
		else
			System.out.println("Unable to go to Facebook using navigate commands --> Test Case 6 - Fail");
		driver.navigate().forward();
		Thread.sleep(1000);
		if(driver.getTitle().equals("Google"))
			System.out.println("Navigated to Google, page title is : "+driver.getTitle()+" --> Test Case 7 - Pass");
		else
			System.out.println("Unable to go to Google using navigate commands --> Test Case 7 - Fail");
	}
	//8. Verify when user refresh the page, title remains same.
	void clickOnRefresh(WebDriver driver) throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(3000);
		if(driver.getTitle().equals("Google"))
			System.out.println("Reloaded Google, page title is :"+driver.getTitle()+" Test Case 8 -- Pass");
		else
			System.out.println("Unable reload Google, page title is "+driver.getTitle()+" Test Case 8  -- Fail");
	}
	public static void main(String[] agrs) throws InterruptedException {
		Assignment2 assignment2 = new Assignment2();
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		assignment2.verifyTitle(driver);
		assignment2.login(driver);
		assignment2.navigation(driver);
		assignment2.clickOnRefresh(driver);
		driver.close();
	}
}