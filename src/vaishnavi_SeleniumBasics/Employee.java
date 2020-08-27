package vaishnavi_SeleniumBasics;

import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import vaishnavi_Base.PredefinedAction;

/* Assignment - 7 22 August 2020
1) Find how many employees reporting to each manager ID. 
2) Find maximum employees reporting to which manager ID. */

public class Employee extends PredefinedAction {
	WebDriver driver;

	void setUp() {
		driver = start();
	}

	void findCountOfReportingEmployees() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//a[text()='Demo Tables']")).click();
			Thread.sleep(2000);

			HashMap<String, Integer> empPerManagerIdCount = new HashMap<String, Integer>();
			int totalRows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
			for (int index = 1; index <= totalRows; index++) {
				String empManagerId = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[4]")).getText();
				// System.out.println(empManagerId);

				if (empPerManagerIdCount.containsKey(empManagerId)) {
					empPerManagerIdCount.put(empManagerId, empPerManagerIdCount.get(empManagerId) + 1);
				} else {
					empPerManagerIdCount.put(empManagerId, 1);
				}
			}
			System.out.println("Epmployee count reporting to each manager is as below: ");

			Set<String> keySet = empPerManagerIdCount.keySet();
			for (String name : keySet) {
				System.out.println("Manager Id: " + name + " - Employee Count: " + empPerManagerIdCount.get(name));
			}

			// Calling method to find maximum employees reporting to which manager ID
			findMaxReportingManager(empPerManagerIdCount);

		} catch (NoSuchElementException ne) {
			ne.printStackTrace();
		}finally {
			driver.close();
		}

	}

	void findMaxReportingManager(HashMap<String, Integer> empPerManagerIdCount) {
		int max = 0;
		Set<String> keySet = empPerManagerIdCount.keySet();
		for (String id : keySet) {
			if (empPerManagerIdCount.get(id) > max) {
				max = empPerManagerIdCount.get(id);
		 }
	   }
		
		System.out.println("Manager with maximum employee count reporting is: ");		
		for(String managerId : keySet) {
			if(empPerManagerIdCount.get(managerId) == max) {
				System.out.println("Manager Id is: " +managerId + " --> " +"With count: " +empPerManagerIdCount.get(managerId));
			}
		}
	}

	public static void main(String[] args) {
		Employee employee = new Employee();
		employee.setUp();
		try {
			employee.findCountOfReportingEmployees();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
