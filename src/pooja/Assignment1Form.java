package pooja;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment1Form {

	public static void main(String[] args) {
	System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
	WebDriver driver=new ChromeDriver();
	driver.get("///C:/Users/Omkar/Downloads/Microsoft.SkypeApp_kzf8qxf38zg5c!App/All/SeleniumAssignment_1.html");
    
	String title=driver.getTitle();
	if(title.equals("TECHNOCREDITS"))
	{
		System.out.println("Test pass1");
	}
	else
	{
		System.out.println("Test fail");
	}
	
	driver.findElement(By.id("first name")).sendKeys("Pooja");
	driver.findElement(By.id("last name")).sendKeys("Raut");
	driver.findElement(By.id("E-mail")).sendKeys("er.poojaraut@gmail.com");
	driver.findElement(By.id("Company Name")).sendKeys("Fresher");
	
	if(driver.findElement(By.id("maleG")).isSelected())
	{ 
		driver.findElement(By.id("femaleG")).click();
	}
	
	driver.findElement(By.id("fresher")).click();
	driver.findElement(By.id("knownlanguages")).sendKeys("java");
	driver.findElement(By.id("python")).click();
	driver.findElement(By.id("other")).click();
	driver.findElement(By.id("termsAndConditions")).click();
	driver.findElement(By.id("resetBtn")).click();
	
	if(driver.findElement(By.id("first name")).getAttribute("value").isEmpty()&&
	driver.findElement(By.id("last name")).getAttribute("value").isEmpty()&&
	driver.findElement(By.id("E-mail")).getAttribute("value").isEmpty()&&
	driver.findElement(By.id("Company Name")).getAttribute("value").isEmpty()
	)
	{
		System.out.println("test pass2");
	}
	else
	{
		System.out.println("test fail");
	}
	
	
	
	if(driver.findElement(By.id("termsAndConditions")).isSelected())
	{
		System.out.println("test pass");
	}
	
	driver.findElement(By.id("morePractice")).click();
	
	
	if(driver.getCurrentUrl().equals("http://automationbykrishna.com/")&& driver.getTitle().equals("Login Signup Demo"))
	{
		System.out.println("Test pass3");
	}
	else
	{
		System.out.println("Test fail");
	}
	}
	
	

}
