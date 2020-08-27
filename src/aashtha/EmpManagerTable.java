package aashtha;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import aashtha.base.*;

/*Assignment - 6 - 22 Aug 2020
*1) Find how many employees reporting to each manager ID. 
*2) Find maximum employees reporting to which manager ID.
**/

public class EmpManagerTable extends PredefinedActions {
	WebDriver driver;
	HashMap<String, Integer> mapEmpManager = new HashMap<String, Integer>();
	
	//initializes the WebDriver and opens the browser window with passed url
	void setUp(String url) {
		driver = start(url);
	}
	
	//Closes the browser window
	void tearDown() {
		driver.close();
	}
	
	//Navigates to Demo table page
	void navigateToDemoTable() throws InterruptedException {
		driver.findElement(By.xpath("//a[@id='demotable']")).click();
		Thread.sleep(2000);
	}

	// 1) Find how many employees reporting to each manager ID.
	void findEmpPerManager() {
		// Below HashSet is created to check if the employee is unique
		HashSet<String> uniqueEmp = new HashSet<String>();
		int totalRecords = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for (int index = 1; index <= totalRecords; index++) {
			String managerId = driver
					.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[4]"))
					.getText();
			String empID = driver
					.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[2]"))
					.getText();
			if (uniqueEmp.add(empID)) {
				if (mapEmpManager.containsKey(managerId)) {
					mapEmpManager.put(managerId, mapEmpManager.get(managerId) + 1);
				} else {
					mapEmpManager.put(managerId, 1);
				}
			}
		}
		// Below logic is written to display the Employees-Manager in proper format
		Set<String> managerKeySet = mapEmpManager.keySet();
		for (String currentManager : managerKeySet) {
			System.out.println("   " + mapEmpManager.get(currentManager) + " - Employees works under Manager with ID: "
					+ currentManager);
		}
	}

	// 2) Find maximum employees reporting to which manager ID.
	void findMaxEmployees() {
		int max = 0;
		Set<String> managerKeySet = mapEmpManager.keySet();
		for (String currentManager : managerKeySet) {
			if (mapEmpManager.get(currentManager) > max) {
				max = mapEmpManager.get(currentManager);
			}
		}
		// Below loop is written to display the Managers having max employees
		for (String currentManager : managerKeySet) {
			if (mapEmpManager.get(currentManager) == max) {
				System.out.println(
						"   " + "Manager with ID: " + currentManager + " has maximum number of Employees i.e. " + max);
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		EmpManagerTable empManagerTable = new EmpManagerTable();
		empManagerTable.setUp("http://automationbykrishna.com/");
		empManagerTable.navigateToDemoTable();
		System.out.println("\n1) Find how many employees reporting to each manager ID?");
		empManagerTable.findEmpPerManager();
		System.out.println("\n2) Find maximum employees reporting to which manager ID?");
		empManagerTable.findMaxEmployees();
		empManagerTable.tearDown();
	}
}
