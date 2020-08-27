package shruti;
import java.util.HashSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//find total unique row-->8 // set needed on unique values//create another class employee and set values
//Duplicate row emp ID and Empname

import shruti.predefinedActionspkg.PtrdefinedActions;
public class WebTableOps extends PtrdefinedActions {
	
	WebDriver driver;
	
	void setUp(){
		driver = start();
	}
	
	void findUniqueRow(){
		
		driver.findElement(By.id("demotable")).click();
		HashSet<Employee> empSet = new HashSet<>();
		int rowSize = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		
		for(int index=1; index<=rowSize; index++){
				
			Employee e1 = new Employee();
			e1.setEmpID(Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[2]")).getText()));
			e1.setEmoName(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[3]")).getText());
			e1.setManagerId(Integer.parseInt(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[4]")).getText()));
			e1.setEmpDept(driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+index+"]/td[5]")).getText());
			
			if(!empSet.add(e1)){
				System.out.println( "Dublicate row is empID: "+ e1.getEmpID()+" and Employee name is "+ e1.getEmpName());
			}	
		}
		System.out.println("Total number of uniqie row: " +empSet.size() );

	}
	public static void main(String[] args) {
		WebTableOps webTableOps = new WebTableOps();
		webTableOps.setUp();
		webTableOps.findUniqueRow();
	}

}
