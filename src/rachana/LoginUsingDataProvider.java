package rachana;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rachana.base.LocateElements;
import rachana.base.PredefinedActions;
import rachana.navigation.HomePageMenuNavigation;

public class LoginUsingDataProvider extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	void SetUp() {
		this.driver = start();
		wait = new WebDriverWait(driver, 15);
	}
	
	@BeforeMethod
	void navigateToMenu() {
		HomePageMenuNavigation menu = new HomePageMenuNavigation(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='collapse navbar-collapse']")));
		menu.navigateToHomePageMenu("Registration").click();
	}	
	
	@Test(dataProvider="logindataprovider")
	public void loginTest(String username ,String password,String expectedResult) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("unameSignin")));
		LocateElements locateelement = new LocateElements(driver);
		locateelement.findElementBy("id", "unameSignin").sendKeys(username);
		locateelement.findElementBy("id", "pwdSignin").sendKeys(password);
		locateelement.findElementBy("xpath", "//button[@id='btnsubmitdetails']").click();
		//handle alert
		Alert alert = driver.switchTo().alert();
		String actualResult = alert.getText();
		alert.accept();
		Assert.assertEquals(actualResult, expectedResult);
		//again refresh with blank input
		navigateToMenu();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("unameSignin")));
	}
	
	@DataProvider(name="logindataprovider")
	public Object[][] dataprovider(){
		String[][]data = new String[3][3];
		//first set of data
		data[0][0]="Rach14";
		data[0][1]="rachana14";
		data[0][2]="Success!";
		//second set of data	
		data[1][0]="Rach20";
		data[1][1]="rachana20";
		data[1][2]="Success!";
		//third set of data
		data[2][0]="kpRach";
		data[2][1]="sdfsfsdf";
		data[2][2]="Failed! please enter strong password";
		
		return data;
	}
	
	@AfterClass
	void tearDown() {
		driver.close();
	}
}
