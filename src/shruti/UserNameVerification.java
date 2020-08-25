package shruti;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import shruti.predefinedActionspkg.PtrdefinedActions;

public class UserNameVerification extends PtrdefinedActions {
	
	WebDriver driver;
	
	void setUp(){
		driver = start();
	}
	
	void tearDown(){
		driver.close();
	}
	
	void verifyUserName(){
		driver.findElement(By.id("demotable")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		int rowSize = driver.findElements(By.xpath("//table[@id = 'table1']/tbody/tr")).size();
		for(int index=1; index<= rowSize; index++){
		String firstName = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[2]")).getText();
		String lastName = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[3]")).getText();
		String expectedUsername = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[4]")).getText();
		String actualUsername = (firstName.charAt(0)+lastName).toLowerCase();
		
		if(expectedUsername.equals(actualUsername)){
			System.out.println("Username exists: Testcase passed");
			}
		else{
			System.out.println("Username does not exists: Testcase Failed");
			}
		}
	}
	public static void main(String[] args) {
		UserNameVerification usernameVerification = new UserNameVerification();
		usernameVerification.setUp();
		usernameVerification.verifyUserName();
		
	}
	
	
}
