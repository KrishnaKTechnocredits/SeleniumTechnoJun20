package suparna;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * Selenium Assignment 1 : 16th Aug 2020

1) Open the form in browser and verify Title is “TECHNOCREDITS”. 

2) Fill all details in the form, for Gender radio button if ‘Female’ radio button is by default selected then select ‘Male’   radio button and vice versa.

3) Reset the form, for that click on “Reset form in same tab” button.

4) Verify Reset works properly on your text field, radio button and checkbox. (For each type, min one validation is required.)

5) Click on the “Go and Practice for it” Button.

6) Website redirect to “automationbykrishna.com”.

7) Verify title and URL of redirected page website.
    Title should be “Login Signup Demo” and the URL should be “http://automationbykrishna.com/”.
 */
public class ResetButtonAndGoURL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("Webdrive.chrome.driver", ".a\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///D:/Suparna_Automation/TechnoGitProject/HTML/SeleniumAssignment_1.html");
		// set default value to web element
		driver.findElement(By.id("first name")).sendKeys("Suparna");
		driver.findElement(By.id("last name")).sendKeys("Nikam");
		driver.findElement(By.id("E-mail")).sendKeys("Suparna.nikam@gmail.com");
		driver.findElement(By.id("Company Name")).sendKeys("Infosys");
		driver.findElement(By.id("femaleG")).click();
		driver.findElement(By.id("entry3")).click();
		driver.findElement(By.id("knownlanguages")).sendKeys("Java");
		driver.findElement(By.id("java")).click();
		driver.findElement(By.id("python")).click();
		driver.findElement(By.id("google")).click();
		driver.findElement(By.id("termsAndConditions")).click();

		// check Reset all values
		driver.findElement(By.id("resetBtn")).click();
		String fName = driver.findElement(By.id("first name")).getAttribute("value");
		String lastName = driver.findElement(By.id("last name")).getAttribute("value");
		String eMail = driver.findElement(By.id("E-mail")).getAttribute("value");
		String companyName = driver.findElement(By.id("Company Name")).getAttribute("value");
		String knownlanguages = driver.findElement(By.id("knownlanguages")).getAttribute("value");

		// driver.findElement(By.id("first name")).getAttribute("value").equals("") -->
		// we can use this also to check text filed value
		if (fName.isEmpty() && lastName.isEmpty() && eMail.isEmpty() && companyName.isEmpty()
				&& knownlanguages.isEmpty()) {
			if (driver.findElement(By.id("femaleG")).isSelected() && (!driver.findElement(By.id("maleG")).isSelected())
					&& (!driver.findElement(By.id("fresher")).isSelected())
					&& (!driver.findElement(By.id("entry4")).isSelected())
					&& (!driver.findElement(By.id("entry3")).isSelected())
					&& (!driver.findElement(By.id("entry2")).isSelected())
					&& (!driver.findElement(By.id("entry1")).isSelected())
					&& (!driver.findElement(By.id("java")).isSelected())
					&& (!driver.findElement(By.id("python")).isSelected())
					&& (!driver.findElement(By.id("javascript")).isSelected())
					&& (!driver.findElement(By.id("google")).isSelected())
					&& (!driver.findElement(By.id("tw")).isSelected())
					&& (!driver.findElement(By.id("other")).isSelected())
					&& (!driver.findElement(By.id("termsAndConditions")).isSelected()))
				System.out.println("All Web element  are set to default value  -> reset value  test case pass ");
		}

		else
			System.out.println("All Web element  are not  set to default value  -> reset test case fail ");

		driver.findElement(By.id("morePractice")).click();
		// check url navigation test case
		if (driver.getCurrentUrl().equals("http://automationbykrishna.com/")
				&& (driver.getTitle().equals("Login Signup Demo")))
			System.out.println("Redirecting to page http://automationbykrishna.com/ --> Navigation test case pass ");
		else
			System.out.println("Redirecting to page " + driver.getCurrentUrl() + " --> Navigation test case faill ");

		driver.close();

	}

}
