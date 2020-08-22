package mahesh;

import mahesh.base.PredefinedActions;
import java.util.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManagerTable extends PredefinedActions{
	WebDriver driver;
	
	void setUp() {
		driver = start();
	}
	
	void managerReporting() throws InterruptedException{
		driver.findElement(By.xpath("//a[@id='demotable']")).click();
		Thread.sleep(2000);
		HashMap<String, Integer> reportingMap = new HashMap<String, Integer>();
		List<WebElement> managerList = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr/td[4]"));
		for(WebElement element: managerList) {
			String managerId = element.getText();
			reportingMap.put(managerId, reportingMap.getOrDefault(managerId, 0)+1);
		}
		System.out.println("Number of Employees reporting to each manager ID: " + reportingMap);
		maxReportingTo(reportingMap);
	}
	
	void maxReportingTo(HashMap<String, Integer> reportingMap) {
		Set<String> managerID = reportingMap.keySet();
		String maxReportingManager= "";
		Integer maxNumberOfReporting =0;
		for (String manager: managerID) {
			if(reportingMap.get(manager) > maxNumberOfReporting) {
				maxReportingManager = manager;
				maxNumberOfReporting = reportingMap.get(manager);
			}
		}
		System.out.println("Manager with maximum reporting is " + maxReportingManager + " with " + maxNumberOfReporting + " employee reporting to him");
	}
	
	public static void main(String[] args) {
		ManagerTable managerTable = new ManagerTable();
		managerTable.setUp();
		try {
			managerTable.managerReporting();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			managerTable.driver.close();
		}
	}
}
