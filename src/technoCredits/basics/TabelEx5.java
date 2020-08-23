package technoCredits.basics;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import technoCredits.basics.base.PredefinedActions;

public class TabelEx5 extends PredefinedActions {
	WebDriver driver;

	void setUp() {
		driver = start();
	}

	void navigateToDemoTable() throws InterruptedException {
		driver.findElement(By.linkText("Demo Tables")).click();
		Thread.sleep(2000);
	}
	
	void printEmployeeCountPerDept() {
		HashMap<String,Integer> empPerDeptMap = new HashMap<String,Integer>();
		
		int totalRows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for(int index=1;index<=totalRows;index++) {
			String deptName = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[5]")).getText();
			//System.out.println(deptName);
			
			if(empPerDeptMap.containsKey(deptName)) {
				
				empPerDeptMap.put(deptName, empPerDeptMap.get(deptName)+1);
			}else {
				empPerDeptMap.put(deptName, 1);
			}
		}
		System.out.println(empPerDeptMap);
	}

	public static void main(String[] args) throws InterruptedException {
		TabelEx5 tableEx1 = new TabelEx5();
		tableEx1.setUp();
		tableEx1.navigateToDemoTable();
		tableEx1.printEmployeeCountPerDept();

	}
}
