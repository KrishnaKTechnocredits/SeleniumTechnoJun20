package abhishek;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import abhishek.base.PredefinedActions;

public class Assignment_14 extends PredefinedActions {
	WebDriver driver;
	
	@BeforeClass
	void setUp() {
		driver = start();
	}

	@AfterClass
	public void tearDown() {

		driver.close();
	}

	@Test(dataProvider = "loginData")
	public void dataDriven(String name, String pwd, String message) {

		driver.findElement(By.id("registration2")).click();

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("unameSignin")));

		driver.findElement(By.id("unameSignin")).sendKeys(name);
		driver.findElement(By.id("pwdSignin")).sendKeys(pwd);
		driver.findElement(By.id("btnsubmitdetails")).click();

		Alert alert = driver.switchTo().alert();

		String actMessage = alert.getText();

		alert.accept();

		String expectedMessage = message;

		if (actMessage.equals(expectedMessage)) {
			System.out.println("Test is Pass");
		} else
			System.out.println("Test is fail");

	}

	@DataProvider(name = "loginData")
	public Object[][] passData() {

		Object[][] data = new Object[2][3];
		data[0][0] = "Abhishek";
		data[0][1] = "Abhhsiffffdg@123";
		data[0][2] = "Success!";

		data[1][0] = "Radhe";
		data[1][1] = "Abh@123";
		data[1][2] = "Failed! please enter strong password";

		return data;

	}
}

