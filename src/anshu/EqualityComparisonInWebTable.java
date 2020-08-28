package anshu;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.chrome.ChromeDriver;

public class EqualityComparisonInWebTable {
	void navigateDemoTable(WebDriver driver) {
		driver.findElement(By.linkText("Demo Tables")).click();
	}
  void tearDown(WebDriver driver) {
	  driver.close();
  }
	void validateUniqueRow(WebDriver driver) {
		Set<EmployeeDetails> empMngTableSet = new HashSet<EmployeeDetails>();
		int rowSize = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);

		for (int index = 1; index <= rowSize; index++) {
			EmployeeDetails empDetails = new EmployeeDetails();
			empDetails.setEmpID(Integer.parseInt(
					driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[2]"))
							.getText()));
			empDetails.setEmpName(
					driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[3]"))
							.getText());
			empDetails.setEmpManagerID(Integer.parseInt(
					driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[4]"))
							.getText()));
			empDetails.setEmpDept(
					driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[5]"))
							.getText());

			if (!empMngTableSet.add(empDetails)) {
				System.out.println("\nDuplicate row -> empId : " + empDetails.getEmpID() + " & empName : "
						+ empDetails.getEmpName());
			}
		}
		System.out.println(empMngTableSet.size());
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("http://automationbykrishna.com");
		System.out.println("AutomationByKrishna page is loaded.");

		EqualityComparisonInWebTable equalTest = new EqualityComparisonInWebTable();
		equalTest.navigateDemoTable(driver);
		equalTest.validateUniqueRow(driver);
		equalTest.tearDown(driver);
		
	}
}
