package anup;

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

import anup.basics.PredefinedActions;

public class ValidateAlertInputFromPropertyFile extends PredefinedActions {

	WebDriver driver;

	void setUp() {
		driver = start();
	}

	String firstName, lastName, companyName;

	void validateAlertInputfromPropertyFile() throws IOException {
		File file = new File(".//src/anup/config/BasicElementData.properties");
		FileInputStream inputStream = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(inputStream);

		firstName = prop.getProperty("firstName");
		lastName = prop.getProperty("lastName");
		companyName = prop.getProperty("companyName");

		driver.findElement(By.linkText("Basic Elements")).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='UserFirstName']")));
		driver.findElement(By.xpath("//input[@id='UserFirstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='UserLastName']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='UserCompanyName']")).sendKeys(companyName);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		String expectedMsg = firstName + " and " + lastName + " and " + companyName;

		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		String actualMsg = alert.getText();

		if (actualMsg.equals(expectedMsg))
			System.out.println("Alert Message validation successfull ");
		else
			System.out.println("Validation failed");
		alert.accept();
	}

	void browserclose() {
		driver.close();
	}

	public static void main(String[] args) {
		ValidateAlertInputFromPropertyFile validateAlertInputFromPropertyFile = new ValidateAlertInputFromPropertyFile();
		validateAlertInputFromPropertyFile.setUp();
		try {
			validateAlertInputFromPropertyFile.validateAlertInputfromPropertyFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		validateAlertInputFromPropertyFile.browserclose();
	}
}
