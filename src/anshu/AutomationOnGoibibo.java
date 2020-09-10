package anshu;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import anshu.base.PredefinedProperty;

public class AutomationOnGoibibo extends PredefinedProperty {
	WebDriver driver;
	WebDriverWait wait;
	Map<String, String> monthMap = new LinkedHashMap();
	Map<String, String> dayMap = new LinkedHashMap();
	String selectDate = "";
	String month = "";
	String day = "";
	String newMonth = "";
	Date d = new Date();

	@BeforeClass
	public void setUp() {
		driver = start("https://www.goibibo.com/");
		wait = new WebDriverWait(driver, 20);
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

	public String getMonth() {

		month = String.valueOf(d.getMonth() + 01);
		if (month.equals("1")) {
			monthMap.put(month, "Janaury");
		} else if (month.equals("2")) {
			monthMap.put(month, "Feburary");
		} else if (month.equals("3")) {
			monthMap.put(month, "March");
		} else if (month.equals("4")) {
			monthMap.put(month, "April");
		} else if (month.equals("5")) {
			monthMap.put(month, "May");
		} else if (month.equals("6")) {
			monthMap.put(month, "June");
		} else if (month.equals("7")) {
			monthMap.put(month, "July");
		} else if (month.equals("8")) {
			monthMap.put(month, "August");
		} else if (month.equals("9")) {
			monthMap.put(month, "September");
		} else if (month.equals("10")) {
			monthMap.put(month, "October");
		} else if (month.equals("11")) {
			monthMap.put(month, "November");
		} else if (month.equals("12")) {
			monthMap.put(month, "December");
		}

		String mon = monthMap.get(month);
		String[] monthCharArray = mon.split("");
		for (int index = 2; index >= 0; index--) {
			newMonth = monthCharArray[index] + newMonth;
		}

		return mon;

	}

	public String getDate() {
		String date = String.valueOf(d.getDate());
		return date;
	}

	public String getYear() {
		String year = String.valueOf(d.getYear() + 1900);
		return year;
	}

	public String getDay() {

		day = String.valueOf(d.getDay() + 01);

		if (day.equals("1")) {
			dayMap.put(day, "Sun");
		} else if (day.equals("2")) {
			dayMap.put(day, "Mon");
		} else if (day.equals("3")) {
			dayMap.put(day, "Tue");
		} else if (day.equals("4")) {
			dayMap.put(day, "Wed");
		} else if (day.equals("5")) {
			dayMap.put(day, "Thu");
		} else if (day.equals("6")) {
			dayMap.equals("Fri");
		} else if (day.equals("7")) {
			dayMap.put(day, "Sat");
		}

		return dayMap.get(day);
	}

	// AutoSuggestion On Source ------------------------

	@Test(priority = 1)
	public void validateSource() {
		String actualCityName = "Pune, India";
		driver.findElement(By.xpath("//input[@id='gosuggest_inputSrc']")).sendKeys("Pun");
		// Thread.sleep(2000);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'dF whiteBg')]/ul//li")));

		// list of name comes on autosuggestion
		int listSize = driver.findElements(By.xpath(
				"//div[@class='dF whiteBg col-md-3 col-sm-5 alignItemsCenter brdrTpBtmLft width23']/ul[@id='react-autosuggest-1']/li[@class='react-autosuggest__suggestion lh1-3']"))
				.size();
		System.out.println(listSize);
		for (int index = 1; index <= listSize; index++) {
			WebElement element = driver.findElement(
					By.xpath("//div[@class='dF whiteBg col-md-3 col-sm-5 alignItemsCenter brdrTpBtmLft width23']/ul/li["
							+ index + "]//div[@class='mainTxt clearfix']/span[position()!=2]"));
			String exptdCityName = element.getText();
			System.out.println(exptdCityName);
			if (actualCityName.contains(exptdCityName)) {
				element.click();
				break;
			}

		}

		// validation on source city is shown to user.
		String exptdSource = driver.findElement(By.id("gosuggest_inputSrc")).getAttribute("value");
		String ActualSource = "Pune (PNQ)";
		Assert.assertEquals(ActualSource, exptdSource);
		System.out.println("source place is shown to user : Test Pass");

	}
	
	
	
	
	// autoSuggestion on destination --------------------

	@Test(priority = 2)
	public void validateDestination() {

		String actualCityName = "Kolkata, India";
		driver.findElement(By.xpath("//input[@id='gosuggest_inputDest']")).sendKeys("kol");
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'dF whiteBg')]/ul//li")));

		// list of name get on auto suggestion
		int listSize = driver.findElements(By.xpath(
				"//div[@class='dF whiteBg col-md-3 col-sm-5 alignItemsCenter  padL20 width23']/ul[@id='react-autosuggest-1']/li[@class='react-autosuggest__suggestion lh1-3']"))
				.size();
		System.out.println(listSize);
		for (int index = 1; index <= listSize; index++) {
			WebElement element = driver.findElement(By.xpath(
					"//div[@class='dF whiteBg col-md-3 col-sm-5 alignItemsCenter  padL20 width23']/ul[@id='react-autosuggest-1']/li["
							+ index + "]//div[@class='mainTxt clearfix']/span[position()!=2]"));
			String exptdCityName = element.getText();
			System.out.println(exptdCityName);
			if (actualCityName.contains(exptdCityName)) {
				element.click();
				break;
			}

		}

		// validation on destination city is shown to user.
		String exptdDestination = driver.findElement(By.id("gosuggest_inputDest")).getAttribute("value");
		String ActualDestination = "Kolkata (CCU)";
		Assert.assertEquals(ActualDestination, exptdDestination);
		System.out.println("Destination place is shown to user : Test Pass");

	}
	
	
	
	// Departure Calender-----------------
 
	@Test(priority = 3)
	public void validateOnDeparture() {

		// 1. validate month and year name showm on calender top.
		driver.findElement(By.xpath("//input[@id='departureCalendar']")).click();
		String exptdMonth = driver.findElement(By.xpath(
				"//input[@id='departureCalendar']//parent::div//following-sibling::div[@class='DayPicker-Month']/div[@class='DayPicker-Caption']"))
				.getText();
		String actualMonth = getMonth() + " " + getYear();
		// String exptdMonth = tmonth;
		System.out.println(exptdMonth);
		Assert.assertEquals(actualMonth, exptdMonth);
		System.out.println(actualMonth + " is visible to user.");

		// 2. validation on no of days in sept
		int exptdNoOfDays = driver.findElements(By.xpath(
				"//div[@class='DayPicker DayPicker--en']/descendant::div[@class='DayPicker-Body']//div/div[@tabindex]"))
				.size();
		int actualNoOfDays = 30;
		Assert.assertEquals(actualNoOfDays, exptdNoOfDays);
		System.out.println(exptdNoOfDays + " no of days is shown to user");

		// 3.validation only next button is shown
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='DayPicker-NavBar']/span")).size(), 1);
		System.out.println(" Only Next button is shown to user .");

		// 4.validation on date disabled and enabled

		int totalDisabled = driver.findElements(By.xpath(
				"//div[@class='DayPicker DayPicker--en']/descendant::div[@class='DayPicker-Body']//div/div[@aria-disabled='true' and @aria-selected='false']"))
				.size();
		int totalNotDisabled = driver.findElements(By.xpath(
				"//div[@class='DayPicker DayPicker--en']/descendant::div[@class='DayPicker-Body']//div/div[@aria-disabled='false' and @aria-selected='false']"))
				.size();
		int todayEnabled = driver.findElements(By.xpath(
				"//div[@class='DayPicker DayPicker--en']/descendant::div[@class='DayPicker-Body']//div/div[@aria-disabled='false' and @aria-selected='true']"))
				.size();

		int totalSum = totalDisabled + totalNotDisabled + todayEnabled;
		Assert.assertEquals(exptdNoOfDays, totalSum);

		System.out.println(totalDisabled + " ---> Disabled date before 10 , " + totalNotDisabled
				+ "---->  Not disabled date after 10. ");

		// 5.validate selected day is today.
		if (Integer.parseInt(getDate()) < 10 && Integer.parseInt(month) < 10) {
			selectDate = getYear() + "0" + month + "0" + getDate();
		} else if (Integer.parseInt(month) < 10 && Integer.parseInt(getDate()) >= 10) {
			selectDate = getYear() + "0" + month + getDate();
		} else {
			selectDate = getYear() + month + getDate();
		}

		String dateId = "fare_" + "" + selectDate;
		String exptdDate = driver.findElement(By.id(dateId)).getText();
		System.out.println(exptdDate);
		String actualdate = getDate();
		// Assert.assertEquals(actualdate, exptdDate);
		System.out.println(actualdate + " is visible to user.");

		String status = driver
				.findElement(By.xpath("//div[@class='DayPicker-Day DayPicker-Day--today DayPicker-Day--selected']"))
				.getAttribute("aria-selected");
		String actualStatus = "true";
		Assert.assertEquals(actualStatus, status);
		System.out.println("Today" + actualdate + " is highlighted.");

		// 7.select date for departure
		String actualvalue = getDay() + " " + newMonth + " " + getDate() + " " + getYear();
		WebElement element = driver.findElement(By.xpath("//div[@aria-label='" + actualvalue + "']"));
		String preStatus = element.getAttribute("aria-selected");
		element.click();
		// Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='departureCalendar']")).click();
		String afterSelected = driver.findElement(By.xpath("//div[@aria-label='" + actualvalue + "']"))
				.getAttribute("aria-selected");
		Assert.assertEquals(afterSelected, preStatus);
		System.out.println(" Departure date is selected successfully ");

		// 8. same date is shown on UI
		String actualdepDate = driver.findElement(By.xpath("//input[@id='departureCalendar']")).getAttribute("value");
		String exptddepDate = getDay() + ", " + getDate() + " " + newMonth;
		Assert.assertEquals(actualdepDate, exptddepDate);
	}

	
	
	/// return calender----------------------------------
	@Test(priority = 4)
	public void validateReturnCalender() {
		String proceed = "";

		// 1. valudation on current Month
		driver.findElement(By.xpath("//input[@id='returnCalendar']")).click();
		String exptdGetMonth = driver.findElement(By.xpath(
				"//input[@id='returnCalendar']//parent::div//following-sibling::div[@class='DayPicker-Month']/div[@class='DayPicker-Caption']"))
				.getText();
		String actualGetMonth = getMonth() + " " + getYear();
		Assert.assertEquals(actualGetMonth, exptdGetMonth);
		System.out.println(actualGetMonth + " is visible to user.");

		do {
			// 2. click on next button
			driver.findElement(By.xpath(
					"//input[@id='returnCalendar']//parent::div//following-sibling::div[@class='DayPicker-Month']/div[@class='DayPicker-Caption']//parent::div[@class='DayPicker-Month']//preceding-sibling::div//span[@class='DayPicker-NavButton DayPicker-NavButton--next']"))
					.click();
			proceed = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
		} while (!proceed.equals("December 2020"));

		Assert.assertEquals(proceed, "December 2020");
		System.out.println("User On same month page.");

		// 3. validation on week day;
		int count = driver.findElements(By.xpath("//div[@class='DayPicker-Weekday']")).size();
		for (int index = 1; index <= count; index++) {
			WebElement days = driver.findElement(By.xpath("//div[@class='DayPicker-Weekday'][" + index + "]/abbr"));
			days.isDisplayed(); // Mon to Sun - days are visible
		}

		// 4. validation on both button is shown
		Assert.assertEquals(driver.findElements(By.xpath("//div/span[@role='button']")).size(), 2);
		System.out.println("Two button is shown .");

		// 5. select return date
		WebElement date = driver.findElement(By.xpath("//div[@id='fare_20201209']"));
		String prevStatus = driver.findElement(By.xpath("//div[@aria-label='Wed Dec 09 2020']"))
				.getAttribute("aria-selected");
		date.click();
		driver.findElement(By.xpath("//input[@id='returnCalendar']")).click();
		String AfterStatus = driver.findElement(By.xpath("//div[@aria-label='Wed Dec 09 2020']"))
				.getAttribute("aria-selected");

		Assert.assertNotEquals(prevStatus, AfterStatus);
		System.out.println("Date is selected ");

		// 7. retuen date is shown to UI

		String actualValue = driver.findElement(By.xpath("//input[@id='returnCalendar']")).getAttribute("value");
		String getValue = "Wed, 9 Dec";
		Assert.assertEquals(actualValue, getValue);
	}

}
