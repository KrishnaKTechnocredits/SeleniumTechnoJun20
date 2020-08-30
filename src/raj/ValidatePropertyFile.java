package raj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import raj.selenium.base.PredefinedActions;

public class ValidatePropertyFile extends PredefinedActions {
	WebDriver driver;

	void setUp() {
		driver = start("http://automationbykrishna.com/");
	}

	void readPropertyFile() throws IOException {
		// Opens the file
		File file = new File(".//src//raj//selenium//config//BasicElementData.properties");
		// enables file in read mode
		FileInputStream inputStream = new FileInputStream(file);

		Properties property = new Properties();
		property.load(inputStream); //loads the file in memory
		String fName = property.getProperty("firstName");
		String lName = property.getProperty("lastName");
		String compName = property.getProperty("companyName");

		enterBasicDetails(fName, lName, compName);
	}

	void enterBasicDetails(String fName, String lName, String compName) {
		// Explicit wait
		WebDriverWait wait = new WebDriverWait(driver, 15);

		driver.findElement(By.linkText("Basic Elements")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserFirstName")));
		driver.findElement(By.id("UserFirstName")).sendKeys(fName);
		driver.findElement(By.id("UserLastName")).sendKeys(lName);
		driver.findElement(By.id("UserCompanyName")).sendKeys(compName);

		driver.findElement(By.xpath("//button[@onclick = 'myFunctionPopUp()']")).click();
		// Handle alert popUp
		Alert alert = driver.switchTo().alert();
		String actualPopUpText = alert.getText();
		alert.accept();
		String expectedAlertText = fName + " and " + lName + " and " + compName;

		if (actualPopUpText.equals(expectedAlertText))
			System.out.println("Test Pass: Alert text matched...!!");
		else
			System.out.println("Test Fail");
	} 

	public static void main(String[] args) throws IOException {
		ValidatePropertyFile propFile = new ValidatePropertyFile();
		propFile.setUp();
		propFile.readPropertyFile();
	}
}

