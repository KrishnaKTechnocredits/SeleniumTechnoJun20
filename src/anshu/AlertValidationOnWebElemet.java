package anshu;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import anshu.base.PredefinedProperty;

public class AlertValidationOnWebElemet extends PredefinedProperty {
	WebDriver driver;

	void setUp() {
		driver = start();
	}

	void teardown() {
		driver.close();
	}

	void navigateToBasicElement() {
		driver.findElement(By.linkText("Basic Elements")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='UserFirstName']")));
	}

	void validateAlert() {

		WebElement firstName = driver.findElement(By.xpath("//input[@id='UserFirstName']"));
		firstName.sendKeys("Anshu");
		WebElement lastName = driver.findElement(By.xpath("//input[@name='ulname']"));
		lastName.sendKeys("Kashyap");
		WebElement companyName = driver.findElement(By.xpath("//input[@placeholder='Enter User Companyname']"));
		companyName.sendKeys("Technocredits");
		driver.findElement(By.xpath("//div[@id='firstRow']/div[1]//button[@class='btn btn-primary']")).click();

		Alert alert = driver.switchTo().alert();
		String actualText = alert.getText();
		alert.dismiss();

		String expectedText = firstName.getAttribute("value") + " and " + lastName.getAttribute("value") + " and "
				+ companyName.getAttribute("value");

		if (actualText.equals(expectedText))
			System.out.println("TestPass : Displayed correct alert message " + '\n' + "'" + expectedText + "'");
		else
			System.out.println("TestFail : Incorrect alert message is displayed '" + expectedText + "'");

	}

	void readProperty() throws IOException {
		File file = new File(".//src//anshu//base//BasicElemet.properties");
		FileInputStream inputStream = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(inputStream);

		String firstName = properties.getProperty("firstName");
		String lastName = properties.getProperty("lastName");
		String companyName = properties.getProperty("companyName");
		System.out.println('\n' + " BasicElement properties file consists these :-");
		System.out.println(
				" First name :- " + firstName + ", Last name :- " + lastName + ",  Company name :- " + companyName);
	}

	public static void main(String[] args) {
		AlertValidationOnWebElemet alertValidation = new AlertValidationOnWebElemet();
		alertValidation.setUp();
		alertValidation.navigateToBasicElement();
		alertValidation.validateAlert();
		try {
			alertValidation.readProperty();
		} catch (IOException ie) {
			System.out.println("Exception is handled.");
		} finally {
			alertValidation.teardown();
		}

	}
}
