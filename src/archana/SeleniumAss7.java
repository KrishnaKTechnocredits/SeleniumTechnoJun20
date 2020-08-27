package archana;

import java.util.HashMap;

import org.openqa.selenium.By;

public class SeleniumAss7 extends PredefinedActions {
	void findEmpReportingToManager() throws InterruptedException {

		driver.findElement(By.xpath("//a[@id='demotable']")).click();
		Thread.sleep(2000);

		String managerid;
		HashMap<String, Integer> details = new HashMap<String, Integer>();
		int rows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for (int index = 1; index <= rows; index++) {
			managerid = driver
					.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[4]"))
					.getText();
			if (details.containsKey(managerid)) {
				details.put(managerid, details.get(managerid) + 1);
			} else {
				details.put(managerid, 1);
			}
		}
		System.out.println("Employees reporting to each manager ID" + details);

		int max = 0;
		String temp = null;

		for (String managernumber : details.keySet()) {
			if (details.get(managernumber) > max) {
				max = details.get(managernumber);
				temp = managernumber;
			}
		}
		System.out.println("Maximum employees count reporting to manager ID: " + temp + " is : " + max);
	}

	public static void main(String[] args) {
		SeleniumAss7 assignment7 = new SeleniumAss7();
		driver = start("http://automationbykrishna.com");
		try {
			assignment7.findEmpReportingToManager();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}