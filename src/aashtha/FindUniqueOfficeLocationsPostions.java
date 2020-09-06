package aashtha;

import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;

/*Assignment - 16 : 6th Sept'2020

1) Find Unique Office location
2) Find Unique Position

URL : https://editor.datatables.net/examples/extensions/excel*/

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import aashtha.base.*;

public class FindUniqueOfficeLocationsPostions extends PredefinedActions {
	WebDriver driver;
	private HashSet<String> setOfUniqueElements;

	// opens the Chrome
	@BeforeTest
	void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
	}

	// closes the browser window
	@AfterTest
	void tearDown() {
		driver.close();
	}

	// initializes the HashSet before finding locations and again before finding
	// positions - for reusability of same HashSet
	@BeforeMethod
	private void initializeHashSet() {
		setOfUniqueElements = new HashSet<String>();
	}

	// below method fetches the text of element from provided list of WebElement and
	// add it in HashSet
	private void addTextToHashSet(List<WebElement> listOfElements) {
		for (WebElement element : listOfElements) {
			setOfUniqueElements.add(element.getText());
		}
	}

	// Below method creates the list of elements from page 1 till Next button is
	// disabled and calls addTextToHashSet() method
	private void findUniqueValues(String column) {
		List<WebElement> listOfElements = driver.findElements(By.xpath("//table[@id='example']//td[" + column + "]"));
		addTextToHashSet(listOfElements);
		while (!driver.findElement(By.xpath("//a[text()='Next']")).getAttribute("class").contains("disabled")) {
			driver.findElement(By.xpath("//a[text()='Next']")).click();
			listOfElements = driver.findElements(By.xpath("//table[@id='example']//td[" + column + "]"));
			addTextToHashSet(listOfElements);
		}
	}

	// TC-1) Find Unique Office location
	@Test(priority = 1)
	public void findUniqueOfficeLocations() {
		findUniqueValues("5");
		System.out.println("Total : " + setOfUniqueElements.size() + " unique office locations are found from table");
		System.out.println(setOfUniqueElements);
		Assert.assertEquals(setOfUniqueElements.size(), 7);
	}

	// TC-2) Find Unique Position
	@Test(priority = 2)
	public void findUniquePositions() {
		driver.findElement(By.xpath("//a[@class='paginate_button '][text()='1']")).click();
		findUniqueValues("4");
		System.out.println("\nTotal : " + setOfUniqueElements.size() + " unique Positions are found from table");
		System.out.println(setOfUniqueElements);
		Assert.assertEquals(setOfUniqueElements.size(), 33);
	}
}