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
package palak;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FacebookSignUpValidation {

	static WebDriver start() {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// 1. Navigate to facebook sign in page using url https://www.facebook.com/r.php
		driver.get("https://www.facebook.com/r.php");
		driver.manage().window().maximize();
		return driver;
	}

	void fillingSignInForm(WebDriver driver) {
		// 2. Verify page title is “Sign up for Facebook | Facebook”.
		String title = driver.getTitle().equals("Sign up for Facebook | Facebook")
				? "Test Pass : Title is -> " + driver.getTitle()
				: "Test Fail : Title is not Sign up for Facebook | Facebook ";
		System.out.println(title);

		driver.findElement(By.name("firstname")).sendKeys("Palak");
		driver.findElement(By.name("lastname")).sendKeys("Soni");

		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("654613251");

		driver.findElement(By.xpath("//input[@id='password_step_input']")).sendKeys("HakunaMatata@1234");

		Select daySelection = new Select(driver.findElement(By.xpath("//select[@name='birthday_day']")));
		daySelection.selectByValue("19");

		Select monthSelection = new Select(driver.findElement(By.xpath("//select[@id='month']")));
		monthSelection.selectByIndex(8);

		Select yearSelection = new Select(driver.findElement(By.xpath("//select[@class='_9407 _5dba _8esg'][3]")));
		yearSelection.selectByVisibleText("1994");

		driver.findElement(By.xpath("//input[@name='sex' and @value='1']")).click();
		// driver.findElement(By.xpath("//input[@id='u_0_6' and @value='1']")).click();

		driver.findElement(By.xpath("//button[@name='websubmit']")).click();
	}

	public static void main(String[] args) {
		FacebookSignUpValidation facebookSignUpValidation = new FacebookSignUpValidation();
		WebDriver driver = start();
		facebookSignUpValidation.fillingSignInForm(driver);
	}
}
