package kartikey;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import kartikey.base.PredDefindActions;

public class VerifyAlertUsingPropertyFile extends PredDefindActions {
	private WebDriver driver;

	void startup(String url) {
		driver = start(url);
	}

	void tearDown() {
		driver.close();
	}

	void waitExplicitly(String xpath, int timeSecds) {
		WebDriverWait wait = new WebDriverWait(driver, timeSecds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));//
	}

	boolean verifyLogin() throws IOException {
		driver.findElement(By.xpath("//a[@id='basicelements']")).click();
		waitExplicitly("//input[@id='UserFirstName']", 15);
		WebElement firstName=	driver.findElement(By.xpath("//input[@id='UserFirstName']"));
		firstName.sendKeys(getDataFromPropertyFile("fName"));
		WebElement lastName=	driver.findElement(By.xpath("//input[@id='UserLastName']"));
		lastName.sendKeys(getDataFromPropertyFile("lName"));
		WebElement companyName=	driver.findElement(By.xpath("//input[@id='UserCompanyName']"));
		companyName.sendKeys(getDataFromPropertyFile("CompanyName"));
		driver.findElement(By.xpath("//div[@class='col-lg-6'][1]//button[@class='btn btn-primary']")).click();
		
		String textFromAlert= driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();	
		String dataprovided=firstName.getAttribute("value")+" and "+lastName.getAttribute("value")+" and "+companyName.getAttribute("value");
	//	System.out.println(textFromAlert);
	//	System.out.println(dataprovided);
		if(dataprovided.equals(textFromAlert))
			return true;
		else
			return false;
	}

	String getDataFromPropertyFile(String data) throws IOException {
		File file = new File(".\\src\\kartikey\\config\\BasicElementsData.properties");
		FileInputStream input = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(input);
		return prop.getProperty(data);
	}

	public static void main(String[] args) throws IOException {
		String url="http://www.automationbykrishna.com";
		VerifyAlertUsingPropertyFile verifyAlertUsingPropertyFile= new VerifyAlertUsingPropertyFile();
		verifyAlertUsingPropertyFile.startup(url);
		if(verifyAlertUsingPropertyFile.verifyLogin()== true)
			System.out.println("Test is passed");
		else
			System.out.println("Test is failed");
		verifyAlertUsingPropertyFile.tearDown();

	}
}
