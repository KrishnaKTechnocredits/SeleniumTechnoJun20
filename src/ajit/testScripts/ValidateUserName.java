package ajit.testScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ajit.base.PredefinedActions;

public class ValidateUserName extends PredefinedActions {

	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}
	
	void navigateToDemoTable() {
		driver.findElement(By.linkText("Demo Tables")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
	}
	
	
	void userNameValidation() {
		int rowCount = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();

		for (int index = 1; index <= rowCount; index++) {
			String firstName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[2]")).getText();
			String lastName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[3]")).getText();
			String actualUserName = (firstName.charAt(0) + lastName).toLowerCase();
			String expectedUserName = driver.findElement(By.xpath("//table[@id='table1']//tbody/tr[" + index + "]/td[4]")).getText();
			
			if(actualUserName.equals(expectedUserName))
				System.out.println("Expected UserName: "+expectedUserName +" ===Actual UserName: "+actualUserName + " ====Result: UserName Matched Test Passed. ");
			else
				System.out.println("Expected UserName: "+expectedUserName +" ===Actual UserName: "+actualUserName + " ====Result: UserName MisMatched Test Failed. ");
		}	
		driver.close();
	}

	public static void main(String[] args) {
		ValidateUserName validateUserName=new ValidateUserName();
		validateUserName.setUp("http://automationbykrishna.com/");
		validateUserName.navigateToDemoTable();
		validateUserName.userNameValidation();
	}

}
