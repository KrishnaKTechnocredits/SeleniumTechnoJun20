package technoCredits.basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import technoCredits.basics.base.PredefinedActions;

public class ImplicitWaitTest extends PredefinedActions {
	WebDriver driver;
	
	private void setUp() {
		driver = start("http://automationbykrishna.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS); // 15 secs // 250 ms
	}
	
	void navigateToMenu(String menu) throws InterruptedException {
		driver.findElement(By.linkText(menu)).click();
	}
	
	void printTotalNumberRows() { 
		int totalRows = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();
		System.out.println(totalRows);
	}
	
	public static void main(String[] args) throws InterruptedException {
		 ImplicitWaitTest implTest = new ImplicitWaitTest();
		 implTest.setUp();
		 implTest.navigateToMenu("Demo Tables");
		 implTest.printTotalNumberRows();
	}
	
	private void tearDown() {
		driver.close();
	}
}
