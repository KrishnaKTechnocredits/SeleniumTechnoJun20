/*Selenium Assignment 1 : 16th Aug 2020
1) Open the form in browser and verify Title is “TECHNOCREDITS”.
2) Fill all details in the form, for Gender radio button if ‘Female’ radio button is by default selected then 
select ‘Male’ radio button and vice versa.
3) Reset the form, for that click on “Reset form in same tab” button.
4) Verify Reset works properly on your text field, radio button and checkbox. (For each type, min one validation is required.)
5) Click on the “Go and Practice for it” Button.
6) Website redirect to “automationbykrishna.com”.
7) Verify title and URL of redirected page website.
Title should be “Login Signup Demo” and the URL should be “http://automationbykrishna.com/”.*/
package palak;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FormAssignment1 {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("H:/TechnoCredits/First Form.html");

		// verifying Title is “TECHNOCREDITS”.
		String title = driver.getTitle();
		if (title.equals("TECHNOCREDITS"))
			System.out.println("Test Pass \n1.Title is ->" + title);
		else
			System.out.println("Test Fail Title is not " + title);

		// Fill all the form
		driver.findElement(By.id("first name")).sendKeys("Palash");
		driver.findElement(By.id("last name")).sendKeys("Soni");
		driver.findElement(By.id("E-mail")).sendKeys("Palash444@gmail.com");
		driver.findElement(By.id("Company Name")).sendKeys("Vodafone");
		
		//Selecting Gender Value
		WebElement genderValue = driver.findElement(By.name("gender"));
		genderValue.click();
		if (genderValue.isSelected())
			System.out.println("Test Pass \n2.Selected Gender is -> "
					+ driver.findElement(By.name("gender")).getAttribute("value"));
		else
			System.out.println("Test Fail");
		
		//Selecting Region Value
		Select regionValue = new Select(driver.findElement(By.name("continents")));
		regionValue.selectByVisibleText("Europe");

		//Selecting Experience Value
		WebElement experienceValue = driver.findElement(By.id("entry"));
		experienceValue.click();
		if (experienceValue.isSelected())
			System.out.println("Test Pass \n3.Total Years of Exp. is selected");
		else
			System.out.println("Test Fail");
		
		//Entering Know Language Value
		driver.findElement(By.id("knownlanguage")).sendKeys("Java");
		String langValue = driver.findElement(By.id("knownlanguage")).getAttribute("value");
		if (!langValue.isEmpty())
			System.out.println("Test Pass \n4.Known Language is -> " + langValue);
		else
			System.out.println("Test Fail Language is not entered");
		
		//Selection Learning Language
		WebElement learningLangValue = driver.findElement(By.id("java"));
		learningLangValue.click();
		if (learningLangValue.isSelected() && learningLangValue.getAttribute("value").equals("python")) {
			System.out.println("Test Pass \n5.Learning Language is -> " + learningLangValue.getAttribute("value"));
		} else
			System.out.println(
					"5.Test Fail \nyou have selected wrong language -> " + learningLangValue.getAttribute("value"));
		
		//Selecting Dream company value
		WebElement dreamCompanyValue = driver.findElement(By.id("google"));
		dreamCompanyValue.click();
		if (dreamCompanyValue.isSelected() && dreamCompanyValue.getAttribute("value").equals("Google"))
			System.out.println("Test Pass\n6.Dream Company is -> " + dreamCompanyValue.getAttribute("value"));
		else
			System.out.println(
					"6.Test Fail you have selected wrong company ->" + dreamCompanyValue.getAttribute("value"));
		
		//Clicking on Confirm details Check box
		WebElement confirmDetailValue = driver.findElement(By.id("confirmDetails"));
		confirmDetailValue.click();
		if (confirmDetailValue.isSelected()) {
			System.out.println("Test Pass\n7.Confirm Details is selected");
		} else
			System.out.println("Test Fail\n7. Confirm details is not selected");

		// Reset Functionality
		WebElement reset = driver.findElement(By.id("resetBtn"));
		reset.click();
		if (driver.findElement(By.id("Company Name")).getAttribute("value").isEmpty())
			System.out.println("Test Pass \n8.Form is reset");
		else
			System.out.println("Test Fail \n8.Form is not reset");

		// Click on the “Go and Practice for it” Button.
		driver.findElement(By.id("morePractice")).click();
		
		//Verify title and URL of redirected page website.
		if (driver.getTitle().equals("Login Signup Demo")
				&& driver.getCurrentUrl().equals("http://automationbykrishna.com/")) {
			System.out.println("Test Pass \n9.Website Title is ->" + driver.getTitle() + "\nand Current Url is -> "
					+ driver.getCurrentUrl());
		} else
			System.out.println("Test Fail \n9.Website not redirected");
	}

}
