/*
1) Office & Number of employees mapping
2) Position and Number of Employees mapping

URL : https://editor.datatables.net/examples/extensions/excel
*/

package aditi;

import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import aditi.base.PredefinedActions;

public class EmployeeMapping extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
		wait = new WebDriverWait(driver, 15);
	}

	@AfterClass
	void tearDown() {
		driver.close();
	}

	HashMap<String, Integer> employeeMapping(int columnValue) {
		HashMap<String, Integer> empMappingCount = new HashMap<String, Integer>();
		int count = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();

		for (int row = 1; row <= count; row++) {
			int totalRows = driver.findElements(By.xpath("//table[@id='example']//tbody//tr")).size();
			for (int column = 1; column <= totalRows; column++) {
				String elementValue = driver
						.findElement(By.xpath("//tbody/tr[" + column + "]//td[" + columnValue + "]")).getText();

				if (empMappingCount.containsKey(elementValue)) {
					empMappingCount.put(elementValue, empMappingCount.get(elementValue) + 1);
				} else {
					empMappingCount.put(elementValue, 1);
				}
			}
			driver.findElement(By.xpath("//span/a[@tabindex='0'][" + row + "]")).click();
		}
		return empMappingCount;
	}

	private void displayData(HashMap<String, Integer> inputData) {
		Set<String> keySet = inputData.keySet();
		for (String name : keySet) {
			System.out.println("\t" + name + " --> " + inputData.get(name));
		}
	}

	@Test()
	void employeeToOfficeCount() {
		HashMap<String, Integer> employeeToOfficeMap = employeeMapping(5);
		System.out.println("\nEmployee Count per location(office): ");
		System.out.println("---------------------------------------");
		displayData(employeeToOfficeMap);
	}

	@Test()
	void employeeToPositionCount() {
		HashMap<String, Integer> employeeToPositionMap = employeeMapping(4);
		System.out.println("\n\nEmployee Count per location(office): ");
		System.out.println("---------------------------------------");
		displayData(employeeToPositionMap);
	}
}
