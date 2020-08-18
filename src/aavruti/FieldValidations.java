package aavruti;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FieldValidations {

	WebDriver driver = new ChromeDriver();

	//Enter values in all the fields
	void enterValuesInFields() {
		driver.get("G:\\SeleniumVideos\\Assignment1\\SeleniumAssignment_1.html");
		if(driver.getTitle().equals("TECHNOCREDITS")) {
			System.out.println("Selenium Assignment opened successfully");
		}
		else {
			System.out.println("Browser not launched successfully");
		}

		WebElement firstName = driver.findElement(By.id("first name"));
		WebElement lastName = driver.findElement(By.id("last name"));
		WebElement emailId = driver.findElement(By.id("E-mail"));
		WebElement companyName = driver.findElement(By.id("Company Name"));
		WebElement experience = driver.findElement(By.id("3-6"));
		WebElement language = driver.findElement(By.id("Java"));

		firstName.sendKeys("Aavruti");
		lastName.sendKeys("Dalmia");
		emailId.sendKeys("aavrutid@yahoo.com");
		companyName.sendKeys("TechnoCredits");		

		if(driver.findElement(By.id("female")).isSelected()) {
			driver.findElement(By.id("male")).click();
			System.out.println("Male is Selected");
		}
		else {
			driver.findElement(By.id("female")).click();
			System.out.println("Female is Selected");
		}
		
		experience.click();		
		language.click();
		driver.findElement(By.id("language")).sendKeys("JAVA");
		driver.findElement(By.id("Python")).click();
		driver.findElement(By.id("ThoughtWorks")).click();		
		driver.findElement(By.id("AgreeOnTerms")).click();
		
		if(firstName.getAttribute("value").equals("Aavruti") && lastName.getAttribute("value").equals("Dalmia")  && emailId.getAttribute("value").equals("aavrutid@yahoo.com") && companyName.getAttribute("value").equals("TechnoCredits") && experience.isSelected() && language.isSelected()){
			System.out.println("Value is entered correctly for all the fields");
		}
		else {
			System.out.println("Value is not entered correctly");
		}
	}

	//Reset the form
	void resetForm() {
		driver.findElement(By.id("resetButton")).click();
		if(driver.findElement(By.id("first name")).getAttribute("value").equals("") && driver.findElement(By.id("female")).isSelected() && driver.findElement(By.id("Java")).isSelected() == false) {
			System.out.println("Reset is Done Successfully");
		}
		else {
			System.out.println("Reset Failed!!");
		}
	}

	//Navigate to new URL
	void navigateToNewUrl() {
		driver.findElement(By.id("link")).click();
		if(driver.getCurrentUrl().equals("http://automationbykrishna.com/") && driver.getTitle().equals("Login Signup Demo")) {
			System.out.println("Successfully Navigated to new URL");
		}
		else {
			System.out.println("Not able to naviigate to http://automationbykrishna.com/ URL");
		}
	}

	public static void main(String[] args) {		
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");

		FieldValidations fieldValidations = new FieldValidations();

		fieldValidations.enterValuesInFields();
		fieldValidations.resetForm();
		fieldValidations.navigateToNewUrl();
		fieldValidations.driver.quit();
	}
}
