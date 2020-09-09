/*
 * Assignment - 17 : 6th Sep'2020

1) Office & Number of employees mapping
2) Position and Number of Employees mapping

 */
package pranita;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pranita.basic.PredefinedFunctions;

public class Assignment_17_EmployeesMappingWithOfficeAndPosition extends PredefinedFunctions{
	WebDriver driver;
	WebDriverWait wait;
	HashMap<String, Integer> OfcEmployeeMap ;
	int totalPages;
	
	@BeforeClass
	void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
	}
	
	@BeforeMethod()
	void initializeHashMap() {
		OfcEmployeeMap = new HashMap<String, Integer>();
		totalPages = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();
	}
	
	@Test(priority = 1)
	 void findUniqueLocations() {
		//int pages = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();
		//System.out.println("Total pages are: "+pages);// number of pages
		int count = 0;
		while (count <= totalPages) {
			List<WebElement> OfcLocationList = driver.findElements(By.xpath("//table[@id='example']//tbody/tr/td[5]"));

			for (WebElement office : OfcLocationList) {
				if (OfcEmployeeMap.containsKey(office.getText())) {
					OfcEmployeeMap.put(office.getText(), OfcEmployeeMap.get(office.getText()) + 1);
				} else
					OfcEmployeeMap.put(office.getText(), 1);
			}
			count++;
			driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
		}
		System.out.println("Mapping of Office & Number of Employees: " + OfcEmployeeMap);
		
	}
	 
	@Test(priority = 2)
	 public void findUniquePosition() {
		driver.navigate().refresh();
		wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='example']//tbody/tr/td[4]")));
		int count = 0;
		while (count <= totalPages) {
			List<WebElement> PositionList = driver.findElements(By.xpath("//table[@id='example']//tbody/tr/td[4]"));
			for (WebElement Position : PositionList) {
				if (OfcEmployeeMap.containsKey(Position.getText())) {
					OfcEmployeeMap.put(Position.getText(), OfcEmployeeMap.get(Position.getText()) + 1);
				} else
					OfcEmployeeMap.put(Position.getText(), 1);
				}
				count++;
				driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
			}
			System.out.println("Mapping of Position & Number of Employees: " + OfcEmployeeMap);
			
		}

	
	@AfterClass
	void tearDown() {
		driver.close();
	}
}

