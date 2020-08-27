/* Validate all username from Employee basic information table.
 * i.e. Username= mkanani -> First letter of first name in lowercase + last name 
 * */
package aditi;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import aditi.base.PredefinedActions;

public class UserNameValidation extends PredefinedActions {
	WebDriver driver;

	void setUp() {
		driver = start("http://automationbykrishna.com");
	}

	void navigateToDemoTable() {
		driver.findElement(By.linkText("Demo Tables")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
	}

	void validateUserName() {
		int rowCount = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();
		//boolean flag = false;
		for (int index = 1; index <= rowCount; index++) {
			String firstName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[2]"))
					.getText();
			String lastName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[3]"))
					.getText();
			String actualUserName = (firstName.charAt(0) + lastName).toLowerCase();
			String ExpectedUserName = driver
					.findElement(By.xpath("//table[@id='table1']//tbody/tr[" + index + "]/td[4]")).getText();

			if (!actualUserName.equals(ExpectedUserName)) {
				System.out.println("Test Fail -> For User Name : " + actualUserName + " - First Name: " + firstName
						+ " || Last Name : " + lastName);
				//flag = false;
			} else
				System.out.println("Test Pass -> For User Name : " + actualUserName + " - First Name: " + firstName
						+ " || Last Name : " + lastName);
		}
//		if(flag)
//			System.out.println("Test Pass: For all User names");
	}

	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) {
		UserNameValidation userNameValidation = new UserNameValidation();

		userNameValidation.setUp();
		userNameValidation.navigateToDemoTable();
		userNameValidation.validateUserName();
		userNameValidation.tearDown();

	}
}
