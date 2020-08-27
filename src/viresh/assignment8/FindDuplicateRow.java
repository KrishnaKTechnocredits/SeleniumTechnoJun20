package viresh.assignment8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindDuplicateRow {
	WebDriver driver;

	void setUp() {
		System.setProperty("webdriver.chrome.driver", ".//resources/windows/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
	}

	void tearDown() {
		driver.close();
	}

	void DupRow() {
		driver.findElement(By.xpath("//a[@id= 'demotable']")).click();
		int rowCnt = driver.findElements(By.xpath("//table[@class= 'table table-striped']/tbody/tr")).size();
		Set<EmpManager> set = new HashSet<>();

		for (int index = 1; index <= rowCnt; index++) {
			EmpManager eManager = new EmpManager();
			eManager.compareEmpId(Integer.parseInt(
					driver.findElement(By.xpath("//table[@class= 'table table-striped']/tbody/tr[" + index + "]/td[2]"))
							.getText()));
			eManager.compareEmpName(
					driver.findElement(By.xpath("//table[@class= 'table table-striped']/tbody/tr[" + index + "]/td[3]"))
							.getText());
			eManager.compareManager(
					driver.findElement(By.xpath("//table[@class= 'table table-striped']/tbody/tr[" + index + "]/td[4]"))
							.getText());
			eManager.compareDept(
					driver.findElement(By.xpath("//table[@class= 'table table-striped']/tbody/tr[" + index + "]/td[5]"))
							.getText());

			if (!set.add(eManager))
				System.out.println(
						"Duplicate Record with Emp ID: " + eManager.empId + " and employee name: " + eManager.eName);
		}
		System.out.println("Total unique rows are: " + set.size());
	}

	public static void main(String[] args) {
		FindDuplicateRow fdRow = new FindDuplicateRow();
		fdRow.setUp();
		fdRow.DupRow();
		fdRow.tearDown();
	}
}
