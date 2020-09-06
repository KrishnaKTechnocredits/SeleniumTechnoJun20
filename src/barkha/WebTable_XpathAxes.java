/*1) Find Unique Office location
2) Find Unique Position*/

package barkha;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import barkha_base.PredefinedFunctions;

public class WebTable_XpathAxes extends PredefinedFunctions {
	WebDriver driver;

	@BeforeClass
	void setUP() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
	}

	@AfterClass
	void tearDown() {
		driver.close();
	}

	@Test(priority = 1)
	void webTable_uniqueLoc() {
		HashSet<String> hashSet_Loc = new HashSet<String>();

		int size = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size(); // number of pages

		List<WebElement> officeLoc = driver.findElements(By.xpath("//tbody/tr/td[5]"));

		Iterator<WebElement> itr = officeLoc.iterator();
		while (itr.hasNext()) {
			String officeLocation = itr.next().getText();
			hashSet_Loc.add(officeLocation);
		}
		for (int index = 2; index <= size; index++) {
			driver.findElement(By.xpath("//span/a[@tabindex='0'][" + index + "]")).click();
			
			List<WebElement> officeLoc1 = driver.findElements(By.xpath("//tbody/tr/td[5]"));

			Iterator<WebElement> itr1 = officeLoc1.iterator();
			while (itr1.hasNext()) {
				String officeLocation = itr1.next().getText();
				hashSet_Loc.add(officeLocation);
			}
		}
		System.out.println("Unique Locations are: " + hashSet_Loc+" \nand count are: "+hashSet_Loc.size());
	}

	@Test(priority = 2)
	void webTable_uniquePosition() {
		HashSet<String> hashSet_Pos = new HashSet<String>();
		
		int size = driver.findElements(By.xpath("//span/a[@tabindex='0']")).size();

		List<WebElement> position = driver.findElements(By.xpath("//tbody/tr/td[4]"));

		Iterator<WebElement> itr = position.iterator();
		while (itr.hasNext()) {
			String uniquePosition = itr.next().getText();
			hashSet_Pos.add(uniquePosition);
		}
		for (int index = 2; index <= size; index++) {
			driver.findElement(By.xpath("//span/a[@tabindex='0'][" + index + "]")).click();
			
			List<WebElement> position1 = driver.findElements(By.xpath("//tbody/tr/td[4]"));

			Iterator<WebElement> itr1 = position1.iterator();
			while (itr1.hasNext()) {
				String officeLocation = itr1.next().getText();
				hashSet_Pos.add(officeLocation);
			}
		}
		System.out.println("Unique Positions are: " + hashSet_Pos+"\nand count are: "+hashSet_Pos.size());
	}
}