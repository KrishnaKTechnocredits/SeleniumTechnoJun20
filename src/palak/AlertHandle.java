//Assignment 10 - validate Alert message, read inputs from properties file.
package palak;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import palak.base.PredefinedActions;

public class AlertHandle extends PredefinedActions {
	WebDriver driver;
	String firstName,lastName,companyName;
	
	void setUp(String url) {
		driver = start(url);
	}

	void navigateToMenu() {
		driver.findElement(By.linkText("Basic Elements")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='UserFirstName']")));
	}
	
	void inputFromPropertiesFile() throws IOException {
		File file = new File("./src/palak/config/BasicElementData.properties");
		FileInputStream inputStream = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(inputStream);

		firstName = properties.getProperty("FirstName");
		lastName = properties.getProperty("LastName");
		companyName = properties.getProperty("CompanyName");
	}

	void validateAlertHandleValue() throws IOException {
		inputFromPropertiesFile();
		driver.findElement(By.xpath("//input[@id='UserFirstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='UserLastName']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='UserCompanyName']")).sendKeys(companyName);
		driver.findElement(By.xpath("//div[@id='firstRow']/div[1]//button[@class='btn btn-primary']")).click();
		Alert alert = driver.switchTo().alert();
		String actualAlertText = alert.getText();
		alert.dismiss();
		String expectedAlertText = firstName + " and "+ lastName +" and "+companyName;
		
		System.out.println(actualAlertText.equals(expectedAlertText)? 
				"Alert Message is Correct : " +expectedAlertText : "Alert Message is incorrect");
	}

	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) throws IOException {
		AlertHandle alertHandle = new AlertHandle();
		alertHandle.setUp("http://automationbykrishna.com/");
		alertHandle.navigateToMenu();
		alertHandle.validateAlertHandleValue();
		alertHandle.tearDown();
	}
}
