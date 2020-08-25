package kartikey;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import kartikey.base.PredDefindActions;

public class WebTableEmployeeManager extends PredDefindActions {
	private WebDriver driver;
	
	WebDriver setup(String url) {
	return	driver= start(url);
	}
	
	String getUrl() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please provide the url");
		String url = scanner.next();
		return url;
	}
	void employeeAndManager(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//a[text()= 'Demo Tables']")).click();
		Thread.sleep(3000);
		List<WebElement> listOfManagerID= driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr/td[4]"));
		
		/*LinkedHashMap<String, Integer>lhm=new LinkedHashMap<String, Integer>();
		for(WebElement element:listOfManagerID) {
			if(lhm.containsKey(element.getText())) 
				lhm.put(element.getText(), lhm.get(element.getText())+1);
			else
				lhm.put(element.getText(), 1);		
		}
		System.out.println(lhm);*/
//************************************<By Set>***********************************		
		HashSet<String> hs= new HashSet<String>();
		for(WebElement element:listOfManagerID) {
			hs.add(element.getText());
		}
		//System.out.println(hs);		
		int maxEmpManager=0;
		String maxEmpManagerID="";
		for(String str:hs) {			
			int cnt=0;
			for(WebElement element:listOfManagerID) {
				if(str.equals(element.getText()))
					cnt++;				
			}
			System.out.println(cnt+" : No. of employess reports to Manager "+str);	
			if(cnt>maxEmpManager) {
				maxEmpManager=cnt;
				maxEmpManagerID=str;
			}
		}	
		System.out.println("ManagerID:"+maxEmpManagerID+" is having maximum no. of employees :"+maxEmpManager);	
		driver.close();
	}
	public static void main(String[] args) throws InterruptedException {
		WebTableEmployeeManager webTableEmployeeManager= new WebTableEmployeeManager();
		webTableEmployeeManager.employeeAndManager(webTableEmployeeManager.setup(webTableEmployeeManager.getUrl()));
		// http://www.automationbykrishna.com
	}

}
