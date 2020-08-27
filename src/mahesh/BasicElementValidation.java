package mahesh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import mahesh.base.PredefinedActions;

public class BasicElementValidation extends PredefinedActions{
	WebDriver driver;
	
	void setUp() {
		driver = start("http://automationbykrishna.com");
	}
	
	void validateBasicElement() throws IOException {
		driver.findElement(By.xpath("//a[@id='basicelements']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Properties prop = loadPropertyFile();
		String firstName = prop.getProperty("firstName");
		String lastName = prop.getProperty("lastName");
		String companyName = prop.getProperty("companyName");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='UserFirstName']"))).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='UserLastName']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='UserCompanyName']")).sendKeys(companyName);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		if ((firstName + " and " + lastName + " and " + companyName).equals(alert.getText()))
			System.out.println("Alert message is displayed correctly");
		else
			System.out.println("Alert message displayed is incorrect");
		alert.accept();
	}
	
	Properties loadPropertyFile() throws IOException{
		File file = new File(".//src//mahesh//config//BasicElementData.properties");
		FileInputStream inputStream = new FileInputStream(file);		
		Properties prop = new Properties();
		prop.load(inputStream);
		return prop;
	}
	
	public static void main(String[] args) {
		BasicElementValidation basicElementValidation = new BasicElementValidation();
		basicElementValidation.setUp();
		try {
			basicElementValidation.validateBasicElement();
		} catch (IOException e) {
			e.printStackTrace();
		}
		basicElementValidation.driver.close();
	}
}
