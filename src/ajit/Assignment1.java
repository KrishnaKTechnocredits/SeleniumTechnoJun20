/*
 Selenium Assignment 1 : 16th Aug 2020

1) Open the form in browser and verify Title is “TECHNOCREDITS”. 
2) Fill all details in the form, for Gender radio button if ‘Female’ radio button is by default selected then select ‘Male’   radio button and vice versa.
3) Reset the form, for that click on “Reset form in same tab” button.
4) Verify Reset works properly on your text field, radio button and checkbox. (For each type, min one validation is required.)
5) Click on the “Go and Practice for it” Button.
6) Website redirect to “automationbykrishna.com”.
7) Verify title and URL of redirected page website.
   Title should be “Login Signup Demo” and the URL should be “http://automationbykrishna.com/”.
 */
 
package ajit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;

public class Assignment1 {

	public static void main(String[] args) throws InterruptedException {
		String os = System.getProperty("os.name").toLowerCase();
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;

		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.get("D:\\JAVA_LEARNING\\Projects\\TechnoGitProject\\FirstForm.html");
		driver.manage().window().maximize();
		System.out.println("AutomationByKrishna website open successfully.");
		// Verify Page Title
		String actualTitle = driver.getTitle();
		String expectedTitle = "TECHNOCREDITS";
		if (actualTitle.equals(expectedTitle))
			System.out.println("Title Matched");
		else
			System.out.println("Title didn't match, Actual URL : " + actualTitle);
		// Select Radio button
		if (driver.findElement(By.id("femaleG")).isSelected()) {
			driver.findElement(By.id("maleG")).click();
			System.out.println("Male is Selected");
		} else {
			driver.findElement(By.id("femaleG")).click();
			System.out.println("Female is Selected");
		}
		// Operation on other Fields
		driver.findElement(By.id("first name")).sendKeys("Ajit");
		driver.findElement(By.id("last name")).sendKeys("Singh");
		driver.findElement(By.id("E-mail")).sendKeys("hi2ajitkumar@gmail.com");
		driver.findElement(By.id("Company Name")).sendKeys("Techno");
		driver.findElement(By.id("knownlanguages")).sendKeys("Python");
		driver.findElement(By.id("fresher")).click();
		driver.findElement(By.id("java")).click();
		driver.findElement(By.id("tw")).click();

		// Reset the From
		driver.findElement(By.id("resetBtn")).click();

		if (driver.findElement(By.id("first name")).getAttribute("value").equals("")
				&& driver.findElement(By.id("femaleG")).isSelected()
				&& driver.findElement(By.id("Java")).isSelected() == false)
			System.out.println("From Reset Done Successfully");
		else
			System.out.println("From Reset  Unsuccessfully");

		// Verify Page Redirect
		driver.findElement(By.id("morePractice")).click();
		Thread.sleep(5000);
		System.out.println("Current URL : " + driver.getCurrentUrl());
		if (driver.getCurrentUrl().equals("http://automationbykrishna.com/")
				&& driver.getTitle().equals("Login Signup Demo"))
			System.out.println("Navigation to new URL is Successful");
		else
			System.out.println("Navigation to new URL is Unsuccessful");
		driver.quit();

	}

}
