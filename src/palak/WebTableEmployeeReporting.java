/*1) Find how many employees reporting to each manager ID. 
2) Find maximum employees reporting to which manager ID.*/
package palak;

import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTableEmployeeReporting {

	WebDriver setUp(String url) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		return driver;
	}

	WebDriver navigateToDemoTable(WebDriver driver) throws InterruptedException {
		driver.findElement(By.linkText("Demo Tables")).click();
		Thread.sleep(3000);
		return driver;
	}

	void printEmployeeReporting(WebDriver driver) {
		HashMap<String, Integer> empPerMngIdMap = new HashMap<>();
		int totalEmployeeSize = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr/td[4]")).size();
		for (int index = 1; index <= totalEmployeeSize; index++) {
			String EmployeeManagerID = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[4]")).getText();

			if (empPerMngIdMap.containsKey(EmployeeManagerID)) {
				empPerMngIdMap.put(EmployeeManagerID, empPerMngIdMap.get(EmployeeManagerID) + 1);
			} else {
				empPerMngIdMap.put(EmployeeManagerID, 1);
			}
		}
		System.out.println("No of Employees to each Manager Id");
		Set<String> keys = empPerMngIdMap.keySet();
		String deptId = "";
		int count = 0;
		for (String mngdeptId : keys) {
			System.out.println(mngdeptId + " -> " + empPerMngIdMap.get(mngdeptId));
			if (count < empPerMngIdMap.get(mngdeptId)) {
				count = empPerMngIdMap.get(mngdeptId);
				deptId = mngdeptId;
			}
		}
		System.out.println("Maximum employees "+ count +" reporting to manager Id "+deptId);
	} 

	public static void main(String[] args) throws InterruptedException {
		WebTableEmployeeReporting employeeReporting = new WebTableEmployeeReporting();
		WebDriver driver = employeeReporting.setUp("http://automationbykrishna.com/");
		driver = employeeReporting.navigateToDemoTable(driver);
		employeeReporting.printEmployeeReporting(driver);
	}
}
