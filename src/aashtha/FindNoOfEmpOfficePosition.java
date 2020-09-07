package aashtha;

/*Assignment - 17 : 6th Sept'2020

1) Office & Number of employees mapping
2) Position and Number of Employees mapping

URL : https://editor.datatables.net/examples/extensions/excel*/

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import aashtha.base.PredefinedActions;

public class FindNoOfEmpOfficePosition extends PredefinedActions {
	WebDriver driver;
	private HashMap<String, Integer> employeeMap;

	// opens the Chrome
	@BeforeTest
	void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
	}

	// closes the browser window
	@AfterTest
	void tearDown() {
		driver.close();
	}

	// Below method initializes the map before each test case
	@BeforeMethod
	private void initializeMap() {
		employeeMap = new HashMap<String, Integer>();
	}

	// Below method accepts the list Of Locations/Positions and saves in map as key
	// and no. of occurrences as value
	private void addInMap(List<WebElement> listOfElements) {
		for (WebElement element : listOfElements) {
			String location = element.getText();
			if (employeeMap.containsKey(location)) {
				employeeMap.put(location, employeeMap.get(location) + 1);
			} else {
				employeeMap.put(location, 1);
			}
		}
	}

	// Below method fetches the list of Locations/Positions from webtable(page 1
	// till end) and call addInMap() method
	private void findNoOfEmployees(String column) {
		List<WebElement> listOfElements = driver.findElements(By.xpath("//table[@id='example']//td[" + column + "]"));
		addInMap(listOfElements);
		while (!driver.findElement(By.xpath("//a[text()='Next']")).getAttribute("class").contains("disabled")) {
			driver.findElement(By.xpath("//a[text()='Next']")).click();
			listOfElements = driver.findElements(By.xpath("//table[@id='example']//td[" + column + "]"));
			addInMap(listOfElements);
		}
	}

	// TC-1) Office & Number of employees mapping
	@Test(priority = 1)
	public void findNoOfEmpPerOffice() {
		driver.findElement(By.xpath("//th[text()='Office']")).click();
		findNoOfEmployees("5");
		System.out.println("\nBelow are the number of employees at each office location:");
		System.out.println(employeeMap);
	}

	// TC-2) Position and Number of Employees mapping
	@Test(priority = 2)
	public void findNoOfEmpPerPosition() {
		driver.findElement(By.xpath("//a[@class='paginate_button '][text()='1']")).click();
		driver.findElement(By.xpath("//th[text()='Position']")).click();
		findNoOfEmployees("4");
		System.out.println("\nBelow are the number of employees for each position:");
		System.out.println(employeeMap);
	}
}
