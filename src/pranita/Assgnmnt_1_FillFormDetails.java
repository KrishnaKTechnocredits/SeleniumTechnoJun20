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
package pranita;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assgnmnt_1_FillFormDetails {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows\\chromedriver.exe");
		Assgnmnt_1_FillFormDetails formValidations = new Assgnmnt_1_FillFormDetails();
		WebDriver driver = new ChromeDriver();
		formValidations.fillTheForm(driver);
		formValidations.resetAndVerify(driver);
		formValidations.verifyRedirectURL(driver);
		driver.close();
	}

	void fillTheForm(WebDriver driver) {
		// Open the form in browser and verify Title is “TECHNOCREDITS”.
		driver.get("file:///C:/Users/Shashank/Downloads/SeleniumAssignment_1.html");
		if (driver.getTitle().equals("TECHNOCREDITS")) {
			System.out.println("Page Title is as expected");
		} else {
			System.out.println("Incorrect Page Title.");
		}
		driver.findElement(By.id("first name")).sendKeys("Pranita");
		driver.findElement(By.id("last name")).sendKeys("Puranik");
		driver.findElement(By.id("E-mail")).sendKeys("pranita.mini@gmail.com");
		driver.findElement(By.id("Company Name")).sendKeys("Cognizant");
		// for Gender radio button if ‘Female’ radio button is by default selected then select ‘Male’ radio button and vice versa
		if (driver.findElement(By.id("femaleG")).isSelected()) {
			driver.findElement(By.id("maleG")).click();
		} else {
			driver.findElement(By.id("femaleG")).click();
		}
		driver.findElement(By.id("entry2")).click();
		driver.findElement(By.id("knownlanguages")).sendKeys("C");
		driver.findElement(By.id("java")).click();
		driver.findElement(By.id("google")).click();
		driver.findElement(By.id("termsAndConditions")).click();
	}

	// Reset the form, for that click on “Reset form in same tab” button.
	// Verify Reset works properly on your text field, radio button and checkbox.
	// (For each type, min one validation is required.)
	void resetAndVerify(WebDriver driver) {
		driver.findElement(By.id("resetBtn")).click();
		if (driver.findElement(By.id("first name")).getAttribute("value").equals("")
				&& !driver.findElement(By.id("entry2")).isSelected()
				&& !driver.findElement(By.id("java")).isSelected()) {
			System.out.println("Form reset functionality is working fine");
		} else {
			System.out.println("Form reset functionality is not working ");
		}
	}

	// Verify title and URL of redirected page website.
	void verifyRedirectURL(WebDriver driver) {
		driver.findElement(By.id("morePractice")).click();
		if (driver.getCurrentUrl().equals("http://automationbykrishna.com/")) {
			System.out.println("URL Redirection is working as expected");
		} else {
			System.out.println("URL Redirection not working properly");
		}
		if (driver.getTitle().equals("Login Signup Demo")) {
			System.out.println("Page Title of Redirected URL is verified and correct.");
			System.out.println("Test Pass");
		} else {
			System.out.println("Page Title of Redirected URL is incorrect.");
		}
	}

}
