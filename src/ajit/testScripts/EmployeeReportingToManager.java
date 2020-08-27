/*Assignment - 7 22 Aug 2020

1) Find how many employees reporting to each manager ID. 
2) Find maximum employees reporting to which manager ID. */
package ajit.testScripts;

import java.util.LinkedHashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ajit.base.PredefinedActions;

public class EmployeeReportingToManager extends PredefinedActions {

	WebDriver driver;

	void setUp() {
		driver = start();
	}
	
	void navigateToMenu(String menu) throws InterruptedException  {
		driver.findElement(By.linkText(menu)).click();
		Thread.sleep(5000);
	}
	
	void printMaxEmpReportingToEachManager() {
		LinkedHashMap<String, Integer> employeeList = new LinkedHashMap<>();
		int rowSize = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr/td[4]")).size();
		for (int index = 1; index <= rowSize; index++) {
			String empManagerId = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[4]")).getText();

			if (employeeList.containsKey(empManagerId)) {
				employeeList.put(empManagerId, employeeList.get(empManagerId) + 1);
			} else {
				employeeList.put(empManagerId, 1);
			}
		}
		System.out.println("Count of employee reporting to each Manager\n===========================================");
		
		Set<String> keys = employeeList.keySet();
		String deptMangerId = "";
		int empCount = 0;
		for (String managerId : keys) {
			System.out.println(employeeList.get(managerId) + " employees are reporting to ManagerID:  " + managerId);
			if (empCount < employeeList.get(managerId)) {
				empCount = employeeList.get(managerId);
				deptMangerId = managerId;
			}
		}
		System.out.println("\nMaximum(" +empCount + ") employees reporting to ManagerID  "+ deptMangerId);
		driver.close();	
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {
		EmployeeReportingToManager employeeReportingToManager =new EmployeeReportingToManager();
		employeeReportingToManager.setUp();
		employeeReportingToManager.navigateToMenu("Demo Tables");
		employeeReportingToManager.printMaxEmpReportingToEachManager();
	}
}
