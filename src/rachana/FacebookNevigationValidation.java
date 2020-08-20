package rachana;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FacebookNevigationValidation {

	static void validateNavigationToFacebook(WebDriver driver) {

		driver.get("https://www.facebook.com/");

		// validate page title to facebook page
		if (driver.getTitle().equals("Facebook � log in or sign up")) {
			System.out.println("Page Title is as expected");
		} else {
			System.out.println("Page Title is not as expected");
		}
		// fill all details to login
		driver.findElement(By.id("email")).sendKeys("some@email.com");
		driver.findElement(By.id("pass")).sendKeys("somepassword");
		driver.findElement(By.name("login")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// navigate to google.com and navigate back to facebook
		if (driver.getTitle().equals("Facebook")) {
			System.out.println("Page title is same as expected: Page navigated to correct link : " + driver.getTitle());
		} else {
			System.out.println("Page title is not as expected: page navigated to incorrect link: " + driver.getTitle());
		}
		
		//navigate to google and verify
		driver.navigate().to("https://www.google.com/");
		
		if(driver.getCurrentUrl().equals("https://www.google.com/")) {
			System.out.println("Navigated to Google.com : Successful ");
		}else {
			System.out.println(" Not Navigated to Google.com : UnSuccessful ");
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//navigate back from google to facebook and verify
		driver.navigate().back();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (driver.getTitle().equals("Facebook")) {
			System.out.println("Page title is same as expected:User correctly navigated back : " + driver.getTitle());
		} else {
			System.out.println(
					"Page title is not as expected: Back navigation to the page is incorrect : " + driver.getTitle());
		}
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".//resources//windows//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		validateNavigationToFacebook(driver);
	}

}