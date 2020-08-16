package ajit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment1 {

	public static void main(String[] args) throws InterruptedException {
		String os = System.getProperty("os.name").toLowerCase();
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;

		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.get("D:\\JAVA_LEARNING\\Projects\\TechnoGitProject\\FirstForm.html");
		System.out.println("AutomationByKrishna website open successfully.");
		// Verify Page Title
		String actualTitle = driver.getTitle();
		String expectedTitle = "TECHNOCREDITS";
		if (actualTitle.equals(expectedTitle))
			System.out.println("Title Matched");
		else
			System.out.println("Title didn't match, Actual URL : " + actualTitle);
		// Select Radio button
		if (driver.findElement(By.id("maleG")).isSelected()) {
			driver.findElement(By.id("femaleG")).click();
			System.out.println("Female is Selected");
		} else {
			driver.findElement(By.id("maleG")).click();
			System.out.println("Male is Selected");
		}
		// Operation on other Fields
		driver.findElement(By.id("first name")).sendKeys("Ajit");
		driver.findElement(By.id("last name")).sendKeys("Singh");
		driver.findElement(By.id("knownlanguages")).sendKeys("Python");
		driver.findElement(By.id("fresher")).click();
		driver.findElement(By.id("java")).click();
		driver.findElement(By.id("tw")).click();

		// Reset the From
		driver.findElement(By.id("resetBtn")).click();
		if (driver.findElement(By.id("first name")).getAttribute("value").equals(""))
			System.out.println("From Reset Done Successfully");
		else
			System.out.println("From Reset  Unsuccessfully");

		// Verify Page Redirect
		driver.findElement(By.id("morePractice")).click();
		Thread.sleep(5000);
		System.out.println("Current URL : " + driver.getCurrentUrl());
		if ((driver.getTitle()).equals("Login Signup Demo"))
			System.out.println("Navigation to new URL is Successful");
		else
			System.out.println("Navigation to new URL is Unsuccessful");
		driver.quit();

	}

}
