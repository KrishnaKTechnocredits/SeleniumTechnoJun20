/*Selenium Assignment -3 : 18th Aug 2020

1. Navigate to facebook sign in page using url https://www.facebook.com/r.php
2. c.
3. Enter Firstname, surname.
4. Enter wrong mobile number (like 788898989hjhj88)
5. Enter any combination of password
6. Using Select class select Date, Month and Year using sequentially selectByValue(), selectByIndex() and selectByVisibleText() 
7. Select gender from given radio button options.
8. Click on SignUp button.
(No need to validate error occurred for not entering a valid phone number.)*/
package amita;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FacebookSignupPageValidation {

	void signupPageValidation(WebDriver driver) {
		
		// Navigate to facebook sign in page using url https://www.facebook.com/r.php
		driver.navigate().to("https://www.facebook.com/r.php");
		driver.manage().window().maximize();

		// Navigate to facebook sign in page using url https://www.facebook.com/r.php
		if (driver.getTitle().equals("Sign Up for Facebook | Facebook")) {
			System.out.println(
					"TEST PASS : Successfully navigated to Facebook signup page.The title is :- " + driver.getTitle());
		} else {
			System.out.println("TEST FAIL : Unsuccessful navigation");
		}

		// Enter Firstname, surname,mobile number,password,
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Amita");
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Rout");
		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("78523jh231");
		driver.findElement(By.xpath("//input[@id='password_step_input']")).sendKeys("password");

		// Using Select class select Date, Month and Year using sequentially
		// selectByValue(), selectByIndex() and selectByVisibleText()
		WebElement element = driver.findElement(By.xpath("//select[@name='birthday_day']"));
		Select dateSelect = new Select(element);
		dateSelect.selectByValue("7");
		Select monthSelect = new Select(driver.findElement(By.xpath("//select[@name='birthday_month']")));
		monthSelect.selectByIndex(5);
		Select yearSelect = new Select(driver.findElement(By.xpath("//select[@name='birthday_year']")));
		yearSelect.selectByVisibleText("2000");

		// Select gender from given radio button options.
		driver.findElement(By.xpath("//span/input[@value='1'and @name='sex']")).click();

		// Click on SignUp button.
		driver.findElement(By.xpath("//div[@class='_1lch']/button[@name='websubmit']")).click();
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				"D:\\JAVA-JUNE20\\TechnoGitProject\\SeleniumTechnoJun20\\resources\\windows\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		FacebookSignupPageValidation facebookSignupvalidation = new FacebookSignupPageValidation();
		facebookSignupvalidation.signupPageValidation(driver);
	}
}
