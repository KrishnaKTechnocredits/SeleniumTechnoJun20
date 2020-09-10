package aavruti;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import aavruti.utility.PropertyFileOperation;

public class GoibiboDatePicker {	

	LocalDate todayDate;
	String formatDateTime, yearValue, monthValue, currentDate;
	DateTimeFormatter format, yearFormat, monthFormat, dateFormat;

	WebDriver driver;
	WebDriverWait wait;
	PropertyFileOperation propRead;

	@BeforeTest
	void setup() {
		driver = GoibiboInit.getDriver();
		wait = GoibiboInit.getDriverWait();
		propRead = GoibiboInit.getProperty();
		
		todayDate = LocalDate.now();
		format = DateTimeFormatter.ofPattern("yyyyMMdd");  
		formatDateTime = todayDate.format(format);
		
		yearFormat = DateTimeFormatter.ofPattern("yyyy");
		yearValue = todayDate.format(yearFormat);
		
		monthFormat = DateTimeFormatter.ofPattern("MM");
		monthValue = todayDate.format(monthFormat);
		
		dateFormat = DateTimeFormatter.ofPattern("dd");
		currentDate = todayDate.format(dateFormat);
	}

	void validateWeekdays(int noOfDays, int currentDate) {		
		// Verify Mon to Sun - days are visible
		for(int index=1;index<=7;index++) {
			driver.findElement(By.xpath(propRead.propRead("weekday"+index))).isDisplayed();
		}

		// Verify days in month
		for(int index=1;index<=noOfDays;index++) {
			if(index<currentDate) {
				if(index<10)
					Assert.assertEquals(driver.findElement(By.xpath("//div[@id='fare_"+(yearValue+monthValue+0+index)+"']//parent::div[@class='DayPicker-Day DayPicker-Day--disabled']")).getAttribute("aria-disabled"),"true");
				else
					Assert.assertEquals(driver.findElement(By.xpath("//div[@id='fare_"+(yearValue+monthValue+index)+"']//parent::div[@class='DayPicker-Day DayPicker-Day--disabled']")).getAttribute("aria-disabled"),"true");
			}
			else if(index==currentDate){
				//Verify Present Date is selected and enabled
				Assert.assertTrue(driver.findElement(By.xpath("//div[@id='fare_"+(todayDate.format(format))+"']//parent::div[@class='DayPicker-Day DayPicker-Day--today DayPicker-Day--selected']")).isEnabled());
			}
			else {
				if(index<10)
					Assert.assertEquals(driver.findElement(By.xpath("//div[@id='fare_"+(yearValue+monthValue+0+index)+"']//parent::div[@class='DayPicker-Day']")).getAttribute("aria-disabled"),"false");
				else
					Assert.assertEquals(driver.findElement(By.xpath("//div[@id='fare_"+(yearValue+monthValue+index)+"']//parent::div[@class='DayPicker-Day']")).getAttribute("aria-disabled"),"false");
			}
		}
	}

	@Test(priority=1)
	void selectDeptDate() throws ParseException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(propRead.propRead("deptCalendar")))).click();

		// 1) Verify Previous month navigation arrow is not present.
		Assert.assertEquals(driver.findElements(By.xpath(propRead.propRead("prevArrow"))).size(),0);

		// 2) Verify Month and Year in date picker
		DateTimeFormatter month = DateTimeFormatter.ofPattern("MMMM yyyy");
		Assert.assertEquals(driver.findElement(By.xpath(propRead.propRead("datePickerCaption"))).getText(), todayDate.format(month));

		YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(yearValue), Integer.parseInt(monthValue));
		validateWeekdays(yearMonthObject.lengthOfMonth(),Integer.parseInt(currentDate));

		// 3) Select next Date and Verify selected Date	
		driver.findElement(By.xpath("//div[@id='fare_"+(todayDate.plusDays(1).format(format))+"']")).click();	    

		format = DateTimeFormatter.ofPattern("EEE, d MMM");
		formatDateTime = todayDate.plusDays(1).format(format);

		Assert.assertEquals(driver.findElement(By.xpath(propRead.propRead("deptCalendar"))).getAttribute("value"), formatDateTime);
	}

	@Test(priority=2)
	void selectReturnDate() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(propRead.propRead("returnCalendar")))).click();
		String currentMonth = driver.findElement(By.xpath(propRead.propRead("datePickerCaption"))).getText();
		int monthCount = 0;

		// 1) Traverse till December 2020
		while(!currentMonth.contains("December")) {
			driver.findElement(By.xpath(propRead.propRead("nextArrow"))).click();
			currentMonth = driver.findElement(By.xpath(propRead.propRead("datePickerCaption"))).getText();
			monthCount++;
		}

		// 2) Verify Month and Year in date picker
		Assert.assertEquals(driver.findElement(By.xpath(propRead.propRead("datePickerCaption"))).getText(), "December " + todayDate.format(yearFormat));

		// 3) Verify Previous month navigation arrow is not present.
		driver.findElement(By.xpath(propRead.propRead("prevArrow"))).isDisplayed();
		driver.findElement(By.xpath(propRead.propRead("nextArrow"))).isDisplayed();

		monthValue = "12";
		YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(yearValue), 12);
		validateWeekdays(yearMonthObject.lengthOfMonth(),0);

		// 4) Select Date and Verify selected Date
		driver.findElement(By.xpath("//div[@id='fare_"+(yearValue+12+currentDate)+"']")).click();	    

		format = DateTimeFormatter.ofPattern("EEE, d MMM");
		formatDateTime = todayDate.plusMonths(monthCount).format(format);
		Assert.assertEquals(driver.findElement(By.xpath(propRead.propRead("returnCalendar"))).getAttribute("value"), formatDateTime);
	}	
}