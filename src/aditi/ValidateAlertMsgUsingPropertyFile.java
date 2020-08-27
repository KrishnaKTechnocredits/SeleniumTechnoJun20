package aditi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import aditi.base.PredefinedActions;

public class ValidateAlertMsgUsingPropertyFile extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	void setUp() {
		driver = start();
	}

	void navigateTo() {
		driver.findElement(By.linkText("Basic Elements")).click();
		wait = new WebDriverWait(driver, 10);
	}

	void validateAlertMessage() throws IOException {
		Properties elementProp = loadPropertyFile();
		String firstName = loadPropertyFile().getProperty("firstName");
		String lastName = loadPropertyFile().getProperty("lastName");
		String companyName = loadPropertyFile().getProperty("companyName");
		WebElement firstElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='UserFirstName']")));
		firstElement.sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='UserLastName']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='UserCompanyName']")).sendKeys(companyName);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		if (alert.getText().equals(firstName + " and " + lastName + " and " + companyName))
			System.out.println("Test Pass -> Correct Alert Message \nAlert Text - " + alert.getText());
		else
			System.out.println("Test Fail -> Alert text doesn't contain value from property file");
		alert.accept();
	}

	Properties loadPropertyFile() throws IOException {
		File file = new File(
				"/Users/apple/Documents/TechnoGitProject/SeleniumTechnoJun20/src/aditi/config/BasicElementData");
		FileInputStream inputStream = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(inputStream);
		return prop;
	}

	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) {
		ValidateAlertMsgUsingPropertyFile validateAlertMsgUsingPropertyFile = new ValidateAlertMsgUsingPropertyFile();
		validateAlertMsgUsingPropertyFile.setUp();
		validateAlertMsgUsingPropertyFile.navigateTo();
		try {
			validateAlertMsgUsingPropertyFile.validateAlertMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		validateAlertMsgUsingPropertyFile.tearDown();
	}
}
