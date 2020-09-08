package anup;
/*Assignment - 16 : 6th Sep'2020

1) Find Unique Office location
2) Find Unique Position

URL : https://editor.datatables.net/examples/extensions/excel*/

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import anup.basics.PredefinedActions;

public class UniqueOfficeLocation extends PredefinedActions {
	WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void uniqueofficelocation() {
		// Number of rows available
		HashSet<String> officeloc = new HashSet<String>();
		HashSet<String> officepos = new HashSet<String>();
		double count = 0;
		String str = driver.findElement(By.xpath("//div[text()='Showing 1 to 10 of 57 entries']")).getText();
		String[] array = str.split(" ");
		int[] num = new int[array.length];

		for (int index = 0; index < array.length; index++) {
			try {
				num[index] = Integer.parseInt(array[index]);
			} catch (Exception e) {
				System.out.println();
			}
		}
		
		double totalPages = Math.ceil(num[5] / num[3]);
		WebElement nextButton = driver.findElement(By.xpath("//a[@class='paginate_button next']"));
		while (count <= totalPages) {
			int rows = driver.findElements(By.xpath("//table[@id='example']/tbody/tr")).size();
			for (int index = 1; index <= rows; index++) {
				String officelocation = driver.findElement(By.xpath("//table[@id='example']//tr[" + index + "]/td[5]"))
						.getText();
				officeloc.add(officelocation);
				String officeposition = driver.findElement(By.xpath("//table[@id='example']//tr[" + index + "]/td[4]"))
						.getText();
				officepos.add(officeposition);
			}
			count++;
			try {
				if (driver.findElement(By.xpath("//a[@class='paginate_button next']")).isDisplayed()) {
					driver.findElement(By.xpath("//a[@class='paginate_button next']")).click();
				}
			} catch (Exception e) {
				break;
			}
		}
		System.out.println(officeloc);
		System.out.println("Total Unique Location " + officeloc.size());
		System.out.println(officepos);
		System.out.println("Total Unique position " + officepos.size());
	}

	@AfterClass
	public void teardown() {
		driver.close();
	}
}
