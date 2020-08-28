package aashtha.aastha_assignment_9;

import java.util.HashSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import aashtha.base.*;
import aashtha.aastha_assignment_9.Employee;

public class EmployeeRecordVerification extends PredefinedActions {
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	void tearDown() {
		driver.close();
	}

	void navigateToDemoTable() throws InterruptedException {
		driver.findElement(By.id("demotable")).click();
		Thread.sleep(2000);
	}
	
	void findDuplicateRecordOfEmployee() {
		HashSet<Employee> empSet = new HashSet<Employee>();
		int totalRecords = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for(int index = 1; index <= totalRecords; index++) {
			Employee employee = new Employee();
			employee.setEmpId(Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[2]")).getText()));
			employee.setEmpName(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[3]")).getText());
			employee.setEmpManagerId(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[4]")).getText());
			employee.setEmpDept(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[5]")).getText());
			if(empSet.add(employee) == false)
				System.out.println("\nDuplicate row -> empId : " + employee.getEmpId() + " & empName : " + employee.getEmpName());
		}
		System.out.println("Total Number of Unique rows -> " + empSet.size());
	}

	public static void main(String[] args) throws InterruptedException {
		EmployeeRecordVerification employeeRecordVerification = new EmployeeRecordVerification();
		employeeRecordVerification.setUp("http://automationbykrishna.com/");
		employeeRecordVerification.navigateToDemoTable();
		employeeRecordVerification.findDuplicateRecordOfEmployee();
		employeeRecordVerification.tearDown();
	}
}
