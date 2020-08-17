/*
 * 1) Open the form in browser and verify Title is “TECHNOCREDITS”. 
 * 2) Fill all details in the form, for Gender radio button if ‘Female’ radio button 
 * is by default selected then select ‘Male’ radio button and vice versa.
 * 3) Reset the form, for that click on “Reset form in same tab” button.
 * 4) Verify Reset works properly on your text field, radio button and checkbox. (For each type, min one validation is required.)
 * 5) Click on the “Go and Practice for it” Button.
 * 6) Website redirect to “automationbykrishna.com”.
 * 7) Verify title and URL of redirected page website.
 * Title should be “Login Signup Demo” and the URL should be “http://automationbykrishna.com/”.
 */
package aditi;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FormFieldValidations {
	WebDriver driver = new ChromeDriver();

	void setElementValueOnForm() {
		try {
			driver.get("file:///Users/apple/Documents/Class_Sessions/PracticeWebPages/SeleniumAssignment_1.html");
		} catch (InvalidArgumentException invalidArgumentException) {
			System.out.println("Please enter Correct filepath");
		}

		// Verifying Title of the web page in browser window
		if (driver.getTitle().equals("TECHNOCREDITS"))
			System.out.println("Test Case Pass - Correct landing page - Title matches to -> " + driver.getTitle());
		else
			System.out.println("Test case fail - landing page URL is: " + driver.getCurrentUrl());

		// Set value of web elements in form
		try {
			driver.findElement(By.id("first name")).sendKeys("Aditi");
			driver.findElement(By.id("last name")).sendKeys("Gajipara");
			driver.findElement(By.id("E-mail")).sendKeys("aditi@gmail.com");
			driver.findElement(By.id("Company Name")).sendKeys("Chase");
			driver.findElement(By.id("continents")).sendKeys("North America");
			driver.findElement(By.id("entry2")).click();
			driver.findElement(By.id("knownlanguages")).sendKeys("JAVA");
			driver.findElement(By.id("python")).click();
			driver.findElement(By.id("javascript")).click();
			driver.findElement(By.id("google")).click();
			driver.findElement(By.id("termsAndConditions")).click();

			// Checking validation for radio button condition
			if (driver.findElement(By.id("femaleG")).isSelected()) {
				driver.findElement(By.id("maleG")).click();
				System.out.println("Default Selection was Female - Radio Button selection changed to Male");
			} else {
				driver.findElement(By.id("femaleG")).click();
				System.out.println("Default Selection was Male - Radio Button selection changed to Female");
			}
		} catch (NoSuchElementException elementException) {
			System.out.println("Provide valid attribute name - Element not found");
		}
	}

	void formReset() {
		// Fprm reset
		driver.findElement(By.id("resetBtn")).click();

		// Validating reset for text box
		if (driver.findElement(By.id("first name")).getAttribute("value").isEmpty()
				&& driver.findElement(By.id("last name")).getAttribute("value").isEmpty()
				&& driver.findElement(By.id("E-mail")).getAttribute("value").isEmpty()
				&& driver.findElement(By.id("Company Name")).getAttribute("value").isEmpty()
				&& driver.findElement(By.id("knownlanguages")).getAttribute("value").isEmpty())
			System.out.println("Reset Passed for Text Box - All text fields reset properly");
		else
			System.out.println("Reset Failed for Text Box - Text box are not reset correctly");

		// Validating reset for radio buttons
		if (driver.findElement(By.id("femaleG")).isSelected())
			System.out.println("Reset Passed for Gender selection Radio Button");
		else
			System.out.println("Reset Failed for Gender selection Radio Button");

		if (!driver.findElement(By.name("experience")).isSelected())
			System.out.println("Reset Passed for Industry Experience selection Radio Button");
		else
			System.out.println("Reset Passed for Industry Experience selection Radio Button");

		// Validating reset for checkboxes
		if (!driver.findElement(By.name("language")).isSelected())
			System.out.println("Reset Passed for \"Language wanted to learn\" selection check box");
		else
			System.out.println("Reset Failed for \"Language wanted to learn\" selection check box");
		if (!driver.findElement(By.id("termsAndConditions")).isSelected())
			System.out.println("Reset Passed for \"Agree on terms and conditions\" selection check box");
		else
			System.out.println("Reset Failed for \"Agree on terms and conditions\" selection check box");

		// Validating Dropdown
		if (driver.findElement(By.id("continents")).getAttribute("value").equals("Asia"))
			System.out.println("Reset Passed for Region Dropdown");
		else
			System.out.println("Reset Failed for Region Dropdown");
	}

	void checkUrlNavigation() {
		driver.findElement(By.id("morePractice")).click();
		if (driver.getCurrentUrl().equals("http://automationbykrishna.com/")
				&& driver.getTitle().equals("Login Signup Demo"))
			System.out.println(
					"Url Redirection Pass - Url redirection to - http://automationbykrishna.com/ is working and verified");
		else
			System.out.println(
					"Url Redirection Failed - Url redirection to - http://automationbykrishna.com/ is not working as Expected");
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				"/Users/apple/Documents/TechnoGitProject/SeleniumBasics/resources/chromedriver");
		FormFieldValidations formFieldValidations = new FormFieldValidations();
		formFieldValidations.setElementValueOnForm();
		formFieldValidations.formReset();
		formFieldValidations.checkUrlNavigation();
		formFieldValidations.driver.quit();
	}

}
