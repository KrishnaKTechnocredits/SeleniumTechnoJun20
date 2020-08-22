package sonal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment7 {
	
	//Start method to initialize driver
	
public WebDriver Start(String url){
		
		System.setProperty("webdriver.chrome.driver","E:/NewWorkSpace/SeleniumBasics1/chromedriver.exe"); // Method to initiate web driver and launch url
		
		WebDriver driver = new ChromeDriver();
		
		driver.get(url);
		
		driver.manage().window().maximize();
		
		return driver;
	}

//Method to work on table

public void tableEx1() throws InterruptedException
{
	WebDriver driver = Start("http://automationbykrishna.com");
	
	driver.findElement(By.linkText("Demo Tables")).click();
	Thread.sleep(5000);
	
	//Get total number of rows	
	int rowSize = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
	
	//Create hashmap to get unique manager ids and number of employees associated with them
	HashMap <String, Integer> hm = new HashMap <String, Integer>();
	
	for(int i=1;i<=rowSize;i++)
	{
	
		String MngId = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+i+"]/td[4]")).getText();
	
		if(hm.containsKey(MngId))
			{
				int cnt = hm.get(MngId);
				hm.put(MngId, cnt+1);
		}else
			{
				hm.put(MngId, 1);
			}
	
	}
	
	int maxNum = 0;
	//print the values of hashmap
	Set <String> keySet = hm.keySet();
	for(String str:keySet)
	{
		System.out.println("Manager ID: "+str+" "+"Number of employees associated are: "+hm.get(str));
		
		if(hm.get(str)>maxNum) // This will be used to find the maximun number  of employees attached to a manager
		{
			maxNum=hm.get(str);
		}
		
	}
	
	
	driver.close();
}

public static void main(String[] args) throws InterruptedException
{
	Assignment7 a1 = new Assignment7();
	a1.tableEx1();
}

}

