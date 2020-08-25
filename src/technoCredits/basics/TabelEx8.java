package technoCredits.basics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import technoCredits.basics.base.PredefinedActions;

public class TabelEx8 extends PredefinedActions {
	WebDriver driver;

	void setUp() {
		driver = start();
	}

	void navigateToDemoTable() throws InterruptedException {
		driver.findElement(By.linkText("Demo Tables")).click();
		Thread.sleep(2000);
	}
	
	void printEmployeeCountPerDept() {
		HashMap<String,Integer> managerIdMap = new HashMap<String,Integer>();
		
		int totalRows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for(int index=1;index<=totalRows;index++) {
			String managerId = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[4]")).getText();
			//System.out.println(deptName);
			
			if(managerIdMap.containsKey(managerId)) {
				managerIdMap.put(managerId, managerIdMap.get(managerId)+1);
			}else {
				managerIdMap.put(managerId, 1);
			}
		}
		System.out.println(managerIdMap.size());
		
		Set<String> setOfManagerId = managerIdMap.keySet();
		System.out.println(setOfManagerId);
	}
	
	void printEmployeeCountPerDeptUsingSet() {
		Set<String> managerIdSet = new HashSet<String>();
		
		int totalRows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for(int index=1;index<=totalRows;index++) {
			String managerId = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[4]")).getText();
			//System.out.println(deptName);
			managerIdSet.add(managerId);
		}
		System.out.println(managerIdSet.size());
		System.out.println(managerIdSet);
	}

	void printEmployeeCountPerDeptUsingList() {
		List<String> managerIdList = new ArrayList<String>();
		
		int totalRows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for(int index=1;index<=totalRows;index++) {
			String managerId = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[4]")).getText();
			//System.out.println(deptName);
			if(!managerIdList.contains(managerId))
				managerIdList.add(managerId);
		}
		System.out.println(managerIdList.size());
		System.out.println(managerIdList);
	}
	
	void printEmployeeCountPerDeptUsingList1() {
		List<String> managerIdList = new ArrayList<String>();
		
		int totalRows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for(int index=1;index<=totalRows;index++) {
			String managerId = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[4]")).getText();
			managerIdList.add(managerId);
		}
		Set<String> setOfManagerId = new HashSet<String>(managerIdList);
		System.out.println(setOfManagerId.size());
		System.out.println(setOfManagerId);
	}
	
	public static void main(String[] args) throws InterruptedException {
		TabelEx8 tableEx1 = new TabelEx8();
		tableEx1.setUp();
		tableEx1.navigateToDemoTable();
		tableEx1.printEmployeeCountPerDeptUsingList1();
		tableEx1.tearDown();
		
	}
	
	void tearDown() {
		driver.close();
	}
}
