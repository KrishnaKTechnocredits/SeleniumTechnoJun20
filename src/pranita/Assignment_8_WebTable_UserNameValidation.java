/*
 * Webtable User Name validation 
 */
package pranita;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pranita.basic.PredefinedFunctions;

public class Assignment_8_WebTable_UserNameValidation extends PredefinedFunctions {
	WebDriver driver;

	void setUp() {
		driver = start();
	}

	void validateUserName() {
		driver.findElement(By.linkText("Demo Tables")).click();
		// driver.findElement(By.id("demotable")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

		int rows = driver.findElements(By.xpath("//table[@id = 'table1']/tbody/tr")).size();

		for (int index = 1; index <= rows; index++) {
			String firstName = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[2]")).getText();
			String lastName = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]")).getText();
			String expectedUserName = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[4]")).getText();
			String actualUserName = (firstName.charAt(0) + lastName).toLowerCase();

			if (expectedUserName.equals(actualUserName)) {
				System.out.println("Username" + actualUserName + " is validated");
			} else {
				System.out.println("Username " + actualUserName + "is not validated");
			}
		}
		driver.close();
	}

	public static void main(String[] args) {
		Assignment_8_WebTable_UserNameValidation assignment_8_WebTable_UserNameValidation = new Assignment_8_WebTable_UserNameValidation();
		assignment_8_WebTable_UserNameValidation.setUp();
		assignment_8_WebTable_UserNameValidation.validateUserName();
		
	}

}
