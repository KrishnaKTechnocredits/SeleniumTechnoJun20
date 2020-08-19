package anup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*1) Open the form in browser and verify Title is “TECHNOCREDITS”. 

2) Fill all details in the form, for Gender radio button if ‘Female’ radio button is by default selected then select ‘Male’   radio button and vice versa.

3) Reset the form, for that click on “Reset form in same tab” button.

4) Verify Reset works properly on your text field, radio button and checkbox. (For each type, min one validation is required.)

5) Click on the “Go and Practice for it” Button.

6) Website redirect to “automationbykrishna.com”.

7) Verify title and URL of redirected page website.
    Title should be “Login Signup Demo” and the URL should be “http://automationbykrishna.com/”.*/

public class SeleniumFormTest {
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "/Users/amitarout/Desktop/TechnoGitProject/SeleniumJun20/SeleniumTechnoJun20/resources/mac/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///Users/amitarout/Desktop/TechnoGitProject/SeleniumBasics/SeleniumAssignment_1_updated.html");
		
		//1) Open the form in browser and verify Title is “TECHNOCREDITS”.
		String expectedTitle = "TECHNOCREDITS";
		String actualTitle = driver.getTitle();
		
		if(expectedTitle.equals(actualTitle)) {
			System.out.println("Test Pass");
			System.out.println("The Title is correct");
		}
		else {
			System.out.println("Test fail");
			System.out.println("The Title is incorrect" + actualTitle);
		}
		
		//2) Fill all details in the form, for Gender radio button if ‘Female’ radio button is by default selected then select ‘Male’   radio button and vice versa.
		WebElement firstName = driver.findElement(By.id("first name"));
		firstName.sendKeys("Anup");
		driver.findElement(By.id("last name")).sendKeys("Sahoo");
		driver.findElement(By.id("E-mail")).sendKeys("anup.sahoo1408@gmail.com");
		driver.findElement(By.id("Company Name")).sendKeys("ABC");
		WebElement gender = driver.findElement(By.name("gender"));
		gender = driver.findElement(By.id("maleG"));
		if(driver.findElement(By.id("maleG")).isSelected()){
			driver.findElement(By.id("femaleG")).click();
		}
		else if(driver.findElement(By.id("femaleG")).isSelected()) {
			driver.findElement(By.id("maleG")).click();
		}
		//driver.findElement(By.name("continents")).click();
		
		driver.findElement(By.id("entry4")).click();
		
		driver.findElement(By.id("knownlanguages")).sendKeys("Java");
		
		driver.findElement(By.id("java")).click();
		driver.findElement(By.id("python")).click();
		driver.findElement(By.id("javascript")).click();
		
		driver.findElement(By.id("google")).click();;
		
		driver.findElement(By.id("termsAndConditions")).click();
		
		System.out.println(firstName.getAttribute("value").length());
	
		//3) Reset the form, for that click on “Reset form in same tab” button.
		driver.findElement(By.id("resetBtn")).click();
		
		//4) Verify Reset works properly on your text field, radio button and checkbox. (For each type, min one validation is required.)
		if(firstName.getAttribute("value").length() == 0) {
			System.out.println("Text box reset validation Test Pass");
		}
		else {
			System.out.println("Text box reset validation Test Fail");
		}
		
		//Reset on gender
		if(!driver.findElement(By.id("maleG")).isSelected() && driver.findElement(By.id("femaleG")).isSelected() ) {
			System.out.println("Test Pass and Gender radio button is reset");
		}
		else {
			System.out.println("Test Fail - gender fileds are not cleared");
		}
		
		//Reset on Total years Experience
		if(!driver.findElement(By.id("fresher")).isSelected() && !driver.findElement(By.id("entry1")).isSelected() && !driver.findElement(By.id("entry2")).isSelected() &&  !driver.findElement(By.id("entry3")).isSelected() && !driver.findElement(By.id("entry4")).isSelected()) {
			System.out.println("Test Pass and Experience radio button is reset");
		}
		else {
			System.out.println("Test Fail - Experience radio button is not reset");
		}
		
		//Reset on Known Language
		
		if(driver.findElement(By.id("knownlanguages")).getAttribute("value").length() == 0) {
			System.out.println("Test Pass : Known Language field is reset");
		}
		else {
			System.out.println("Test Fail : Known Language field is not reset");
		}
		
		//Reset on language wanted to learn
		
		if(!driver.findElement(By.id("java")).isSelected() && !driver.findElement(By.id("python")).isSelected() && !driver.findElement(By.id("javascript")).isSelected()){
			System.out.println("Test Pass - The Language wanted to learn fields are reset");
		}
		else {
			System.out.println("Test Fail - The Language wanted to learn fields are not reset");
		}
		
		
		//5) Click on the “Go and Practice for it” Button.
		driver.findElement(By.id("morePractice")).click();
		String expectedURL = "http://automationbykrishna.com/";
		if(expectedURL.equals(driver.getCurrentUrl())) {
			System.out.println("Expected URL Test Pass");
		}
		else {
			System.out.println("Test Fail and the current url opened is " + driver.getCurrentUrl());
		}
		String navigatedTitle = "Login Signup Demo";
		if(navigatedTitle.equals(driver.getTitle())){
			System.out.println("Test Pass and the page title is " + driver.getTitle());
		}
		else {
			System.out.println("Test Fail and the new page Title is :" + driver.getTitle());
		}
		driver.close();
		}
	}
