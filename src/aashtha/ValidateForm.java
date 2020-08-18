package aashtha;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*Selenium Assignment 1 : 16th Aug 2020
1) Open the form in browser and verify Title is “TECHNOCREDITS”. 
2) Fill all details in the form, for Gender radio button if ‘Female’ radio button is by default selected then select ‘Male’   radio button and vice versa.
3) Reset the form, for that click on “Reset form in same tab” button.
4) Verify Reset works properly on your text field, radio button and checkbox. (For each type, min one validation is required.)
5) Click on the “Go and Practice for it” Button.
6) Website redirect to “automationbykrishna.com”.
7) Verify title and URL of redirected page website.
    Title should be “Login Signup Demo” and the URL should be “http://automationbykrishna.com/”.*/

public class ValidateForm {
	WebDriver driver = new ChromeDriver();
	WebElement firstName, lastName, email, companyName, genderFemale, genderMale, continents, experience, langKnown,
			langLearn, dreamCompany, termsConditions;

	void setWebElementsInForm() {
		firstName = driver.findElement(By.id("first name"));
		lastName = driver.findElement(By.id("last name"));
		email = driver.findElement(By.id("E-mail"));
		companyName = driver.findElement(By.id("Company name"));
		genderFemale = driver.findElement(By.id("femaleG"));
		genderMale = driver.findElement(By.id("maleG"));
		continents = driver.findElement(By.id("continents"));
		experience = driver.findElement(By.id("entry1"));
		langKnown = driver.findElement(By.id("knownlanguages"));
		langLearn = driver.findElement(By.id("python"));
		dreamCompany = driver.findElement(By.id("google"));
		termsConditions = driver.findElement(By.id("termsAndConditions"));
	}

	void openBrowserFillForm() {
		driver.get("file:///D:/JavaBasics_JUN20/Class-Recordings/61-16Aug/SeleniumAssignment_1.html");
		if (driver.getTitle().equals("TECHNOCREDITS")) {
			System.out.println("1) Title of current page is “TECHNOCREDITS”");
			setWebElementsInForm();
			firstName.sendKeys("Sanket");
			lastName.sendKeys("Jaiswal");
			email.sendKeys("sanketjaiswal@gmail.com");
			companyName.sendKeys("Cognizant");
			if (genderFemale.isSelected()) {
				genderMale.click();
				System.out.println("2) Female is selected by default - Male selected now");
			} else {
				genderFemale.click();
				System.out.println("2) Male is selected by default - Female selected now");
			}
			continents.sendKeys("Europe");
			experience.click();
			langKnown.sendKeys("JAVA");
			langLearn.click();
			dreamCompany.click();
			termsConditions.click();
			System.out.println("---All the feilds on TECHNOCREDITS form are filled in successfully");
		} else
			System.out.println("1) Title of current page is not “TECHNOCREDITS”");
	}

	void validateResetButton() {
		driver.findElement(By.id("resetBtn")).click();
		System.out.println("3) Reset button is clicked successfully");
		if (firstName.getAttribute("value").equals("") && lastName.getAttribute("value").equals("")
				&& email.getAttribute("value").equals("") && companyName.getAttribute("value").equals("")
				&& langKnown.getAttribute("value").equals(""))
			System.out.println("---All the text boxes are reset successfully");
		else
			System.out.println("---TextBox reset failed");
		if (genderMale.isSelected() && dreamCompany.isSelected())
			System.out.println("---Radio buttons reset failed");
		else
			System.out.println("---All the radio buttons are reset successfully");
		if (langLearn.isSelected() && termsConditions.isSelected())
			System.out.println("---Check box reset failed");
		else
			System.out.println("---All the check boxes are reset successfully");
	}

	void goAndPractice() {
		driver.findElement(By.id("morePractice")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (driver.getCurrentUrl().equals("http://automationbykrishna.com/") && driver.getTitle().equals("Login Signup Demo")) {
			System.out.println("5) “Go and Practice for it” Button clicked successfully");
			System.out.println("6) Current page URL is : " + driver.getCurrentUrl());
			System.out.println("7) Current page's title is : " + driver.getTitle());
		} else
			System.out.println("Navigation to automationbykrishna.com failed");
		driver.close();
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		ValidateForm validateForm = new ValidateForm();
		validateForm.openBrowserFillForm();
		validateForm.validateResetButton();
		validateForm.goAndPractice();
	}
}
