/*Assignment - 16 : 6th Sep'2020
1) Find Unique Office locations.
2) Find Unique Position.
*/

package raj;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import raj.selenium.base.PredefinedActions;

public class UniqueValuesFromTable extends PredefinedActions{
		WebDriver driver;
		WebDriverWait wait;

		@BeforeMethod
		void setUp() {
			driver = start("https://editor.datatables.net/examples/extensions/excel");
			wait = new WebDriverWait(driver, 20);
			driver.manage().window().maximize();
		}

		@Test
		void findUniquePositionAndOfficeLocation() {
			boolean flag = true;
			
			//Unique Office location
			Set<String> uniqueOfficeLocation = new HashSet<String>(); 
			//Unique Position
			Set<String> uniquePosition = new HashSet<String>();
			
			while(flag == true) {
				int totalRowsInTable = driver.findElements(By.xpath("//table[@id='example']/tbody/tr")).size();
				for (int index=1; index <= totalRowsInTable; index++) {
					uniquePosition.add(driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+index+"]/td[4]")).getText());
					uniqueOfficeLocation.add(driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+index+"]/td[5]")).getText());
				}
				if(driver.findElement(By.xpath("//a[@id='example_next']")).getAttribute("class").equals("paginate_button next")) {
					driver.findElement(By.xpath("//a[@id='example_next']")).click();
				}else {
					flag=false;
				}
			}
			System.out.println("Unique Positions present in table are : " +uniquePosition);
			System.out.println("Unique Office Locations present in table are : " +uniqueOfficeLocation);
		}
		
		@AfterMethod
		void tearDown() {
			driver.close();
		}
	}

