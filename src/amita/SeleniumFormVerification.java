/*Selenium Assignment 1 : 16th Aug 2020
1) Open the form in browser and verify Title is “TECHNOCREDITS”. 
2) Fill all details in the form, for Gender radio button if ‘Female’ radio button is by default selected then select ‘Male’   radio button and vice versa.
3) Reset the form, for that click on “Reset form in same tab” button.
4) Verify Reset works properly on your text field, radio button and checkbox. (For each type, min one validation is required.)
5) Click on the “Go and Practice for it” Button.
6) Website redirect to “automationbykrishna.com”.
7) Verify title and URL of redirected page website.
    Title should be “Login Signup Demo” and the URL should be “http://automationbykrishna.com/”.
SeleniumAssignment_1.html*/

package amita;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumFormVerification {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				"D:\\JAVA-JUNE20\\TechnoGitProject\\SeleniumTechnoJun20\\resources\\windows\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///D:/JAVA-JUNE20/TechnoGitProject/SeleniumBasics/SeleniumAssignment_1.html");

		// 1) Open the form in browser and verify Title is “TECHNOCREDITS”.
		if (driver.getTitle().equals("TECHNOCREDITS")) {
			System.out.println("Test case : PASS. The Title is matching with expected result and the title is : "
					+ driver.getTitle());
		} else {
			System.out.println("Test case : FAIL The Title is not matching with expected result and the title is : "
					+ driver.getTitle());
		}
		// 2) Fill all details in the form
		// WebElement firstName = driver.findElement(By.id("first name"));
		// firstName.sendKeys("Amita");
		driver.findElement(By.id("first name")).sendKeys("Amita");
		driver.findElement(By.id("last name")).sendKeys("Rout");
		driver.findElement(By.id("E-mail")).sendKeys("amitarout11@gmail.com");
		driver.findElement(By.id("Company Name")).sendKeys("Technocredits");
		driver.findElement(By.id("continents")).sendKeys("North America");
		driver.findElement(By.id("entry2")).click();
		driver.findElement(By.id("knownlanguages")).sendKeys("Java");
		driver.findElement(By.id("java")).click();
		driver.findElement(By.id("python")).click();
		driver.findElement(By.id("javascript")).click();
		driver.findElement(By.id("google")).click();
		driver.findElement(By.id("termsAndConditions")).click();

		// for Gender radio button if ‘Female’ radio button is by default selected then
		// select ‘Male’ radio button and vice versa
		if (driver.findElement(By.id("femaleG")).isSelected()) {
			driver.findElement(By.id("maleG")).click();
			System.out.println("Radio button default selection was female .Now male radio button is selected");
		} else {
			driver.findElement(By.id("femaleG")).click();
			System.out.println("Radio button default selection was male .Now female radio button is selected");
		}

		// 3) Reset the form, for that click on “Reset form in same tab” button.
		driver.findElement(By.id("resetBtn")).click();

		// 4) Verify Reset works properly on your text field, radio button and checkbox.
		// verifying Reset for text box
		if (driver.findElement(By.id("first name")).getAttribute("value").isEmpty()
				&& driver.findElement(By.id("last name")).getAttribute("value").isEmpty()
				&& driver.findElement(By.id("E-mail")).getAttribute("value").isEmpty()
				&& driver.findElement(By.id("Company Name")).getAttribute("value").isEmpty()) {
			System.out.println("Test case : PASS. All text box reset verification is working properly");
		} else {
			System.out.println("Test case : FAIL. All text box reset verification is not working properly");

		}

		// verifying Reset for radio button
		if (driver.findElement(By.id("femaleG")).isSelected() && !driver.findElement(By.id("maleG")).isSelected())
			System.out.println("Test case : PASS. gender radio button reset verification is working properly");
		else
			System.out.println("Test case : FAIL. gender radio button reset verification is not working properly");

		// verifying Reset for checkbox
		if (!driver.findElement(By.id("java")).isSelected() && !driver.findElement(By.id("python")).isSelected()
				&& !driver.findElement(By.id("javascript")).isSelected()) {
			System.out.println("Test case : PASS. Check box reset verification is working properly");
		} else {
			System.out.println("Test case : PASS. Check box reset verification is not working properly");
		}

		// 5)Click on the “Go and Practice for it” Button.
		driver.findElement(By.id("morePractice")).click();

		// 7) Website redirect to “automationbykrishna.com”.
		if (driver.getTitle().equals("Login Signup Demo")
				&& driver.getCurrentUrl().equals("http://automationbykrishna.com/")) {
			System.out.println(
					"Test case : PASS . Successfully navigated to http://automationbykrishna.com/ and the title is Login Signup Demo ");
		} else {
			System.out.println("Test case : Fail . Navigation unsuccessfull");
		}

		driver.close();
	}
}
