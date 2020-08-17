package archana;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumAss2 {
	WebDriver driver;

	void facebookLogin() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		String facebookPageTitle = driver.getTitle();
		if (facebookPageTitle.equals("Facebook – log in or sign up"))
			System.out.println(" Facebook page opened sucessfully");
		else
			System.out.println(" Incorrect page opened");

		driver.manage().window().maximize();

		// enter email and Pass
		driver.findElement(By.id("email")).sendKeys("9028553461");
		driver.findElement(By.name("pass")).sendKeys("Harsh@1017");
		driver.findElement(By.name("login")).click();
		Thread.sleep(500);

		// verify login
		if (driver.getTitle().equals("Facebook"))
			System.out.println("\n Login sucessfully");
		else
			System.out.println("\n Login Fail");
	}

	void navigateOperation() {
		driver.navigate().to(" https://www.google.com/");
		driver.navigate().back();
		if (driver.getTitle().equals("(2) Facebook"))
			System.out.println("\n Back--Naviagte to back sucessfully");
		else
			System.out.println("\n Back--Not naviagte to back");
		driver.navigate().forward();
		if (driver.getTitle().equals("Google"))
			System.out.println("\n Forward--Naviagte to forward sucessfully");
		else
			System.out.println("\n Forward--Not naviagte to forward");
		driver.navigate().refresh();
		if (driver.getTitle().equals("Google"))
			System.out.println("\n Refresh--Page Refresh sucessfully");
		driver.quit();
	}

	public static void main(String[] args) {
		SeleniumAss2 assignment2 = new SeleniumAss2();
		try {
			assignment2.facebookLogin();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assignment2.navigateOperation();
	}

}
