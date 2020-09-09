package sonal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Assignment17 {
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

		driver = Start("https://editor.datatables.net/examples/extensions/excel");
	}

	@AfterClass
	public void tearDown() {

		driver.close();
	}
	
	@Test
	public void uniqueOfficeLocation()
	{
		HashMap <String, Integer> officeEmployees = new HashMap<String, Integer>();
		
		HashMap <String, Integer> positionEmployees = new HashMap<String, Integer>();
		
		getLocation(officeEmployees,positionEmployees);
		
		int pageCount = driver.findElements(By.xpath("//div[@id='example_paginate']//span//a")).size();
		
		//traverse through different pages
		for(int j=2;j<=pageCount;j++)
		{
			driver.findElement(By.xpath("//div[@id='example_paginate']//span//a[@data-dt-idx='"+j+"']")).click();
			getLocation(officeEmployees,positionEmployees);//get the office and position wise employee data
		}
		
		//display the data
		display(officeEmployees);
		display1(positionEmployees);
		
		
	}
	
	public void getLocation(HashMap<String, Integer>officeEmployees,HashMap<String, Integer>positionEmployees)
	{
		int rowSize = driver.findElements(By.xpath("//table[@id='example']//tbody/tr")).size();
			
		for (int i=1;i<=rowSize;i++)
		{
			String office = driver.findElement(By.xpath("//table[@id='example']//tbody/tr["+i+"]//td[5]")).getText();
			if(officeEmployees.containsKey(office))
			{
				int cnt = officeEmployees.get(office);
				officeEmployees.put(office, cnt+1);
			}else
			{
				officeEmployees.put(office, 1);
			}
			
			String position = driver.findElement(By.xpath("//table[@id='example']//tbody/tr["+i+"]//td[4]")).getText();
			if(positionEmployees.containsKey(position))
			{
				int cnt = positionEmployees.get(position);
				positionEmployees.put(position, cnt+1);
			}else
			{
				positionEmployees.put(position, 1);
			}
			
			
		}
	}
	

	
	public void display(HashMap<String, Integer>officeEmployees)
	{
		int maxNum = 0;
		Set <String> keySet = officeEmployees.keySet();
		for(String str:keySet)
		{
			System.out.println("Office: "+str+" "+"Number of employees associated are: "+officeEmployees.get(str));
			
			if(officeEmployees.get(str)>maxNum) 
			{
				maxNum=officeEmployees.get(str);
			}
		
	}
	}

	public void display1(HashMap<String, Integer>positionEmployees)
	{
		int maxNum = 0;
		Set <String> keySet = positionEmployees.keySet();
		for(String str:keySet)
		{
			System.out.println("Position: "+str+" "+"Number of employees associated are: "+positionEmployees.get(str));
			
			if(positionEmployees.get(str)>maxNum) 
			{
				maxNum=positionEmployees.get(str);
			}
		
	}
	}



}

