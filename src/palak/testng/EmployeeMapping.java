/*Assignment - 17 : 6th Sep'2020

1) Office & Number of employees mapping
2) Position and Number of Employees mapping
*/
package palak.testng;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import palak.base.PredefinedActions;

public class EmployeeMapping extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	@BeforeTest
	void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
	}

	@Test(priority = 1)
	void officeAndNumberMapping() {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Ashton']")));
		WebElement scrollElement = driver.findElement(By.xpath("//td[text()='Ashton']"));

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", scrollElement);
		Map<String, Integer> officeNumberMap = new HashMap<>();
		int count = 0;
		int totalTableRowsize = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();

		while(count<=totalTableRowsize) {
			int rowSize = driver.findElements(By.xpath("//tr[@role='row']//td[5]")).size();
			for (int index = 1; index <= rowSize; index++) {			
				String officeLocation = driver.findElement(By.xpath("//tbody//tr[" + index + "]//td[5]")).getText();
				if (officeNumberMap.containsKey(officeLocation)) {
					officeNumberMap.put(officeLocation, officeNumberMap.get(officeLocation) + 1);
				} else {
					officeNumberMap.put(officeLocation, 1);
				}
			}			
			if (!driver.findElement(By.xpath("//a[contains(@id,'example_next')]")).getAttribute("class").contains("disabled")) {
				count++;
				//Click on Page number element
				driver.findElement(By.xpath("//a[@class='paginate_button next']")).click();
			}else
				break;		
		}
		System.out.println(officeNumberMap);
	}
	
	@Test(priority = 2)
	void positionAndNumberMapping() {
		driver.findElement(By.xpath("//a[@data-dt-idx='1']")).click();
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Ashton']")));
		WebElement scrollElement = driver.findElement(By.xpath("//td[text()='Ashton']"));

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", scrollElement);
		Map<String, Integer> PositionNumberMap = new HashMap<>();
		int count =0;
		int totalTableRowsize = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();
		while(count<=totalTableRowsize) {
			int rowSize = driver.findElements(By.xpath("//tr[@role='row']//td[5]")).size();
			for (int index = 1; index <= rowSize; index++) {
			
				String position = driver.findElement(By.xpath("//tbody//tr[" + index + "]//td[4]")).getText();
				if (PositionNumberMap.containsKey(position)) {
					PositionNumberMap.put(position, PositionNumberMap.get(position) + 1);
				} else {
					PositionNumberMap.put(position, 1);
				}
			}
			if (!driver.findElement(By.xpath("//a[contains(@id,'example_next')]")).getAttribute("class").contains("disabled")) {
				count++;
				//Click on Page number element
				driver.findElement(By.xpath("//a[@class='paginate_button next']")).click();
			}else
				break;	
		}
		System.out.println(PositionNumberMap);
	}
	@AfterTest
	void tearDown() {
		driver.close();
	}
}
