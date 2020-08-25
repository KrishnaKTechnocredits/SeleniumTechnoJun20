package raj;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import raj.selenium.base.PredefinedActions;

public class UserNameValidation extends PredefinedActions {
	WebDriver driver;

	void setup() {
		driver = start("http://automationbykrishna.com/") ;	
		driver.manage().window().maximize();
	}

	void validateUserNameInTable() {
		boolean flag = false;
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//a[text()='Demo Tables']")).click();

		int totalRowsOfTable = driver.findElements(By.xpath("//table[@id='table1']//tbody/tr")).size();

		for(int index = 1 ; index <= totalRowsOfTable ; index++ ) {
			String fName = driver.findElement(By.xpath("//table[@id='table1']//tbody/tr[" + index + "]/td[2]")).getText();
			String lName = driver.findElement(By.xpath("//table[@id='table1']//tbody/tr[" + index + "]/td[3]")).getText();
			String userName = driver.findElement(By.xpath("//table[@id='table1']//tbody/tr[" + index + "]/td[4]")).getText();
			String expectedUserName = (fName.charAt(0) + lName).toLowerCase() ;
			if(!expectedUserName.equals(userName)) {
				System.out.println("UserName for user " +fName+ " " +lName+ " doesn't matched...!!");
				flag = false; 
			}
			/* if (userName.equals(expectedUserName)) {
				System.out.println("Test Pass: );
			} else {
				System.out.println("Test Fail: );
			} */
		}
		if(flag = true) {
			System.out.println("UserName validation Test Passed...!!");
		}	
	}	

	public static void main(String[] args) {
		UserNameValidation userNameValidate = new UserNameValidation();
		userNameValidate.setup();
		userNameValidate.validateUserNameInTable();
	}
}
