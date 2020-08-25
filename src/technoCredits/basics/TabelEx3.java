package technoCredits.basics;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import technoCredits.basics.base.PredefinedActions;

public class TabelEx3 extends PredefinedActions {
	WebDriver driver;

	void setUp() {
		driver = start();
	}

	void navigateToDemoTable() throws InterruptedException {
		driver.findElement(By.linkText("Demo Tables")).click();
		Thread.sleep(2000);
	}
	
	void printCol() throws InterruptedException {
		WebElement row = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[3]"));
		System.out.println(row.getText());
	}
	
	void printTable(){
		System.out.println(driver.findElement(By.xpath("//table[@id='table1']/tbody")).getText());
	}

	public static void main(String[] args) throws InterruptedException {
		TabelEx3 tableEx1 = new TabelEx3();
		tableEx1.setUp();
		tableEx1.navigateToDemoTable();
		tableEx1.printTable();

	}
}
