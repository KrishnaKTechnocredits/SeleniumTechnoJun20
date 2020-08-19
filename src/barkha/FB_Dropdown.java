/*1. Navigate to facebook sign in page using url https://www.facebook.com/r.php
2. Verify page title is “Sign up for Facebook | Facebook”.
3. Enter Firstname, surname.
4. Enter wrong mobile number (like 788898989hjhj88)
5. Enter any combination of password
6. Using Select class select Date, Month and Year using sequentially selectByValue(), selectByIndex() and selectByVisibleText() 
7. Select gender from given radio button options.
8. Click on SignUp button.
(No need to validate error occurred for not entering a valid phone number.)*/
package barkha;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class FB_Dropdown {
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
		//Navigate to facebook sign in page using url https://www.facebook.com/r.php
		driver.get("https://www.facebook.com/r.php");
		driver.manage().window().maximize();
		
		//Verify page title is “Sign up for Facebook | Facebook”
		if (driver.getTitle().equals("Sign up for Facebook | Facebook")) {
			System.out.println("You are on Facebook SignUp page having title Sign up for Facebook | Facebook");
		}else
			System.out.println("You on wrong page");
		
		//Enter Firstname, surname
		driver.findElement(By.name("firstname")).sendKeys("Barkha");
		String fname=driver.findElement(By.name("firstname")).getAttribute("value");
		
		driver.findElement(By.name("lastname")).sendKeys("Patle");
		String lname=driver.findElement(By.name("lastname")).getAttribute("value");
		
		//Enter wrong mobile number (like 788898989hjhj88)
		driver.findElement(By.name("reg_email__")).sendKeys("12562145");
		String num =driver.findElement(By.name("reg_email__")).getAttribute("value");
		
		// Enter any combination of password
		driver.findElement(By.id("password_step_input")).sendKeys("******");
		String pword =driver.findElement(By.id("password_step_input")).getAttribute("value");
		
		//validation on text boxes
		if (fname.equals("Barkha") && lname.equals("Patle") && num.equals("12562145") && pword.equals("******"))
			System.out.println("Values entered successfully in textboxes.");
		else
			System.out.println("Values not entered in textboxes.");
			
		//Using Select class select Date, Month and Year using sequentially selectByValue(), selectByIndex() and selectByVisibleText()
		Select select=new Select(driver.findElement(By.id("day")));
		select.selectByValue("18");
		WebElement date=select.getFirstSelectedOption();
		String enteredDate=date.getText();
		
		Select select1=new Select(driver.findElement(By.id("month")));
		select1.selectByIndex(5);
		WebElement month=select1.getFirstSelectedOption();
		String enteredMonth=month.getText();
		
		Select select2=new Select(driver.findElement(By.id("year")));
		select2.selectByVisibleText("1993");
		WebElement year=select2.getFirstSelectedOption();
		String enteredYear=year.getText();
		
		//validation on date of birth
		if (enteredDate.equals("18") && enteredMonth.equals("May") && enteredYear.equals("1993"))
			System.out.println("Correct DOB selected.");
		else
			System.out.println("DOB is not selected.");
		
		//Select gender from given radio button options
		driver.findElement(By.xpath("//input[@value='1' and @name='sex']")).click();
		
		//validation on Gender button
		if (driver.findElement(By.xpath("//input[@value='1' and @name='sex']")).isSelected())
			System.out.println("Gender(female) is selected successfully.");
		else
			System.out.println("Gender(female) is not selected.");
		
		//Click on SignUp button
		driver.findElement(By.xpath("//button[@name='websubmit']")).click();
		System.out.println("Program Close!!!");
		
		driver.close();
	}
}