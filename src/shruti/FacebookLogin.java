package shruti;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/*
 Selenium Assignment -3 : 18th Aug 2020

 1. Navigate to facebook sign in page using url https://www.facebook.com/r.php
 2. Verify page title is “Sign up for Facebook | Facebook”.
 3. Enter Firstname, surname.
 4. Enter wrong mobile number (like 788898989hjhj88)
 5. Enter any combination of password
 6. Using Select class select Date, Month and Year using sequentially selectByValue(), selectByIndex() and selectByVisibleText() 
 7. Select gender from given radio button options.
 8. Click on SignUp button.
 (No need to validate error occurred for not entering a valid phone number.)
 */
public class FacebookLogin {

	void createFacebookUser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				".\\Resources\\windows\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// create account

		driver.get("https://www.facebook.com/r.php");
		Thread.sleep(1000);

		if (driver.getTitle().equals("Sign up for Facebook | Facebook")) {
			System.out.println("You are on facebook Sign up page");
			driver.findElement(By.name("firstname")).sendKeys("Shruti");
			String firtName = driver.findElement(By.name("firstname"))
					.getAttribute("value");
			System.out.println("Firstname: "+firtName );

			driver.findElement(By.name("lastname")).sendKeys("Dubey");
			String lastName = driver.findElement(By.name("lastname"))
					.getAttribute("value");
			System.out.println("Lastname: "+lastName );

			driver.findElement(By.name("reg_email__")).sendKeys("999899989");
			String email = driver.findElement(By.name("reg_email__")).getAttribute("value");
			System.out.println("Phone Number: "+email );

			driver.findElement(By.name("reg_passwd__")).sendKeys("*********");
			//String password = driver.findElement(By.name("reg_passwd__")).getAttribute("value");

			Select daySelect = new Select(driver.findElement(By.id("day")));
			daySelect.selectByValue("30");

			Select monthSelect = new Select(driver.findElement(By.id("month")));
			monthSelect.selectByIndex(3);

			Select yearSelect = new Select(driver.findElement(By.id("year")));
			yearSelect.selectByVisibleText("1992");
			

			driver.findElement(By.xpath("//input[@id='u_0_4']")).click();
			driver.findElement(By.name("websubmit")).click();

			if (firtName.equals("Shruti") && lastName.equals("Dubey"))
				System.out.println("data is valid - Login Test passed");
			else
				System.out.println("Invalid Data");

		} else {
			System.out.println("please enter correct URL");
		}
	}

	public static void main(String[] args) {
		FacebookLogin FacebookLogin = new FacebookLogin();
		try {
			FacebookLogin.createFacebookUser();
		} catch (InterruptedException e) {
			System.out.println("Something went wrong!!");
		}
	}
}
