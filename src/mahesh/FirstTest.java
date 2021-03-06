package mahesh;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///D:/Technocredit/Project/TechnoGitProject/SeleniumBasics/SeleniumAssignment_1.html");
		if (driver.getTitle().equals("TECHNOCREDITS")) {
			WebElement firstName = driver.findElement(By.id("first name"));
			firstName.sendKeys("Mahesh");
			driver.findElement(By.id("last name")).sendKeys("Kumavat");
			driver.findElement(By.id("E-mail")).sendKeys("MaheshKumavat@Gmail.com");
			driver.findElement(By.id("Company Name")).sendKeys("Citi");
			WebElement genderFemale = driver.findElement(By.id("female"));
			if(genderFemale.isSelected())
				driver.findElement(By.id("male")).click();
			else
				genderFemale.click();
			driver.findElement(By.id("10+")).click();
			driver.findElement(By.id("planguage")).sendKeys("Java,Python,VB");
			WebElement java = driver.findElement(By.id("java"));
			java.click();
			driver.findElement(By.id("python")).click();
			WebElement thoughtworks = driver.findElement(By.id("thoughtworks"));
			thoughtworks.click();
			driver.findElement(By.id("terms")).click();			
			driver.findElement(By.id("reset")).click();			//Reset form to clear all the input values
			if (firstName.getAttribute("value").equals("") && !thoughtworks.isSelected() && !java.isSelected()) {
				System.out.println("Reset Successful");
			}
			else {
				System.out.println("Reset Failed");
			}
			driver.findElement(By.id("redirect")).click();
			if(driver.getCurrentUrl().equals("http://automationbykrishna.com/") && driver.getTitle().equals("Login Signup Demo"))
				System.out.println("User Redirected to correct webpage.Test Completed Successfully");
			else
				System.out.println("User Redirected to incorrect webpage. Test Failed");
		}else {
			System.out.println("Incorrect URL");
		}
		driver.close();
		
	}
}
