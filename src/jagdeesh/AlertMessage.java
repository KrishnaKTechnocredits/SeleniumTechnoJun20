package jagdeesh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertMessage {
	WebDriver driver;
	WebDriverWait wait;
	
	void navigateToDemoTables (WebDriver driver) throws IOException{
		this.driver=driver;
		 wait = new WebDriverWait(driver, 15);
		driver.findElement(By.xpath("//a[@id='basicelements']")).click();
		WebElement fname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='ufname']")));
		File file = new File(".\\src\\jagdeesh\\config\\AlertMessage.properties");
		FileInputStream inputStream = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(inputStream);
		fname.sendKeys(prop.getProperty("FirstName"));
		driver.findElement(By.xpath("//input[@name='ulname']")).sendKeys(prop.getProperty("LastName"));
		driver.findElement(By.xpath("//input[@name='cmpname']")).sendKeys(prop.getProperty("CompanyName"));
		driver.findElement(By.xpath("//div[4]/button[@type='submit']")).click();
		Alert alert = driver.switchTo().alert();
		String expectedText = prop.getProperty("FirstName")+" and "+prop.getProperty("LastName")+" and "+(prop.getProperty("CompanyName"));
		String actualText= alert.getText();
		if(actualText.equals(expectedText))
			System.out.println("Expected text --> "+expectedText+" , Actual text --> "+ actualText+" : is same");
		else
			System.out.println("Expected text --> "+expectedText+" ,  Actual text --> "+ actualText+" : is not same");
		alert.accept();
		driver.close();
		
	}
	 
	public static void main(String[] agrs) throws IOException {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com");
		new AlertMessage().navigateToDemoTables(driver);
	}
}