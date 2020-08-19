/*1. Navigate to facebook sign in page using url https://www.facebook.com/r.php
2. Verify page title is “Sign up for Facebook | Facebook”.
3. Enter Firstname, surname.
4. Enter wrong mobile number (like 788898989hjhj88)
5. Enter any combination of password
6. Using Select class select Date, Month and Year using sequentially selectByValue(), selectByIndex() and selectByVisibleText() 
7. Select gender from given radio button options.
8. Click on SignUp button.
(No need to validate error occurred for not entering a valid phone number.)*/

package raj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FacebookSignup {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/r.php");

		//Verify FB Signup page title 
		if (driver.getTitle().equals("Sign up for Facebook | Facebook")) {
			System.out.println("FB Sign-up Page title TC passed...!! ");
		}else
			System.out.println("FB Sign-up Page Title TC failed...!!");

		driver.findElement(By.name("firstname")).sendKeys("Demo");
		String firstName = driver.findElement(By.name("firstname")).getAttribute("value");
		Thread.sleep(300);

		driver.findElement(By.name("lastname")).sendKeys("User");
		String lastName=driver.findElement(By.name("lastname")).getAttribute("value");
		Thread.sleep(300);

		driver.findElement(By.name("reg_email__")).sendKeys("788898989hjhj88");
		String mobNumber =driver.findElement(By.name("reg_email__")).getAttribute("value");
		Thread.sleep(300);

		driver.findElement(By.name("reg_passwd__")).sendKeys("Test@1234");
		String pwd =driver.findElement(By.id("password_step_input")).getAttribute("value");
		Thread.sleep(300);

		//Text field validation
		if (firstName.equals("Demo") && lastName.equals("User") && mobNumber.equals("788898989hjhj88") && pwd.equals("Test@1234"))
			System.out.println("Text field's validation passed...!!");
		else
			System.out.println("Text field's validation failed...!!");

		//Dropdown Day
		Select dSelect = new Select(driver.findElement(By.id("day")));
		dSelect.selectByVisibleText("6");
		WebElement daySelect = dSelect.getFirstSelectedOption(); 
		String day = daySelect.getText();
		Thread.sleep(300);

		//Dropdown Month
		Select mSelect = new Select(driver.findElement(By.id("month")));
		Thread.sleep(1000);
		mSelect.selectByVisibleText("Apr");
		WebElement monthSelect = mSelect.getFirstSelectedOption();
		String month = monthSelect.getText();
		Thread.sleep(500);

		//Dropdown Year
		Select ySelect = new Select(driver.findElement(By.id("year")));
		ySelect.selectByVisibleText("1996");
		WebElement yearSelect = ySelect.getFirstSelectedOption();
		String year = yearSelect.getText();
		Thread.sleep(300);

		//DOB(dd/mm/yyyy) field validation
		if (day.equals("6") && month.equals("Apr") && year.equals("1996"))
			System.out.println("Entered DOB is valid. Case Passed...!!");
		else
			System.out.println("Entered DOB is invalid. Case failed...!!");

		driver.findElement(By.xpath("//span/input[@id='u_0_5' and @value='2']")).click();
		//Gender field Validation
		if (driver.findElement(By.xpath("//span/input[@id='u_0_5' and @value='2']")).isSelected())
			System.out.println("Gender selection passed...!!");
		else
			System.out.println("Gender selection failed...!!");

		driver.findElement(By.name("websubmit")).click();
		Thread.sleep(1000);

		driver.close();
	}

}
