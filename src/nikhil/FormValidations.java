package nikhil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FormValidations {
	void fillTheForm(WebDriver driver) {
		driver.get("file:///C:/Users/Nikhil.Nikhil-Universe.000/Desktop/SeleniumAssignment_1.html");
		if(driver.getTitle().equals("TECHNOCREDITS")) {
			System.out.println("Page Title Verified!");
		}else {
			System.out.println("Incorrect Page Title Encountered!");
		}
		driver.findElement(By.id("first name")).sendKeys("Nikhil");
		driver.findElement(By.id("last name")).sendKeys("Mahabaleshwarkar");
		driver.findElement(By.id("E-mail")).sendKeys("nam.nikhil@gmail.com");
		driver.findElement(By.id("Company Name")).sendKeys("Cybage");
		if(driver.findElement(By.id("femaleG")).isSelected()) {
			driver.findElement(By.id("maleG")).click();
		}else {
			driver.findElement(By.id("femaleG")).click();
		}
		driver.findElement(By.id("entry3")).click();
		driver.findElement(By.id("knownlanguages")).sendKeys("C, C++, Java");
		driver.findElement(By.id("java")).click();
		driver.findElement(By.id("google")).click();
		driver.findElement(By.id("termsAndConditions")).click();
	}
	
	void resetAndVerify(WebDriver driver) {
		driver.findElement(By.id("resetBtn")).click();
		if(driver.findElement(By.id("first name")).getAttribute("value").equals("") && !driver.findElement(By.id("entry3")).isSelected() && !driver.findElement(By.id("java")).isSelected()) {
			System.out.println("Form Reset Functionality is Working as Expected.");
		}else {
			System.out.println("Form Reset Functionality is NOT Working as Expected!");
		}
	}
	
	void verifyRedirectURL(WebDriver driver) {
		driver.findElement(By.id("morePractice")).click();
		if(driver.getCurrentUrl().equals("http://automationbykrishna.com/")) {
			System.out.println("URL Redirect is Working as Expected.");
		}else {
			System.out.println("URL Redirect Failed!");
		}
		if(driver.getTitle().equals("Login Signup Demo")) {
			System.out.println("Page Title of Redirected URL is Verified.");
		}else {
			System.out.println("Page Title of Redirected URL is Incorrect.");
		}
	}
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows\\chromedriver.exe");
		
		FormValidations formValidations = new FormValidations();
		WebDriver driver = new ChromeDriver();
		formValidations.fillTheForm(driver);
		formValidations.resetAndVerify(driver);
		formValidations.verifyRedirectURL(driver);
		driver.close();
	}
}
