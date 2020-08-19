package anup;
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


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class FacebookSignup {
	WebDriver driver = new ChromeDriver();
	void facebooksignup() {
		
		//Navigate to facebook sign in page using url https://www.facebook.com/r.php
		driver.get("https://www.facebook.com/r.php");
		
		//2. Verify page title is “Sign up for Facebook | Facebook”.
		
		if(driver.getTitle().contains("Sign Up for Facebook | Facebook")) {
			System.out.println("Test Pass - The title is as expected " + driver.getTitle() );
		}
		else {
			System.out.println("Test Fail - The title is not as expected " + driver.getTitle() );
		}
		
		//3. Enter Firstname, surname.
		driver.findElement(By.id("u_0_n")).sendKeys("Anup");
		driver.findElement(By.id("u_0_p")).sendKeys("Sahoo");
		
		//4. Enter wrong mobile number (like 788898989hjhj88)
		driver.findElement(By.id("u_0_s")).sendKeys("788898989hjhj88");
		
		//5. Enter any combination of password
		driver.findElement(By.id("password_step_input")).sendKeys("ghgjhghjgj");
		
		//6 Using Select class select Date, Month and Year using sequentially selectByValue(), selectByIndex() and selectByVisibleText() 
		WebElement element = driver.findElement(By.id("day"));
		
		Select dateSelect = new Select(element);
		dateSelect.selectByValue("21");
		
		new Select(driver.findElement(By.id("month"))).selectByIndex(1);
		
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("2000");
		
		//driver.findElement(By.id("u_0_5")).click();
		driver.findElement(By.xpath("//div[@class='mtm _5wa2 _5dbb']//input[@id='u_0_5']")).click();
		//8. Click on SignUp button.
		driver.findElement(By.xpath("//div[@class='_1lch']/button[@id='u_0_14']")).click();
		driver.close();
	}
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".//resources//mac//chromedriver");
		FacebookSignup facebook = new FacebookSignup();
		facebook.facebooksignup();
		
	}

}
