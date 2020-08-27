package aavruti;

import aavruti.base.PredefinedActions;

import java.io.*;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertMessageValidation extends PredefinedActions{

	WebDriver driver;
	WebDriverWait wait;
	
	String firstName,lastName,companyName;
	
	void launchBrowser() {
		driver = start();
		wait = new WebDriverWait(driver,15);
	}
	
	void loadPropertiesFile() throws IOException{
		File file = new File(".//src/aavruti/config/BasicElementsData.properties");
		FileInputStream inputStream = new FileInputStream(file);
		
		Properties properties = new Properties();
		properties.load(inputStream);
		
		firstName = properties.getProperty("firstName");
		lastName = properties.getProperty("lastName");
		companyName = properties.getProperty("companyName");		
	}
	
	void validateAlertMessage() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Basic Elements"))).click();
		WebElement firstNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='UserFirstName']")));
		
		firstNameElement.sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='UserLastName']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='UserCompanyName']")).sendKeys(companyName);		
		driver.findElement(By.xpath("//button[@onclick='myFunctionPopUp()']")).click();
		
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText().equals(firstName + " and " + lastName + " and " + companyName) 
						   ? "Alert Message --> " + firstName + " and " + lastName + " and " + companyName 
						   : "Alert Message is incorrect");
		
		alert.accept();
	}
	
	void closeBrowser() {
		driver.quit();
	}
	
	public static void main(String[] args) throws IOException{
		AlertMessageValidation alertMessageValidation = new AlertMessageValidation();
		
		alertMessageValidation.loadPropertiesFile();
		alertMessageValidation.launchBrowser();
		alertMessageValidation.validateAlertMessage();
		alertMessageValidation.closeBrowser();
	}
}
