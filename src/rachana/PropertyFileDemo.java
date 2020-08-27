package rachana;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rachana.base.PredefinedActions;

public class PropertyFileDemo extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	static String firstName, lastName, companyName;

	void setUp() {
		driver = start();
		wait = new WebDriverWait(driver, 15); // 500 ms
	}

	void tearDown() {
		driver.close();
	}
	
	void NavigateToMenu() {
		driver.findElement(By.xpath("//a[@id='basicelements']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-lg-6']/section")));
	}

	void loadPropertyFile() throws IOException {

		File file = new File(".//src//rachana//config//BasicElements.properties");
		FileInputStream inputstream = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(inputstream);
		firstName = prop.getProperty("firstName");
		lastName = prop.getProperty("lastName");
		companyName = prop.getProperty("companyName");
	}

	void actionsOnUI() {
		driver.findElement(By.id("UserFirstName")).sendKeys(firstName);
		driver.findElement(By.id("UserLastName")).sendKeys(lastName);
		driver.findElement(By.id("UserCompanyName")).sendKeys(companyName);
		driver.findElement(By.xpath("//button[@onclick='myFunctionPopUp()']")).click();
	}

	void validateAlertMessage() {
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		alert.accept();
		System.out.println(alertMessage);
		System.out.println(alertMessage.equals(firstName + " and " + lastName + " and " + companyName)
				? "Pass:Alert message verified"
				: "Fail:Alertmessage not verified");
	}

	public static void main(String[] args) throws IOException {
		PropertyFileDemo propertyfiledemo = new PropertyFileDemo();
		propertyfiledemo.setUp();
		propertyfiledemo.NavigateToMenu();
		propertyfiledemo.loadPropertyFile();
		propertyfiledemo.actionsOnUI();
		propertyfiledemo.validateAlertMessage();
		propertyfiledemo.tearDown();

	}

}
