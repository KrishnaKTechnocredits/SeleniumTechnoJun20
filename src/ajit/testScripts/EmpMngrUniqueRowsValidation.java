package ajit.testScripts;

import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ajit.base.PredefinedActions;

public class EmpMngrUniqueRowsValidation extends PredefinedActions {
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}
	
	void navigateToDemoTables() {
		driver.findElement(By.linkText("Demo Tables")).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='table table-striped']/tbody")));
	}

	void displayDuplicateRow() {
		int rowsCount = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		Set<EmployeeManagerDetails> managerDetailsList = new HashSet<EmployeeManagerDetails>();
		for (int index = 1; index <= rowsCount; index++) {
			EmployeeManagerDetails employeeManagerDetails = new EmployeeManagerDetails();
			employeeManagerDetails.setEmpId(Integer.parseInt(
					driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[2]"))
							.getText()));
			employeeManagerDetails.setEmpName(
					driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[3]"))
							.getText());
			employeeManagerDetails.setManagerId(Integer.parseInt(
					driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[4]"))
							.getText()));
			employeeManagerDetails.setManagerName(
					driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[5]"))
							.getText());
			if (!managerDetailsList.add(employeeManagerDetails))
				System.out.println(
						"Duplicate Row Data ====> empId: " + employeeManagerDetails.getEmpId() + " and empName: " + employeeManagerDetails.getEmpName());
		}
		System.out.println("Total Number of Unique Rows in table====> " + managerDetailsList.size());
	}

	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) {
		EmpMngrUniqueRowsValidation empMngrUniqueRowsValidation = new EmpMngrUniqueRowsValidation();
		empMngrUniqueRowsValidation.setUp("http://automationbykrishna.com/");
		empMngrUniqueRowsValidation.navigateToDemoTables();
		empMngrUniqueRowsValidation.displayDuplicateRow();
		empMngrUniqueRowsValidation.tearDown();
	}
}
