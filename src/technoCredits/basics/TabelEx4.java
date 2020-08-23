package technoCredits.basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import technoCredits.basics.base.PredefinedActions;

public class TabelEx4 extends PredefinedActions {
	WebDriver driver;

	void setUp() {
		driver = start();
	}

	void navigateToDemoTable() throws InterruptedException {
		driver.findElement(By.linkText("Demo Tables")).click();
		Thread.sleep(2000);
	}
	
	void printRowWithMatchingSurname() {
		for(int index=1;index<=5;index++) {
			WebElement surNameElement = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td[3]"));
			if(surNameElement.getText().equals("Sharma"))
				System.out.println(driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]")).getText());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		TabelEx4 tableEx1 = new TabelEx4();
		tableEx1.setUp();
		tableEx1.navigateToDemoTable();
		tableEx1.printRowWithMatchingSurname();

	}
}
