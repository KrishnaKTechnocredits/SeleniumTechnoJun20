/*Assignment - 16 : 6th Sep'2020

1) Find Unique Office location
2) Find Unique Position*/
package amita;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import amita.base.PredefinedActions;

public class FindUniqueOffcLocation extends PredefinedActions {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
		wait = new WebDriverWait(driver, 5);
	}

	@Test
	public void uniqueOfficeLocation() {

		Set<String> uniqueOfficeLocation = new HashSet<String>();
		Set<String> uniqueOfficePosition = new HashSet<String>();

		int tabCount = driver.findElements(By.xpath("//span/a[@class='paginate_button ']")).size();

		int count = 0;
		while (count <= tabCount) {
			int rowCount = driver.findElements(By.xpath("//table[@id='example']/tbody/tr")).size();
			for (int index = 1; index <= rowCount; index++) {
				String officeLocation = driver.findElement(By.xpath("//table[@id='example']//tr[" + index + "]/td[5]"))
						.getText();
				uniqueOfficeLocation.add(officeLocation);
				String officePosition = driver.findElement(By.xpath("//table[@id='example']//tr[" + index + "]/td[4]"))
						.getText();
				uniqueOfficePosition.add(officePosition);
			}
			
			////to check the next button is available or not and to click on it
			if (!driver.findElement(By.xpath("//a[text()='Next']")).getAttribute("class").contains("disabled")) {
				count++;
				driver.findElement(By.xpath("//a[text()='Next']")).click();
			} else {
				break;
			}
		}
		System.out.println(uniqueOfficeLocation);
		System.out.println("Total unique office locations are " + uniqueOfficeLocation.size());
		System.out.println(uniqueOfficePosition);
		System.out.println("Total unique office positons are " + uniqueOfficePosition.size());
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
