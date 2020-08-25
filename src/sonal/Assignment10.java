package sonal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assignment10 {
	
		WebDriver driver;
		
		public  WebDriver Start(String url){
				
				System.setProperty("webdriver.chrome.driver","E:/NewWorkSpace/SeleniumBasics1/chromedriver.exe"); // Method to initiate web driver and launch url
				
				WebDriver driver = new ChromeDriver();
				
				driver.get(url);
				
				driver.manage().window().maximize();
				
				return driver;
			}
		
		public void testPropertyFile() throws IOException
		{
			File file = new File(".//src//sonal//Config//Property.properties");
			FileInputStream inputStream = new FileInputStream(file);
			
			Properties prop = new Properties();
			prop.load(inputStream);
			
			String firstName = prop.getProperty("firstName");
			String lastName = prop.getProperty("lastName");
			String cmpName = prop.getProperty("companyName");
			
			WebDriver driver = Start("http://automationbykrishna.com");
			
			driver.findElement(By.id("basicelements")).click();
			
			WebDriverWait wait = new WebDriverWait(driver,15);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserFirstName")));
			
			driver.findElement(By.id("UserFirstName")).sendKeys(firstName);
			driver.findElement(By.name("ulname")).sendKeys(lastName);
			driver.findElement(By.id("UserCompanyName")).sendKeys(cmpName);
			driver.findElement(By.xpath("//div[@id='firstRow']/div[@name='secondSegment'][1]//button")).click();
			
			Alert alert = driver.switchTo().alert();
			String actualMessage  = alert.getText();
			alert.accept();
			String expMessage = firstName+" and "+lastName+" and "+cmpName;
			
			if(actualMessage.equals(expMessage))
			{
				System.out.println("Test is Pass");
			}else
				System.out.println("Test is Fail");
			
					
			driver.close();
		}

		public static void main(String[] args) throws IOException {
			
			Assignment10 a1 = new Assignment10();
			
			a1.testPropertyFile();
	}

}

