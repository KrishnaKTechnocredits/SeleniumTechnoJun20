package barkha;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import barkha_base.PredefinedFunctions;

public class WebTable_UserNameValidation extends PredefinedFunctions {

	WebDriver driver;

	void setUp(String URL) {
		driver = start(URL);
	}

	void userNameValidation() {
		HashSet<String> hashSet = new HashSet<String>();

		driver.findElement(By.xpath("//a[@id='demotable']")).click();
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);

		int studentCount = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();

		boolean flag = true;
		for (int index = 1; index <= studentCount; index++) {
			String actualUserName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[4]"))
					.getText();
			String firstName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[2]"))
					.getText();
			String lastName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[3]"))
					.getText();

			String expectedUserName = (firstName.charAt(0) + lastName).toLowerCase();

			if (actualUserName.equals(expectedUserName))
				hashSet.add(actualUserName);
			else
				flag = false;
		}
		if (flag == true) {
			System.out.println("Test Case is pass for usernames: " + hashSet);
		} else
			System.out.println("Test Case Fail");
	}

	void tearBreak() {
		driver.close();
	}

	public static void main(String[] args) {
		WebTable_UserNameValidation userName = new WebTable_UserNameValidation();
		userName.setUp("http://automationbykrishna.com/");
		userName.userNameValidation();
		userName.tearBreak();
	}
}