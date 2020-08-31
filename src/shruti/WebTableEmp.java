package shruti;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import shruti.predefinedActionspkg.PtrdefinedActions;

public class WebTableEmp extends PtrdefinedActions {
	WebDriver driver;

	void setUp() {
		driver = start();
	}

	void printcol() throws InterruptedException {
		// driver.findElement(By.xpath("//a[@id = 'demotable']")).click();
		driver.findElement(By.id("demotable")).click();
		Thread.sleep(4000);
		List<WebElement> header = driver.findElements(By
				.xpath("//table[@id = 'table1']/thead/tr/th"));
		System.out.println(header.size());
		for (WebElement headername : header) {
			System.out.print(headername.getText() + "\t");
		}
		System.out.println();
		for (int i = 1; i <= 4; i++) {
			List<WebElement> row = driver.findElements(By
					.xpath("//table[@id = 'table1']/tbody/tr[" + i + "]/td"));
			for (WebElement row1 : row) {
				System.out.print(row1.getText() + "\t");
			}
			System.out.println();
		}
	}

	void printrowsirname() throws InterruptedException {
		driver.findElement(By.id("demotable")).click();
		Thread.sleep(4000);
		List<WebElement> sz = driver.findElements(By
				.xpath("//table[@id = 'table1']/tbody/tr"));
		for(int index=1; index<=sz.size(); index++){
		WebElement sirname = driver.findElement(By.xpath("//table[@id = 'table1']/tbody/tr["+index+"]/td[3]"));
		if(sirname.getText().equals("Sharma")){
			System.out.println(driver.findElement(By.xpath("//table[@id = 'table1']/tbody/tr["+index+"]")).getText());
		}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		WebTableEmp webTableEmp = new WebTableEmp();
		webTableEmp.setUp();
		Thread.sleep(3000);
		// webTableEmp.printcol();
		webTableEmp.printrowsirname();

	}

}
