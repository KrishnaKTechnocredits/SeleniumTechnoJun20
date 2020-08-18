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

package nikhil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FacebookSignUp {
	private static WebDriver start(String url) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}
	
	void pageVerification(WebDriver driver) {
		if(driver.getTitle().equals("Sign up for Facebook | Facebook")) {
			System.out.println("Sign up page title for Facebook is verified.");
		}else {
			System.out.println("Sign up page title for Facebook is NOT verified.");
		}
	}
	
	void signUp(WebDriver driver) {
		driver.findElement(By.id("u_0_n")).sendKeys("Steve");
		driver.findElement(By.id("u_0_p")).sendKeys("Rogers");
		driver.findElement(By.id("u_0_s")).sendKeys("1st Avenger");
		driver.findElement(By.id("password_step_input")).sendKeys("CaptainAmerica");
		WebElement element = driver.findElement(By.id("day"));
		Select oSelect = new Select(element);
		oSelect.selectByValue("4");
		element = driver.findElement(By.id("month"));
		oSelect = new Select(element);
		oSelect.selectByIndex(7);
		element = driver.findElement(By.id("year"));
		oSelect = new Select(element);
		oSelect.selectByVisibleText("1920");
		driver.findElement(By.xpath("//input[@value='2']")).click();
		driver.findElement(By.id("u_0_14")).click();
	}
	
	public static void main(String[] args) {
		WebDriver driver = start("https://www.facebook.com/r.php");
		FacebookSignUp facebookSignUp = new FacebookSignUp();
		facebookSignUp.pageVerification(driver);
		facebookSignUp.signUp(driver);
		//driver.close();
	}
}
