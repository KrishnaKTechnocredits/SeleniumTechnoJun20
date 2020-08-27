/*Assignment-9 : Find total number of unique rows in EMPLOYEE MANAGER table
 Print the duplicate row with Employee Id and Employee Name */
package amita;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import amita.base.PredefinedActions;

public class WebTableUniqueRowValidation extends PredefinedActions{
	
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}
	
	void tearDown() {
		driver.close();
	}

	void navigateDemoTable() {
		driver.findElement(By.linkText("Demo Tables")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
	}
	
	void validateUniqueRow() {
		Set<EmployeeManagerTableData> empManagerSet = new HashSet<EmployeeManagerTableData>();
		int rowSize = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for(int index=1; index<=rowSize; index++) {
			EmployeeManagerTableData emp = new EmployeeManagerTableData();
			emp.setEmpID(Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[2]")).getText()));
			emp.setEmpName(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[3]")).getText());
			emp.setEmpManagerID(Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[4]")).getText()));
			emp.setEmpDept(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[5]")).getText());
			
			if(!empManagerSet.add(emp)) {
				System.out.println("This WebTable has duplicate row ---> Employee Id : "+emp.getEmpID()+" and Employee Name : "+emp.getEmpName());				
			}
		}
		System.out.println("Total no of unique rows---> "+empManagerSet.size());						
	}
	public static void main(String[] args) {
		WebTableUniqueRowValidation uniqueRowValidation = new WebTableUniqueRowValidation();
		uniqueRowValidation.setUp("http://automationbykrishna.com");
		uniqueRowValidation.navigateDemoTable();
		uniqueRowValidation.validateUniqueRow();
		uniqueRowValidation.tearDown();
	}
}
