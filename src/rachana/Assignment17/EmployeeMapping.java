/*Assignment - 17 : 6th Sep'2020

1) Office & Number of employees mapping
2) Position and Number of Employees mapping*/
package rachana.Assignment17;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import rachana.base.PredefinedActions;

public class EmployeeMapping extends PredefinedActions {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
		wait = new WebDriverWait(driver, 20);
	}

	@Test
	void employeeOffice() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//table[@id='example']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='example']//tr")));
		int totalPages = driver.findElements(By.xpath("//span/a[contains(@class,'paginate_button ')]")).size();
		Map<String, Integer> hm = new HashMap<String, Integer>();
		int count = 0;
		while (count <= totalPages) {
			List<WebElement> locations = driver.findElements(By.xpath("//table[@id='example']//tr/td[5]"));
			for (WebElement ele : locations) {
				String uniqueLocation = ele.getText();
				if (hm.containsKey(uniqueLocation)) {
					hm.put(uniqueLocation, hm.get(uniqueLocation) + 1);
				} else {
					hm.put(uniqueLocation, 1);
				}
			}
			count++;
			driver.findElement(By.xpath("//a[text()='Next']")).click();
		}
		System.out.println("----No of emplyees belongs to particuler office----");
		System.out.println(hm);
	}

	@Test
	void employeePosition() {
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='example']//tr")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//table[@id='example']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='example']//tr")));
		
		Map<String, Integer> hmap = new HashMap<String, Integer>();
		int totalPages = driver.findElements(By.xpath("//span/a[contains(@class,'paginate_button ')]")).size();
		int count = 0;
		while (count <= totalPages) {
			List<WebElement> positions = driver.findElements(By.xpath("//table[@id='example']//tr/td[4]"));
			for (WebElement ele : positions) {
				String uniquePosition = ele.getText();
				if (hmap.containsKey(uniquePosition)) {
					hmap.put(uniquePosition, hmap.get(uniquePosition) + 1);
				} else {
					hmap.put(uniquePosition, 1);
				}
			}
			count++;
			driver.findElement(By.xpath("//a[text()='Next']")).click();
		}
		System.out.println("----No of employees for particular position ----");
		System.out.println(hmap);
	}

	// @AfterClass
	// void tearDown() {
	// driver.close();
	// }
}
