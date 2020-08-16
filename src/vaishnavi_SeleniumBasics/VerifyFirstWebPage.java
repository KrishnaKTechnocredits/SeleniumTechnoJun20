package vaishnavi_SeleniumBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

public class VerifyFirstWebPage {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///C:/Users/vaish/Desktop/Selenium_Recordings/TechnoCredits.html");

		// Trying to verify title of the page
		if (driver.getTitle().equals("TECHNOCREDITS"))
			System.out.println("Yes, You are on a correct Page with title TECHNOCREDITS");
		else
			System.out.println("You are on a page of title: " + driver.getTitle());

		// Filling all the details of form
		driver.findElement(By.id("first name")).sendKeys("Vaishnavi");
		driver.findElement(By.id("last name")).sendKeys("Vaidya");
		driver.findElement(By.id("E-mail")).sendKeys("Vaishnavivaidya06@gmail.com");
		driver.findElement(By.id("Company Name")).sendKeys("Atos-Syntel");

		WebElement maleRadioButton = driver.findElement(By.id("male"));
		maleRadioButton.click();

		WebElement experience = driver.findElement(By.id("1-3"));
		experience.click();
		driver.findElement(By.name("lang")).sendKeys("JAVA");
		WebElement language = driver.findElement(By.id("script"));
		language.click();
		WebElement dreamCompany = driver.findElement(By.id("google1"));
		dreamCompany.click();
		WebElement termsAndCondition = driver.findElement(By.id("check"));
		termsAndCondition.click();

		// Reseting the values of this form
		driver.findElement(By.id("reset12")).click();

		// Checking if all the radio buttons and check boxes reset is successfully done
		if (maleRadioButton.isSelected() == false)
			System.out.println("Reset is successfull for Male Female Radio button");
		else
			System.out.println("Reset was unsuccessfull for Male Female Radio button");
		if (experience.isSelected() == false)
			System.out.println("Reset is successfull for experience Radio button");
		else
			System.out.println("Reset was unsuccessfull for experience Radio button");
		if (language.isSelected() == false)
			System.out.println("Reset is successfull for language checkBox");
		else
			System.out.println("Reset was unsuccessfull for language checkBox");
		if (dreamCompany.isSelected() == false)
			System.out.println("Reset is successfull for dreamCompany radio button");
		else
			System.out.println("Reset was unsuccessfull for dreamCompany radio button");
		if (termsAndCondition.isSelected() == false)
			System.out.println("Reset is successfull for termsAndCondition");
		else
			System.out.println("Reset was unsuccessfull for termsAndCondition");
		
		//clicks on Go Url
		driver.findElement(By.id("Go")).click();
		
		//Condition to check if expected url is opened
		if(driver.getCurrentUrl().equals("http://automationbykrishna.com/") && driver.getTitle().equals("Login Signup Demo"))
			System.out.println("You are getting expected result and you are redirecting to correct page with title Login Signup Demo");
		else
			System.out.println("Yout code failed");
		
		driver.close();
	}

}
