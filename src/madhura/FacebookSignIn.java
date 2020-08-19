package madhura;

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
public class FacebookSignIn {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();

		driver.navigate().to("https://www.facebook.com/r.php");
		if (driver.getTitle().equals("Sign up for Facebook | Facebook")) {
			System.out.println("Signup page Title --> Passed");
		}

		driver.findElement(By.name("firstname")).sendKeys("Madhura");
		driver.findElement(By.name("lastname")).sendKeys("Hoshing");
		driver.findElement(By.name("reg_email__")).sendKeys("788898989hjhj88");
		driver.findElement(By.name("reg_passwd__")).sendKeys("abcd1234");
		//System.out.println("Fill details --> Passed");
		// date of birth dropdown
		Select oselect = new Select(driver.findElement(By.id("day")));
		oselect.selectByValue("19");

		new Select(driver.findElement(By.id("month"))).selectByIndex(8);
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1995");
		// select gender and click on sign up button
		driver.findElement(By.xpath("//input[@value='1']")).click();
		driver.findElement(By.id("u_0_13")).click();

		driver.quit();
	}
}