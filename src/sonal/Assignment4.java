package sonal;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment4 {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "E:/NewWorkSpace/SeleniumBasics1/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("file:///C:/Users/Dell/Desktop/Missing%20Links.html");
		
		List <WebElement> oList = driver.findElements(By.xpath("//a")); //store all the links in List
		
		int size = oList.size();  //get size of list
		
		System.out.println("total Number of links on screen are: "+ size);
		String url;
		int i=0;
		for(WebElement we:oList)
		{
			
			System.out.println(oList.get(i).getText()); //display the link texts
			
			url = oList.get(i).getAttribute("href"); //display the href values
			System.out.println(url);
			if (url == null || url.isEmpty())
				
			{
				
				System.out.println("Missing href attribute: " +oList.get(i).getText());   //print missing href attribute
			}
			i++;
		}
			driver.close();	

	}

}

