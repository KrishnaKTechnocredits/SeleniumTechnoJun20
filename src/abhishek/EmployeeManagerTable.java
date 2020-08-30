package abhishek;

import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import abhishek.base.PredefinedActions;

/*Assignment - 7 22 Aug 2020
	1) Find how many employees reporting to each manager ID. 
	2) Find maximum employees reporting to which manager ID.*/

public class EmployeeManagerTable extends PredefinedActions {
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	void navigateToDemoTable() throws InterruptedException {
		driver.findElement(By.linkText("Demo Tables")).click();
		Thread.sleep(3000);
	}

	// how many employees reporting to each manager ID
	void numOfEmployeeToEachManager() {
		HashMap<String, Integer> managerIdEmpMap = new HashMap<String, Integer>();

		int totalManagerscount = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		// System.out.println(totalManagers);
		for (int index = 1; index <= totalManagerscount; index++) {
			String managerId = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[4]")).getText();

			if (managerIdEmpMap.containsKey(managerId)) {
				managerIdEmpMap.put(managerId, managerIdEmpMap.get(managerId) + 1);
			} else {
				managerIdEmpMap.put(managerId, 1);
			}
		}

		Set<String> keys = managerIdEmpMap.keySet();
		System.out.println("Number of employees reporting to each manager ID : ");
		int maxKey = 0;
		String mngId = "";
		for (String mapKey : keys) {
			if (managerIdEmpMap.get(mapKey) > 1) {
				System.out.println(mapKey + " : " + managerIdEmpMap.get(mapKey));
			}
			// maximum employees reporting to which manager ID
			if (maxKey < managerIdEmpMap.get(mapKey)) {
				maxKey = managerIdEmpMap.get(mapKey);
				mngId = mapKey;
			}
		}
		System.out.println("Maximum employee reporting to ManagerId : ");
		System.out.println(mngId + " : " + managerIdEmpMap.get(mngId));

		driver.close();
	}

	public static void main(String[] args) throws InterruptedException {
		EmployeeManagerTable employeeManagerTable = new EmployeeManagerTable();
		employeeManagerTable.setUp("http://automationbykrishna.com/");
		employeeManagerTable.navigateToDemoTable();
		employeeManagerTable.numOfEmployeeToEachManager();
	}
}
