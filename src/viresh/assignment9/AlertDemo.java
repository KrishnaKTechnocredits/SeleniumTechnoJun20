package viresh.assignment9;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import viresh.base.PredefinedActions;

public class AlertDemo extends PredefinedActions {
	WebDriver driver;
	Properties prop;

	void setUp() {
		driver = start();
	}

	void propertyRead() throws IOException {
		File file = new File(".//src/viresh/assignment9/config/BasicElementsData.properties");
		FileInputStream inputStream = new FileInputStream(file);
		prop = new Properties();
		prop.load(inputStream);
	}

	void validateAlertMsg() throws IOException {
		driver.findElement(By.xpath("//a[text()='Basic Elements']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='UserFirstName']")));
		propertyRead();
		String fname = prop.getProperty("firstName");
		driver.findElement(By.xpath("//input[@id='UserFirstName']")).sendKeys(fname);
		System.out.println("First name entered: " + fname);
		String lname = prop.getProperty("lastName");
		driver.findElement(By.xpath("//input[@id='UserLastName']")).sendKeys(lname);
		System.out.println("Last name entered: " + lname);
		String cname = prop.getProperty("compName");
		driver.findElement(By.xpath("//input[@id='UserCompanyName']")).sendKeys(cname);
		System.out.println("Company name entered: " + cname);
		driver.findElement(By.xpath("//div[4]/button[text()='Submit']")).click();

		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();
		String concatInputText = fname + " and " + lname + " and " + cname;
		if (concatInputText.equals(alertText)) {
			System.out.println("TC Pass: The AlertText is correct: " + concatInputText);
		} else {
			System.out.println("TC Fail: The AlertText is incorrect:" + concatInputText);
			System.out.println("The input given is: " + fname + ", " + lname + ", " + cname);
		}
	}

	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) {
		AlertDemo aDemo = new AlertDemo();
		aDemo.setUp();
		try {
			aDemo.validateAlertMsg();
		} catch (IOException e) {
		}
		aDemo.tearDown();
	}
}
