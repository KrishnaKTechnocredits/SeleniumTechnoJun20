package vaishnavi_SeleniumBasics;

import java.util.HashMap;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import vaishnavi_Base.PredefinedAction;

/* 1) Office & Number of employees mapping
2) Position and Number of Employees mapping */

public class OfficeMapping extends PredefinedAction {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeTest
	void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
		wait = new WebDriverWait(driver, 5);
	}

	
	HashMap<String, Integer> findMapping(int columnNum) {
		HashMap<String, Integer> empMappingCount = new HashMap<String, Integer>();
		int count = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();
		
		for (int outerIndex = 1; outerIndex <= count; outerIndex++) {
			int rowsCount = driver.findElements(By.xpath("//table[@id='example']//tbody//tr")).size();
			for (int index = 1; index <= rowsCount; index++) {
				String location = driver.findElement(By.xpath("//tbody/tr[" + index + "]//td["+columnNum+"]")).getText();

				if (empMappingCount.containsKey(location)) {
					empMappingCount.put(location, empMappingCount.get(location) + 1);
				} else {
					empMappingCount.put(location, 1);
				}
			}
			driver.findElement(By.xpath("//span/a[@tabindex='0']["+outerIndex+"]")).click();		
		}
		
		return empMappingCount;
	}
	
	@Test(priority=1)
	void findNumberOfEmployeesAccordingToOffice() {
		HashMap<String,Integer> mappingCountForOffice = findMapping(5);
		System.out.println("Epmployee count per Office location is as below: ");

		Set<String> keySet = mappingCountForOffice.keySet();
		for (String officeName : keySet) {
			System.out.println("Office Location: " + officeName + " - Employee Count: " + mappingCountForOffice.get(officeName));
		}
	}
	
	@Test(priority=2)
	void findNumberOfEmployeesAccordingToPosition() {
		HashMap<String,Integer> mappingCountForPosition = findMapping(4);
		System.out.println("-----------------------------------------------------------");
		System.out.println("Epmployee count per Position is as below: ");

		Set<String> keySet = mappingCountForPosition.keySet();
		for (String position : keySet) {
			System.out.println("Position: " + position + " ------------- Employee Count: " + mappingCountForPosition.get(position));
		}
	}
}
