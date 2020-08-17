/*1) Open the form in browser "file:///C:/Users/DELL%201801/Downloads/Microsoft.SkypeApp_kzf8qxf38zg5c!App/All/SeleniumAssignment_1%20(3).html" and verify Title is “TECHNOCREDITS”. 

2) Fill all details in the form, for Gender radio button if ‘Female’ radio button is by default selected then select ‘Male’   radio button and vice versa.

3) Reset the form, for that click on “Reset form in same tab” button.

4) Verify Reset works properly on your text field, radio button and checkbox. (For each type, min one validation is required.)

5) Click on the “Go and Practice for it” Button.

6) Website redirect to “automationbykrishna.com”.

7) Verify title and URL of redirected page website.
    Title should be “Login Signup Demo” and the URL should be “http://automationbykrishna.com/”.
*/
package barkha;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment1 {
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("file:///C:/Users/DELL%201801/Downloads/Microsoft.SkypeApp_kzf8qxf38zg5c!App/All/SeleniumAssignment_1%20(3).html");
		
		//verify correct title
		if (driver.getTitle().equals("TECHNOCREDITS"))
			System.out.println("You are on a correct Page having title TECHNOCREDITS.");
		else
			System.out.println("You are on a incorrect page.");
		
		//filling all details in the form
		WebElement firstName=driver.findElement(By.id("first name"));
		firstName.sendKeys("Barkha");
		
		WebElement lastName=driver.findElement(By.id("last name"));
		lastName.sendKeys("Patle");
		
		WebElement email=driver.findElement(By.id("E-mail"));
		email.sendKeys("barkhapatle01@gmail.com");
		
		WebElement company=driver.findElement(By.id("Company Name"));
		company.sendKeys("Syntel");
		
		//if female radio button is selected then click on male and vice versa
		if (driver.findElement(By.id("femaleG")).isSelected()) {
			driver.findElement(By.id("maleG")).click();
			
		}else {
			driver.findElement(By.id("femaleG")).click();;
			
		}
		
		WebElement exp=driver.findElement(By.id("entry2"));
		exp.click();
		
		WebElement lang=driver.findElement(By.id("knownlanguages"));
		lang.sendKeys("Java");
		
		WebElement learnLang1=driver.findElement(By.id("java"));
		learnLang1.click();
		
		WebElement learnLang2=driver.findElement(By.id("python"));
		learnLang2.click();
		
		WebElement comName=driver.findElement(By.id("google"));
		comName.click();
		
		WebElement terms=driver.findElement(By.id("termsAndConditions"));
		terms.click();
		
		//click on reset button
		driver.findElement(By.id("resetBtn")).click();
		
		//checking reset button is working or not (checked on all fields: texts/radio button/check boxes)
		if(firstName.isSelected()==false && lastName.isSelected()==false &&  email.isSelected()==false && company.isSelected()==false && driver.findElement(By.id("maleG")).isSelected()==false && exp.isSelected()==false && lang.isSelected()==false && learnLang1.isSelected()==false && learnLang2.isSelected()==false && comName.isSelected()==false && terms.isSelected()==false) {
			System.out.println("Reset is Done Successfully on all fields.");
		}
		else {
			System.out.println("Reset Failed!!");
		}
		
		//verifying that redirecting to correct URL or not
		driver.findElement(By.id("morePractice")).click();
		if(driver.getCurrentUrl().equals("http://automationbykrishna.com/") && driver.getTitle().equals("Login Signup Demo"))
			System.out.println("You are redirecting to correct page having title Login Signup Demo.");
		else
			System.out.println("You have redirected to unexpected site");
		
		driver.close();
	}
}	