package mahesh;

import mahesh.base.PredefinedActions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebTableUserNameValidation extends PredefinedActions {
	WebDriver driver;
	
	void setUp() {
		driver = start();
	}
	
	void userNameValidation() {
		driver.findElement(By.xpath("//a[@id='demotable']")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MICROSECONDS);
		int countOfRow = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();
		for (int index=1;index<=countOfRow;index++) {
			String firstName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td[2]")).getText();
			String lastName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td[3]")).getText();
			String userName = firstName.toLowerCase().charAt(0) + lastName.toLowerCase();
			if (!userName.equals(driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td[4]")).getText()))
				System.out.println("Match_Fail: UserName is not matching for user with first name: " + firstName + " and Last name: " + lastName);
			else
				System.out.println("Match_Pass: UserName is matching for user with first name: " + firstName + " and Last name: " + lastName);
		}
	}
	
	public static void main(String[] args) {
		WebTableUserNameValidation webTableUserNameValidation = new WebTableUserNameValidation();
		webTableUserNameValidation.setUp();
		webTableUserNameValidation.userNameValidation();
		webTableUserNameValidation.driver.close();
	}
}
