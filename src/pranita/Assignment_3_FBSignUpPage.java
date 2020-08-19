/*
 * Selenium Assignment -3 : 18th Aug 2020

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
package pranita;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignment_3_FBSignUpPage {
	void fbSingUP(WebDriver driver) {
		driver.get("https://www.facebook.com/r.php");
		driver.manage().window().maximize();

		/*
		 * if (driver.getCurrentUrl().equals("https://www.facebook.com/r.php"))
		 * System.out.println("FB sign page displayed "); else
		 * System.out.println("Sign in page not displayed"); try { Thread.sleep(10000);
		 * } catch (InterruptedException IE) { IE.printStackTrace(); }
		 */
		String expectedTitle = driver.getTitle();
		System.out.println(expectedTitle);
		if (driver.getTitle().equals("Sign up for Facebook | Facebook")) {
			System.out.println("Correct Page Title");
		} else {
			System.out.println("Incorrect Page Title.");
		}
		
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Pranita");
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Puranik");
		driver.findElement(By.xpath("//input[@aria-label = 'Mobile number or email address']"))
				.sendKeys("pranita.mini@gmail.com");
		driver.findElement(By.xpath("//input[@name='reg_passwd__']")).sendKeys("*LoveForCats*");

		Select birthDateDropDown = new Select(driver.findElement(By.id("day")));
		birthDateDropDown.selectByIndex(28);

		Select birthMonthDropDown = new Select(driver.findElement(By.name("birthday_month")));
		birthMonthDropDown.selectByValue("7");

		Select birthYearDropDown = new Select(driver.findElement(By.id("year")));
		birthYearDropDown.selectByVisibleText("1990");

		driver.findElement(By.xpath("//span/input[@value='1']")).click();
		// driver.findElement(By.id("u_0_4")).click();

		driver.findElement(By.xpath("//button[@name='websubmit']")).click();
		driver.findElement(By.id("u_0_14")).click();
		driver.close();
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		new Assignment_3_FBSignUpPage().fbSingUP(driver);
	}

}
