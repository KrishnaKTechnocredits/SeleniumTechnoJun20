package rachana.Assignment18;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import rachana.base.PredefinedActions;

public class TestGoibibo extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	void setUp() {
		driver = start("https://www.goibibo.com/");
		wait = new WebDriverWait(driver, 2000);
	}

	@AfterClass
	void tearDown() {
		driver.close();
	}

	@Test(priority = 1)
	void verifySource() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gosuggest_inputSrc")));
		WebElement inputFrom = driver.findElement(By.id("gosuggest_inputSrc"));
		inputFrom.sendKeys("pun");
		Thread.sleep(2000);
		int listsize = driver.findElements(By.xpath("//div[contains(@class, 'brdrTpBtmLft')]/ul/li//child::div[@class='mainTxt clearfix']")).size();
		System.out.println("Total Number of autosuggestions :" + listsize);
		System.out.println("Auto populated list is:");
		for (int index = 1; index < listsize; index++) {
			String cityName = driver.findElement(By.xpath("//div[contains(@class, 'brdrTpBtmLft')]/ul/li[" + index + "]//child::div[@class='mainTxt clearfix']/span[1]")).getText();
			String cityName_suffix = driver.findElement(By.xpath("//div[contains(@class, 'brdrTpBtmLft')]/ul/li[" + index + "]//child::div[@class='mainTxt clearfix']/span[2]")).getText();
			System.out.println(cityName + " " + cityName_suffix);
		}
		String expectedCityName = driver.findElement(By.xpath("//div[contains(@class, 'brdrTpBtmLft')]/ul/li[1]//child::div[@class='mainTxt clearfix']/span[1]")).getText();
		driver.findElement(By.xpath("//div[contains(@class, 'brdrTpBtmLft')]/ul/li[1]//child::div[@class='mainTxt clearfix']/span[1]")).click();
		Thread.sleep(2000);
		String actualCityName = inputFrom.getAttribute("value");
		if (actualCityName.contains(expectedCityName.subSequence(0, 3))) {
			System.out.println("Pune has been selected successfully: Pass");
		} else
			System.out.println("Pune has not been selected successfully: Fail");
	}

	@Test(priority = 2)
	void verifyDestination() throws InterruptedException {
		// set value in destination
		WebElement inputdestination = driver.findElement(By.id("gosuggest_inputDest"));
		inputdestination.sendKeys("Del");
		Thread.sleep(2000);
		int dlistsize = driver.findElements(By.xpath("//div[contains(@class, 'padL20')]/ul/li//child::div[@class='mainTxt clearfix']/span[1]")).size();
		System.out.println("\nTotal Number of autosuggestions :" + driver.findElements(By.xpath("//div[contains(@class, 'padL20')]/ul/li//child::div[@class='mainTxt clearfix']/span[1]")).size());
		System.out.println("--AutoPopulated list for destination is--");
		for (int index = 1; index < dlistsize; index++) {
			String dcityName = driver.findElement(By.xpath("//div[contains(@class, 'padL20')]/ul/li[" + index + "]//child::div[@class='mainTxt clearfix']/span[1]")).getText();
			String dcityname_Suffix = driver.findElement(By.xpath("//div[contains(@class, 'padL20')]/ul/li[" + index + "]//child::div[@class='mainTxt clearfix']/span[2]")).getText();
			System.out.println(dcityName + " " + dcityname_Suffix);
		}
		String expecteddestination = driver.findElement(By.xpath("//div[contains(@class, 'padL20')]/ul/li[1]//child::div[@class='mainTxt clearfix']/span[1]")).getText();
		driver.findElement(By.xpath("//div[contains(@class, 'padL20')]/ul/li[1]//child::div[@class='mainTxt clearfix']/span[1]")).click();
		Thread.sleep(2000);
		String actualdestination = inputdestination.getAttribute("value");
		if (actualdestination.contains(expecteddestination.substring(0, 4))) {
			System.out.println("Delhi has been selected successfully: Pass");
		} else {
			System.out.println("Delhi has not been selected successfully: Fail");
		}
	}

	@Test(priority = 3)
	void verifyDepartureDatePicker() throws InterruptedException {

		// 4.Select today or tomorrows date in "Departure" date picker and verify
		WebElement departCalender = driver.findElement(By.id("departureCalendar"));
		departCalender.click();

		// Mon to Sun - days are visible,
		int totalweekdaysExpected = driver.findElements(By.xpath("//div[@class='DayPicker-Weekday']")).size();
		int totalweekdaysActual = 7;
		Assert.assertEquals(totalweekdaysActual, totalweekdaysExpected);

		// Verify month-year text is correct,
		LocalDate dt = LocalDate.now();
		Month month = dt.getMonth();
		int year = dt.getYear();
		String expected_year_month = (String.valueOf(month) + " " + String.valueOf(year)).toLowerCase();
		String actual_year_month = (driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText()).toLowerCase();
		Assert.assertEquals(actual_year_month, expected_year_month);

		// Previous month navigation arrow should not be present.
		int prevnext = driver.findElements(By.xpath("//div[@class='DayPicker DayPicker--en']//div[@class='DayPicker-NavBar']/span")).size();
		Assert.assertTrue(prevnext < 2);

		// Select date should be enabled
		String currentdateSelected = driver.findElement(By.xpath("//div[@class='DayPicker-Day DayPicker-Day--today DayPicker-Day--selected']/div")).getText();
		String selectdate = String.valueOf(dt.getDayOfMonth() + 1);
		WebElement datetobeselect = driver.findElement(By.xpath("//div[@class='DayPicker-Day']/div[text()=" + selectdate + "]//parent::div[@class='DayPicker-Day' ]"));
		// Assert.assertTrue(datetobeselect.getAttribute("aria-disabled").equals("false"));
		if (datetobeselect.getAttribute("aria-disabled").equals("false")) {
			System.out.println("\nDate to be selected is enabled : Pass");
			driver.findElement(By.xpath("//div[@class='DayPicker-Day']/div[text()=" + selectdate + "]")).click();
			Thread.sleep(1000);
		} else {
			System.out.println("Date to be selected is not enabled: Fail");
		}

		// 5.Verify selected date should be visible in Departure field.
		String actualdateInDepartfield = driver.findElement(By.id("departureCalendar")).getAttribute("value").toLowerCase();// Sat, 12 Sep
		dt = dt.plusDays(1);
		int date = dt.getDayOfMonth();
		DayOfWeek wkday = dt.getDayOfWeek();
		String expected_selecteddate = wkday.toString().toLowerCase().substring(0, 3) + ", " + date + " " + month.toString().toLowerCase().substring(0, 3);
		// Assert.assertTrue(actualdateInDepartfield.equals(expected_selecteddate));
		if (actualdateInDepartfield.equals(expected_selecteddate)) {
			System.out.println("Selected date is visible in departure field :Pass");
		} else {
			System.out.println("Selected date is not visible in departure field :Fail");
		}
	}

	@Test(priority = 4)
	void verifyReturnDatePicker() throws InterruptedException {
		// Select any day of Dec 2020 in "Return" date picker and
		WebElement returnCalender = driver.findElement(By.id("returnCalendar"));
		returnCalender.click();

		// Mon to Sun - days are visible,
		int totalweekdaysExpected = driver.findElements(By.xpath("//div[@class='DayPicker-Weekday']")).size();
		int totalweekdaysActual = 7;
		Assert.assertEquals(totalweekdaysActual, totalweekdaysExpected);

		// Verify month-year text is correct,
		LocalDate dt = LocalDate.now();
		Month month = dt.getMonth();
		int year = dt.getYear();
		String expected_year_month = (String.valueOf(month) + " " + String.valueOf(year)).toLowerCase();
		String actual_year_month = (driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText()).toLowerCase();
		Assert.assertEquals(actual_year_month, expected_year_month);

		// go to dec 2020 in datepicker
		WebElement next = driver.findElement(By.xpath("//div[@class='DayPicker-NavBar']/span[@class='DayPicker-NavButton DayPicker-NavButton--next']"));
		String dcaption = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
		while (!dcaption.equals("December 2020")) {
			next.click();
			dcaption = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
		}

		// Previous and next month navigation arrow should be present.
		int prevnext = driver.findElements(By.xpath("//div[@class='DayPicker DayPicker--en']//div[@class='DayPicker-NavBar']/span")).size();
		Assert.assertTrue(prevnext == 2);

		// Select date should be enabled
		String selectdate = String.valueOf(dt.getDayOfMonth());
		WebElement datetobeselect = driver.findElement(By.xpath("//div[@class='DayPicker-Day']/div[text()=" + selectdate + "]//parent::div[@class='DayPicker-Day' ]"));
		Assert.assertTrue(datetobeselect.getAttribute("aria-disabled").equals("false"));
		if (datetobeselect.getAttribute("aria-disabled").equals("false")) {
			System.out.println("\nDate to be selected is enabled : Pass");
			driver.findElement(By.xpath("//div[@class='DayPicker-Day']/div[text()=" + selectdate + "]")).click();
			Thread.sleep(1000);
		} else {
			System.out.println("Date to be selected is not enabled: Fail");
		}

		// 5.Verify selected date should be visible in Departure field.
		String actualdateInReturnfield = driver.findElement(By.id("returnCalendar")).getAttribute("value").toLowerCase();// Sat, 12 Sep
		dt = dt.plusMonths(3);
		month = dt.getMonth();
		int date = dt.getDayOfMonth();
		DayOfWeek wkday = dt.getDayOfWeek();
		String expected_selecteddate = wkday.toString().toLowerCase().substring(0, 3) + ", " + date + " " + month.toString().toLowerCase().substring(0, 3);
		if (actualdateInReturnfield.equals(expected_selecteddate)) {
			System.out.println("Selected date is visible in departure field :Pass");
		} else {
			System.out.println("Selected date is not visible in departure field :Fail");
		}
	}
}
