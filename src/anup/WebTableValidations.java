package anup;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import anup.basics.PredefinedActions;
//1) Find how many employees reporting to each manager ID. 
//2) Find maximum employees reporting to which manager ID.

public class WebTableValidations extends PredefinedActions {

	WebDriver driver;

	void setup() {
		driver = start();
	}

	void printcol() throws InterruptedException {
		driver.findElement(By.xpath("//a[@id='demotable']")).click();
		Thread.sleep(2000);

		// 1) Find how many employees reporting to each manager ID.
		String managerid;
		HashMap<String, Integer> managerDetails = new HashMap<String, Integer>();
		int rows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for (int index = 1; index <= rows; index++) {
			managerid = driver
					.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[4]"))
					.getText();
			if (managerDetails.containsKey(managerid)) {
				managerDetails.put(managerid, managerDetails.get(managerid) + 1);
			} else {
				managerDetails.put(managerid, 1);
			}
		}
		System.out.println("The number of employees reporting to each manager ID" + managerDetails);

		// 2) Find maximum employees reporting to which manager ID.
		int max = 0;
		String temp = null;

		for (String managernumber : managerDetails.keySet()) {
			if (managerDetails.get(managernumber) > max) {
				max = managerDetails.get(managernumber);
				temp = managernumber;
			}
		}
		System.out.println("The number of maximum employees reporting to manager ID: " + temp + " is : " + max);
	}

	void sessionClose() {
		driver.close();
	}

	public static void main(String[] args) throws InterruptedException {
		WebTableValidations webTableValidations = new WebTableValidations();
		webTableValidations.setup();
		webTableValidations.printcol();
		webTableValidations.sessionClose();
	}
}
