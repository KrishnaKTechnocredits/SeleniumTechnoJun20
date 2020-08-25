package technoCredits.basics;

import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import technoCredits.basics.base.PredefinedActions;

public class TabelEx7 extends PredefinedActions {
	WebDriver driver;

	void setUp() {
		driver = start();
	}

	void navigateToDemoTable() throws InterruptedException {
		driver.findElement(By.linkText("Demo Tables")).click();
		Thread.sleep(2000);
	}
	
	void printEmployeeCountPerDept() {
		HashMap<String,Integer> empIdMap = new HashMap<String,Integer>();
		
		int totalRows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for(int index=1;index<=totalRows;index++) {
			String empId = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[2]")).getText();
			//System.out.println(deptName);
			
			if(empIdMap.containsKey(empId)) {
				empIdMap.put(empId, empIdMap.get(empId)+1);
			}else {
				empIdMap.put(empId, 1);
			}
		}
		
		Set<String> keys= empIdMap.keySet();
		for(String empIdKey : keys) {
			if(empIdMap.get(empIdKey) >1)
				System.out.println(empIdKey + " -> " + empIdMap.get(empIdKey));
		}
	}

	public static void main(String[] args) throws InterruptedException {
		TabelEx7 tableEx1 = new TabelEx7();
		tableEx1.setUp();
		tableEx1.navigateToDemoTable();
		tableEx1.printEmployeeCountPerDept();

	}
}
