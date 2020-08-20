package sonal;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignment5_1 {
	
public WebDriver Start(String url){
		
		System.setProperty("webdriver.chrome.driver","E:/NewWorkSpace/SeleniumBasics1/chromedriver.exe"); // Method to initiate web driver and launch url
		
		WebDriver driver = new ChromeDriver();
		
		driver.get(url);
		
		driver.manage().window().maximize();
		
		return driver;
	}
	
public void ddValidations() throws InterruptedException
{
	WebDriver driver = Start("http://automationbykrishna.com");
	
	driver.findElement(By.id("basicelements")).click();
	
	Thread.sleep(5000);
	
	JavascriptExecutor js = (JavascriptExecutor)driver;		
	js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.id("inlineCheckbox1"))); //scroll down the page
	
	WebElement element = driver.findElement(By.xpath("//select[@class='form-control']"));	
	Select dd = new Select(element);	
	List <WebElement> oList = dd.getOptions();	
	int sizeofList = oList.size();
	System.out.println("Total Number of Options: "+sizeofList); //get number of options in drop down
	int count=0;
	for(WebElement we:oList)
	{
		String s1 = we.getText();
		int s2 = Integer.parseInt(s1);
		
		if(s2%2 == 0)  //Select even options
		{
			dd.selectByVisibleText(s1);
			System.out.println("Selected Option: "+s1); //print selected option
			count++;
			
		}
			
	
	}
		System.out.println("Number of even options: "+count); // print number of options selected
	
	for(WebElement we1:oList)
		{
	
			if(we1.isSelected())
				{
		
				}else
				{
					System.out.println("Deselected Option: "+we1.getText());  //display the not selected options
				}
				}
	
	dd.deselectAll();
	
	List<WebElement> l1= dd.getAllSelectedOptions();
	
	if(l1.isEmpty())  //Check list is empty which means no options is selected.
	{
		System.out.println("All options are deselected");  
	}
	
	
	
}

public static void main(String[] args) throws InterruptedException
{
	Assignment5_1 a1 = new Assignment5_1();
	
	a1.ddValidations();
}
}

