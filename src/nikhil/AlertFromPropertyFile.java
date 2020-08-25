package nikhil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import nikhil.base.PreDefinedActions;

public class AlertFromPropertyFile extends PreDefinedActions{
	WebDriver driver;
	
	void setUp() {
		driver = start();
	}
	
	void tearDown() {
		driver.close();
	}
	
	void navigateTo() {
		driver.findElement(By.linkText("Basic Elements")).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@id='UserFirstName']")));
	}
	
	void verifyAlertTextFromPropertyFile() throws IOException {
		File file = new File(".\\src\\nikhil\\config\\BasicElementData.properties");
		FileInputStream fileInputStream = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInputStream);
		
		//Getting data from property file
		String fName = properties.getProperty("FirstName");
		String lName = properties.getProperty("LastName");
		String cName = properties.getProperty("CompanyName");
		
		//Finding elements and inputing the data
		driver.findElement(By.xpath("//input[@id='UserFirstName']")).sendKeys(fName);
		driver.findElement(By.xpath("//input[@id='UserLastName']")).sendKeys(lName);
		driver.findElement(By.xpath("//input[@id='UserCompanyName']")).sendKeys(cName);
		driver.findElement(By.xpath("//button[@onclick='myFunctionPopUp()']")).click();
		
		//Verifying Alert data
		Alert alert = (Alert) driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();
		if(alertText.contains(fName) && alertText.contains(lName) && alertText.contains(cName)) {
			System.out.println("Alert text is : "+alertText+"\nAlert text contains "+fName+", "+lName+" & "+cName+" taken from the property file.");
		}
	}
	
	public static void main(String[] args) throws IOException {
		AlertFromPropertyFile alertFromPropertyFile = new AlertFromPropertyFile();
		alertFromPropertyFile.setUp();
		alertFromPropertyFile.navigateTo();
		alertFromPropertyFile.verifyAlertTextFromPropertyFile();
		alertFromPropertyFile.tearDown();
	}
}
