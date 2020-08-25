package anup;
//Validate the username is correct in demo table

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import anup.basics.PredefinedActions;

public class UserNameValidation extends PredefinedActions {
	WebDriver driver;
	void setup() {
		driver = start();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}
	void userNameValidationwebTable() {
		driver.findElement(By.linkText("Demo Tables")).click();
		int rows = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();
		boolean flag = true;
		for(int index =1;index<=rows;index++) {
			String firstName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+ index +"]/td[2]")).getText();
			String lastName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+ index +"]/td[3]")).getText();
			String actualuserName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+ index +"]/td[4]")).getText();
			String expectedUserName = (firstName.charAt(0)+lastName).toLowerCase();
			if(!expectedUserName.equals(actualuserName)) {
				System.out.println("The user name is incorrect "+ actualuserName);
				flag = false;
			}
		}
		if(flag) {
			System.out.println("Test Pass: All the usernames are correct");
		}
		
	}
	void closeBrowser() {
		driver.close();
	}
	public static void main(String[] args) {
		UserNameValidation userNameValidation = new UserNameValidation();
		userNameValidation.setup();
		userNameValidation.userNameValidationwebTable();
		userNameValidation.closeBrowser();
	}
}
