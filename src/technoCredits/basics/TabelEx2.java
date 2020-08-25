package technoCredits.basics;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import technoCredits.basics.base.PredefinedActions;

public class TabelEx2 extends PredefinedActions {
	WebDriver driver;

	void setUp() {
		driver = start();
	}

	void printCol() throws InterruptedException{
		driver.findElement(By.linkText("Demo Tables")).click();
		Thread.sleep(2000);
		List<WebElement> headers = driver.findElements(By.xpath("//table[@id='table1']/thead/tr/th"));
		System.out.println("Total Headers : " + headers.size());
		
		for(WebElement headerElement : headers) {
			System.out.print(headerElement.getText() + " ");
		}
		System.out.println();
	int totalRows = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();	
	for(int index=1;index<=totalRows;index++) {
		List<WebElement> firstrowCol = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td"));
		
		for(WebElement colElement : firstrowCol) {
			System.out.print(colElement.getText() + " ");
		}
		System.out.println();
	}
		
	}

	public static void main(String[] args) throws InterruptedException {
		TabelEx2 tableEx1 = new TabelEx2();
		tableEx1.setUp();
		tableEx1.printCol();
		int x = 10;
		String temp =  "Hello"+x+"Hi";
	}
}
