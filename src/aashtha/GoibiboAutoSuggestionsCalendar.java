package aashtha;

/*Steps :
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
7) Verify selected date should be visible in Return field. */

import aashtha.base.*;

import java.time.LocalDate;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoibiboAutoSuggestionsCalendar extends PredefinedActions{
	WebDriver driver;
	WebDriverWait wait;
	LocalDate date;
	String dateString;

	// opens the Chrome
	@BeforeTest
	void setUp() {
		driver = start("https://www.goibibo.com/");
		wait = new WebDriverWait(driver, 20);
		date = LocalDate.now(); //storing the system date
		dateString = date.toString(); //converting system date in string format
	}

	// closes the browser window
	@AfterTest
	void tearDown() {
		driver.close();
	}
	
	//prints all the auto-suggestions for source/destination fields
	void printAutoSuggestionsAndClick(String requiredPlace) {
		int totalSuggestions = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='react-autosuggest-1']/li"))).size();
		int placeIndex = 0;
		for(int index = 1; index <= totalSuggestions; index++) {
			String suggestion = driver.findElement(By.xpath("//ul[@id='react-autosuggest-1']/li[" + index + "]/div/div[2]/div[1]/span[1]")).getText();
			System.out.println(suggestion);
			if(suggestion.contains(requiredPlace))
				placeIndex = index;
		}
		driver.findElement(By.xpath("//ul[@id='react-autosuggest-1']/li[" + placeIndex + "]/div/div[2]/div[1]/span[1]")).click();
	}
	
	//maintains mapping of month no. -> month name
	//returns month name value on passing the moth no. as key
	String getMonthName(String monthKey) {
		HashMap<String,String> hm = new HashMap<String, String>();
        hm.put("01", "January");
        hm.put("02", "February");
        hm.put("03", "March");
        hm.put("04", "April");
        hm.put("05", "May");
        hm.put("06", "June");
        hm.put("07", "July");
        hm.put("08", "August");
        hm.put("09", "September");
        hm.put("10", "October");
        hm.put("11", "November");
        hm.put("12", "December");
        return hm.get(monthKey);
	}
	
	//verifies if all the days are visible in correct format in departure/return date calendar
	void checkDaysVisible() {
		int totalDays = driver.findElements(By.xpath("//div[@class='DayPicker-WeekdaysRow']/div")).size();
		String[] actualWeekDays = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		for(int index = 1; index <= totalDays; index++) {
			String actualDay = actualWeekDays[index-1].substring(0,2);
			String expectedDay = driver.findElement(By.xpath("//div[@class='DayPicker-WeekdaysRow']/div[" + index + "]/abbr")).getText();
			Assert.assertEquals(actualDay, expectedDay);
		}
	}
	
	//prints all the auto suggestions for source location and verifies if "Pune" selected successfully
	@Test(priority = 1)
	void validateSource() {
		String source = "Pune";
		driver.findElement(By.id("gosuggest_inputSrc")).sendKeys("Pun");
		System.out.println("\nBelow is the list of auto suggestions on entering 'Pun' in source:");
		printAutoSuggestionsAndClick(source);
		Assert.assertTrue(driver.findElement(By.id("gosuggest_inputSrc")).getAttribute("value").contains(source));
	}
	
	//prints all the auto suggestions for destination location and verifies if "Delhi" selected successfully
	@Test(priority = 2)
	void validateDestination() {
		String destination = "Delhi";
		System.out.println("\nBelow is the list of auto suggestions on entering 'Del' in Destination :");
		driver.findElement(By.id("gosuggest_inputDest")).sendKeys("Del");
		printAutoSuggestionsAndClick(destination);
		Assert.assertTrue(driver.findElement(By.id("gosuggest_inputDest")).getAttribute("value").contains(destination));
	}
	
	//Validates the calendar for departure 
	@Test(priority = 3)
	void validateDepartureCalendar() {
		driver.findElement(By.id("departureCalendar")).click();
		String year = dateString.substring(0,4);
		String month = dateString.substring(5,7);
		String departDate = dateString.substring(8,10);
		
		//Verify month-year text is correct
		String expectedMonthYear = getMonthName(month) + " " + year;
		String actualMonthYear = driver.findElement(By.className("DayPicker-Caption")).getText();
		Assert.assertEquals(actualMonthYear, expectedMonthYear);
		
		//Mon to Sun - days are visible
		checkDaysVisible();
		
		//Select date should be enabled
		WebElement departureDate = driver.findElement(By.id("fare_" + year + month +  departDate));
		Assert.assertTrue(departureDate.isEnabled());
		
		//Previous month navigation arrow should not be present.
		Assert.assertTrue(driver.findElements(By.xpath("//*[@class='DayPicker-NavButton DayPicker-NavButton--prev']")).size()==0);
		
		//5) Verify selected date should be visible in Departure field. 
		departureDate.click();
		String actualDateText = driver.findElement(By.id("departureCalendar")).getAttribute("value");
		String dayOfWeek = date.getDayOfWeek().toString().substring(0,1) + date.getDayOfWeek().toString().substring(1,3).toLowerCase();
		String expectedDateText = dayOfWeek + ", " + departDate + " " + getMonthName(month).substring(0,3);
		Assert.assertEquals(actualDateText, expectedDateText);
	}

	//Validates the calendar for Return journey
	@Test(priority = 4)
	void validateReturnCalendar() {
		LocalDate returnIn3Months = date.plusMonths(3); //date after 3 months from current date
		String year = returnIn3Months.toString().substring(0,4);
		String monthName = getMonthName(returnIn3Months.toString().substring(5,7));
		String returnDate = returnIn3Months.toString().substring(8,10);
		
		//6) Select any day of Dec 2020 in "Return" date picker
		driver.findElement(By.id("returnCalendar")).click();
		WebElement returnMonthDiv = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']"));
		while(!returnMonthDiv.getText().contains(monthName)) {
			driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
		}
		
		//Verify month-year text is correct
		String expectedReturnMonthYear = monthName + " " + year;
		String actualReturnMonthYear = driver.findElement(By.className("DayPicker-Caption")).getText();
		Assert.assertEquals(actualReturnMonthYear, expectedReturnMonthYear);
		
		//Mon to Sun - days are visible, 
		checkDaysVisible();
		
		//Select date should be enabled
		WebElement returnDateEnabled = driver.findElement(By.id("fare_" + year + returnIn3Months.toString().substring(5,7) +  returnDate));
		Assert.assertTrue(returnDateEnabled.isEnabled());
		
		//Previous month navigation arrow and next month navigation arrow should be present.
		Assert.assertTrue(driver.findElements(By.xpath("//*[@class='DayPicker-NavButton DayPicker-NavButton--prev']")).size()!=0);
		Assert.assertTrue(driver.findElements(By.xpath("//span[contains(@class,'next')]")).size()!=0);
		
		//7) Verify selected date should be visible in Return field
		driver.findElement(By.xpath("//div[contains(text(),'" + returnDate + "')]")).click();
		String actualReturnDateText = driver.findElement(By.id("returnCalendar")).getAttribute("value");
		String returnDayOfWeek = returnIn3Months.getDayOfWeek().toString().substring(0,1) + returnIn3Months.getDayOfWeek().toString().substring(1,3).toLowerCase();
		String expectedReturnDateText = returnDayOfWeek + ", " + returnDate + " " + monthName.substring(0,3);
		Assert.assertEquals(actualReturnDateText, expectedReturnDateText);
	}
}
