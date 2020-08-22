/*1) Find how many employees reporting to each manager ID. 
2) Find maximum employees reporting to which manager ID.
*/
package barkha;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import barkha_base.PredefinedFunctions;

public class WebTableFuntions extends PredefinedFunctions {
	
	int empCount=0;
	String managerID;
	WebDriver driver;
	
	void setUp() {
		driver=start();
	}
	
	void handleWebTable() throws InterruptedException {
		driver.findElement(By.linkText("Demo Tables")).click();
		Thread.sleep(2000);
		int totalEmpCount=driver.findElements(By.xpath("//*[@class='table table-striped']/tbody/tr/td[4]")).size();
		
		HashMap<String,Integer> hashMap=new HashMap<String, Integer>();
		
		for (int index=1; index<=totalEmpCount; index++) {
			String empManagerID=driver.findElement(By.xpath("//*[@class='table table-striped']/tbody/tr["+index+"]/td[4]")).getText();
			
			if (hashMap.containsKey(empManagerID)) {
				hashMap.put(empManagerID, hashMap.get(empManagerID)+1);
				
				if (hashMap.get(empManagerID)>empCount){
					empCount=hashMap.get(empManagerID);
					managerID=empManagerID;
				}
			}else
				hashMap.put(empManagerID, 1);
		}
		System.out.println("No. of Employees reporting to each manager ID are: "+hashMap);
		System.out.println("Maximum employees with count -> "+empCount +" are reporting to manager ID is-> "+managerID);
		
		//To print emp count against each manager ID one by one
		
		/*Set<String> keys = hashMap.keySet();
		System.out.println("Number of employees reporting to each manager ID : ");
		int maxKey = 0;
		String mngId = "";
		for (String mapKey : keys) {
			if (hashMap.get(mapKey) > 1) {
				System.out.println(mapKey + " -> " + hashMap.get(mapKey));
			}
			// maximum employees reporting to which manager ID
			if (maxKey < hashMap.get(mapKey)) {
				maxKey = hashMap.get(mapKey);
				mngId = mapKey;
			}
		}
		System.out.println("Maximum employee reporting to ManagerId : ");
		System.out.println(mngId + " -> " + managerIdEmpMap.get(mngId));
		*/
		}
	
	void tearDown() {
		driver.close();
	}
	public static void main(String[] args) throws InterruptedException {
		WebTableFuntions webTable= new WebTableFuntions();
		webTable.setUp();
		webTable.handleWebTable();
		webTable.tearDown();
	}

}
