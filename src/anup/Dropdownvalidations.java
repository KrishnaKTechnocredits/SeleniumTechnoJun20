package anup;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import anup.basics.PredefinedActions;

/*Go to AutomationByKrishna website -> Basic Elements -> MultiDropdown
1) Print total number of options.
2) Select all even numbers.
3) Print all selected options.
4) Print all deselected options.
5) Using deselectAll() method, deselect all the options.
6) Now verify total number of selected options is zero [hint : getAllSelectedOptions()]*/

public class Dropdownvalidations extends PredefinedActions{
	
	WebDriver driver;
	void setup(String url) {
		driver = start(url);
	}
	void selectdropdown() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Basic Elements']")).click();;
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//select[@class='form-control']")));
		
		WebElement element = driver.findElement(By.xpath("//select[@class='form-control']"));
		Select oselect = new Select(element);
		List<WebElement> listofelement = oselect.getOptions();
		
		//1) Print total number of options.
		System.out.println("total number of options " + listofelement.size());
		
		//2) Select all even numbers.
		//3) Print all selected options.
		System.out.println("The selected options are : ");
		for(WebElement optionelement : listofelement) {
			if(Integer.parseInt(optionelement.getText())%2 ==0) {
				oselect.selectByVisibleText(optionelement.getText());
				System.out.println(optionelement.getText());
			}
			
		}
		
		//4) Print all deselected options.
		System.out.println("The deselected options are : ");
		for(WebElement optionelement : listofelement) {
			if(!optionelement.isSelected()) {
				System.out.println(optionelement.getText());
			}
		}
		
		//5) Using deselectAll() method, deselect all the options.
		oselect.deselectAll();
	
		//6) Now verify total number of selected options is zero [hint : getAllSelectedOptions()]*/
		if(oselect.getAllSelectedOptions().size() == 0) {
			System.out.println("All elements are deselected");
		}
		else {
			System.out.println("Test Fail: Not all elements are deselected.");
		}
		driver.close();
	
	}
	public static void main(String[] args) throws InterruptedException {
		Dropdownvalidations dropdownvalidations = new Dropdownvalidations();
		dropdownvalidations.setup("http://automationbykrishna.com/");
		dropdownvalidations.selectdropdown();
	}

}
