package nikhil;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import nikhil.base.PreDefinedActions;

public class UsernameValidation extends PreDefinedActions {
	WebDriver driver;
	
	void setUp(String url) {
		driver = start(url);
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
	}
	
	void tearDown() {
		driver.close();
	}
	
	void tabNavigation() {
		driver.findElement(By.xpath("//a[text()='Demo Tables']")).click();
	}
	
	void validate() {
		int maxRows = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();
		for(int index = 1; index <= maxRows; index++) {
			//Finding the expected username
			String firstName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td[2]")).getText();
			String lastName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td[3]")).getText();
			String expectedUsername = (firstName.charAt(0) + lastName).toLowerCase();
			
			//Fetching the actual username
			String actualUsername = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td[4]")).getText();
			
			//Comparing expected and actual username
			if(expectedUsername.equals(actualUsername)) {
				System.out.println("Username is as per format for : "+firstName+" "+lastName+" ---> "+actualUsername);
			}else {
				System.out.println("Username is NOT as per format for : "+firstName+" "+lastName);
			}
		}
	}
	
	public static void main(String[] args) {
		UsernameValidation usernameValidation = new UsernameValidation();
		usernameValidation.setUp("http://automationbykrishna.com/");
		usernameValidation.tabNavigation();
		usernameValidation.validate();
		usernameValidation.tearDown();
	}
}
