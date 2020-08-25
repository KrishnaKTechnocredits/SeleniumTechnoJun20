package vaishnavi_SeleniumBasics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Read values from property file

public class ValidationUsingPropertyFile {

	void filBasicDetails(String fName, String lName, String companyName) {
		WebDriver driver = new ChromeDriver();
		// Adding explicit wait
		WebDriverWait wait = new WebDriverWait(driver, 2);
		try {
			driver.get("http://automationbykrishna.com/");
			driver.findElement(By.xpath("//a[text()='Basic Elements']")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserFirstName")));
			driver.findElement(By.id("UserFirstName")).sendKeys(fName);
			driver.findElement(By.id("UserLastName")).sendKeys(lName);
			driver.findElement(By.id("UserCompanyName")).sendKeys(companyName);

			driver.findElement(By.xpath("//div/button[@onclick='myFunctionPopUp()']")).click();
			// Handle alert popUp
			Alert alert = driver.switchTo().alert();
			String finalPopUp = alert.getText();
			alert.accept();
			String expectedAlert = fName + " and " + lName + " and " + companyName;

			if (finalPopUp.equals(expectedAlert))
				System.out.println("Test Pass: Alert text and expected text matches");
			else
				System.out.println("Test Fail");
		} catch (NoSuchElementException ne) {
			ne.printStackTrace();
		} finally {
			driver.close();
		}
	}

	void readPropertyFile() throws IOException {
		File file = new File(".//src/vaishnavi/confiig/BasicElementsData.properties");
		FileInputStream inputstream = new FileInputStream(file);
		Properties properties = new Properties();

		properties.load(inputstream);
		String firstName = properties.getProperty("firstName");
		String lastName = properties.getProperty("lastName");
		String companyName = properties.getProperty("companyName");

		filBasicDetails(firstName, lastName, companyName);
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows//chromedriver.exe");
		try {
			new ValidationUsingPropertyFile().readPropertyFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
