package aavruti;

import aavruti.base.PredefinedActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsernameVerification extends PredefinedActions{

	WebDriver driver;

	void launchBrowser() {
		driver = start("http://automationbykrishna.com/");
	}

	void verifyUserName() {
		driver.findElement(By.linkText("Demo Tables")).click();

		boolean flag = false;
		int totalRows = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();		

		for(int index=1;index<=totalRows;index++) {
			String rowDynamicXpath = "//table[@id='table1']/tbody/tr[" + index + "]";
			String firstName = driver.findElement(By.xpath(rowDynamicXpath + "/td[2]")).getText();
			String lastName = driver.findElement(By.xpath(rowDynamicXpath + "/td[3]")).getText();
			String userName = driver.findElement(By.xpath(rowDynamicXpath + "/td[4]")).getText();
			String tempUserName = (firstName.charAt(0) + lastName).toLowerCase();
			
			if(!tempUserName.equals(userName)) {
				System.out.println("UserName is not getting matched!! \n Original Username --> " + tempUserName + " Incorrect UserName --> " + userName);
				flag = false;
			}			
		}
		if(flag)
			System.out.println("All the usernames available in Table are correct");
	}

	void closeBrowser() {
		driver.quit();
	}

	public static void main(String[] args) {
		UsernameVerification usernameVerification = new UsernameVerification();
		usernameVerification.launchBrowser();
		usernameVerification.verifyUserName();
		usernameVerification.closeBrowser();
	}
}
