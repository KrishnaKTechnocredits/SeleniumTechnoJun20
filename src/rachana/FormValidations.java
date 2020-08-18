package rachana;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FormValidations {

	// validate page title
	static void validatepageTitle(WebDriver driver, String pageTitle) {
		if (driver.getTitle().equals(pageTitle)) {
			System.out.println("Redirected to Correct page :Page Title verified :" + driver.getTitle());
		} else {
			System.out.println("Redirected to InCorrect page :Page Title incorrect!:" + driver.getTitle());
		}
	}

	//Fill form
	static void fillFormAndValidate(WebDriver driver) {

		WebElement firstName = driver.findElement(By.id("first name"));
		firstName.sendKeys("Rachana");
		driver.findElement(By.id("last name")).sendKeys("Ghayal");
		driver.findElement(By.id("E-mail")).sendKeys("rach.g14@gmail.com");
		driver.findElement(By.id("Company Name")).sendKeys("Synechron");

		if (driver.findElement(By.id("Female Gender")).isSelected()) {
			driver.findElement(By.id("Male Gender")).click();
		} else {
			driver.findElement(By.id("Female Gender")).click();
		}

		driver.findElement(By.id("entry3")).click();
		driver.findElement(By.id("languageKnown")).sendKeys("Java");
		driver.findElement(By.id("javascript")).click();
		driver.findElement(By.id("other")).click();
		driver.findElement(By.id("termsandcondition")).click();

		System.out.println("Form filled successfully");
		
	}
	//validate Reset button functionality
	static void validateResetFunctionality(WebDriver driver) {

		driver.findElement(By.id("resetbtn")).click();
		if (driver.findElement(By.id("first name")).getText().equals("")
				&& !driver.findElement(By.id("entry3")).isSelected()) {
			System.out.println("Reset functionality working as expected");
		} else
			System.out.println("Reset functionality is not working as expected");

	}
	//validate redirect link 
	static void validateRedirectLink(WebDriver driver) {

		driver.findElement(By.id("practiceurl")).click();
		String expectedUrl = "http://automationbykrishna.com/";
		if (expectedUrl.equals(driver.getCurrentUrl())) {
			System.out.println("Redirected to expected url successfully");
		} else {
			System.out.println("Redirection to expected url is fail");
		}
	
		validatepageTitle(driver, "Login Signup Demo");
	}

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", ".//resources//windows//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///G:/Technocredits/TechnoGitSeleniumProject/SeleniumAssignment_1.html");

		validatepageTitle(driver, "TECHNOCREDITS");
		fillFormAndValidate(driver);
		validateResetFunctionality(driver);
		validateRedirectLink(driver);

	}

}
