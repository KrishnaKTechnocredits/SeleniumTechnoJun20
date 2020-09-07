package pranita;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pranita.basic.PredefinedFunctions;

public class Assignment_16_UniqueOfficeLocationPosition extends PredefinedFunctions{
	WebDriver driver;
	HashSet<String> uniqueElementsSet;
	
	@BeforeClass
	void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
	}
	
	
	@BeforeMethod()
	void initializeHashSet() {
		uniqueElementsSet = new HashSet<String>();
	}
	
	@Test(priority = 1)
	 void findUniqueLocations() {
		int size = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();
		System.out.println("Total pages are: "+size);// number of pages

		List<WebElement> location = driver.findElements(By.xpath("//tbody/tr/td[5]"));

		Iterator<WebElement> itr = location.iterator();
		while (itr.hasNext()) {
			String officeLocation = itr.next().getText();
			uniqueElementsSet.add(officeLocation);
		}
		
		for (int index = 2; index <= size; index++) {
			driver.findElement(By.xpath("//span/a[@tabindex='0'][" + index + "]")).click();//select page 2 to 6

			List<WebElement> position = driver.findElements(By.xpath("//table[@id='example']//tbody/tr/td[5]"));

			Iterator<WebElement> itr1 = position.iterator();
			while (itr1.hasNext()) {
				String officePosition = itr1.next().getText();
				uniqueElementsSet.add(officePosition);
			}
		}
		System.out.println("Unique Locations are: " + uniqueElementsSet+" \nTotal locations are : "+uniqueElementsSet.size());
		
	}
	 
	@Test(priority = 2)
	 public void findUniquePosition() {
			int size = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();

			List<WebElement> position = driver.findElements(By.xpath("//tbody/tr/td[4]"));

			Iterator<WebElement> itr = position.iterator();
			
			while (itr.hasNext()) {
				String uniquePosition = itr.next().getText();
				uniqueElementsSet.add(uniquePosition);
			}
			for (int index = 2; index <= size; index++) {
				driver.findElement(By.xpath("//span/a[@tabindex='0'][" + index + "]")).click();

				List<WebElement> position1 = driver.findElements(By.xpath("//table[@id='example']//tbody/tr/td[5]"));

				Iterator<WebElement> itr1 = position1.iterator();
				while (itr1.hasNext()) {
					String officeLocation = itr1.next().getText();
					uniqueElementsSet.add(officeLocation);
				}
			}
			System.out.println("Unique Positions are: " + uniqueElementsSet+"\nand all positions are: "+uniqueElementsSet.size());
		}

	
	@AfterClass
	void tearDown() {
		driver.close();
	}
}
