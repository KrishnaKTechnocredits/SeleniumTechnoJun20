/*1) Go to https://www.goibibo.com/
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

package barkha;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import barkha_base.PredefinedFunctions;

public class GOIBIBO_AutoSuggestion_Calender extends PredefinedFunctions {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	void setUp() {
		driver = start("https://www.goibibo.com/");
	}

	@AfterClass
	void tearDown() {
		driver.close();
	}

	@Test(priority = 1)
	void ticketBooking() {
		// source
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Pun");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//li[@id='react-autosuggest-1-suggestion--0']")));

		// Enter 'Pun' in From field, Print all the option from given auto-suggestions
		// and Select Pune.
		int size = driver
				.findElements(By.xpath(
						"//div[@class='dF whiteBg col-md-3 col-sm-5 alignItemsCenter brdrTpBtmLft width23']/ul/li"))
				.size();
		System.out.println("Available Options after Entering Pun are: ");
		for (int index = 1; index <= size; index++) {
			List<WebElement> avaialbleOptions = driver.findElements(
					By.xpath("//div[@class='dF whiteBg col-md-3 col-sm-5 alignItemsCenter brdrTpBtmLft width23']/ul/li["
							+ index + "]/div/div[@class='dib marginL10 pad0 textOverflow width90']/div[1]/span[1]"));
			for (WebElement options : avaialbleOptions) {
				System.out.println(options.getText());
			}
		}
		// Select Pune from list
		for (int index = 1; index <= size; index++) {
			List<WebElement> avaialbleOptions = driver.findElements(
					By.xpath("//div[@class='dF whiteBg col-md-3 col-sm-5 alignItemsCenter brdrTpBtmLft width23']/ul/li["
							+ index + "]/div/div[@class='dib marginL10 pad0 textOverflow width90']/div[1]/span[1]"));
			for (WebElement options : avaialbleOptions) {
				if (options.getText().equals("Pune, India")) {
					options.click();
				}
			}
		}
		// Destination
		driver.findElement(By.xpath("//input[@id='gosuggest_inputDest']")).sendKeys("Del");
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//ul[@class='autoSuggestBoxList flt autoSgstFlt autoSuggestBoxListHm']")));

		// Enter 'Del' in Destination field, Print all the option from given
		// auto-suggestions and select Delhi.
		int size1 = driver
				.findElements(
						By.xpath("//div[@class='dF whiteBg col-md-3 col-sm-5 alignItemsCenter  padL20 width23']/ul/li"))
				.size();

		System.out.println("\nAvailable Options after Entering Del are: ");
		for (int index = 1; index <= size1; index++) {
			List<WebElement> avaialbleOptions1 = driver.findElements(
					By.xpath("//div[@class='dF whiteBg col-md-3 col-sm-5 alignItemsCenter  padL20 width23']/ul/li["
							+ index + "]/div/div[2]/div[1]/span[1]"));
			for (WebElement options : avaialbleOptions1) {
				System.out.println(options.getText());
			}
		}
		// Select Delhi from List
		for (int index = 1; index <= size1; index++) {
			List<WebElement> avaialbleOptions1 = driver.findElements(
					By.xpath("//div[@class='dF whiteBg col-md-3 col-sm-5 alignItemsCenter  padL20 width23']/ul/li["
							+ index + "]/div/div[2]/div[1]/span[1]"));
			for (WebElement options : avaialbleOptions1) {
				if (options.getText().equals("Delhi, India")) {
					options.click();
				}
			}
		}
	}

	@Test(priority = 2)
	void validation_DepartureCalender() {
		driver.findElement(By.id("departureCalendar")).click();
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='DayPicker-Body']")));

		// Select today or tomorrows date in "Departure" date picker
		// Verify month-year text is correct
		String actualText_Dept = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
		Assert.assertEquals(actualText_Dept, "September 2020");

		// Mon to Sun - days are visible,
		int count = driver.findElements(By.xpath("//div[@class='DayPicker-Weekday']")).size();
		for (int index = 1; index <= count; index++) {
			WebElement days = driver.findElement(By.xpath("//div[@class='DayPicker-Weekday'][" + index + "]/abbr"));
			Assert.assertTrue(days.isDisplayed());
		}
		 
		// Select date should be enabled
		WebElement selectedDate_Dept = driver.findElement(By.xpath("//div[@id='fare_20200911']"));
		Assert.assertTrue(selectedDate_Dept.isEnabled());
		selectedDate_Dept.click();

		// Previous month navigation arrow should not be present WebElement
		// prevArrow_Dept=driver.findElement(By.xpath("//div/span[@role='button'][1]"));
		// Assert.assertFalse(prevArrow_Dept.isEnabled());

		// Verify selected date should be visible in Departure field
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Fri, 11 Sep']")));
		String ActualDate_Dept = driver.findElement(By.xpath("//input[@value='Fri, 11 Sep']")).getAttribute("value");   //IMP
		Assert.assertEquals(ActualDate_Dept, "Fri, 11 Sep");

		// Select any day of Dec 2020 in "Return" date picker
		driver.findElement(By.xpath("//input[@id='returnCalendar']")).click();
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='DayPicker-Body']")));

		// Verify month-year text is correct
		while (!driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText().equals("December 2020")) {  //IMP
			driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
		}
		String actualText_Ret = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
		Assert.assertEquals(actualText_Ret, "December 2020");

		// Mon to Sun - days are visible,
		int count1 = driver.findElements(By.xpath("//div[@class='DayPicker-Weekday']")).size();
		for (int index = 1; index <= count1; index++) {
			WebElement days = driver.findElement(By.xpath("//div[@class='DayPicker-Weekday'][" + index + "]/abbr"));
			Assert.assertTrue(days.isDisplayed());
		}
		// Previous month navigation arrow and next month navigation arrow should be
		// present
		WebElement prevArrow_Ret = driver
				.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--prev']"));
		Assert.assertTrue(prevArrow_Ret.isDisplayed());

		WebElement nextvArrow_Ret = driver
				.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']"));
		Assert.assertTrue(nextvArrow_Ret.isDisplayed());

		// Select date should be enabled
		WebElement selectedDate_Ret = driver.findElement(By.xpath("//div[@id='fare_20201211']"));
		Assert.assertTrue(selectedDate_Ret.isEnabled());
		selectedDate_Ret.click();

		// Verify selected date should be visible in Return field
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Fri, 11 Dec']")));
		String ActualDate_Ret = driver.findElement(By.xpath("//input[@value='Fri, 11 Dec']")).getAttribute("value");   //IMP
		Assert.assertEquals(ActualDate_Ret, "Fri, 11 Dec");

		System.out.println("\nCalender Test Case PASS");
	}
}