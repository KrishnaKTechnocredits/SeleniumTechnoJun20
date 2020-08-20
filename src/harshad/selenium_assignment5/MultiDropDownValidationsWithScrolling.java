package harshad.selenium_assignment5;

/*Selenium Assignment-5 :  20th Aug 2020

Go to AutomationByKrishna website -> Basic Elements -> MultiDropdown
1) Print total number of options.
2) Select all even numbers.
3) Print all selected options.
4) Print all deselected options.
5) Using deselectAll() method, deselect all the options.
6) Now verify total number of selected options is zero [hint : getAllSelectedOptions()]*/

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import harshad.basepkg.PredefinedSetOfActions;

public class MultiDropDownValidationsWithScrolling extends PredefinedSetOfActions{
	
	WebDriver driver;
	
	void setURL(String url) {
		driver = start(url);
	}

	void performOperationsOnMultiDropDOwn() {
		
		driver.findElement(By.xpath("//a[text()='Basic Elements']")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		JavascriptExecutor je = (JavascriptExecutor)driver;
		je.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//select[2]")));
		
		WebElement multiSelectElement = driver.findElement(By.xpath("//select[2]"));
		Select selectElements = new Select(multiSelectElement);
		
		if(selectElements.isMultiple()) {
			List<WebElement> listOfOptions = selectElements.getOptions();
			
			System.out.println("\nTotal number of options in multiDropDownList are: "+listOfOptions.size());
			
			System.out.println("\nSelecting all Even numbers from multiDropDownlist:");
			for(WebElement element: listOfOptions) {
				if(Integer.parseInt(element.getText()) % 2 == 0) {
					selectElements.selectByVisibleText(element.getText());
				}
			}
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("\nAll selected options:");
			List<WebElement> selectedElements = selectElements.getAllSelectedOptions();
			for(WebElement element: selectedElements) {
				System.out.println("Selected option: "+element.getText());	
			
			}
			
			System.out.println("\nAll Deselected options:");
			List<WebElement> deselectedElements = selectElements.getOptions();
			deselectedElements.removeAll(selectedElements);
			for(WebElement element: deselectedElements) {
				System.out.println("Deselected option: "+element.getText());	
			
			}
			
			System.out.println("\nUsing deselectAll() method, deselecting all the options...");
			selectElements.deselectAll();
			System.out.println("Using deselectAll() method, deselecting of all the options done");
			
			System.out.println("\nVerifying total number of selected options...");
			System.out.println("\nAfter deselecting all options total number of selected options are: "+selectElements.getAllSelectedOptions().size());
			
			driver.close();

		}else {
			System.out.println("The select tag is does not have Multiselect options. Please check the locator...");
		}
		
	}
	
	public static void main(String[] args) {
		
		MultiDropDownValidationsWithScrolling multiDropDownValidationsWithScrolling = new MultiDropDownValidationsWithScrolling();
		multiDropDownValidationsWithScrolling.setURL("http://automationbykrishna.com/");
		multiDropDownValidationsWithScrolling.performOperationsOnMultiDropDOwn();
	}
}
