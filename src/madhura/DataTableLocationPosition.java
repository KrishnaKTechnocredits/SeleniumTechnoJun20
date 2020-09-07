package madhura;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import madhura.base.PredefinedActions;

/*Assignment - 16 : 6th Sep'2020

1) Find Unique Office location
2) Find Unique Position

URL : https://editor.datatables.net/examples/extensions/excel */
public class DataTableLocationPosition extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
		wait = new WebDriverWait(driver, 15);
	}

	@Test(priority = 1)
	void uniqueOfficeLocation() {
		Set<String> officeLocationSet = new LinkedHashSet<String>();
		int size = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();

		List<WebElement> locationList = driver.findElements(By.xpath("//tbody/tr/td[5]"));

		Iterator<WebElement> itr = locationList.iterator();
		while (itr.hasNext()) {
			String officeLocation = itr.next().getText();
			officeLocationSet.add(officeLocation);
		}
		for (int index = 2; index <= size; index++) {
			driver.findElement(By.xpath("//span/a[@tabindex='0'][" + index + "]")).click();
			List<WebElement> locationList2 = driver.findElements(By.xpath("//tbody/tr/td[5]"));

			Iterator<WebElement> itr1 = locationList2.iterator();
			while (itr1.hasNext()) {
				String officeLocation = itr1.next().getText();
				officeLocationSet.add(officeLocation);
			}
		}
		System.out.println("Unique Office locations : ");
		System.out.println(officeLocationSet);

	}

	@Test(priority = 2)
	void findUniquePosition() {
		driver.findElement(By.xpath("//a[@data-dt-idx='1']")).click();
		Set<String> uniquePositionSet = new LinkedHashSet<String>();

		int size = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();
		for (int outerIndex = 1; outerIndex <= size; outerIndex++) {
			int totalRows = driver.findElements(By.xpath("//tr[@role='row']//td[5]")).size();
			for (int index = 1; index <= totalRows; index++) {
				WebElement positionName = driver.findElement(By.xpath("//tbody/tr[" + index + "]//td[4]"));
				uniquePositionSet.add(positionName.getText());
			}
			driver.findElement(By.xpath("//span/a[@tabindex='0'][" + outerIndex + "]")).click();
		}
		System.out.println("Unique Positions : ");
		for (String uniquePosition : uniquePositionSet)
			System.out.println(uniquePosition);
	}

	@AfterClass
	void tearDown() {
		driver.quit();
	}
}