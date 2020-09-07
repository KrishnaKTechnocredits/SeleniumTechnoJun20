package mahesh;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import mahesh.base.PredefinedActions;
import org.testng.annotations.*;
import java.util.*;


public class EmployeeMappingWebTable extends PredefinedActions{
	WebDriver driver;
	
	@BeforeMethod
	void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
	}
	
	@AfterMethod
	void tearDown() {
		driver.close();
	}
	
	@Test
	void employeeMapping() {
		driver.manage().window().maximize();
		Map<String, Integer> mapEmployeePosition = new HashMap<String, Integer>();
		Map<String, Integer> mapEmployeeLocation = new HashMap<String, Integer>();
		boolean flag = true;
		while(flag == true) {
			int countOfRows = driver.findElements(By.xpath("//table[@id='example']/tbody/tr")).size();
			for (int index=1;index<=countOfRows;index++) {
				String position = driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+index+"]/td[4]")).getText();
				String officeLocation = driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+index+"]/td[5]")).getText();
				mapEmployeeLocation.put(officeLocation, mapEmployeeLocation.getOrDefault(officeLocation, 0)+1);
				mapEmployeePosition.put(position, mapEmployeePosition.getOrDefault(position, 0)+1);
			}
			if(driver.findElement(By.xpath("//a[@id='example_next']")).getAttribute("class").equals("paginate_button next"))
				driver.findElement(By.xpath("//a[@id='example_next']")).click();
			else
				flag=false;
		}
		System.out.println("Number of Employee per Position: " + mapEmployeePosition);
		System.out.println("Number of Employee per Office Location: " + mapEmployeeLocation);
	}
}
