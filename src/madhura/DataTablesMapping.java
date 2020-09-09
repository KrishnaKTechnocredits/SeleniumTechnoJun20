package madhura;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import madhura.base.PredefinedActions;

/*Assignment - 17 : 6th Sep'2020

1) Office & Number of employees mapping
2) Position and Number of Employees mapping

URL : https://editor.datatables.net/examples/extensions/excel*/
public class DataTablesMapping extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
		wait = new WebDriverWait(driver, 15);
	}

	@Test
	void findOfficeAndEmployees() {
		Map<String, Integer> offcEmployeeMap = new HashMap<String, Integer>();
		int pages = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();

		int count = 0;
		while (count <= pages) {
			List<WebElement> officeList = driver.findElements(By.xpath("//tbody/tr/td[5]"));

			for (WebElement office : officeList) {
				if (offcEmployeeMap.containsKey(office.getText())) {
					offcEmployeeMap.put(office.getText(), offcEmployeeMap.get(office.getText()) + 1);
				} else
					offcEmployeeMap.put(office.getText(), 1);
			}
			count++;
			driver.findElement(By.xpath("//a[text()='Next']")).click();
		}
		System.out.println("Office & Number of Employees mapping : ");
		System.out.println(offcEmployeeMap);
	}

	@Test
	void findPositionANdEmployees() {
		driver.navigate().refresh();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='example']//tbody/tr/td[2]")));
		Map<String, Integer> positionEmpMap = new HashMap<>();
		int pages = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();
		int count = 0;
		while (count <= pages) {
			List<WebElement> positionList = driver.findElements(By.xpath("//table[@id='example']//tbody/tr/td[4]"));
			for (WebElement positionName : positionList) {
				if (positionEmpMap.containsKey(positionName.getText())) {
					positionEmpMap.put(positionName.getText(), positionEmpMap.get(positionName.getText()) + 1);
				} else
					positionEmpMap.put(positionName.getText(), 1);
			}
			count++;
			driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
		}
		System.out.println("\nPosition & Number of Employees Mapping: ");
		System.out.println(positionEmpMap);
	}

	@AfterClass
	void tearDown() {
		driver.quit();
	}
}