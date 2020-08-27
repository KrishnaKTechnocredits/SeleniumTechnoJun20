package aavruti;

import aavruti.base.PredefinedActions;

import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmpManagerWebTable extends PredefinedActions{

	WebDriver driver;

	void launchBrowser() {
		driver = start();
	}

	void navigateToDemoTable() throws InterruptedException{
		driver.findElement(By.xpath("//a[text()='Demo Tables']")).click();	
		Thread.sleep(3000);
	}

	//Find how many employees reporting to each manager ID.
	HashMap<String,Integer> findCountOfEmpReportingToManager() {

		HashMap<String,Integer> totalEmpPerManager = new HashMap<>();

		int totalRows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		String empID = ",";

		for(int index=1;index<=totalRows;index++) {
			String managerID = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[4]")).getText();
			String empIDDuplicate = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[2]")).getText();

			if(totalEmpPerManager.containsKey(managerID)) {
				if(!empID.contains("," + empIDDuplicate + ","))				
					totalEmpPerManager.put(managerID, totalEmpPerManager.get(managerID)+1);				
			}
			else{
				totalEmpPerManager.put(managerID, 1);
				empID += empIDDuplicate + ",";
			}
		}
		System.out.println("\nManagerID = Employee Count");
		System.out.println(totalEmpPerManager);
		return totalEmpPerManager;
	}

	//Find maximum employees reporting to which manager ID.
	void findManagerWithMaxEmpCount() {
		HashMap<String,Integer> totalEmpPerManager = findCountOfEmpReportingToManager();

		int max = 0;
		Set<String> empCountKeySet = totalEmpPerManager.keySet();

		for(String empCount : empCountKeySet) {
			if(totalEmpPerManager.get(empCount) > max) {
				max = totalEmpPerManager.get(empCount);
			}
		}

		System.out.println("\nManagerID with maximum Employee Count");
		for(String empCount : empCountKeySet) {
			if(totalEmpPerManager.get(empCount) == max) {
				System.out.println(empCount + " --> " + totalEmpPerManager.get(empCount));
			}
		}
	}

	void closeBrowser() {
		driver.quit();
	}

	public static void main(String[] args) throws InterruptedException{
		EmpManagerWebTable empManagerWebTable = new EmpManagerWebTable();
		try {
			empManagerWebTable.launchBrowser();
			empManagerWebTable.navigateToDemoTable();
			empManagerWebTable.findManagerWithMaxEmpCount();			
		}
		catch(IllegalStateException ie) {
			System.out.println("Launch Browser unsuccessfull " + ie.getMessage());
			System.exit(0);
		}
		catch(NoSuchElementException ne) {
			System.out.println("Given Element not found " + ne.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			empManagerWebTable.closeBrowser();
		}
	}
}