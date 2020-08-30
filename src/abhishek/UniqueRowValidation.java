package abhishek;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import abhishek.base.PredefinedActions;

public class UniqueRowValidation extends PredefinedActions {
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
		Set<Assignment_EmployeeMAnagerTable> empManagerSet = new HashSet<Assignment_EmployeeMAnagerTable>();
		int rows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for (int index = 1; index <= rows; index++) {
			Assignment_EmployeeMAnagerTable emp = new Assignment_EmployeeMAnagerTable();
			emp.setEmpID(Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[2]")).getText()));
			emp.setEmpName(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[3]")).getText());
			emp.setEmpManagerID(Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[4]")).getText()));
			emp.setEmpDept(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[5]")).getText());

			if (!empManagerSet.add(emp)) {
				System.out.println("Duplicate row -> Employee Id : " + emp.getEmpID() + " and Employee Name : "+ emp.getEmpName());
			}
		}
		System.out.println("Total no of unique rows---> " + empManagerSet.size());
	}

	public static void main(String[] args) {
		UniqueRowValidation uniqueRowValidation = new UniqueRowValidation();
		uniqueRowValidation.setUp("http://automationbykrishna.com");
		uniqueRowValidation.navigateDemoTable();
		uniqueRowValidation.validateUniqueRow();
		uniqueRowValidation.tearDown();
	}

}
