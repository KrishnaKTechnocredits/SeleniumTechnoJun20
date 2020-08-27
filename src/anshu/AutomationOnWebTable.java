
/*1) Find how many employees reporting to each manager ID. 
2) Find maximum employees reporting to which manager ID.*/
package anshu;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import anshu.base.PredefinedProperty;

public class AutomationOnWebTable extends PredefinedProperty {
	WebDriver driver;

	void setUp() {
		driver = start();
	}

	void NavigateToUrl() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Demo Tables']")).click();
		Thread.sleep(5000);
	}

	void PrintMaxEmployeeReportingToEachManager() {
		try {
			LinkedHashMap<String, Integer> emplist = new LinkedHashMap<>();
			int rowSize = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr/td[4]")).size();
			for (int index = 1; index <= rowSize; index++) {
				String managerId = driver
						.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[4]"))
						.getText();

				if (emplist.containsKey(managerId)) {
					emplist.put(managerId, emplist.get(managerId) + 1);
				} else {
					emplist.put(managerId, 1);
				}
			}
			System.out.println("List Of total no of employee to each Manager :-");
			Set<String> keyset = emplist.keySet();
			String deptMangerId = "";
			int count = 0;
			for (String mngid : keyset) {
				System.out.println("  - " + emplist.get(mngid) + " employees are reported to this  " + mngid + "  Manager.");
				if (count < emplist.get(mngid)) {
					count = emplist.get(mngid);
					deptMangerId = mngid;
				}
			}
			System.out.println('\n'+" and this  " + deptMangerId  + "  manager has maximum  (" +count + ") employees");
		} catch (NoSuchElementException ne) {
			System.out.println("NoSuchElement Exception Handled");
		} finally {
			driver.close();
		}
	}

	public static void main(String[] args) {
		AutomationOnWebTable employeeTable = new AutomationOnWebTable();
		employeeTable.setUp();
		try {
			employeeTable.NavigateToUrl();

		} catch (InterruptedException ie) {
			System.out.println("Interuptted exception has been handled.");
		}

		employeeTable.PrintMaxEmployeeReportingToEachManager();

	}
}
