package suparna.Assignment07;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import suparna.basics.base.PredefineAction;

/*
 * find how many emp reporting to each manager and manager id having max no of emp reporting 
 */
public class ManagerIDOperation extends PredefineAction {

	public void findTotalEmpReportingCnt() throws InterruptedException {
		System.out.println("ManagerId \t Reporting EMP count ");
		driver.findElement(By.xpath("//a[@id='demotable']")).click();
		Thread.sleep(1000);
		HashMap<String, Integer> mangerReportingCnt = new HashMap<String, Integer>();
		int rowCnt = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for (int index = 1; index <= rowCnt; index++) {
			String managerID = driver
					.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td [4]"))
					.getText();
			if (mangerReportingCnt.containsKey(managerID))
				mangerReportingCnt.put(managerID, mangerReportingCnt.get(managerID) + 1);
			else
				mangerReportingCnt.put(managerID, 1);
		}
		Set<String> key = mangerReportingCnt.keySet();
		for (String managerId : key) {
			System.out.println(managerId + "\t \t" + mangerReportingCnt.get(managerId));
		}
		maxEmpReportingManager(mangerReportingCnt, key);
	}
	public void maxEmpReportingManager(HashMap<String, Integer> mangerReportingCnt, Set<String> key) {
		int temp = 0, max = 0;
		String maxManageid = "";
		for (String managerId : key) {
			temp = mangerReportingCnt.get(managerId);
			if (temp > max) {
				max = temp;
				maxManageid = managerId;
			}
		}
		System.out.println("Manager id to whom max employ reporting is ");
		System.out.println(max + "   employees reporting to  --> " + maxManageid);
	}
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ManagerIDOperation managerIDOperation = new ManagerIDOperation();
		managerIDOperation.setDriver();
		managerIDOperation.findTotalEmpReportingCnt();
		driver.close();
	}

}
