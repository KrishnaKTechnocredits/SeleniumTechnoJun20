package vaishnavi_SeleniumBasics;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// Find Unique rows count in table
// Print Duplicate row

public class EmployeeUsingEqualsHashCode {

	void uniqueData(WebDriver driver) {
		driver.findElement(By.xpath("//a[text()='Demo Tables']")).click();
		int tableRows = driver.findElements(By.xpath("//table[@class='table table-striped']//tbody/tr")).size();
		Set<EmployeeDetails> hsObject = new HashSet<EmployeeDetails>();

		for (int index = 1; index <= tableRows; index++) {
			EmployeeDetails employeeDetails = new EmployeeDetails();

			String xpathForRow = "//table[@class='table table-striped']//tbody/tr[" + index + "]";
			employeeDetails.setEmpId(Integer.parseInt(driver.findElement(By.xpath(xpathForRow + "/td[2]")).getText()));
			employeeDetails.setEmpName(driver.findElement(By.xpath(xpathForRow + "/td[3]")).getText());
			employeeDetails.setEmpManagerId(Integer.parseInt(driver.findElement(By.xpath(xpathForRow + "/td[4]")).getText()));
			employeeDetails.setEmpDepartmentName(driver.findElement(By.xpath(xpathForRow + "/td[5]")).getText());
			
			if (!hsObject.add(employeeDetails)){
				System.out.println("Duplicate Row --> empId : " + employeeDetails.getEmpId() + " empName : "
						+ employeeDetails.getEmpName());
			}
		}
		System.out.println("Total Number of Unique Rows: --> " + hsObject.size());
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com/");
		driver.manage().window().maximize();
		new EmployeeUsingEqualsHashCode().uniqueData(driver);
	}

}
