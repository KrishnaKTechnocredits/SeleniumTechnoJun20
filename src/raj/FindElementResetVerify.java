package raj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindElementResetVerify {
	public static void main(String[] args) {
	
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///G:/Technocredits/SeleniumAssignment_1.html");
		System.out.println(driver.getTitle());
		
		//FirstName 
		driver.findElement(By.id("first name")).sendKeys("Raj");
		
		//LastName
		driver.findElement(By.id("last name")).sendKeys("Adatiya");
		
		//Email
		driver.findElement(By.id("E-mail")).sendKeys("rajadatiya35@gmail.com");
		
		//CompanyName 
		driver.findElement(By.id("Company Name")).sendKeys("IISU");
		
		//Gender
		WebElement radioButtonMale = driver.findElement(By.id("maleG"));
		radioButtonMale.click();
		
		//driver.findElement(By.name("continents")).click();
		
		//Total years of experience
		WebElement totalExperience = driver.findElement(By.id("entry1"));
		totalExperience.click();
		
		//Programming language you know
		driver.findElement(By.id("knownlanguages")).sendKeys("Java Language");
		
		//language wanted to learn
		WebElement language = driver.findElement(By.id("java"));
		language.click();
		
		//Dream Company
		WebElement dreamCompany = driver.findElement(By.id("other"));
		dreamCompany.click();
		
		//Agreement
		WebElement termsConditions = driver.findElement(By.id("termsAndConditions"));
		termsConditions.click();
		
		//Reset Form
		driver.findElement(By.id("resetBtn")).click();
		
		//Validations for Reset
		if (radioButtonMale.isSelected() == false)
			System.out.println("Reset successfull for Gender field.");
		else
			System.out.println("Reset unsuccessfull for Gender.");
		
		if (totalExperience.isSelected() == false)
			System.out.println("Reset successfull for Total Experience field.");
		else
			System.out.println("Reset unsuccessfull for Total Experience field.");
		
		if (language.isSelected() == false)
			System.out.println("Reset successfull for language to learn field.");
		else
			System.out.println("Reset unsuccessfull for language to learn field.");
		
		if (dreamCompany.isSelected() == false)
			System.out.println("Reset successfull for dream Company field. ");
		else
			System.out.println("Reset unsuccessfull for dream Company field.");
		
		if (termsConditions.isSelected() == false)
			System.out.println("Reset successfull for termsAndCondition field.");
		else
			System.out.println("Reset unsuccessfull for termsAndCondition field.");
		
		//Link Redirect
		driver.findElement(By.linkText("Go And Practice For it")).click();
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		// url validation
		if(driver.getCurrentUrl().contains("http://automationbykrishna.com/")) {
			System.out.println("Current Url Test is Passed.");
		} else {
			System.out.println("Current url Test is Failed.");
		}
		// Title validation
		if(driver.getTitle().contentEquals("Login Signup Demo")){
			System.out.println("Title Test Passed.");
		} else {
			System.out.println("Title Test Failed.");
		}
	}
	
}