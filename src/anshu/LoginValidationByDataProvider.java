package anshu;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import anshu.base.PredefinedProperty;

public class LoginValidationByDataProvider extends PredefinedProperty {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setUp() {
		driver = start();
		wait = new WebDriverWait(driver, 20);
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

	@Test(dataProvider = "logindataprovider")
	public void loginValidation(String userName, String passWord, String exptdResult) {
		try {
			driver.findElement(By.xpath("//a[text()='Registration']")).click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='unameSignin']"))))
					.sendKeys(userName);
			driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys(passWord);
			driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();

			Alert alert = driver.switchTo().alert();
			String actualText = alert.getText();
			alert.accept();

			Assert.assertEquals(actualText, exptdResult);
		} catch (NoSuchElementException ne) {
			ne.printStackTrace();
		}

	}

	@DataProvider(name = "logindataprovider")
	public String[][] dataProvider() {
		String data[][] = new String[3][3];

		data[0][0] = "anshukashyap12";
		data[0][1] = "akashyap123#";
		data[0][2] = "Success!";

		data[1][0] = "aaaaaaa";
		data[1][1] = "11";
		data[1][2] = "Failed! please enter strong password";

		data[2][0] = "anshuSharma";
		data[2][1] = "akashyap123#";
		data[2][2] = "Success!";

		return data;
	}
}
