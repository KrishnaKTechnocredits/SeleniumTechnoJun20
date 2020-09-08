package amita;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import amita.base.PredefinedActions;

public class FindEmployeeOffcAndPosition extends PredefinedActions {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
		wait = new WebDriverWait(driver, 5);
	}

	@Test
	public void employeePerOffcAndPosition() {

		Map<String, Integer> empPerOffc = new HashMap<String, Integer>();
		Map<String, Integer> empPerPosition = new HashMap<String, Integer>();
		int tabCount = driver.findElements(By.xpath("//span/a[@class='paginate_button ']")).size();

		int count = 0;
		while (count <= tabCount) {

			int rowCount = driver.findElements(By.xpath("//table[@id='example']/tbody/tr")).size();

			for (int index = 1; index <= rowCount; index++) {
				// Office location and employee mapping
				String officeLocation = driver.findElement(By.xpath("//table[@id='example']//tr[" + index + "]/td[5]"))
						.getText();
				if (empPerOffc.containsKey(officeLocation)) {
					empPerOffc.put(officeLocation, empPerOffc.get(officeLocation) + 1);
				} else {
					empPerOffc.put(officeLocation, 1);
				}

				// Employee Position location and employee mapping
				String officePosition = driver.findElement(By.xpath("//table[@id='example']//tr[" + index + "]/td[4]"))
						.getText();
				if (empPerPosition.containsKey(officePosition)) {
					empPerPosition.put(officePosition, empPerPosition.get(officePosition) + 1);
				} else {
					empPerPosition.put(officePosition, 1);
				}
			}
			
			//to check the next button is available or not and to click on it
			if (!driver.findElement(By.xpath("//a[text()='Next']")).getAttribute("class").contains("disabled")) {
				count++;
				driver.findElement(By.xpath("//a[text()='Next']")).click();
			} else {
				break;
			}
		}
		
		//Print Number of employees in different office locations 
		System.out.println("Number of employees in different office locations are :  ");
		for (String offcLocation : empPerOffc.keySet()) {
			System.out.println(offcLocation + " : " + empPerOffc.get(offcLocation));
		}
		
		////Print Number of employees in different position  
		System.out.println("\nNumber of employees in different positions are :  ");
		for (String position : empPerPosition.keySet()) {
			System.out.println(position + " : " + empPerPosition.get(position));
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}