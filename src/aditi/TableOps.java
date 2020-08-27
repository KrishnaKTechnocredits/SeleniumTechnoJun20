/*
 * Assignment - 6 22 Aug 2020
 * 1) Find how many employees reporting to each manager ID. 
 * 2) Find maximum employees reporting to which manager ID.
*/

package aditi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import aditi.base.PredefinedActions;

public class TableOps extends PredefinedActions {
	WebDriver driver;

	void setUp() throws InterruptedException {
		driver = start();
		driver.findElement(By.xpath("//a[@id= 'demotable']")).click();
		Thread.sleep(3000);
	}

	LinkedHashMap<String, Integer> employeePerManager() {
		LinkedHashMap<String, Integer> empPerManager = new LinkedHashMap<String, Integer>();
		HashSet<String> uniqueEmpId = new HashSet<String>();
		int totalRows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for (int index = 1; index <= totalRows; index++) {
			String managerId = driver
					.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[4]"))
					.getText();
			String empId = driver
					.findElement(By.xpath("//table[@class=\"table table-striped\"]/tbody/tr[" + index + "]/td[2]"))
					.getText();
			if (uniqueEmpId.add(empId)) {
				if (empPerManager.containsKey(managerId))
					empPerManager.put(managerId, empPerManager.get(managerId) + 1);
				else
					empPerManager.put(managerId, 1);
			}
		}
		return empPerManager;
	}

	void findManagerWithMaxEmp() {
		HashMap<String, Integer> empPerManager = employeePerManager();
		Set<String> empCountKeySet = empPerManager.keySet();
		int max = 0;

		for (String empCount : empCountKeySet) {
			if (empPerManager.get(empCount) > max)
				max = empPerManager.get(empCount);
		}
		System.out.println("Manager with maximun reportees: ");
		for (String managerId : empCountKeySet) {
			if (empPerManager.get(managerId) == max)
				System.out.println(managerId + " -> " + empPerManager.get(managerId));
		}
	}

	public static void main(String[] args) {
		TableOps tableOps = new TableOps();
		try {
			tableOps.setUp();
			System.out.println("Employee Reporting to each manager: \n" + tableOps.employeePerManager());
			tableOps.findManagerWithMaxEmp();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			tableOps.driver.close();
		}
	}
}
