package rachana;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import rachana.assignment9.Employee;
import rachana.base.PredefinedActions;

public class TestEmployeeTable extends PredefinedActions{
	WebDriver driver;
	List<Employee> emplist = new ArrayList<Employee>();
	
	void setUp() {
		driver = start();
	}
	
	void navigateToMenu() {
		driver.findElement(By.xpath("//a[@id='demotable']")).click();
	}
	
	void fetchTableData() {
		
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr"));
		for(int i=1;i<=rows.size();i++) {
			Employee e = new Employee();
			e.setEmployeeId( driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+i+"]/td[2]")).getText());
			e.setEmployeeName(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+i+"]/td[3]")).getText());
			e.setEmployeeManagerId( driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+i+"]/td[4]")).getText());
			e.setEmployeeDepartment(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+i+"]/td[5]")).getText());
			emplist.add(e);
		}
	}
	
	void findUniqueRows() {
		Set<Employee> hs = new HashSet<Employee>(emplist);
		System.out.println("Unique number of rows in Employee table : "+hs.size());
	}
	
	void findduplicateIdAndName() {
		List<Employee> templist = new ArrayList<Employee>();
		for(Employee e :emplist) {
			if(templist.contains(e)) {
				System.out.println("Duplicate Employee ID and Name: "+e.getEmployeeId()+" "+e.getEmployeeName());
			}else {
				templist.add(e);
			}
		}
	}
	
	void tearDown() {
		driver.close();
	}
	
	public static void main(String[] args) {
		
		TestEmployeeTable testemptable = new TestEmployeeTable();
		testemptable.setUp();
		testemptable.navigateToMenu();
		testemptable.fetchTableData();
		testemptable.findUniqueRows();
		testemptable.findduplicateIdAndName();
		
 		
	}

}
