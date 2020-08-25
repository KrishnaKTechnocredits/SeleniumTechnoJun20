/*
 1) Find how many employees reporting to each manager ID. 
2) Find maximum employees reporting to which manager ID.
 */
package shruti;
import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import shruti.predefinedActionspkg.PtrdefinedActions;
public class TablesEx extends PtrdefinedActions {
	WebDriver driver;
	void setUp() {
		driver = start();
	}
	// Find how many employees reporting to each manager ID.
	void EmpToMang() throws InterruptedException {
		driver.findElement(By.id("demotable")).click();
		Thread.sleep(4000);
		HashMap<String, Integer> managerMap = new HashMap<>();
		int managerCount = driver.findElements(
				By.xpath("//table[@class = 'table table-striped']/tbody/tr"))
				.size();
		for (int index = 1; index <= managerCount; index++) {
			String manager = driver.findElement(By.xpath("//table[@class = 'table table-striped']/tbody/tr["+ index + "]/td[4]")).getText();
			if (managerMap.containsKey(manager)) {
				managerMap.put(manager, managerMap.get(manager) + 1);
			} else {
				managerMap.put(manager, 1);
			}
		}
		System.out.println("total num of manager-> "+managerMap.size());
		Set<String> setOfManager =managerMap.keySet();
		System.out.println(setOfManager);	
		//Find maximum employees reporting to which manager ID.
		int empCount = 0;
		String MaxEmp = null;
		for (String managernum : managerMap.keySet()) {
			if (managerMap.get(managernum) > empCount) {
				empCount = managerMap.get(managernum);
				MaxEmp = managernum;
			}
		}
		System.out.println("Maximum number employees reporting to manager ID: " + MaxEmp + " is : " + empCount);

	}
	public static void main(String[] args) throws InterruptedException {
		TablesEx tablesEx = new TablesEx();
		tablesEx.setUp();
		tablesEx.EmpToMang();
	}
}
