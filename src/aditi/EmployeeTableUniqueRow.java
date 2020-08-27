/* Assignment 8:
 * Go to - http://automationbykrishna.com/
 * From "Employee Manager" table - Print unique rows count and print duplicate row.
 * */

package aditi;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import aditi.base.PredefinedActions;

public class EmployeeTableUniqueRow extends PredefinedActions {
	WebDriver driver;

	void setUp() {
		driver = start("http://automationbykrishna.com");
	}

	void navigateToDemoTable() {
		driver.findElement(By.linkText("Demo Tables")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
	}

	void tearDown() {
		driver.close();
	}

	void uniqueRowCount() {
		int rowCount = driver.findElements(By.xpath("//table[@class='table table-striped']//tbody/tr")).size();
		Set<EmployeeInfo> empInfoList = new LinkedHashSet<EmployeeInfo>();

		EmployeeInfo employeeInfo = new EmployeeInfo();
		for (int index = 1; index <= rowCount; index++) {
			String rowXpath = "//table[@class='table table-striped']//tbody/tr[" + index + "]";

			employeeInfo.setEmpId(Integer.parseInt(driver.findElement(By.xpath(rowXpath + "/td[2]")).getText()));
			employeeInfo.setEmpName(driver.findElement(By.xpath(rowXpath + "/td[3]")).getText());
			employeeInfo.setManagerId(Integer.parseInt(driver.findElement(By.xpath(rowXpath + "/td[4]")).getText()));
			employeeInfo.setDeptName(driver.findElement(By.xpath(rowXpath + "/td[5]")).getText());

			if (!empInfoList.add(employeeInfo)) {
				System.out.println("Duplicate Row: empId -> " + employeeInfo.getEmpId() + "  empName ->"
						+ employeeInfo.getEmpName());
			}
		}
		System.out.println("Total number of unique rows: " + empInfoList.size());
	}

	public static void main(String[] args) {
		EmployeeTableUniqueRow employeeTableUniqueRow = new EmployeeTableUniqueRow();
		employeeTableUniqueRow.setUp();
		employeeTableUniqueRow.navigateToDemoTable();
		employeeTableUniqueRow.uniqueRowCount();
		employeeTableUniqueRow.tearDown();
	}
}
