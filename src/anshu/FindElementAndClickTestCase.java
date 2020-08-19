package anshu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class FindElementAndClickTestCase {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///C:/Users/hp/Desktop/Anshu/TechnoGitProject/SeleniumAssignment_1.html");
		
	     /// first name
		
		WebElement firstNametext = driver.findElement(By.id("first name"));
		 firstNametext.sendKeys("Anshu");
	     System.out.println( firstNametext.getAttribute("value"));
		  
		  // lastName
		  
		 WebElement lastNametext = driver.findElement(By.id("last name"));
		 lastNametext.sendKeys("Kashyap");
		  System.out.println(lastNametext.getAttribute("value"));
		 
		 // email
		 
		 WebElement emailText = driver.findElement(By.id("E-mail"));
		 emailText.sendKeys("anshu.v.kumari02@gamil.com");
		  System.out.println(emailText.getAttribute("value"));
		 
		 //companyName
		 
		 WebElement companyNameText = driver.findElement(By.id("Company Name"));
		 companyNameText.sendKeys("TechnoCredits");
		 System.out.println(companyNameText.getAttribute("value"));
		 
		 // gender
		 
		 WebElement genderRadio = driver.findElement(By.id("female"));
		 System.out.println(genderRadio.getAttribute("value"));
		 genderRadio.click();
		 
		 
		 //student
		 WebElement experienceRadio = driver.findElement(By.id("student"));
		 System.out.println(experienceRadio.getAttribute("value"));
		 experienceRadio.click();
		
		 // language text
		 WebElement languageText = driver.findElement(By.id("language"));
		 System.out.println(languageText.getAttribute("value"));
		 languageText.sendKeys("java");
		 
		 // language checkbutton
		 
		 WebElement languagewantToLearnCheck = driver.findElement(By.id("java"));
		 System.out.println(languagewantToLearnCheck.getAttribute("value"));
		 languagewantToLearnCheck.click();
		
		 
		 WebElement languagewantToLearnTextCheck = driver.findElement(By.id("js"));
		 System.out.println(languagewantToLearnTextCheck.getAttribute("value"));
		 languagewantToLearnTextCheck.click();
		 
		 
		
		 // company check button
		 
		 WebElement dreamComapnyCheck = driver.findElement(By.id("google"));
		 System.out.println(dreamComapnyCheck.getAttribute("value"));
		 dreamComapnyCheck.click();
		
		 
		 // reset find element
		 
		 WebElement resetButton = driver.findElement(By.id("reset"));
		 System.out.println(resetButton.getAttribute("value"));
		 System.out.println();
		 
		 //testcasecode
		System.out.println("Test Case on Reset Click");
		System.out.println();
		 
		 resetButton.click();
		
		 
		 
		if(resetButton.isSelected()) {
			 System.out.println("Test Fail");
		 }else {
			 System.out.println(" Test pass");
		 }
		 
		 if(experienceRadio.isSelected() && languagewantToLearnCheck.isSelected() && dreamComapnyCheck.isSelected()) {
			 System.out.println("Test Fail");
		 }else {
			 System.out.println("Test pass");
		 }
		 
		 
		 // navigation link
		 
		 WebElement goAndPracticeClick = driver.findElement(By.id("link"));
		 goAndPracticeClick.click();
		 
		 String url = driver.getCurrentUrl();
			System.out.println(url);
			
			
		     if(url.equals("http://automationbykrishna.com/")) {
		    	 System.out.println("Test Pass");
		     }else {
		    	 System.out.println("Test fail");
		     }
		
		
		 driver.close();
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		
		 
		 
		
	}
	
}
