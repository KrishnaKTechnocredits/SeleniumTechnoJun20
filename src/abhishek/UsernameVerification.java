package abhishek;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import abhishek.base.PredefinedActions;

public class UsernameVerification extends PredefinedActions {
	
	WebDriver driver;
	
	void setUp(String url) {
		driver = start(url);
	}
	
	void verifyUserName() {
		driver.findElement(By.xpath("//a[@id ='demotable']")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		
		int rowSize = driver.findElements(By.xpath("//table[@id = 'table1']/tbody/tr")).size();
		for (int index = 1; index <= rowSize; index++) {
			String firstName = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[2]")).getText();
			String lastName = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]")).getText();
			String expectedUsername = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[4]")).getText();
			String actualUsername = (firstName.charAt(0) + lastName).toLowerCase();
			
			if (expectedUsername.equals(actualUsername))
				System.out.println("employee " + firstName +" " + lastName+" is present:  Test passed");
			else
				System.out.println("employee " + firstName +" " + lastName+" is not present: Test Failed");
		}
		driver.close();
	}
	
	public static void main(String[] args) {
		UsernameVerification usernameVerification = new UsernameVerification();
		usernameVerification.setUp("http://automationbykrishna.com/");
		usernameVerification.verifyUserName();
	}
}




