package aditi;

import java.util.HashSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import aditi.base.PredefinedActions;

public class UniqueOfcLocationAndPosition extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
		wait = new WebDriverWait(driver, 15);
	}

	@AfterClass
	void tearDown() {
		driver.close();
	}

	private HashSet<String> getUniqueData(int columnValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span//a[@class='paginate_button ']")));
		int count = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();
		HashSet<String> uniqueData = new HashSet<String>();

		for (int row = 1; row <= count; row++) {
			int totalRows = driver.findElements(By.xpath("//table[@id='example']//tbody//tr")).size();
			for (int column = 1; column <= totalRows; column++) {
				String location = driver.findElement(By.xpath("//tbody/tr[" + column + "]//td[" + columnValue + "]"))
						.getText();
				uniqueData.add(location);
			}
			driver.findElement(By.xpath("//span/a[@tabindex='0'][" + row + "]")).click();
		}
		return uniqueData;
	}

	private void displayData(HashSet<String> inputData) {
		for (String data : inputData) {
			System.out.println(data);
		}
	}

	@Test()
	public void findUniqueOfficeLocations() {
		HashSet<String> uniqueLocations = getUniqueData(5);
		System.out.println("Total Unique Office locations are: " + uniqueLocations.size());
		System.out.println("----------------------------------");
		displayData(uniqueLocations);
	}

	@Test()
	public void findUniquePositions() {
		HashSet<String> uniquePositions = getUniqueData(4);
		System.out.println("\n\nUnique Positions are: " + uniquePositions.size());
		System.out.println("----------------------------------");
		displayData(uniquePositions);
	}
}
