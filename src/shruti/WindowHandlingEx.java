package shruti;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import shruti.predefinedActionspkg.PtrdefinedActions;

//Print page title and then close that window & open next window for same operation.*/
public class WindowHandlingEx extends PtrdefinedActions{

	WebDriver driver;
	
	void setUp(){
		driver = start();
	}
	
	void windowHandling(){
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String maintab = driver.getWindowHandle();
		int btnCnt = driver.findElements(By.xpath("//button")).size();
		System.out.println(btnCnt);
		for(int index=1; index<=btnCnt; index++){
			driver.findElement(By.xpath("//button["+index+"]")).click();
			
			Set<String> multiWin  = driver.getWindowHandles();
			Iterator<String> itr =multiWin.iterator();
			while(itr.hasNext()){
				String currentTab = itr.next();
				if(!maintab.equals(currentTab)){
					driver.switchTo().window(currentTab);
					System.out.println("You are navigated to-->" + driver.getTitle());
					
				}
			}
			driver.close();
			driver.switchTo().window(maintab);
		}
		System.out.println("You are on window-->" + driver.getTitle());
	}
	
	public static void main(String[] args) {
		WindowHandlingEx windowHandling = new WindowHandlingEx();
		windowHandling.setUp();
		windowHandling.windowHandling();
		
	}
}
