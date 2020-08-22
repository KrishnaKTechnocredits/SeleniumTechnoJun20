package jagdeesh;

import java.util.HashMap;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class EmpReportingMan {
	WebDriver driver;
	//To navigate to DemoTables tab
	void navigateToDemoTables(WebDriver driver) throws InterruptedException{
		this.driver = driver;
		driver.findElement(By.linkText("Demo Tables")).click();
		Thread.sleep(1000);
	}
	//1) Find how many employees reporting to each manager ID
	void countOfEmpReportingToEachMngr() {
		HashMap<String, Integer> empCountToMngr = new HashMap<String, Integer>();
		int rowCount = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for (int index = 1; index <= rowCount; index++) {
			String managerId = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[" + index + "]/td[4]")).getText();
			if (empCountToMngr.containsKey(managerId))
				empCountToMngr.put(managerId, empCountToMngr.get(managerId) + 1);
			else
				empCountToMngr.put(managerId, 1);
		}
		Set<String> keys = empCountToMngr.keySet();
		int maxEmpCount = 0;
		String manager = "";
		for (String managerid : keys) {
			System.out.println("ManagerID :" + managerid + " --> " + empCountToMngr.get(managerid) + " Employees");
			if ((empCountToMngr.get(managerid)) > maxEmpCount) {
				maxEmpCount = empCountToMngr.get(managerid);
				manager = managerid;
			}
		}
		// 2.Find maximum employees reporting to which manager ID
		System.out.println("================================");
		System.out.println("ManagerID having max employees --> " + manager);

	}
	public static void main(String[] agrs) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com");
		driver.manage().window().maximize();
		EmpReportingMan reporting = new EmpReportingMan();
		reporting.navigateToDemoTables(driver);
		reporting.countOfEmpReportingToEachMngr();
		driver.close();
	}
}