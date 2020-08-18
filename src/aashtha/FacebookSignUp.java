package aashtha;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/*Selenium Assignment -3 : 18th Aug 2020

1. Navigate to facebook sign in page using url https://www.facebook.com/r.php
2. Verify page title is “Sign up for Facebook | Facebook”.
3. Enter Firstname, surname.
4. Enter wrong mobile number (like 788898989hjhj88)
5. Enter any combination of password
6. Using Select class select Date, Month and Year using sequentially selectByValue(), selectByIndex() and selectByVisibleText() 
7. Select gender from given radio button options.
8. Click on SignUp button.
(No need to validate error occurred for not entering a valid phone number.)*/

public class FacebookSignUp {
	WebDriver driver = new ChromeDriver();

	void signUpOnFacebook() {
		driver.get("https://www.facebook.com/r.php");
		driver.manage().window().maximize();
		if (driver.getTitle().equals("Sign up for Facebook | Facebook")) {
			System.out.println("Sign up for Facebook | Facebook - page loaded successfully");
			driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Morphy");
			driver.findElement(By.xpath("//input[@aria-label='Surname']")).sendKeys("Richards");
			driver.findElement(By.xpath("//input[@aria-label='Mobile number or email address']"))
					.sendKeys("788898989hjhj88");
			driver.findElement(By.xpath("//input[@id='password_step_input']")).sendKeys("abcd1234");
			Select selectDay = new Select(driver.findElement(By.xpath("//select[@name='birthday_day']")));
			Select selectMonth = new Select(driver.findElement(By.xpath("//select[@name='birthday_month']")));
			Select selectYear = new Select(driver.findElement(By.xpath("//select[@name='birthday_year']")));
			selectDay.selectByValue("15");
			selectMonth.selectByIndex(3);
			selectYear.selectByVisibleText("1980");
			driver.findElement(By.xpath("//span/input[@value='2']")).click();
			driver.findElement(By.xpath("//button[@name='websubmit']")).click();
			if (!(driver.findElement(By.xpath("//input[@name='firstname']")).getAttribute("value").equals(""))
					&& selectDay.getFirstSelectedOption().getText().equals("15")
					&& selectMonth.getFirstSelectedOption().getText().equals("Mar")
					&& selectYear.getFirstSelectedOption().getText().equals("1980")
					&& driver.findElement(By.xpath("//span/input[@value='2']")).isSelected()) {
				System.out.println("All the feilds are Filled in or Selected successfully");
			} else
				System.out.println("Feilds validation failed");
		} else
			System.out.println("https://www.facebook.com/r.php - could not be loaded");
		driver.close();
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		new FacebookSignUp().signUpOnFacebook();
	}
}
