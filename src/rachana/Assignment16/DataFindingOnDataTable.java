package rachana.Assignment16;

import java.util.HashMap;
import java.util.HashSet;
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

public class DataFindingOnDataTable extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	void SetUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
		wait = new WebDriverWait(driver, 15);
	}

	//Find Unique Office location
	@Test
	void findUniqueLocation() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//table[@id='example']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='example']//tr")));
		int totalPages = driver.findElements(By.xpath("//span/a[@class='paginate_button ']")).size();
		Set<String> uniquelocations = new HashSet<String>();
		int count = 0;
		while (count <= totalPages) {
			List<WebElement> locations = driver.findElements(By.xpath("//table[@id='example']//tr/td[5]"));
			for (WebElement ele : locations) {
				uniquelocations.add(ele.getText());
			}
			count++;
			driver.findElement(By.xpath("//a[text()='Next']")).click();
		}
		System.out.println(uniquelocations);
		System.out.println("Total Unique Locations are:" + uniquelocations.size());
	}
	//Find Unique Position
	@Test
	void findUniqueposition() {

		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='example']//tr")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//table[@id='example']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='example']//tr")));
		Set<String> uniquepositions = new HashSet<String>();
		int totalPages = driver.findElements(By.xpath("//span/a[@class='paginate_button ']")).size();
		int count = 0;
		while (count <= totalPages) {
			List<WebElement> positions = driver.findElements(By.xpath("//table[@id='example']//tr/td[4]"));
			for (WebElement ele : positions) {
				uniquepositions.add(ele.getText());
			}
			count++;
			driver.findElement(By.xpath("//a[text()='Next']")).click();
		}
		System.out.println(uniquepositions);
		System.out.println("Total Unique Positions are:" + uniquepositions.size());

	}

	@AfterClass
	void tearDown() {
		driver.close();
	}
}
