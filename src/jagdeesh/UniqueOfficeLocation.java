package jagdeesh;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


//Find Unique Office location
// Find Unique Position
public class UniqueOfficeLocation extends PreDefinedActions{
	WebDriver driver;
	@Test
	public void displayLocations() {
		Set<String> setOfLocations = new TreeSet<String>();
		Set<String> setOfPositions = new TreeSet<String>();

		do {
			int countOfRows = driver.findElements(By.xpath("//table[@id='example']/tbody/tr/td[5]")).size();
			for (int index = 1; index <= countOfRows; index++) {
				String location = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + index + "]/td[5]"))
						.getText();
				setOfLocations.add(location);
				String position = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + index + "]/td[4]"))
						.getText();
				setOfPositions.add(position);
			}
			driver.findElement(By.xpath("//a[@id='example_next']")).click();
		} while (!driver.findElement(By.xpath("//a[@id='example_next']")).getAttribute("class").contains("disabled"));
		System.out.println("Total unique locations are --> " + setOfLocations.size());
		System.out.println("Total unique Positions are --> " + setOfPositions.size());
		Iterator<String> itr = setOfLocations.iterator();
		System.out.println("Unique Office Locations :");
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
		
		itr = setOfPositions.iterator();
		System.out.println("====================");
		System.out.println("Unique Positions :");
		System.out.println("====================");
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}

	}
	
	@BeforeTest
	public void openApplication() {
		 driver = start("https://editor.datatables.net/examples/extensions/excel");
	}

}
