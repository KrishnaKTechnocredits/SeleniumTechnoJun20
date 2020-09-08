package abhishek;

import java.util.LinkedHashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import abhishek.base.PredefinedActions;

public class Assignment16_uniqueElementInTable extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	@BeforeTest
	void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
	}

	@AfterTest
	void tearDown() {
		driver.close();
	}

	@Test(priority = 1)
	void findUniqueOfficeLocation() {
		Set<String> officeLocationSet = new LinkedHashSet<String>();
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Ashton']")));
		WebElement scrollElement = driver.findElement(By.xpath("//td[text()='Ashton']"));

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", scrollElement);

		int size = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();

		for (int outerIndex = 1; outerIndex <= size; outerIndex++) {
			int rowSize = driver.findElements(By.xpath("//tr[@role='row']//td[5]")).size();

			for (int index = 1; index <= rowSize; index++) {
				WebElement officeLocationElement = driver.findElement(By.xpath("//tbody//tr[" + index + "]//td[5]"));
				officeLocationSet.add(officeLocationElement.getText());
			}
			// Click on Page element
			driver.findElement(By.xpath("//span/a[@tabindex='0'][" + outerIndex + "]")).click();
		}

		System.out.println("Unique office Locations in the table");
		for (String officeLocation : officeLocationSet) {
			System.out.println(officeLocation);
		}
	}

	@Test(priority = 2)
	void findUniquePosition() {
		driver.findElement(By.xpath("//a[@data-dt-idx='1']")).click();
		Set<String> uniquePositionSet = new LinkedHashSet<String>();

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Ashton']")));
		WebElement scrollElement = driver.findElement(By.xpath("//td[text()='Ashton']"));

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", scrollElement);

		int size = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();

		for (int outerIndex = 1; outerIndex <= size; outerIndex++) {
			int rowSize = driver.findElements(By.xpath("//tr[@role='row']//td[5]")).size();

			for (int index = 1; index <= rowSize; index++) {
				WebElement positionElement = driver.findElement(By.xpath("//tbody/tr[" + index + "]//td[4]"));
				uniquePositionSet.add(positionElement.getText());
			}
			// Click on Page element
			driver.findElement(By.xpath("//span/a[@tabindex='0'][" + outerIndex + "]")).click();
		}

		System.out.println("\nUnique Positions list in the table");
		for (String uniquePosition : uniquePositionSet)
			System.out.println(uniquePosition);
	}
}
