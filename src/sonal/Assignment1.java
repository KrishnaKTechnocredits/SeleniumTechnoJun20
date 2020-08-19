package sonal;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class Assignment1 {



	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("file:///C:/Users/Dell/Downloads/SeleniumAssignment_1.html");
		
		String acttitle = driver.getTitle();
		
		System.out.println("Title of the webpage is: " +acttitle);  //print the title
		
		String exptitle = "TECHNOCREDITS";
		
		if (acttitle.equals(exptitle))
			{
				System.out.println("Test is Passed: Title is correct");
			}else
			
				System.out.println("Test is Failed: Title is incorrect");
		
		driver.findElement(By.id("first name")).sendKeys("Sonal");
		driver.findElement(By.id("last name")).sendKeys("Inamdar");
		driver.findElement(By.id("E-mail")).sendKeys("Inamdar@gmail.com");
		driver.findElement(By.id("Company Name")).sendKeys("Inamdar");
		
	
		
	WebElement radio1= driver.findElement(By.xpath("//input[@value='male']"));
	WebElement radio2 = driver.findElement(By.xpath("//input[@value='female']"));
	
	if(radio2.isSelected())  //check if female radio button is selected
		radio1.click();
	else
		radio2.click();
		
		WebElement dd = driver.findElement(By.id("continents"));
		
		driver.findElement(By.xpath("//input[@placeholder='Enter language you know']")).sendKeys("Java");
		driver.findElement(By.xpath("//input[@value='Java']")).click();
		driver.findElement(By.xpath("//input[@value='Google']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(5000);
		
		//driver.findElement(By.xpath("//input[@name='Agree on terms and conditions']")).click();
		driver.findElement(By.xpath("//input[@value='Reset form in same tab']")).click();
		
		String name = driver.findElement(By.id("first name")).getText();
		if( name.isEmpty())
		{
			System.out.println("Test is Passed. Text box is empty");
								
		}
		else
			System.out.println("Test is Failed. Form is not reset");
		
		if (radio1.isSelected())
		{
			System.out.println("Test is Failed. Radio buttons are selected");
			
		}
		else
			System.out.println("Test is Passed. Form is reset");
		
		driver.findElement(By.linkText("Go And Practice For it")).click();
		
		Thread.sleep(5000);
		
		String newtitle = driver.getTitle();
		
		String newexptitle = "Login Signup Demo";
		
		if (newtitle.equals(newexptitle))
		{
			System.out.println("Test is Passed: Title is correct");
		}else
		
			System.out.println("Test is Failed: Title is incorrect");
	
		String newurl = driver.getCurrentUrl();
		//System.out.println(newurl);
		String expurl = "http://automationbykrishna.com/";
		
		if (newurl.equals(expurl))
		{
			System.out.println("Test is Passed: URL is correct");
		}else
		
			System.out.println("Test is Failed: URL is incorrect");
		
		driver.close();
		
		}
	
	
		
		
		
		
		

		
		
		
		

	}





