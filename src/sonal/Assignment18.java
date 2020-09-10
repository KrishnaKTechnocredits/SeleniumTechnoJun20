package sonal;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Assignment18 {

	WebDriver driver;

	public WebDriver Start(String url) {

		System.setProperty("webdriver.chrome.driver", "E:/NewWorkSpace/SeleniumBasics1/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		return driver;
	}

	@BeforeClass
	public void setUp() {

		driver = Start("https://www.goibibo.com/");
	}

	@AfterClass
	public void tearDown() {

		driver.close();
	}
	
	@Test
	public void checkHiddenList()
	{
		driver.findElement(By.xpath("//input[@id='gosuggest_inputSrc']")).sendKeys("Pun");
		
		//get total number of options returned
		int totalOptions = driver.findElements(By.xpath("//div[@class='dF whiteBg col-md-3 col-sm-5 alignItemsCenter brdrTpBtmLft width23']//ul//li")).size();
		System.out.println(totalOptions);
		//print all 'from' options
		for(int i=1;i<=totalOptions;i++)
		{
			String optionName = driver.findElement(By.xpath("//div[@class='dF whiteBg col-md-3 col-sm-5 alignItemsCenter brdrTpBtmLft width23']//ul//li["+i+"]/div/div[2]/div[1]/span[1]")).getText();
			System.out.println("Option returned when 'Pun' is entered in 'From' list are: "+optionName);
		}
		
		//Select Pune in 'From' list
		driver.findElement(By.xpath("//div[@class='dF whiteBg col-md-3 col-sm-5 alignItemsCenter brdrTpBtmLft width23']//ul//li[1]/div/div[2]/div[1]/span[1]")).click();
	
		//COde for selection of Destination
		//enter data in destination field
		driver.findElement(By.xpath("//input[@id='gosuggest_inputDest']")).sendKeys("Del");
		
		//get total number of options returned when searched 'Del'
		int totalDepOptions = driver.findElements(By.xpath("//div[@class='dF whiteBg col-md-3 col-sm-5 alignItemsCenter  padL20 width23']//ul//li")).size();
		System.out.println(totalDepOptions);
		
		//print all the returned options
		for(int i=1;i<=totalDepOptions;i++)
		{
			String optionName = driver.findElement(By.xpath("//div[@class='dF whiteBg col-md-3 col-sm-5 alignItemsCenter  padL20 width23']//ul//li["+i+"]/div/div[2]/div[1]/span[1]")).getText();
			System.out.println("Options returned when 'Del' is entered in 'Destination' field are: "+optionName);
		}
		
		driver.findElement(By.xpath("//div[@class='dF whiteBg col-md-3 col-sm-5 alignItemsCenter  padL20 width23']//ul//li[1]/div/div[2]/div[1]/span[1]")).click();
		
		////div[@class='DayPicker-Caption']
		
		//display the calendar
		
		driver.findElement(By.xpath("//input[@id='departureCalendar']")).click();
		//Verify month year text is correct
		//Get current system date
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMMM yyyy zzzz");
		String strDate = formatter.format(date);
		System.out.println(strDate);
		
		String substrDate = strDate.substring(3,12);
		
		System.out.println(substrDate);//Get current month
		
		//Get month displayed on Calendar in website
		String monthFromCalendar = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
		//System.out.println(monthFromCalendar);
		
		String submonthFromCalendar = monthFromCalendar.substring(0,9);
		System.out.println(submonthFromCalendar);
		
		//compare the months
		if(submonthFromCalendar.equals(substrDate))
		{
			System.out.println("Current month "+submonthFromCalendar+" is correctly displayed in calendar");
		}else
			System.out.println("Incorrect month is displayed in calender");
		//check all seven days are displayed
		int totalDays = driver.findElements(By.xpath("//div[@class='DayPicker-Weekdays']//div[@class='DayPicker-Weekday']")).size();
		System.out.print("Total Number of days displayed are: "+totalDays+" and the days are: ");
		
		for(int i=1;i<=totalDays;i++)
		{
			String weekDays = driver.findElement(By.xpath("//div[@class='DayPicker-Weekdays']//div[@class='DayPicker-Weekday']["+i+"]/abbr")).getText();
			System.out.print(" "+weekDays);
		}
		
		System.out.println();
		
		//Verify todays date is selected
		String todayDate = strDate.substring(0,2);
		System.out.println(todayDate);
		
		String selectedDate = driver.findElement(By.xpath("//div[contains(@class,'--selected')]/div")).getText();
		if(selectedDate.equals(todayDate))
		{
			System.out.println("Todays date "+todayDate+"  is by default selected");
		}else
			System.out.println("Todays date is not selected");
			
		//Verify previous month navigation is disabled
		
		/*if((driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--prev']")).isDisplayed()))
				{
					System.out.println("Functionality is not working as expected");
									
				}else
					System.out.println("Cannot navigate to previous month. Functonality is working as expected");*/
		
		//select departure date 14September
		String selDate = driver.findElement(By.xpath("//div[@id='fare_20200914']")).getText();
		driver.findElement(By.xpath("//div[@id='fare_20200914']")).click();
		
		
		//verify selected date is displayed in departure field
		String depDate = driver.findElement(By.xpath("//input[@id='departureCalendar']")).getAttribute("value");
		String subdepdate = depDate.substring(5,7);
		
		if(subdepdate.equals(selDate))
		{
			System.out.println("Correct date is displayed in departure field");
		}else
			System.out.println("Its a wrong date");
		
		//Select return calendar
		driver.findElement(By.xpath("//input[@id='returnCalendar']")).click();
		
		for(int i=1; i<=3;i++)
		{
		driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
		}
		
		//Check December month is displayed
		String checkMonth = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
		String subcheckMonth = checkMonth.substring(0,8);
		if(subcheckMonth.equals("December"))
		{
			System.out.println("December month is selected");
		}else
			System.out.println("its a wrong month");
		
		//check previous month and next month navigation arrows are present
		if(driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--prev']")).isDisplayed())
		{
			System.out.println("Previous navigation arrow is present");
		}else
			System.out.println("Previous navigation arrow is not present"); 
		
		if(driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).isDisplayed())
		{
			System.out.println("Next navigation arrow is present");
		}else
			System.out.println("Next navigation arrow is not present");
			
		//Select date
		String newDate = driver.findElement(By.xpath("//div[@id='fare_20201220']")).getText();
		driver.findElement(By.xpath("//div[@id='fare_20201220']")).click();
		String retDate = driver.findElement(By.xpath("//input[@id='returnCalendar']")).getAttribute("value");
		String subretDate = retDate.substring(5,7);
		
		if(subretDate.equals(newDate))
		{
			System.out.println("Correct date is displayed in return date field");
		}else
			System.out.println("Its a wrong date");
		
			
	
		
		
	}

}

