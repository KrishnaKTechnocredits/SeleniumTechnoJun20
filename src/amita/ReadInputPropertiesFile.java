package amita;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amita.base.PredefinedActions;

public class ReadInputPropertiesFile extends PredefinedActions{
	
	WebDriver driver;
	private String firstName,lastName,companyName;

	void setUp() {
		driver = start();
	}
	
	//Reading input data from properties file
	void inputFromPropertyFile() throws IOException{
		File file = new File(".//src/amita/config/BasicElementData.properties");
		FileInputStream inputStream = new FileInputStream(file);		
		Properties prop = new Properties();
		prop.load(inputStream);
		
		firstName = prop.getProperty("firstName");
		lastName = prop.getProperty("lastName");
		companyName = prop.getProperty("companyName");
	}
	
	//Validate alert message
	void validateAlertMsg() throws IOException {
		driver.findElement(By.linkText("Basic Elements")).click();
		WebDriverWait wait = new WebDriverWait(driver,15);
		inputFromPropertyFile();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='UserFirstName']")));
		driver.findElement(By.xpath("//input[@id='UserFirstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='UserLastName']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='UserCompanyName']")).sendKeys(companyName);		
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		String expectedAlertMsg = firstName + " and " + lastName + " and " + companyName;
		//System.out.println(expectedAlertMsg);
	
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		String actualAlertMsg = alert.getText();
		//System.out.println(actualAlertMsg);
		
		if (actualAlertMsg.equals(expectedAlertMsg))
			System.out.println("TEST PASS: Alert Message verification successfull ");
		else
			System.out.println("TEST FAIL: Alert Message verification Failed ");
		alert.accept();		
	}
	
	public static void main(String[] args) {
		ReadInputPropertiesFile readInputPropertiesFile = new ReadInputPropertiesFile();
		readInputPropertiesFile.setUp();
		try {
			readInputPropertiesFile.validateAlertMsg();
		} catch (IOException e) {
			e.printStackTrace();
		}
		readInputPropertiesFile.driver.close();
	}
}