package shruti;

import java.util.LinkedHashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import shruti.predefinedActionspkg.PtrdefinedActions;

//Unique managerid using MAP
public class TableEx2 extends PtrdefinedActions{

	WebDriver driver;
	void setUp() {
		driver = start();
	}
	
	void findmanager() throws InterruptedException{
		driver.findElement(By.id("demotable")).click();
		Thread.sleep(4000);
		Set<String> mnagerIDset = new LinkedHashSet<String>();
		int managerCount = driver.findElements(
				By.xpath("//table[@class = 'table table-striped']/tbody/tr"))
				.size();
		for (int index = 1; index <= managerCount; index++) {
			String managerid = driver.findElement(By.xpath("//table[@class = 'table table-striped']/tbody/tr["
									+ index + "]/td[4]")).getText();
			if(mnagerIDset.add(managerid))
			System.out.println(managerid);
		
	}
		System.out.println("total manager"+mnagerIDset.size());
		System.out.println(mnagerIDset);
	}

	
	
	public static void main(String[] args) throws InterruptedException {
		TableEx2 tableEx2 = new TableEx2();
		tableEx2.setUp();
		tableEx2.findmanager();
		
		
	}
}
