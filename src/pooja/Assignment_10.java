package pooja;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assignment_10 {
	
	void readPropertyFile() throws IOException {
		File file=new File(".//src//pooja//pooja//config//FormInfo.properties");
		
		FileInputStream inputStream=new FileInputStream(file);
		
		Properties prop=new Properties();
		prop.load(inputStream);
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://automationbykrishna.com");
		driver.findElement(By.id("basicelements")).click();
		WebDriverWait wait = new WebDriverWait(driver, 15); 
	   WebElement firstName= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserFirstName")));
	   driver.findElement(By.id("UserFirstName")).sendKeys(prop.getProperty("FirstName"));
	   driver.findElement(By.id("UserLastName")).sendKeys(prop.getProperty("LastName"));
	   driver.findElement(By.id("UserCompanyName")).sendKeys(prop.getProperty("CompanyName"));
	   driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
	   String actualText= driver.switchTo().alert().getText();
	   String expectedText=prop.getProperty("FirstName")+" and "+prop.getProperty("LastName")+" and "+prop.getProperty("CompanyName");
	   if(actualText.equals(expectedText))
		   System.out.println("test pass");
	   else {
		System.out.println("test fail");
	}
	}

	public static void main(String[] args) throws IOException {
		Assignment_10 assignment_10=new Assignment_10();
		assignment_10.readPropertyFile();

	}

}
