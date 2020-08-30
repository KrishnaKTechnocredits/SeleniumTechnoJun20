package anup;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import anup.basics.PredefinedActions;

public class EmployeeManagerUniqueRowsValidation extends PredefinedActions {
	WebDriver driver;
	void setup() {
		driver = start();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}
	void uniqueElementValidation() {
		driver.findElement(By.linkText("Demo Tables")).click();
		Set<EmployeeManager> empManagerSet = new HashSet<EmployeeManager>();
		int rowSize = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for(int index=1; index<=rowSize; index++) {
			EmployeeManager empMng = new EmployeeManager();
			empMng.setEmpID(Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[2]")).getText()));
			empMng.setEmpName(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[3]")).getText());
			empMng.setEmpManagerID(Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[4]")).getText()));
			empMng.setEmpDept(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[5]")).getText());
			if(!empManagerSet.add(empMng)) {
				System.out.println("Duplicate row :  Employee Id : "+empMng.getEmpID()+" and Employee Name : "+empMng.getEmpName());				
			}
		}
		System.out.println("Total no of unique rows---> "+empManagerSet.size());
		}
	
	void browserclose() {
		driver.close();
	}
	public static void main(String[] args) {
		EmployeeManagerUniqueRowsValidation employeeManagerUniqueRowsValidation = new EmployeeManagerUniqueRowsValidation();
		employeeManagerUniqueRowsValidation.setup();
		employeeManagerUniqueRowsValidation.uniqueElementValidation();
		employeeManagerUniqueRowsValidation.browserclose();
	}

}
