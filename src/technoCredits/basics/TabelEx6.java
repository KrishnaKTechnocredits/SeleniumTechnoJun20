package technoCredits.basics;

import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import technoCredits.basics.base.PredefinedActions;

public class TabelEx6 extends PredefinedActions {
	WebDriver driver;

	void setUp() {
		driver = start();
	}

	void navigateToDemoTable() throws InterruptedException {
		driver.findElement(By.linkText("Demo Tables")).click();
		Thread.sleep(2000);
	}
	
	void printEmployeeCountPerDept() {
		HashMap<String,Integer> deptNameMap = new HashMap<String,Integer>();
		
		int totalRows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for(int index=1;index<=totalRows;index++) {
			String deptName = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[5]")).getText();
			//System.out.println(deptName);
			
			if(deptNameMap.containsKey(deptName)) {
				deptNameMap.put(deptName, deptNameMap.get(deptName)+1);
			}else {
				deptNameMap.put(deptName, 1);
			}
		}
		
		Set<String> keys= deptNameMap.keySet();
		for(String deptIdKey : keys) {
			//if(deptNameMap.get(deptIdKey) >2)
				System.out.println(deptIdKey + " -> " + deptNameMap.get(deptIdKey));
		}
	}

	public static void main(String[] args) throws InterruptedException {
		TabelEx6 tableEx1 = new TabelEx6();
		tableEx1.setUp();
		tableEx1.navigateToDemoTable();
		tableEx1.printEmployeeCountPerDept();

	}
}
