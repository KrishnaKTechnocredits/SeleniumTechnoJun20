package palak.testng;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import palak.base.PredefinedActions;
import palak.utility.PropertyFileOperation;

public class Goibibo_Autocomplete_Calendar extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeTest
	void setUp() {
		driver = start("https://www.goibibo.com/");
	}

	Date getCurrentDateObj() {
		Date dateobj = new Date();
		/// DateFormat df = new SimpleDateFormat("dd/MM/yy");
		return dateobj;
	}

	ArrayList<String> getExpectedWeekDays() {
		ArrayList<String> expectedWeekdays = new ArrayList<>();
		expectedWeekdays.add("Sunday");
		expectedWeekdays.add("Monday");
		expectedWeekdays.add("Tuesday");
		expectedWeekdays.add("Wednesday");
		expectedWeekdays.add("Thursday");
		expectedWeekdays.add("Friday");
		expectedWeekdays.add("Saturday");

		return expectedWeekdays;
	}

	void calendarWeekdaysValidation(){
		// Validate Mon to Sun days are visible
		List<WebElement> weekdays = driver.findElements(By.xpath("//div[@class='DayPicker-Weekday']/abbr"));
		List<String> expectedWeekDays = getExpectedWeekDays();
		int size = weekdays.size();

		for (int index = 0; index < size; index++) {
			String actualWeekdayTitleValue = weekdays.get(index).getAttribute("title");
			String expectedWeekday = expectedWeekDays.get(index);
			// Visibility check
			Assert.assertEquals(weekdays.get(index).isDisplayed(), true);
			// Sun to Sat validation
			Assert.assertEquals(actualWeekdayTitleValue, expectedWeekday);
		}
	}

	@Test
	void fillSourceDestination() throws IOException {
		String expectedSourceText = "Pune, India";
		
		driver.findElement(By.xpath("//input[@id='gosuggest_inputSrc']")).sendKeys("Pun");
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='react-autosuggest-1']/li")));

		int totalSourcsListSize = driver.findElements(By.xpath("//ul[@id='react-autosuggest-1']/li")).size();
		String acutalSourceText = driver
				.findElement(By.xpath("//ul[@id='react-autosuggest-1']/li[1]//div[@class='mainTxt clearfix']/span[1]"))
				.getText();

		// iterating through list of source location
		for (int index = 1; index <= totalSourcsListSize; index++) {
			WebElement source = driver.findElement(By.xpath(
					"//ul[@id='react-autosuggest-1']/li[" + index + "]//div[@class='mainTxt clearfix']/span[1]"));
			if (source.getText().equals(expectedSourceText)) {
				source.click();
				break;
			}
		}
		Assert.assertEquals(acutalSourceText, expectedSourceText);

		// Destination selection
		String expectedDestText = "Mumbai, India";
		driver.findElement(By.xpath("//input[@id='gosuggest_inputDest']")).sendKeys("Mum");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='react-autosuggest-1']/li")));

		int totalDestListSize = driver.findElements(By.xpath("//ul[@id='react-autosuggest-1']/li")).size();
		String acutalDestText = driver
				.findElement(By.xpath("//ul[@id='react-autosuggest-1']/li[1]//div[@class='mainTxt clearfix']/span[1]"))
				.getText();
		for (int index = 1; index <= totalDestListSize; index++) {
			WebElement destination = driver.findElement(By.xpath(
					"//ul[@id='react-autosuggest-1']/li[" + index + "]//div[@class='mainTxt clearfix']/span[1]"));
			if (destination.getText().equals(expectedDestText)) {
				destination.click();
				break;
			}
		}
		Assert.assertEquals(acutalDestText, expectedDestText);
	}

	@Test(priority = 1)
	void fillDepartureDate() {
		// Date Text
		// driver.findElement(By.xpath("//input[@id='departureCalendar']"));
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='application']")));

		// Validate prev Arrow in current month
		//Assert.assertFalse(driver.findElement(By.xpath("//div[@class='DayPicker-NavBar']/span[contains(@class,'prev')]")).isDisplayed());

		// Validate Current month and Year September 2020
		Date dateObj = getCurrentDateObj();
		DateFormat month = new SimpleDateFormat("MMMM" + " " + "y"); // September 2020
		String actualCurrentMonthYear = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
		Assert.assertEquals(actualCurrentMonthYear, month.format(dateObj));

		//weekdays Validation
		calendarWeekdaysValidation();

		// Creating customize Id for selecting current date
		DateFormat date = new SimpleDateFormat("YMMdd");
		String selectDate = "fare_" + date.format(dateObj);

		// Fill Current date 
		DateFormat expectedPopulatedDateValue = new SimpleDateFormat("d MMM");
		driver.findElement(By.xpath("//div[@id=\'" + selectDate + "\']")).click();
		//Verifying selected date should be populated in field
		String populatedDateValue = driver.findElement(By.xpath("//input[@id='departureCalendar']")).getAttribute("value");
		Assert.assertTrue(populatedDateValue.contains(expectedPopulatedDateValue.format(dateObj)));
	}
	
	@Test(priority = 2)
	void fillReturnDate() {
		/*wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='application']")));*/
		driver.findElement(By.xpath("//input[@id='returnCalendar']")).click();
		
		while(!driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText().equals("December 2020")) {
			driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
		}
		
		// Validate Current month and Year September 2020
		String expectedMonthYear =  "December 2020";
		String actualCurrentMonthYear = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
		Assert.assertEquals(actualCurrentMonthYear, expectedMonthYear);
		
		//Weekdays Validation
		calendarWeekdaysValidation();
		
		//Prev Next Button Validation
		WebElement prevButton = driver.findElement(By.xpath("//div[@class='DayPicker-NavBar']/span[contains(@class,'prev')]"));
		Assert.assertTrue(prevButton.isDisplayed());
		
		WebElement nextButton = driver.findElement(By.xpath("//div[@class='DayPicker-NavBar']/span[contains(@class,'next')]"));
		Assert.assertTrue(nextButton.isDisplayed());
		
		
		driver.findElement(By.xpath("//div[@id='fare_20201209']/parent::div[@aria-disabled='false']")).click();
		
		//Verifying selected date should be populated in field
		String populatedReturnDateValue = driver.findElement(By.xpath("//input[@id='returnCalendar']")).getAttribute("value");
		Assert.assertTrue(populatedReturnDateValue.contains("9 Dec"));
			
	}

	@AfterTest
	void tearDown() {
		driver.close();
	}

}
