package rachana;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rachana.base.PredefinedActions;

public class EmployeeManagerTableTest extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	void setUp() {
		driver = start();
		wait = new WebDriverWait(driver, 15); // 500 ms
	}

	void NavigateToMenu() {
		driver.findElement(By.xpath("//a[@id='demotable']")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='table table-striped']/tbody")));
	}

	void testEmployeeTable() {

		Map<String, Integer> manager = new HashMap<String, Integer>();
		int totalrows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for (int index = 1; index <= totalrows; index++) {
			String managerId = driver
					.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[4]"))
					.getText();
			if (manager.containsKey(managerId)) {
				manager.put(managerId, manager.get(managerId) + 1);
			} else {
				manager.put(managerId, 1);
			}
		}
		Set<String> keys = manager.keySet();
		int maxKey = 0;
		String mngId = "";
		System.out.println("Number of employees reporting to each manager ID : ");
		for (String mngkey : keys) {
			if (manager.get(mngkey) > 1) {
				System.out.println(mngkey + " : " + manager.get(mngkey));
			}
			if (maxKey < manager.get(mngkey)) {
				maxKey = manager.get(mngkey);
				mngId = mngkey;
			}
		}
		System.out.println("Maximum employee reporting to ManagerId : ");
		System.out.println(mngId + " : " + manager.get(mngId));
	}

	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) {
		EmployeeManagerTableTest empManagertest = new EmployeeManagerTableTest();
		empManagertest.setUp();
		empManagertest.NavigateToMenu();
		empManagertest.testEmployeeTable();
		empManagertest.tearDown();
	}
}
