package kartikey;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import kartikey.base.PredDefindActions;

public class VerifyUserNme extends PredDefindActions {
	WebDriver driver;

	private void startup(String url) {
		driver = start(url);
	}

	void waitimplicitly(long time) {// for now hardcoded to miliseconds
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.MILLISECONDS);
	}

	void teardown() {
		driver.close();
	}

	boolean verifyUserID() {
		driver.findElement(By.xpath("//a[@id='demotable']")).click();
		waitimplicitly(3000);
		boolean ispass = true;
		int totalRows = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[4]")).size();
		for (int index = 1; index <= totalRows; index++) { // charAt()
			String expected = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[4]"))
					.getText();
			String actual = (driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[2]"))
					.getText().substring(0, 1)
					+ driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td[3]")).getText()).toLowerCase();
			if (!expected.equals(actual)) {
				ispass = false;
				System.out.println("Test is failed for username: " + actual);
			}
		}
		return ispass;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please provide the url");
		String url = scanner.next();
		VerifyUserNme verifyUserNme = new VerifyUserNme();
		verifyUserNme.startup(url);	
		if(verifyUserNme.verifyUserID()== true)
			System.out.println("Test is passed");
		else
			System.out.println("Test is failed");
		verifyUserNme.teardown();
		// http://www.automationbykrishna.com
	}

}
