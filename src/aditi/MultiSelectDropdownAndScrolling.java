/*
Go to AutomationByKrishna website -> Basic Elements -> MultiDropdown
1) Print total number of options.
2) Select all even numbers.
3) Print all selected options.
4) Print all deselected options.
5) Using deselectAll() method, deselect all the options.
6) Now verify total number of selected options is zero [hint : getAllSelectedOptions()]
*/

package aditi;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import aditi.base.PredefinedActions;

public class MultiSelectDropdownAndScrolling extends PredefinedActions {
	WebDriver driver;

	void driverSetup(String url) {
		driver = start(url);
	}

	void multiSelectDropdownFunctions() throws InterruptedException {
		driver.findElement(By.xpath("//a[@id='basicelements']")).click();
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",
				driver.findElement(By.xpath("//select[@class='form-control']")));

		WebElement element = driver.findElement(By.xpath("//select[2]"));

		// Verify it's a multiselect Dropdown
		if (element.getAttribute("multiple").equals("true")) {
			System.out.println("It's a multiselect dropdown");
		}

		Select selectElement = new Select(element);
		// To print total number of options.
		System.out.println("\nTotal number of options in dropdown -> " + selectElement.getOptions().size());

		// Select all even numbers and print
		selectEvenValueOption(selectElement);
		System.out.println("\nSelected even values from the dropdown is as below: ");
		displayOptionList(selectElement.getAllSelectedOptions());

		// Print all deselected option values.
		System.out.println("\nDeselected options values from the dropdown is as below");
		displayOptionList(deselectedOptions(selectElement));

		// deselectAll()
		selectElement.deselectAll();
		System.out.println((selectElement.getAllSelectedOptions().size() == 0)
				? "\nTest Pass - All options are deselected successfully"
				: "\nTest Fail - Issue while deselecting dropdown options");
	}

	void selectEvenValueOption(Select selectElement) {
		for (WebElement element : selectElement.getOptions()) {
			String optionValue = element.getText();
			if ((Integer.parseInt(optionValue) % 2) == 0)
				selectElement.selectByVisibleText(optionValue);
		}
	}

	void displayOptionList(List<WebElement> optionList) {

		for (WebElement element : optionList) {
			System.out.println(" -> " + element.getText());
		}
	}

	List<WebElement> deselectedOptions(Select selectElement) {
		List<WebElement> allOptions = selectElement.getOptions();
		allOptions.removeAll(selectElement.getAllSelectedOptions());
		return allOptions;
	}

	public static void main(String[] args) {

		MultiSelectDropdownAndScrolling multiSelectDropdownAndScrolling = new MultiSelectDropdownAndScrolling();
		multiSelectDropdownAndScrolling.driverSetup("http://automationbykrishna.com");
		try {
			multiSelectDropdownAndScrolling.multiSelectDropdownFunctions();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			multiSelectDropdownAndScrolling.driver.close();
		}
	}
}
