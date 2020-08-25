package raj;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import raj.selenium.base.PredefinedActions;

public class UniqueRowValidation extends PredefinedActions {
	WebDriver driver ;

	void setup() {
		driver = start("http://automationbykrishna.com");
	}

	void validateUniqueRowOfManagerTable() {
		driver.findElement(By.linkText("Demo Tables")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		Set<EmployeeManagerTable> setOfManager = new HashSet<EmployeeManagerTable>();
		int rowSize = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for(int index=1; index<=rowSize; index++) {
			EmployeeManagerTable emp = new EmployeeManagerTable();
			emp.setEmpID(Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[2]")).getText()));
			emp.setEmpName(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[3]")).getText());
			emp.setEmpManagerID(Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[4]")).getText()));
			emp.setEmpDept(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[5]")).getText());

			if(!setOfManager.add(emp)) {
				System.out.println("Duplicate row in Employee Manager Table is: ---> Employee Id : "+emp.getEmpID()+" and Employee Name : "+emp.getEmpName());				
			}
		}
		System.out.println("Total number of unique rows are :" +setOfManager.size());
	}

	public static void main(String[] args) {

		UniqueRowValidation rowValidation = new UniqueRowValidation();
		rowValidation.setup();
		rowValidation.validateUniqueRowOfManagerTable();

	}

}
