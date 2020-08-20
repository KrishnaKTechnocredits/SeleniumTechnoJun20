package mahesh;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import mahesh.base.PredefinedActions;

public class DropdownSelect extends PredefinedActions{
	
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}
	
	void selectValueFromDropdown() throws InterruptedException{
		if (driver.getTitle().equals("Login Signup Demo")) {
			driver.manage().window().maximize();
			driver.findElement(By.xpath("//a[text()='Basic Elements']")).click();
			Thread.sleep(3000);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//select[@class ='form-control']")));
			Select select = new Select(driver.findElement(By.xpath("//select[@class ='form-control']")));
			
			System.out.println("Total option in dropdown are: " + allOptions(select).size());
			selectEvenValue(select);
			
			System.out.println("Selected Values from the list are below:");
			displayListValues(selectOptions(select));
			
			System.out.println("UnSelected Values from the list are below:");
			displayListValues(unSelectOptions(select));
			
			select.deselectAll();
			if (select.getAllSelectedOptions().size() == 0) 
				System.out.println("All options are deselected from the dropdown");
			else
				System.out.println("All options are not deselected from the dropdown");
			driver.close();
		}
	}
	
	List<WebElement> allOptions(Select select){
		return select.getOptions();
	}
	
	List<WebElement> selectOptions(Select select){
		return select.getAllSelectedOptions();
	}
	
	List<WebElement> unSelectOptions(Select select){
		List<WebElement> list = allOptions(select);
		list.removeAll(selectOptions(select));
		return list;
	}
	
	void selectEvenValue(Select select) {
		for(WebElement element: allOptions(select)) {
			String value = element.getText();
			if ((Integer.parseInt(value) % 2) == 0) {
				select.selectByVisibleText(value);;
			}
		}
	}
	
	void displayListValues(List<WebElement> list) {
		for(WebElement element: list) {
			System.out.println(element.getText());
		}
	}
	
	public static void main(String[] args) {
		DropdownSelect dropdownSelect = new DropdownSelect();
		dropdownSelect.setUp("http://automationbykrishna.com");
		try {
			dropdownSelect.selectValueFromDropdown();
		} catch (InterruptedException ie) {
			System.out.println(ie.getMessage());
		}
	}
}
