package suparna.Assignment09;

//Find dublicate record and total no of unique EMPrecod 
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.chrome.ChromeDriver;

public class EmpObjComp extends EmpDetails {

	void validateUniqueEmpRow() {
		boolean flag = true;
		int empCnt = 0, duplicateCnt =0;
		String duplicateRecord;
		HashSet<String> doplicateEmp = new HashSet<String>();
		driver.findElement(By.linkText("Demo Tables")).click();
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
		Set<EmpDetails> empDetailSet = new HashSet<EmpDetails>();
		int rowCnt = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);

		for (int index = 1; index <= rowCnt; index++) {
			EmpDetails empDetails = new EmpDetails();
			empDetails.setEmpID(Integer.parseInt(
					driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[2]"))
							.getText()));
			empDetails.setEmpName(
					driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[3]"))
							.getText());
			empDetails.setEmpManagerID(Integer.parseInt(
					driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[4]"))
							.getText()));
			empDetails.setEmpDept(
					driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[5]"))
							.getText());
			empCnt++;
			if (!empDetailSet.add(empDetails)) {
				flag = false;
				duplicateCnt ++;
				
				duplicateRecord = empDetails.getEmpID() + " " + empDetails.getEmpName() + " "
						+ empDetails.getEmpManagerID() + " " + empDetails.getEmpDept();
				doplicateEmp.add(duplicateRecord);
			}
		}
		if (flag)
			System.out.println("No Dublicate records found");
		else {
			System.out.println("Total no of dupliacate  EMp record count is " + duplicateCnt);
			System.out.println("Below are dublicate records");
			for (String empRec : doplicateEmp) {
				System.out.println(empRec);
			}
		}
		System.out.println("Total no of EMP records are : " + empCnt);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmpObjComp emp = new EmpObjComp();
		emp.setDriver();
		emp.validateUniqueEmpRow();
		emp.tearDown();
	}

}
