/*Assignment - 7 22 Aug 2020

1) Find how many employees reporting to each manager ID. 
2) Find maximum employees reporting to which manager ID.*/
package amita;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import amita.base.PredefinedActions;

public class EmployeeManagerWebTable extends PredefinedActions {

	WebDriver driver;

	void setUp() {
		driver = start();
	}

	// 1) Find how many employees reporting to each manager ID.
	void employeePerMananger() throws InterruptedException {
		driver.findElement(By.linkText("Demo Tables")).click();
		Thread.sleep(2000);

		HashMap<String, Integer> employeePerManager = new HashMap<String, Integer>();
		HashSet<String> empIDUnique = new HashSet<String>();
		int totalRows = driver.findElements(By.xpath("//table[@class=\"table table-striped\"]/tbody/tr")).size();
		for (int index = 1; index <= totalRows; index++) {
			String managerID = driver.findElement(By.xpath("//table[@class=\"table table-striped\"]/tbody/tr[" + index + "]/td[4]")).getText();
			String empId = driver.findElement(By.xpath("//table[@class=\"table table-striped\"]/tbody/tr["+index+"]/td[2]")).getText();
			if(empIDUnique.add(empId)) {
				if (employeePerManager.containsKey(managerID)) {
					employeePerManager.put(managerID, employeePerManager.get(managerID) + 1);
				} else {
					employeePerManager.put(managerID, 1);
				}
			}
		}
		System.out.println("No of employees reporting to each Manager ID " + employeePerManager);
		//method to call //2) Find maximum employees reporting to which manager ID.
		maxEmployeesReportToManager(employeePerManager);		
	}
	
	//2) Find maximum employees reporting to which manager ID.
	void maxEmployeesReportToManager(HashMap<String, Integer> employeePerManager) {
		Set<String> managerIDdetails = employeePerManager.keySet();
		String reportingManager = "";
		Integer maxEmployeeReporting = 0;
		System.out.println("Managers with maximum reporting employees are : ");
		for (String managerNos : managerIDdetails) {
			if(employeePerManager.get(managerNos) >= maxEmployeeReporting) {
				maxEmployeeReporting = employeePerManager.get(managerNos);
				reportingManager = managerNos;
				System.out.println(reportingManager+ " ---> "+ maxEmployeeReporting);
			}		
		}			
	}

	void sessionClose() {
		driver.close();
	}

	public static void main(String[] args) {
		EmployeeManagerWebTable employeeManagerTable = new EmployeeManagerWebTable();
		employeeManagerTable.setUp();
		try {			
			employeeManagerTable.employeePerMananger();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		employeeManagerTable.sessionClose();	
	}
}
