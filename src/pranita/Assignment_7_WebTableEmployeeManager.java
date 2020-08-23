/* Assignment - 7* 22 Aug 2020

1) Find how many employees reporting to each manager ID. 
2) Find maximum employees reporting to which manager ID.

 */
package pranita;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pranita.basic.PredefinedFunctions;

public class Assignment_7_WebTableEmployeeManager extends PredefinedFunctions {
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	public static void main(String[] args) {
		Assignment_7_WebTableEmployeeManager assignment_7_WebTableEmployeeManager = new Assignment_7_WebTableEmployeeManager();
		assignment_7_WebTableEmployeeManager.setUp("http://automationbykrishna.com/");
		assignment_7_WebTableEmployeeManager.numbOfEmployeePerManager();
	}

	// 1) Find how many employees reporting to each manager ID.
	void numbOfEmployeePerManager() {
		driver.findElement(By.linkText("Demo Tables")).click();
		// Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		HashMap<String, Integer> employeePerManager = new HashMap<String, Integer>();
		HashSet<String> empIDUnique = new HashSet<String>();
		int totalRows = driver.findElements(By.xpath("//table[@class=\"table table-striped\"]/tbody/tr")).size();
		for (int index = 1; index <= totalRows; index++) {
			String managerID = driver
					.findElement(By.xpath("//table[@class=\"table table-striped\"]/tbody/tr[" + index + "]/td[4]"))
					.getText();
			String empId = driver
					.findElement(By.xpath("//table[@class=\"table table-striped\"]/tbody/tr[" + index + "]/td[2]"))
					.getText();
			if (empIDUnique.add(empId)) {
				if (employeePerManager.containsKey(managerID)) {
					employeePerManager.put(managerID, employeePerManager.get(managerID) + 1);
				} else {
					employeePerManager.put(managerID, 1);
				}
			}
		}
		System.out.println("No of employees reporting to each Manager ID " + employeePerManager);

		// 2) Find maximum employees reporting to which manager ID.
		Set<String> managerIDdetails = employeePerManager.keySet();
		String reportingManager = "";
		Integer maxEmployeeReporting = 0;
		System.out.println("Managers with maximum reporting employees are : ");
		for (String managerNos : managerIDdetails) {
			if (employeePerManager.get(managerNos) >= maxEmployeeReporting) {
				maxEmployeeReporting = employeePerManager.get(managerNos);
				reportingManager = managerNos;
				System.out.println(reportingManager + " ---> " + maxEmployeeReporting);
			}
		}
	}
}
