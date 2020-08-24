package palak;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import palak.base.PredefinedActions;

public class WebTableFindDuplicateRow extends PredefinedActions  {
	WebDriver driver;
	private void setUp(String url) {
		driver =start(url);
	}

	private void navigateToMenu() throws InterruptedException {
		driver.findElement(By.linkText("Demo Tables")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
	}

	void findDuplicateRow() {
		Set<WebTableEmployeeManager> employeeHashSet = new HashSet<WebTableEmployeeManager>();
		int totalRows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for (int index = 1; index <= totalRows; index++) {
			String employeeId = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[2]")).getText();
			String employeeName = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[3]")).getText();
			String empMngId = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[4]")).getText();
			String empDept = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[5]")).getText();
			
			WebTableEmployeeManager employeeManager = new WebTableEmployeeManager();
			
			employeeManager.setEmployeeId(employeeId);
			employeeManager.setEmployeeName(employeeName);
			employeeManager.setEmployeeManagerId(empMngId);
			employeeManager.setEmployeeDept(empDept);
			boolean flag = employeeHashSet.add(employeeManager);
			if (flag == false)
				System.out.println("Duplicate row -> Employee Id: "+ employeeId +" & Employee Name : "+ employeeName);
		}		
		System.out.println("Total number of unique rows -> "+employeeHashSet.size());
	}

	public static void main(String[] args) throws InterruptedException {
		WebTableFindDuplicateRow webTableFindDuplicateRow = new WebTableFindDuplicateRow();
		webTableFindDuplicateRow.setUp("http://automationbykrishna.com/");
		webTableFindDuplicateRow.navigateToMenu();
		webTableFindDuplicateRow.findDuplicateRow();
	}
}
