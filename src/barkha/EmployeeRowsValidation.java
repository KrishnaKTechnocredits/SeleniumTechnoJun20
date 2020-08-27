package barkha;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import barkha_base.PredefinedFunctions;

public class EmployeeRowsValidation extends PredefinedFunctions {

	WebDriver driver;

	void setUp(String URL) {
		driver = start(URL);
	}

	void validateRows() {
		
		HashSet<EmployeeRowsValidation_Employee> hashSet=new HashSet<EmployeeRowsValidation_Employee>();
		
		driver.findElement(By.linkText("Demo Tables")).click();
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
		
		int totalRecords = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		
		for (int index=1; index<=totalRecords; index++) {
			
			EmployeeRowsValidation_Employee employee=new EmployeeRowsValidation_Employee();
			
			employee.setEmpID(Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[2]")).getText()));
			employee.setEmpName(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[3]")).getText());
			employee.setEmpManagerID(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[4]")).getText());
			employee.setEmpDept(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[5]")).getText());
			
			//if duplicating then it will not add in HashSet and will return false
			if (hashSet.add(employee)==false)
				System.out.println("\nRow is duplicating having name and ID respectively=> empName : " + employee.getEmpName()+" &  empId : " + employee.getEmpID());
		}
		
		System.out.println("Total Number of Unique rows are => " + hashSet.size());
	}

	void tearBreak() {
		driver.close();
	}

	public static void main(String[] args) {
		EmployeeRowsValidation rowValidation = new EmployeeRowsValidation();
		rowValidation.setUp("http://automationbykrishna.com/");
		rowValidation.validateRows();
		rowValidation.tearBreak();
	}

}
