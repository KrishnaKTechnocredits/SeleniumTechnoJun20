package archana;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;

public class SeleniumAss10 extends PredefinedActions {

	void verifyWait() throws IOException {
		// read Property file
		File file = new File(".//src//archana//Config.properties");
		FileInputStream inputStream = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(inputStream);
		String firstName = prop.getProperty("FirstName");
		String lastName = prop.getProperty("LastName");
		String companyName = prop.getProperty("CompanyName");

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Basic Elements"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='UserFirstName']")));
		driver.findElement(By.xpath("//input[@id='UserFirstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='UserLastName']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='UserCompanyName']")).sendKeys(companyName);

		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		// verify alert msg
		Alert alert = driver.switchTo().alert();
		if (alert.getText().equals(firstName + " and " + lastName + " and " + companyName))
			System.out.println("alter message is correct");
		else
			System.out.println("alter message is Incorrect");
		alert.accept();
	}

	public static void main(String[] args) {
		driver = start("http://automationbykrishna.com");
		SeleniumAss10 assignment10 = new SeleniumAss10();
		try {
			assignment10.verifyWait();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
}