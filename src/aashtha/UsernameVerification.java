package aashtha;

import aashtha.base.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsernameVerification extends PredefinedActions{
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	void tearDown() {
		driver.close();
	}
	
	void navigateToDemoTable() throws InterruptedException {
		driver.findElement(By.id("demotable")).click();
		Thread.sleep(2000);
	}
	
	void verifyUsername() {
		int totalRecords = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();
		for(int index = 1; index <= totalRecords; index++) {
			String firstName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+ index +"]/td[2]")).getText();
			String lastName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[3]")).getText();
			String actualUsername = (firstName.charAt(0) + lastName).toLowerCase();
			if(actualUsername.equals(driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[4]")).getText()))
				System.out.println("For Employee - " + firstName + " " + lastName + " - Username given in table is CORRECT");
			else
				System.out.println("For Employee - " + firstName + " " + lastName + " - Username given in table is NOT correct");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		UsernameVerification usernameVerification = new UsernameVerification();
		usernameVerification.setUp("http://automationbykrishna.com/");
		usernameVerification.navigateToDemoTable();
		usernameVerification.verifyUsername();
		usernameVerification.tearDown();
	}
}
