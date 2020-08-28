package pranita;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pranita.basic.PredefinedFunctions;

public class Assignment_10_validateAlert extends PredefinedFunctions {
	WebDriver driver;
	String firstName, lastName, companyName;

	void setUp(String url) {
		driver = start(url);
	}

	void readPropertiesFile() throws IOException {
		File file = new File(".//src//pranita//config//BasicElementsData.properties");
		FileInputStream inputStream = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(inputStream);

		firstName = properties.getProperty("firstName");
		lastName = properties.getProperty("lastName");
		companyName = properties.getProperty("companyName");
	}

	void validateAlert() throws IOException {
		driver.findElement(By.linkText("Basic Elements")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='UserFirstName']")));
		
		driver.findElement(By.xpath("//input[@id='UserFirstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name='ulname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@placeholder='Enter User Companyname']")).sendKeys(companyName);
		driver.findElement(By.xpath("//div[@id='firstRow']/div[1]//button[@class='btn btn-primary']")).click();
		
		//driver.findElement(By.xpath("//div[@class='form-group']/div/button[@class='btn btn-primary']")).click();
		//When used this xpath got exception'org.openqa.selenium.ElementNotInteractableException: element not interactable'
		

		Alert alert = driver.switchTo().alert();
		String actualAlertText = alert.getText();
		alert.dismiss();
		String expectedAlertText = firstName + " and " + lastName + " and " + companyName;

		if (actualAlertText.equals(expectedAlertText))
			System.out.println("Alert Message displayed is Correct ");
		else
			System.out.println("Incorrect Alert Message displayed ");

	}

	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) throws IOException {
		Assignment_10_validateAlert assignment_10_validateAlert = new Assignment_10_validateAlert();
		assignment_10_validateAlert.setUp("http://automationbykrishna.com/");
		assignment_10_validateAlert.readPropertiesFile();
		assignment_10_validateAlert.validateAlert();
		assignment_10_validateAlert.tearDown();
	}
}
