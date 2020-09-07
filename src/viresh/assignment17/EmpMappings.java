/*
Assignment - 17 : 6th Sep'2020

1) Office & Number of employees mapping
2) Position and Number of Employees mapping

URL : https://editor.datatables.net/examples/extensions/excel
*/

package viresh.assignment17;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import viresh.utility.SetUp;

public class EmpMappings extends SetUp {

	WebDriver driver;
	int totalDivisions;
	HashSet<String> offices;
	HashSet<String> positions;
	HashMap<String, Integer> empCountPerLocation;
	HashMap<String, Integer> empCountPerPosition;
	HashMap<String, Integer> hm;

	WebElement search;
	int sortedDivision, rowCnt, currentKey;

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
	@BeforeClass
	void officesAndPositions() {
		offices = new HashSet<>();
		positions = new HashSet<>();
		int currentRows = driver.findElements(By.xpath("//tbody/tr[@role='row']")).size();
		for (int index = 1; index <= currentRows; index++) {
			offices.add(driver.findElement(By.xpath("//tr[" + index + "]/td[5]")).getText());
			positions.add(driver.findElement(By.xpath("//tr[" + index + "]/td[4]")).getText());
		}
		//System.out.println("Offices are: " + offices.size() + " " + offices);
		//System.out.println("Positions are: " + positions.size() + " " + positions);
		System.out.println();
	}

	void getCount(HashSet<String> hs) {
		hm = new HashMap<>();
		search = driver.findElement(By.xpath("//input[@type='search']"));

		for (String currentValue : hs) {
			search.clear();
			search.sendKeys(currentValue);
			search.sendKeys(Keys.ENTER);
			sortedDivision = driver.findElements(By.xpath("//div[@id='example_paginate']/span/a")).size();
			if (sortedDivision > 1) {
				currentKey = (sortedDivision * 10) - 10;
			} else
				currentKey = 0;
			for (int index = sortedDivision; index <= sortedDivision; index++) {
				driver.findElement(By.xpath("//div[@id='example_paginate']/span/a[" + index + "]")).click();
				rowCnt = driver.findElements(By.xpath("//tbody/tr[contains(@id, 'row')]")).size();
				for (int inIndex = 1; inIndex <= rowCnt; inIndex++) {
					if (hm.containsKey(currentValue)) {
						currentKey = hm.get(currentValue);
						hm.put(currentValue, currentKey + 1);
					} else
						hm.put(currentValue, currentKey + 1);
				}
			}
		}
	}

	@Test
	void officewiseEmpCount() {
		getCount(offices);
		empCountPerLocation = new HashMap<>(hm);
		System.out.println("Employee per office location: " + empCountPerLocation);
		System.out.println();
	}

	@Test
	void positionwiseEmpCount() {
		getCount(positions);
		empCountPerPosition = new HashMap<>(hm);
		System.out.println("Employee per Position       : " + empCountPerPosition);
		System.out.println();
	}

	@AfterTest
	void releaseResources() {
		driver.close();
	}
}
