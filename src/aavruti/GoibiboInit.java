package aavruti;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import aavruti.base.PredefinedActions;
import aavruti.utility.PropertyFileOperation;

public class GoibiboInit  extends PredefinedActions {

	private static WebDriver driver;
	private static WebDriverWait wait;
	private static PropertyFileOperation propRead;

	public static WebDriver getDriver() {
        return driver;
    }
	
	public static WebDriverWait getDriverWait() {
        return wait;
    }
	
	public static PropertyFileOperation getProperty() {
        return propRead;
    }	
	
	@BeforeSuite
	void launchBrowser() throws IOException{
		driver = start("https://www.goibibo.com/");
		wait = new WebDriverWait(driver,150);
		propRead = new PropertyFileOperation(".\\src\\aavruti\\config\\GoibiboLocators.properties");
	}
	
	@AfterSuite
	void closeBrowser() {
		driver.quit();
	}
}
