/*
 *Selenium Assignment -3 : 18th Aug 2020

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
package ajit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DroplistConcept {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/r.php");
		driver.manage().window().maximize();

		// Verify SignIn Page Title
		if ((driver.getTitle()).equals("Sign up for Facebook | Facebook"))
			System.out.println("Title-Sign-In Page Matched Successfully");
		else
			System.out.println("Title-Sign-In Page Mismatched");

		// Enter FirstName, LastName, MobileNumber, Password
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Ajit");
		String fName = driver.findElement(By.xpath("//input[@name='firstname']")).getAttribute("value");

		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Singh");
		String lName = driver.findElement(By.xpath("//input[@name='lastname']")).getAttribute("value");

		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("78989hjhj88");
		String email = driver.findElement(By.xpath("//input[@name='reg_email__']")).getAttribute("value");

		driver.findElement(By.xpath("//input[@id='password_step_input']")).sendKeys("fBook@1");
		String pswd = driver.findElement(By.xpath("//input[@id='password_step_input']")).getAttribute("value");

		if ((fName.contains("Ajit")) && (lName.contains("Singh")) && (email.contains("78989hjhj88"))
				&& (pswd.contains("fBook@1")))
			System.out.println("Value Entered succesfully in all these Fields[FirstName, LastName,MobileNumber,Password]");
		else
			System.out.println("Value not Entered in any of these Fields[FirstName, LastName,MobileNumber,Password]");

		// Select DATE OF BIRTH and Capture selected value From DropList
		Select selDay = new Select(driver.findElement(By.xpath("//select[@id='day']")));
		selDay.selectByVisibleText("28");
		WebElement dayOption = selDay.getFirstSelectedOption();
		String daySelection = dayOption.getText();

		Select selMonth = new Select(driver.findElement(By.xpath("//select[@id='month']")));
		selMonth.selectByIndex(11);
		String monthOption = selMonth.getFirstSelectedOption().getText();

		Select selYear = new Select(driver.findElement(By.xpath("//select[@id='year']")));
		selYear.selectByValue("2014");
		String yearOption = selYear.getFirstSelectedOption().getText();

		// Verify Date of Birth Selection
		if (daySelection.equals("28") && monthOption.equals("Nov") && yearOption.equals("2014"))
			System.out.println("Date Of Birth selection successful");
		else
			System.out.println("Date Of Birth selection Unsuccessful");

		// Select Gender and Verify Selection
		driver.findElement(By.xpath("//input[@name='sex' and @value='2']")).click();
		if (driver.findElement(By.xpath("//input[@name='sex' and @value='2']")).isSelected())
			System.out.println("Gender Option Selection Successful");
		else
			System.out.println("Gender option Selection Unsuccessful");

		// Click on SignUp button
		driver.findElement(By.xpath("//button[@name='websubmit']")).click();
		System.out.println("End of Program : All operations Performed");

		driver.close();
	}
}
