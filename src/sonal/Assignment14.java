package sonal;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Assignment14 {

	WebDriver driver;

	public WebDriver Start(String url) {

		System.setProperty("webdriver.chrome.driver", "E:/NewWorkSpace/SeleniumBasics1/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		return driver;
	}

	@BeforeClass
	public void setUp() {

		driver = Start("http://automationbykrishna.com");
	}

	@AfterClass
	public void tearDown() {

		driver.close();
	}

	@Test(dataProvider = "data1")
	public void dataDrivenDemo(String name, String pwd, String message) {

		driver.findElement(By.id("registration2")).click();

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("unameSignin")));

		driver.findElement(By.id("unameSignin")).sendKeys(name);
		driver.findElement(By.id("pwdSignin")).sendKeys(pwd);
		driver.findElement(By.id("btnsubmitdetails")).click();

		Alert alert = driver.switchTo().alert();

		String actMessage = alert.getText();

		alert.accept();

		String expMessage = message;

		if (actMessage.equals(expMessage)) {
			System.out.println("Test is Pass");
		} else
			System.out.println("Test is fail");

	}

	@DataProvider(name = "data1")
	public Object[][] passData() {

		Object[][] data = new Object[2][3];
		data[0][0] = "Sonal";
		data[0][1] = "Abdghjiuty";
		data[0][2] = "Success!";

		data[1][0] = "Sonal";
		data[1][1] = "Ab";
		data[1][2] = "Failed! please enter strong password";

		return data;

	}

}

