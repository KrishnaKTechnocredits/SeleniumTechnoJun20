package sonal;



import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Assignment16 {
	
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
		HashSet <String> uniqueLocation = new HashSet<String>();
		
		HashSet<String> uniquePosition = new HashSet<String>();
		
		getLocation(uniqueLocation,uniquePosition);
		
		int pageCount = driver.findElements(By.xpath("//div[@id='example_paginate']//span//a")).size();
		
		for(int j=2;j<=pageCount;j++)
		{
			driver.findElement(By.xpath("//div[@id='example_paginate']//span//a[@data-dt-idx='"+j+"']")).click();
			getLocation(uniqueLocation,uniquePosition);
		}
		
		display(uniqueLocation);
		
		System.out.println();
		display(uniquePosition);
	}
	
	public void getLocation(HashSet<String> uniqueLocation,HashSet<String>uniquePosition)
	{
		int rowSize = driver.findElements(By.xpath("//table[@id='example']//tbody/tr")).size();
			
		for (int i=1;i<=rowSize;i++)
		{
			String location = driver.findElement(By.xpath("//table[@id='example']//tbody/tr["+i+"]//td[5]")).getText();
			uniqueLocation.add(location);
			
			String position = driver.findElement(By.xpath("//table[@id='example']//tbody/tr["+i+"]//td[4]")).getText();
			uniquePosition.add(position);
		}
	}
	

	
	public void display(HashSet<String> uniquePosition)
	{
		Iterator<String> itr = uniquePosition.iterator();
		while(itr.hasNext())
		{
			System.out.print(itr.next()+" ");
		}
	}

}

