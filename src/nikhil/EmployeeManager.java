/*
Assignment - 6 22 Aug 2020

1) Find how many employees reporting to each manager ID.
2) Find maximum employees reporting to which manager ID.
*/

package nikhil;

import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import nikhil.base.PreDefinedActions;

public class EmployeeManager extends PreDefinedActions {
	WebDriver driver;
	
	void setUp() {
		driver = start();
	}
	
	void breakDown() {
		driver.close();
	}
	
	void tabNavigation() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Demo Tables']")).click();
		Thread.sleep(500);
	}
	
	//Getting data about managers and reporting employees into HashTable
	HashMap<String, Integer> getData() {
		HashMap<String, Integer> empManager = new HashMap<String, Integer>();
		
		int totalRows = driver.findElements(By.xpath("//table[@class = 'table table-striped']/tbody/tr")).size();
		for(int index=1; index <= totalRows; index++) {
			String mngrId = driver.findElement(By.xpath("//table[@class = 'table table-striped']/tbody/tr["+index+"]/td[4]")).getText();
			
			if(empManager.containsKey(mngrId)) {
				empManager.put(mngrId, empManager.get(mngrId)+1);
			}else {
				empManager.put(mngrId, 1);
			}
		}
		return empManager;
	}
	
	//Displaying the management structure of the org.
	void displayMgmtStructure(HashMap<String, Integer> empManager) {
		System.out.println("Info about managers and No. of reportees:-\nManager ID    No. of Reportees");
		Set<String> mngrs = empManager.keySet();
		int maxReportee = 0;
		String mngrMax = "";
		for(String mngr: mngrs) {
			System.out.println(mngr+"         "+empManager.get(mngr));
			if((int)empManager.get(mngr) > maxReportee) {
				maxReportee = (int)empManager.get(mngr);
				mngrMax = mngr;
			}
		}
		System.out.println("\nManager with max reporting employees:-\n"+mngrMax+" ---> "+maxReportee);
	}
	
	public static void main(String[] args) throws InterruptedException {
		EmployeeManager employeeManager = new EmployeeManager();
		employeeManager.setUp();
		employeeManager.tabNavigation();
		HashMap<String, Integer> empManager = employeeManager.getData();
		employeeManager.displayMgmtStructure(empManager);
		employeeManager.breakDown();
	}
}
