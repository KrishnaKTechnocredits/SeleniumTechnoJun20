package mahesh;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import mahesh.base.PredefinedActions;
import java.util.*;

public class WebTableUniqueRows extends PredefinedActions{
	WebDriver driver;
	
	void setUp() {
		driver = start();
	}
	void displayDuplicateRow() {
		driver.findElement(By.xpath("//a[@id='demotable']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		int countOfRows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		Set<EmployeeManagerData> managerDataList = new HashSet<EmployeeManagerData>();
		for (int index=1;index<=countOfRows;index++) {
			EmployeeManagerData emp = new EmployeeManagerData();
			emp.setEmpId(Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[2]")).getText()));
			emp.setEmpName(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[3]")).getText());
			emp.setManagerId(Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[4]")).getText()));
			emp.setManagerName(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[5]")).getText());
			if (!managerDataList.add(emp))
				System.out.println("Duplicate Row -> empId: " + emp.getEmpId() + " empName: " + emp.getEmpName());
		}
		System.out.println("Total Number of Unique Rows in table: " + managerDataList.size());
	}
	public static void main(String[] args) {
		WebTableUniqueRows webTableUniqueRows = new WebTableUniqueRows();
		webTableUniqueRows.setUp();
		webTableUniqueRows.displayDuplicateRow();
		webTableUniqueRows.driver.close();
	}
}
