package madhura;

//Assignment 8: Automationbykrishna -> demotables -> table 1
import java.util.LinkedHashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import madhura.base.PredefinedActions;

public class UsernameVerification extends PredefinedActions {
	WebDriver driver;

	void setUp() {
		driver = start();
	}

	void navigateToDemoTables() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Demo Tables"))).click();
	}

	@Test
	void verificationOfUsername() {
		setUp();
		navigateToDemoTables();
		Set<String> usernamesSet = new LinkedHashSet<String>();
		boolean flag = true;
		int rows = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();
		for (int index = 1; index <= rows; index++) {
			String firstName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[2]"))
					.getText();
			String lastName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[3]"))
					.getText();

			String expectedUname = firstName.charAt(0) + lastName;
			expectedUname = expectedUname.toLowerCase();

			String actualUName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[4]"))
					.getText();
			if (actualUName.equals(expectedUname)) {
				usernamesSet.add(expectedUname);
			} else {
				flag = false;
				break;
			}
		}
		if (flag == true) {
			System.out.println("Username verification -> Passed");
		} else
			System.out.println("Username verification -> Failed");
		driver.quit();
	}
}