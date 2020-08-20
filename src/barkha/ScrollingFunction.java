/*Go to AutomationByKrishna website -> Basic Elements -> MultiDropdown
1) Print total number of options.
2) Select all even numbers.
3) Print all selected options.
4) Print all deselected options.
5) Using deselectAll() method, deselect all the options.
6) Now verify total number of selected options is zero [hint : getAllSelectedOptions()]*/

package barkha;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import barkha_base.PredefinedFunctions;

public class ScrollingFunction extends PredefinedFunctions {
	WebDriver driver;

	void getURL(String url) {
		driver = start(url);
	}

	void selectOptionFromDrodown() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Basic Elements']")).click();
		Thread.sleep(2000);

		// Scrolling function
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//select[1]")));

		// Print total number of options in the dropdown
		Select select = new Select(driver.findElement(By.xpath("//select[1]")));
		List<WebElement> listOfOptions = select.getOptions();
		System.out.println("Total Number of options present in the Dropdown are: "+listOfOptions.size());
		
		System.out.println("===========================================================");

		// Select all even numbers and print all selected options
		System.out.println("Selected Options are: ");
		for (WebElement num : listOfOptions) {
			if (Integer.parseInt(num.getText()) % 2 == 0) {
				select.selectByVisibleText(num.getText());
					System.out.print(num.getText() + " ");
				}
			}
			
		System.out.println("\n===========================================================");		
			
		// Print all deselected options
		System.out.println("Not Selected Options are: ");
		for (WebElement num : listOfOptions) {
			if (Integer.parseInt(num.getText()) % 2 != 0) {

				select.selectByVisibleText(num.getText());
				System.out.print(num.getText() + " ");
			}
		}
		
		System.out.println("\n===========================================================");

		//Using deselectAll() method, deselect all the optionsand verify total number of selected options is zero
		Select select1=new Select(driver.findElement(By.xpath("//select[2]")));
		select1.deselectAll();		
		Thread.sleep(2000);
		
		System.out.println("Total number of Selected Options after Deselecting all: "+select1.getAllSelectedOptions().size());
		
		driver.close();
	}	
	public static void main(String[] args) throws InterruptedException {
		ScrollingFunction scroll = new ScrollingFunction();
		scroll.getURL("http://automationbykrishna.com/");
		scroll.selectOptionFromDrodown();
	}
}	