package aashtha;

import aashtha.base.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertMessageVerification extends PredefinedActions {
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	void tearDown() {
		driver.close();
	}

	void navigateToBasicElements() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		driver.findElement(By.id("basicelements")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='firstRow']/div[1]/section/header")));
	}

	void verifyAlertMessage() throws IOException {
		File file = new File(".//src/aashtha/base/AlertMessageVerificationData");
		FileInputStream inputStream = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(inputStream);
		String firstName = properties.getProperty("firstName");
		String lastName = properties.getProperty("lastName");
		String companyName = properties.getProperty("companyName");
		String expectedMessage = firstName + " and " + lastName + " and " + companyName;
		driver.findElement(By.xpath("//input[@id='UserFirstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='UserLastName']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='UserCompanyName']")).sendKeys(companyName);
		driver.findElement(By.xpath("//div[@id='firstRow']/div[1]/section/div/div[4]/button")).click();
		Alert alert = driver.switchTo().alert();
		String actualMessage = alert.getText();
		if (actualMessage.equals(expectedMessage))
			System.out.println("Alert message verification : Passed");
		else
			System.out.println("Alert message verification : Failed");
		alert.accept();
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		AlertMessageVerification alertMessageVerification = new AlertMessageVerification();
		alertMessageVerification.setUp("http://automationbykrishna.com/");
		alertMessageVerification.navigateToBasicElements();
		alertMessageVerification.verifyAlertMessage();
		alertMessageVerification.tearDown();
	}
}
