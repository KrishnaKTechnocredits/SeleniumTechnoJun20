package raj;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import raj.selenium.base.PredefinedActions;

public class PositionLocationMapping extends PredefinedActions {
	WebDriver driver;

	@BeforeMethod
	void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
		driver.manage().window().maximize();
	}

	@Test
	void employeeLocationAndPositionMapping() {
		boolean flag = true;
		
		//Position Map
		Map<String, Integer> employeePositionMap = new HashMap<String, Integer>();
		//Office location Map
		Map<String, Integer> employeeOfficeLocationMap = new HashMap<String, Integer>();
		
		while(flag == true) {
			int totalRowsInTable = driver.findElements(By.xpath("//table[@id='example']/tbody/tr")).size();
			
			for (int index=1; index<=totalRowsInTable; index++) {
				String position = driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+index+"]/td[4]")).getText();
				String officeLocation = driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+index+"]/td[5]")).getText();
				
				//Emp and office location mapping
				employeeOfficeLocationMap.put(officeLocation, employeeOfficeLocationMap.getOrDefault(officeLocation, 0)+1);
				//Emp and Position mapping
				employeePositionMap.put(position, employeePositionMap.getOrDefault(position, 0)+1);
			}
			
			if(driver.findElement(By.xpath("//a[@id='example_next']")).getAttribute("class").equals("paginate_button next")) {
				driver.findElement(By.xpath("//a[@id='example_next']")).click();
			}else {
				flag=false;
			}
		}
		System.out.println("Number of Employee based on Position : " +employeePositionMap);
		System.out.println("Number of Employee based on Office Location : " +employeeOfficeLocationMap);
	}

	@AfterMethod
	void tearDown() {
		driver.close();
	}

}
