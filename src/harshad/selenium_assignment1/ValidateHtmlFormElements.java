package harshad.selenium_assignment1;

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

public class ValidateHtmlFormElements {
	WebDriver driver = new ChromeDriver();
	WebElement firstName,lastName,emailID,companyName,genderAsMale,genderAsFemale,region,totalExperience,programmingLanguageKnown,languageWantToLearn,dreamCompany,agreeTermsAndConditions,practiceLink;
	
	void openBrowserAndAccessHtmlForm() {
		driver.get("file:///S:/TechnoGitProject/SeleniumProject/SeleniumAssignment_1.html");
		if (driver.getTitle().equals("TECHNOCREDITS")) {
			System.out.println("Verified title of current page is TECHNOCREDITS");
			firstName = driver.findElement(By.id("first name"));
			firstName.sendKeys("Harshad");
			lastName = driver.findElement(By.id("last name"));
			lastName.sendKeys("Bhore");
			emailID = driver.findElement(By.id("E-mail"));
			emailID.sendKeys("hsb9595@gmail.com");
			companyName = driver.findElement(By.id("Company Name"));
			companyName.sendKeys("Hexaware");
			genderAsFemale = driver.findElement(By.id("femaleG"));
			genderAsMale = driver.findElement(By.id("maleG"));
			if (genderAsFemale.isSelected()) {
				System.out.println("By default Female is selected");
				genderAsMale.click();
				System.out.println("Now Male is selected");
			} else {
				System.out.println("By default Male is selected");
				genderAsFemale.click();
				System.out.println("Now Female is selected");
			}
			
			region = driver.findElement(By.id("continents"));
			region.sendKeys("North America");
			totalExperience = driver.findElement(By.id("entry1"));
			totalExperience.click();
			programmingLanguageKnown = driver.findElement(By.id("knownlanguages"));
			programmingLanguageKnown.sendKeys("JAVA");
			languageWantToLearn = driver.findElement(By.id("python"));
			languageWantToLearn.click();
			dreamCompany = driver.findElement(By.id("google"));
			dreamCompany.click();
			agreeTermsAndConditions = driver.findElement(By.id("termsAndConditions"));
			agreeTermsAndConditions.click();
			System.out.println(driver.getTitle()+" form filled successfully");
		} else
			System.out.println("Verified title of current page is not TECHNOCREDITS it is "+driver.getTitle());
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void validateResetButton() {
		driver.findElement(By.id("resetBtn")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Reset button clicked successfully");
		if (firstName.getAttribute("value").equals("") && lastName.getAttribute("value").equals("")
				&& emailID.getAttribute("value").equals("") && companyName.getAttribute("value").equals("")
				&& programmingLanguageKnown.getAttribute("value").equals(""))
			System.out.println("Resetting textboxes successful");
		else
			System.out.println("TextBox reset failed");
		if (genderAsMale.isSelected() && dreamCompany.isSelected())
			System.out.println("Radio buttons reset failed");
		else
			System.out.println("Resetting radio buttons successful");
		if (languageWantToLearn.isSelected() && agreeTermsAndConditions.isSelected())
			System.out.println("Check boxes reset failed");
		else
			System.out.println("Resetting checkboxes successful");
		
	}

	void validateGoAndPracticeLink() {
		driver.findElement(By.id("morePractice")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (driver.getCurrentUrl().equals("http://automationbykrishna.com/") && driver.getTitle().equals("Login Signup Demo")) {
			System.out.println("Website redirected to “automationbykrishna.com” successfully");
			System.out.println("URL of the page : " + driver.getCurrentUrl());
			System.out.println("Title of the page is : " + driver.getTitle());
		} else
			System.out.println("Redirection to automationbykrishna.com failed");
		driver.close();
	}
	
	
	public static void main(String[] args) {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : "+ os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;

		System.setProperty("webdriver.chrome.driver", path);
		ValidateHtmlFormElements validateHtmlFormElements = new ValidateHtmlFormElements();
		validateHtmlFormElements.openBrowserAndAccessHtmlForm();
		validateHtmlFormElements.validateResetButton();
		validateHtmlFormElements.validateGoAndPracticeLink();
	}
}
