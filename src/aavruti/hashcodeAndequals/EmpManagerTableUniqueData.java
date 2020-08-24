package aavruti.hashcodeAndequals;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import aavruti.base.PredefinedActions;

public class EmpManagerTableUniqueData extends PredefinedActions {

	WebDriver driver;
	
	void launchBrowser() {
		driver = start("http://automationbykrishna.com");
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
	}
	
	void employeeInfo() {
		driver.findElement(By.linkText("Demo Tables")).click();
		
		Set<EmpManagerDetails> empDetailsList = new HashSet<EmpManagerDetails>();
		int totalRows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		
		for(int index=1;index<=totalRows;index++) {
			EmpManagerDetails empDetails = new EmpManagerDetails();
			
			String dynamicRowXpath = "//table[@class='table table-striped']/tbody/tr[" + index + "]";
			empDetails.setEmpId(Integer.parseInt(driver.findElement(By.xpath(dynamicRowXpath + "/td[2]")).getText()));
			empDetails.setEmpName(driver.findElement(By.xpath(dynamicRowXpath + "/td[3]")).getText());
			empDetails.setManagerId(Integer.parseInt(driver.findElement(By.xpath(dynamicRowXpath + "/td[4]")).getText()));
			empDetails.setEmpDept(driver.findElement(By.xpath(dynamicRowXpath + "/td[5]")).getText());
			
			if(!empDetailsList.add(empDetails)) {
				System.out.println("Duplicate Row --> empId : " + empDetails.getEmpId() + " empName : " + empDetails.getEmpName());
			}
		}		
		System.out.println("Total Number of Unique Rows --> " + empDetailsList.size());
	}
	
	public static void main(String[] args) {
		EmpManagerTableUniqueData empManagerTableUniqueData = new EmpManagerTableUniqueData();
		
		empManagerTableUniqueData.launchBrowser();
		empManagerTableUniqueData.employeeInfo();
		empManagerTableUniqueData.closeBrowser();
	}
	
	void closeBrowser() {
		driver.quit();
	}
}
