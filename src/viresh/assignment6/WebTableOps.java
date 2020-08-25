/*
 Assignment - 6 22 Aug 2020
1) Find how many employees reporting to each manager ID. 
2) Find maximum employees reporting to which manager ID.
 */
package viresh.assignment6;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import viresh.base.PredefinedActions;

public class WebTableOps extends PredefinedActions {

	WebDriver driver;
	LinkedHashMap<String, Integer> mgr;
	Set<String> hSet;
	void setUp() throws InterruptedException {
		driver = start();
		driver.findElement(By.xpath("//a[@id= 'demotable']")).click();
		Thread.sleep(3000);
	}
	// finds number of employees reporting to each manager ID.
	void employeeUnderEachManager() {
		int cnt = 0;
		int tableSize = driver.findElements(By.xpath("//table[@class= 'table table-striped']/tbody/tr")).size();
		mgr = new LinkedHashMap<>();
		for (int index = 1; index <= tableSize; index++) {
			String key = driver
					.findElement(By.xpath("//table[@class= 'table table-striped']/tbody/tr[" + index + "]/td[4]"))
					.getText();
			if (mgr.containsKey(key)) {
				cnt = mgr.get(key);
				mgr.put(key, cnt + 1);
			} else {
				mgr.put(key, 1);
			}
		}
		System.out.println("Employee count reporting under each manager ID : ");
		hSet = new LinkedHashSet<>();
		hSet = mgr.keySet();

		for (String mgrId : hSet) {
			System.out.println("  - " + mgr.get(mgrId) + " employees under Manager ID: " + mgrId);
		}
	}
	// finds manager ID to which the maximum employees are reporting.
	void ManagerWithMaxEmployee() {
		int max = 0, cnt = 0;
		String mId=null;
		for (String mgrId : hSet) {
			cnt = mgr.get(mgrId);
			if (cnt > max) {
				max = cnt;
				mId= mgrId;
			}
		}
		System.out.println("Maximum employees "+ max + " reporting to Manager ID: " + mId);
	}

	public static void main(String[] args) throws InterruptedException {
		WebTableOps wTable = new WebTableOps();
		wTable.setUp();
		wTable.employeeUnderEachManager();
		System.out.println();
		wTable.ManagerWithMaxEmployee();
	}
}
