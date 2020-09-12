/*
 Steps :
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
7) Verify selected date should be visible in Return field. 
*/

package aditi;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import aditi.base.PredefinedActions;

public class GoIbiboDataPickerAndAutoComplete extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	String currentDate, year, month, dateTimeFormat;
	LocalDate currentDay;
	DateTimeFormatter dateFormat, yearFormat, monthFormat, dayFormat;

	@BeforeClass
	void setUp() {
		driver = start("https://www.goibibo.com/");
		wait = new WebDriverWait(driver, 15);
		// String currentDate= new Date().toString();
	}

	@AfterClass
	void tearDown() {
		driver.close();
	}

	void displayAutoSuggestionsAndSelect(String selectedSrcOrDest) {
		WebElement srcOrDest = null;
		int expectedValueIndex = 0;
		int autoSuggestionListSize = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='react-autosuggest-1']/li")))
				.size();
		for (int index = 1; index <= autoSuggestionListSize; index++) {
			srcOrDest = driver.findElement(
					By.xpath("//ul[@id='react-autosuggest-1']/li[" + index + "]/div/div[2]/div[1]/span[1]"));
			System.out.println(srcOrDest.getText());
			if (srcOrDest.getText().equals(selectedSrcOrDest))
				expectedValueIndex = index;
		}
		/*
		 * Validating Selected source/dest value matches expected value or not and then
		 * clicking on expected source or destination value for selection from list
		 */
		Assert.assertEquals(selectedSrcOrDest,
				driver.findElement(By.xpath(
						"//ul[@id='react-autosuggest-1']/li[" + expectedValueIndex + "]/div/div[2]/div[1]/span[1]"))
						.getText());
		driver.findElement(
				By.xpath("//ul[@id='react-autosuggest-1']/li[" + expectedValueIndex + "]/div/div[2]/div[1]/span[1]"))
				.click();
	}

	@Test(priority = 1)
	public void validateSource() {
		String expectedSourceValue = "Pune, India";
		driver.findElement(By.xpath("//input[@id='gosuggest_inputSrc']")).sendKeys("Pun");
		// wait = new WebDriverWait(driver, 10);
		System.out.println("List of auto suggestions for \"Pun\" as Source");
		displayAutoSuggestionsAndSelect(expectedSourceValue);
	}

	@Test(priority = 2)
	public void validateDestination() {
		String expectedDestinationValue = "San Francisco, United States";
		driver.findElement(By.id("gosuggest_inputDest")).sendKeys("San");
		System.out.println("\n\nList of auto suggestions for \"San\" as Source");
		displayAutoSuggestionsAndSelect(expectedDestinationValue);
	}

	private void validateWeekDays() {
		int daysOfWeek = driver.findElements(By.xpath("//div[@class='DayPicker-WeekdaysRow']/div")).size();
		String[] expectedDaysOfWeek = DateFormatSymbols.getInstance().getWeekdays();
		for (int index = 1; index <= daysOfWeek; index++) {
			String actualDay = driver
					.findElement(By.xpath("//div[@class='DayPicker-WeekdaysRow']/div[" + index + "]/abbr")).getText();
			Assert.assertEquals(expectedDaysOfWeek[index].substring(0, 2), actualDay);
		}

	}

	@BeforeTest
	private void dateFormatting() {
		currentDay = LocalDate.now();
		dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		dateTimeFormat = currentDay.format(dateFormat);

		yearFormat = DateTimeFormatter.ofPattern("yyyy");
		year = currentDay.format(yearFormat);

		monthFormat = DateTimeFormatter.ofPattern("MM");
		month = currentDay.format(monthFormat);

		dayFormat = DateTimeFormatter.ofPattern("dd");
		currentDate = currentDay.format(dateFormat);
	}

	@Test(priority = 3)
	public void selectAndVerifyDeptDate() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='departureCalendar']"))).click();

		// For departure - for current month prev month arrow should not be there
		String actualMonthYear = (driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText())
				.toUpperCase();
		String actualMonth = actualMonthYear.substring(0, actualMonthYear.length() - 5);// (removing ' 2020' to extract
																						// month)
		String currentMonth = currentDay.getMonth().toString();
		if (actualMonth.equalsIgnoreCase(currentMonth.toLowerCase())) {
			Assert.assertEquals(driver
					.findElements(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--prev']")).size(),
					0);
		}
		String expectedMonthYear = currentDay.getMonth().toString() + " " + year;

		// Validate Calendar Month and year header
		Assert.assertEquals(expectedMonthYear, actualMonthYear);

		// Validate Weekdays
		validateWeekDays();

		// Set current day as departure date and check it's selected or not.
		WebElement selectedDate = driver.findElement(By.xpath("//div[@id='fare_" + dateTimeFormat + "']"));
		selectedDate.click();
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='departureCalendar']"))).click();
		// Assert.assertFalse(selectedDate.isSelected());
		dayFormat = DateTimeFormatter.ofPattern("EEE, d MMM"); // (Changing date format to i.e - Fri, 11 Dec)
		String expectedSelectedDate = currentDay.format(dayFormat);

		// Validate selected date matches actual displayed date
		Assert.assertEquals(expectedSelectedDate,
				driver.findElement(By.xpath("//input[@id='departureCalendar']")).getAttribute("value"));
	}

	@Test(priority = 4)
	public void selectAndVerifyReturnDate() {
		driver.findElement(By.xpath("//input[@id='returnCalendar']")).click();

		// For return - for current month previous month arrow should not be there
		String actualMonthYear = (driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText())
				.toUpperCase();
		String actualMonth = actualMonthYear.substring(0, actualMonthYear.length() - 5);// (removing ' 2020' to extract
																						// month)
		String currentMonth = currentDay.getMonth().toString();
		if (actualMonth.equalsIgnoreCase(currentMonth.toLowerCase())) {
			Assert.assertEquals(driver
					.findElements(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--prev']")).size(),
					0);
		}
		// Will book return date from 3rd month after current month (i.e. September->
		// December) - If need user can hard-code value for 'expectedSelectedMonthYear'
		String expectedSelectedMonthYear = currentDay.plusMonths(3).getMonth().toString() + " " + year;
		while (!driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText()
				.equalsIgnoreCase(expectedSelectedMonthYear)) {
			driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
		}
		// Validate Calendar Month and year header
		Assert.assertEquals(expectedSelectedMonthYear,
				driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText().toUpperCase());

		// Validate Weekdays
		validateWeekDays();

		// Validation for Prev and next button
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='DayPicker-NavBar']/span[contains(@class,'prev')]"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='DayPicker-NavBar']/span[contains(@class,'next')]"))
				.isDisplayed());
		String expectedSelectedDate = currentDay.plusMonths(3).format(dateFormat);

		// Selecting date as current day + 3 month
		WebElement selectedReturnDate = driver.findElement(By.xpath("//div[@id='fare_" + expectedSelectedDate + "']"));
		selectedReturnDate.click();

		dayFormat = DateTimeFormatter.ofPattern("EEE, d MMM");
		expectedSelectedDate = currentDay.plusMonths(3).format(dayFormat);
		// Validate selected date matches actual displayed date
		Assert.assertEquals(expectedSelectedDate,
				driver.findElement(By.xpath("//input[@id='returnCalendar']")).getAttribute("value"));
	}
}
