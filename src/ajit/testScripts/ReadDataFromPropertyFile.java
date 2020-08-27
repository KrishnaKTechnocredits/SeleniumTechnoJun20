package ajit.testScripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ajit.base.PredefinedActions;

public class ReadDataFromPropertyFile extends PredefinedActions {

	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	void navigateToBasicElements() {
		driver.findElement(By.linkText("Basic Elements")).click();
		WebDriverWait wait = new WebDriverWait(driver, 15); // 500 ms
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='UserFirstName']")));
	}

	void tearDown() {
		driver.close();
	}

	//Read data from Properties Files
	void readPropertiesFile() throws IOException {
		File file = new File(".//src//ajit//Config//BasicElementsData.properties");
		FileInputStream fileInputStream = new FileInputStream(file);

		Properties properties = new Properties();
		properties.load(fileInputStream);

		String firstName = properties.getProperty("firstName");
		String lastName = properties.getProperty("lastName");
		String companyName = properties.getProperty("companyName");

		EnterDataUsingPropFile(firstName, lastName, companyName);
	}

	//Enter Data from Porperties Files,Handle Alert and Verify Alert
	void EnterDataUsingPropFile(String firstName, String lastName, String companyName) {
		driver.findElement(By.xpath("//input[@id='UserFirstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='UserLastName']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='UserCompanyName']")).sendKeys(companyName);
		driver.findElement(By.xpath("//div[4]/button[@class='btn btn-primary']")).click();
		
		Alert alert = driver.switchTo().alert();
		String actualAlertText = alert.getText();
		System.out.println("ActualAlertMsg   : " + actualAlertText);
		alert.accept();
		String expectedAlertText = firstName + " and " + lastName + " and " + companyName;
		System.out.println("ExpectedAlertMsg : " + expectedAlertText);
		System.out.println("====================================================");

		if (actualAlertText.equals(expectedAlertText))
			System.out.println("ActaulAlert matched with ExpectedAlert :Test Passed");
		else
			System.out.println("ActaulAlert not matched with ExpectedAlert :Test Failed");

	}

	public static void main(String[] args) throws IOException {
		ReadDataFromPropertyFile readDataFromPropertyFile = new ReadDataFromPropertyFile();
		readDataFromPropertyFile.setUp("http://automationbykrishna.com/");
		readDataFromPropertyFile.navigateToBasicElements();
		readDataFromPropertyFile.readPropertiesFile();
		readDataFromPropertyFile.tearDown();
	}
}
