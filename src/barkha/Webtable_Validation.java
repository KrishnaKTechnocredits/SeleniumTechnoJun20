/*1) Office & Number of employees mapping
2) Position and Number of Employees mapping */

package barkha;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import barkha_base.PredefinedFunctions;

public class Webtable_Validation extends PredefinedFunctions {
	WebDriver driver;
	WebDriverWait wait;
	@BeforeClass
	void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
	}

	@AfterClass
	void tearDown() {
		driver.close();
	}

	@Test(priority = 1)
	void find_Office_EmployeesNum() {
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		int totalPages = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();

		int count = 0;
		while (count <= totalPages) {
			List<WebElement> officeList = driver.findElements(By.xpath("//tbody/tr/td[5]"));

			for (WebElement office : officeList) {
				if (hashMap.containsKey(office.getText())) {
					hashMap.put(office.getText(), hashMap.get(office.getText()) + 1);
				} else
					hashMap.put(office.getText(), 1);
			}
			count++;
			driver.findElement(By.xpath("//a[text()='Next']")).click();
		}
		System.out.println(
				"Office & Number of employees mapping is: " + hashMap + "\nand the count is: " + hashMap.size());
	}

	@Test(priority = 2)
	void find_Position_EmployeesNum() {

		driver.navigate().refresh();
		wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr/td[4]")));

		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		int totalPages = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();

		int count = 0;
		while (count <= totalPages) {
			List<WebElement> position = driver.findElements(By.xpath("//tbody/tr/td[4]"));

			for (WebElement designation : position) {
				if (hashMap.containsKey(designation.getText())) {                 // will find text of WebElement
					hashMap.put(designation.getText(), hashMap.get(designation.getText()) + 1);
				} else
					hashMap.put(designation.getText(), 1);
			}
			count++;
			driver.findElement(By.xpath("//a[text()='Next']")).click();
		}
		System.out.println(
				"Position &  Number of Employees mapping is: " + hashMap + "\nand the count is: " + hashMap.size());
	}
}