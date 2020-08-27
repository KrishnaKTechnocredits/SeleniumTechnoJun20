package barkha;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import barkha_base.PredefinedFunctions;

public class ReadPropertyFile_HandleAlert extends PredefinedFunctions {
	
	WebDriver driver;
	
	void setUP(String URL) {
		driver=start(URL);
	}
	void readPropertyFileAndHandleAlert() throws IOException {
		//open file
		File file=new File("./src/barkha_config/BasicElements.properties");
		//enable readable mode
		FileInputStream inputStream=new FileInputStream(file);
		
		//load file in memory
		Properties prop=new Properties();
		prop.load(inputStream);
		
		String fName=prop.getProperty("FirstName");   //prop.getProperty() only return String
		String lName=prop.getProperty("LastName");
		String comName=prop.getProperty("CompanyName");
		
		driver.findElement(By.linkText("Basic Elements")).click();
		
		//Explicit wait
		WebDriverWait waits=new WebDriverWait(driver, 4000);
		waits.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserFirstName"))).sendKeys(fName);
		
		driver.findElement(By.id("UserLastName")).sendKeys(lName);
		driver.findElement(By.id("UserCompanyName")).sendKeys(comName);
		
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		
		//Handle Alert
		Alert alert=driver.switchTo().alert();
		String actualTextOnAlert=alert.getText();
		alert.accept();
		
		String expectedTextOnAlert=fName+" and "+lName+" and "+comName;
		
		//validation on Alert box
		if (actualTextOnAlert.endsWith(expectedTextOnAlert))
			System.out.println("Message on Alert box is correct.");
		else
			System.out.println("Message on Alert box is incorrect.");
	}
	
	void tearBreak() {
		driver.close();
	}
	public static void main(String[] args) throws IOException {
		ReadPropertyFile_HandleAlert handleAlert=new ReadPropertyFile_HandleAlert();
		handleAlert.setUP("http://automationbykrishna.com/");
		handleAlert.readPropertyFileAndHandleAlert();
		handleAlert.tearBreak();
	}
}