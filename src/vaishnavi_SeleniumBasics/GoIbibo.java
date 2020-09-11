package vaishnavi_SeleniumBasics;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import vaishnavi_Base.PredefinedAction;

/* Steps :
1) Go to https://www.goibibo.com/
2) Enter 'Pun' in From field, Print all the option from given auto-suggestions and Select Pune.
3) Enter 'Del' in Destination field, Print all the option from given auto-suggestions and select Delhi.
4) Select today or tomorrows date in "Departure" date picker and 
       Verify month-year text is correct,
       Mon to Sun - days are visible, 
       Select date should be enabled, 
       Previous month navigation arrow should not be present.
5) Verify selected date should be visible in Departure field. 
6) Select any day of Dec 2020 in "Return" date picker and
       Verify month-year text is correct,
       Mon to Sun - days are visible, 
       Select date should be enabled, 
       Previous month navigation arrow and next month navigation arrow should be present.
7) Verify selected date should be visible in Return field.  */

public class GoIbibo extends PredefinedAction{
	
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeTest
	void setUp() {
		driver = start("https://www.goibibo.com/");
		wait = new WebDriverWait(driver,3);
	}
	
	
	ArrayList<String> verifyAutoSuggestions() {
	
		ArrayList<String> autoSuggestions = new ArrayList<String>();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'dF whiteBg')]/ul//li")));
		int count = driver.findElements(By.xpath("//div[contains(@class,'dF whiteBg')]/ul//li")).size();
		
		for(int index=1;index<=count;index++) {
			String suggestion = driver.findElement(By.xpath("//div[contains(@class,'dF whiteBg')]/ul//li["+index+"]/div/div[@class='dib marginL10 pad0 textOverflow width90']/div[1]/span[1]")).getText();
			autoSuggestions.add(suggestion);						
		}
		return autoSuggestions;
	}
	
	// Method to print auto suggestions after we type Pun in from field and Click on Pune
	@Test(priority =1 )
	void getFromAutoSuggestions() {
		driver.findElement(By.xpath("//input[@id='gosuggest_inputSrc']")).sendKeys("Pun"); //Enter Pun in Form field
		ArrayList<String> fromSuggestions = verifyAutoSuggestions(); //Call another method to get the list
		System.out.println("From autoSuggestions are as below: ");
		System.out.println(fromSuggestions);
		for(int index=0;index<fromSuggestions.size();index++) {
			if(fromSuggestions.get(index).equals("Pune, India")) {
				WebElement expected = driver.findElement(By.xpath("//div[contains(@class,'dF whiteBg')]/ul//li["+(index+1)+"]/div/div[@class='dib marginL10 pad0 textOverflow width90']/div[1]/span[1]"));
				expected.click();
			}			
		}
	}
	
	// Method to print auto suggestions after we type Del in Destination field and click on Delhi
	@Test(priority=2)
	void getDestinationAutoSuggestions() {
		driver.findElement(By.xpath("//input[@id='gosuggest_inputDest']")).sendKeys("Del"); //Enter Del in Destination field
		ArrayList<String> destinationSuggestions = verifyAutoSuggestions(); //Call another method to get the list
		System.out.println("Destination autoSuggestions are as below: ");
		System.out.println(destinationSuggestions);
		for(int index=0;index<destinationSuggestions.size();index++) {
			if(destinationSuggestions.get(index).equals("Delhi, India")) {
				WebElement expected = driver.findElement(By.xpath("//div[contains(@class,'dF whiteBg')]/ul//li["+(index+1)+"]/div/div[@class='dib marginL10 pad0 textOverflow width90']/div[1]/span[1]"));
				expected.click();
			}			
		}
	}
	
	// Method for Departure calendar Validation
	@Test(priority=3)
	void calendarDepartureValidation() {
		String expected = "September 2020";
		String actual = driver.findElement(By.xpath("//div[@role='heading']")).getText();
		assertEquals(expected, actual); //Verify month-year text is correct
	   
		int count = driver.findElements(By.xpath("//div[@class='DayPicker-Weekday']")).size();
		for(int index=1;index<=count;index++) {
		    WebElement days = driver.findElement(By.xpath("//div[@class='DayPicker-Weekday']["+index+"]/abbr"));
		    days.isDisplayed();  //  Mon to Sun - days are visible
		}
		
		WebElement date = driver.findElement(By.xpath("//div[@id='fare_20200911']"));
		date.isEnabled(); //Select date should be enabled
		
		assertEquals(driver.findElements(By.xpath("//div/span[@role='button']")).size(), 1); //Previous month navigation arrow should not be present.
		
		date.click(); //Select today or tomorrows date in "Departure" date picker
		
		driver.findElement(By.xpath("//input[@value='Fri, 11 Sep']")).isDisplayed(); //Verify selected date should be visible in Departure field.
	}

    @Test(priority=4)
	void calendarReturnValidation() {
    	driver.findElement(By.xpath("//input[@id='returnCalendar']")).click();
    	String expected = "December 2020";
    	String actual = driver.findElement(By.xpath("//div[@role='heading']")).getText();
    	
    	while(!actual.equals(expected)) {
		  driver.findElement(By.xpath("//div/span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
		  actual = driver.findElement(By.xpath("//div[@role='heading']")).getText();	
    	}    	
    	assertEquals(expected, actual); //Verify month-year text is correct
    	
    	int count = driver.findElements(By.xpath("//div[@class='DayPicker-Weekday']")).size();
		for(int index=1;index<=count;index++) {
		    WebElement days = driver.findElement(By.xpath("//div[@class='DayPicker-Weekday']["+index+"]/abbr"));
		    days.isDisplayed();  //  Mon to Sun - days are visible
		}
		
		WebElement date = driver.findElement(By.xpath("//div[@id='fare_20201205']"));
		date.isEnabled(); //Select date should be enabled
		
		assertEquals(driver.findElements(By.xpath("//div/span[@role='button']")).size(), 2); //Previous month navigation arrow and next month navigation arrow should be present.
		date.click();
		driver.findElement(By.xpath("//input[@value='Sat, 5 Dec']")).isDisplayed(); //Verify selected date should be visible in Departure field.
	}
}
