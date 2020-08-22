package aashtha;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import aashtha.base.PredefinedActions;

/*Selenium Assignment-5 :  20th Aug 2020
Go to AutomationByKrishna website -> Basic Elements -> MultiDropdown
1) Print total number of options.
2) Select all even numbers.
3) Print all selected options.
4) Print all deselected options.
5) Using deselectAll() method, deselect all the options.
6) Now verify total number of selected options is zero [hint : getAllSelectedOptions()]
*/

public class TestDropDowns extends PredefinedActions {
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	void testMultidropdown() {
		driver.findElement(By.xpath("//a[text()='Basic Elements']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",
				driver.findElement(By.xpath("//select[@class='form-control']")));
		Select oselect = new Select(driver.findElement(By.xpath("//select[@class='form-control']")));
		List<WebElement> listOfOptions = oselect.getOptions();
		System.out.println("\n1) Total number of options in MultiDropdown : " + listOfOptions.size());
		for (WebElement currentOption : listOfOptions) {
			if (Integer.parseInt(currentOption.getText()) % 2 == 0)
				currentOption.click();
		}
		System.out.println("\n2) and 3) Below options are Selected/Deselected in MultiDropdown:");
		for (WebElement currentOption : listOfOptions) {
			if (currentOption.isSelected())
				System.out.println(" -> " + currentOption.getText() + " - selected");
			else
				System.out.println(" -> " + currentOption.getText() + " - deSelected");
		}
		oselect.deselectAll();
		System.out.println("\n4) All the options are deselected now");
		List<WebElement> listOfDeselectedOptions = oselect.getAllSelectedOptions();
		if (listOfDeselectedOptions.size() == 0) {
			System.out.println("\n5)All the options are deselected");
			System.out.println("  Total number of selected options is : " + listOfDeselectedOptions.size());
		} else
			System.out.println("\n5)All the options are not deselected");
		driver.close();
	}

	public static void main(String[] args) {
		TestDropDowns testDropDowns = new TestDropDowns();
		testDropDowns.setUp("http://automationbykrishna.com/");
		testDropDowns.testMultidropdown();
	}
}
