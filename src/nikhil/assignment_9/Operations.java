package nikhil.assignment_9;

import java.util.HashSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import nikhil.base.PreDefinedActions;

public class Operations extends PreDefinedActions {
	WebDriver driver;
	
	void setUp() {
		driver = start();
	}
	
	void tearDown() {
		driver.close();
	}
	
	void navigateTo() {
		driver.findElement(By.linkText("Demo Tables")).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@class='table table-striped']")));
	}
	
	void collectUniqueInfo() {
		HashSet<Employee> empData = new HashSet<Employee> ();
		int totalRows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for(int index = 1; index <= totalRows; index++) {
			int employeeId = Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[2]")).getText());
			String employeeName = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[3]")).getText();
			int employeeManagerId = Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[4]")).getText());
			String employeeDept = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[5]")).getText();
			
			//Creating employee object
			Employee employee = new Employee();
			employee.setEmployeeId(employeeId);
			employee.setEmployeeName(employeeName);
			employee.setEmployeeManagerId(employeeManagerId);
			employee.setEmployeeDept(employeeDept);
			
			//Adding employee to the set
			if(!empData.add(employee)) {
				System.out.println("\nDuplicate Entry Row --->  Emp ID: "+employeeId+"  Emp Name: "+employeeName+"  Emp Mngr ID: "+employeeManagerId+"  Emp Dept: "+employeeDept);
			}
		}
		System.out.println("\nTotal Number of Unique Rows ---> "+empData.size());
	}
	
	public static void main(String[] args) {
		Operations operations = new Operations();
		operations.setUp();
		operations.navigateTo();
		operations.collectUniqueInfo();
		operations.tearDown();
	}
}
