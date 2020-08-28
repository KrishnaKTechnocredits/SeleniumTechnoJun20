package shruti;

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

import shruti.predefinedActionspkg.PtrdefinedActions;

public class HandleAlert  extends PtrdefinedActions{
	
	WebDriver driver;
	String firstName,lastName,companyName;
	
	void setUp(){
		driver = start();
	}
	
	void propRead() throws IOException{
	File file = new File(".//src/shruti/Config/BasicElement.properties");
	FileInputStream inputStream = new FileInputStream(file);
	
	Properties prop = new Properties();
	prop.load(inputStream);
	
	firstName = prop.getProperty("firstName");
	lastName = prop.getProperty("lastName");
	companyName = prop.getProperty("companyName");

}
	
	void confirmAlertTitle(){
		driver.findElement(By.id("basicelements")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='UserFirstName']")));
		
		driver.findElement(By.xpath("//input[@id='UserFirstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='UserLastName']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='UserCompanyName']")).sendKeys(companyName);
		driver.findElement(By.xpath("//div[@id='firstRow']/div[1]//button[@class='btn btn-primary']")).click();
		
		Alert alert = driver.switchTo().alert();
		String actualAlert = alert.getText();
		alert.dismiss();
		String expectedAlert = firstName + " and "+ lastName +" and "+companyName;
		
		if(actualAlert.equals(expectedAlert))
			System.out.println("correct Alert message");
		else{
			System.out.println("Incorrect Alert message");
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		HandleAlert handleAlert = new HandleAlert();
		handleAlert.setUp();
		handleAlert.propRead();
		handleAlert.confirmAlertTitle();
		
	}
}
