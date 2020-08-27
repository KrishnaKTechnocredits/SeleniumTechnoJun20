package pranita;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pranita.basic.PredefinedFunctions;

public class Asgnmnt9_UniqueRowValidation extends PredefinedFunctions {
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
		Set<Asgnmnt9_EmployeeManagerTable> empManagerSet = new HashSet<Asgnmnt9_EmployeeManagerTable>();
		int rows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for (int index = 1; index <= rows; index++) {
			Asgnmnt9_EmployeeManagerTable emp = new Asgnmnt9_EmployeeManagerTable();
			emp.setEmpID(Integer.parseInt(
					driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[2]"))
							.getText()));
			emp.setEmpName(
					driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[3]"))
							.getText());
			emp.setEmpManagerID(Integer.parseInt(
					driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[4]"))
							.getText()));
			emp.setEmpDept(
					driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[5]"))
							.getText());

			if (!empManagerSet.add(emp)) {
				System.out.println("Duplicate row -> Employee Id : " + emp.getEmpID()
						+ " and Employee Name : " + emp.getEmpName());
			}
		}
		System.out.println("Total no of unique rows---> " + empManagerSet.size());
	}

	public static void main(String[] args) {
		Asgnmnt9_UniqueRowValidation uniqueRowValidation = new Asgnmnt9_UniqueRowValidation();
		uniqueRowValidation.setUp("http://automationbykrishna.com");
		uniqueRowValidation.navigateDemoTable();
		uniqueRowValidation.validateUniqueRow();
		uniqueRowValidation.tearDown();
	}

}
