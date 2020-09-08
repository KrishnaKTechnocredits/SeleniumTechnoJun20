/*
Assignment - 16 : 6th Sep'2020

1) Find Unique Office location
2) Find Unique Position

URL : https://editor.datatables.net/examples/extensions/excel
*/

package viresh.assignment16;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import viresh.utility.SetUp;

public class TableTraverse extends SetUp {

	WebDriver driver;
	int totalDivisions;

	@BeforeTest
	void startup() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
	}

	@BeforeClass
	void setVisibility() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//th[text()='Position']")));
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[1]/a[text()='1']")).click();
		totalDivisions = driver.findElements(By.xpath("//a[@tabindex='0']")).size() - 1;
	}

	@Test
	void uniqueLocations() {

		HashSet<String> locationSet = new HashSet<>();

		for (int oIndex = 1; oIndex <= totalDivisions; oIndex++) {
			driver.findElement(By.xpath("//span[1]/a[" + oIndex + "]")).click();
			int currentRows = driver.findElements(By.xpath("//tbody/tr[@role='row']")).size();
			for (int iIndex = 1; iIndex <= currentRows; iIndex++) {
				locationSet.add(driver.findElement(By.xpath("//tr[" + iIndex + "]/td[5]")).getText());
			}
		}

		System.out.println(" >> Unique Office Locations: " + locationSet.size() + " " + locationSet);
		//System.out.println(" Location List: " + locationSet);

	}

	@Test
	void uniquePositions() {

		HashSet<String> PositionSet = new HashSet<>();

		for (int oIndex = 1; oIndex <= totalDivisions; oIndex++) {
			driver.findElement(By.xpath("//span[1]/a[" + oIndex + "]")).click();
			int currentRows = driver.findElements(By.xpath("//tbody/tr[@role='row']")).size();
			for (int iIndex = 1; iIndex <= currentRows; iIndex++) {
				PositionSet.add(driver.findElement(By.xpath("//tr[" + iIndex + "]/td[4]")).getText());
			}
		}

		System.out.println(" >> Unique Positions: " + PositionSet.size() + " " + PositionSet);
		//System.out.println(" Positions List: " + PositionSet);
	}

	@AfterClass
	void flush() {
		driver.close();
	}

}
