package jagdeesh;

//Total number of unique rows
//Duplicate row

import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UniqueEmpDetails {
	WebDriver driver;
	void navigateToDemoTables(WebDriver driver){
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//div/div[2]/ul/li[3]/a")).click();
		HashSet<Employee> hsEmpDetails = new HashSet<Employee>();
		int rowCount=driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		for(int index=1;index<=rowCount;index++) {
			Employee emp = new Employee();
			emp.setEmpId(Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[2]")).getText()));
			emp.setEmpName(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[3]")).getText());
			emp.setMngrId(Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[4]")).getText()));
			emp.setDept(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[5]")).getText());
			if(!hsEmpDetails.add(emp))
				System.out.println("Duplicate row --> "+emp.empId +" : "+ emp.empName +" : "+ emp.mngrId +" : "+emp.dept);
		}
		System.out.println("Unique rows in the Table : "+hsEmpDetails.size());
	}
		 
	public static void main(String[] agrs) {
		UniqueEmpDetails uniqueEmployeeDetails = new UniqueEmpDetails();
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com");
		driver.manage().window().maximize();
		uniqueEmployeeDetails.navigateToDemoTables(driver);
		driver.close();

	}
}