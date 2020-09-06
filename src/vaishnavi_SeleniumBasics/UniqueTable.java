package vaishnavi_SeleniumBasics;

import java.util.HashSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/* 1) Find Unique Office location
2) Find Unique Position */

import vaishnavi_Base.PredefinedAction;

public class UniqueTable extends PredefinedAction {

	WebDriver driver;
    WebDriverWait wait;  
	@BeforeTest
	void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
		wait = new WebDriverWait(driver,5);
	}

	HashSet<String> Unique(int columnNum) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span//a[@class='paginate_button ']")));
		int count = driver.findElements(By.xpath("//span//a[@class='paginate_button ']")).size();
		HashSet<String> uniqueElements = new HashSet<String>();
        int outerIndex=0;
		while(outerIndex<=count){
			int rowsCount = driver.findElements(By.xpath("//table[@id='example']//tbody//tr")).size();
			for (int index = 1; index <= rowsCount; index++) {
				String location = driver.findElement(By.xpath("//table[@id='example']//tbody//tr[" + index + "]//td["+columnNum+"]")).getText();
				uniqueElements.add(location);
			}
			driver.findElement(By.xpath("//a[text()='Next']")).click();
			count--;
		}
		return uniqueElements;
	}
	
	@Test(priority=1)
	void findUniqueOfficeLocations() {
		HashSet<String> officeLocations = Unique(5);
		System.out.println("Unique Office locations are: " +officeLocations);
	}
	
	@Test(priority=2)
	void findUniquePositions() {
		HashSet<String> positions = Unique(4);
		System.out.println("Unique Positions are: " +positions);
	}
	
	@AfterTest
	void close() {
		driver.close();
	}
}
