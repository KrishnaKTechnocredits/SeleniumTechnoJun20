/* Selenium Assignment-5 :  20th Aug 2020

Go to AutomationByKrishna website -> Basic Elements -> MultiDropdown
1) Print total number of options.
2) Select all even numbers.
3) Print all selected options.
4) Print all deselected options.
5) Using deselectAll() method, deselect all the options.
6) Now verify total number of selected options is zero [hint : getAllSelectedOptions()] */
package ajit.testScripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import ajit.base.PredefinedActions;

public class MultiDropListWithScroll extends PredefinedActions {
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	void selectOptionFromDropList() throws InterruptedException {
		driver.findElement(By.linkText("Basic Elements")).click();
		Thread.sleep(3000);
		// Scroll Then Page
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].scrollIntoView;", driver.findElement(By.xpath("//select[2]")));

		// WebElement multiSelectElement =
		// (driver.findElement(By.xpath("//select[2]")));
		// Select oSelect = new Select(multiSelectElement);
		Select oSelect = new Select(driver.findElement(By.xpath("//select[2]")));

		// Count total Number of Options
		List<WebElement> listOfOptions = oSelect.getOptions();
		System.out.println("Total Number of Options in the DropList are : " + listOfOptions.size());

		System.out.println("=======================================================");

		// Select All Even Numbers and Print Selected Options
		for (WebElement optionElement : listOfOptions) {
			if (Integer.parseInt(optionElement.getText()) % 2 == 0) {
				// optionElement.click();
				oSelect.selectByVisibleText(optionElement.getText());
				System.out.println(optionElement.getText() + " is Selected");
			} else
				System.out.println(optionElement.getText() + " is not Selected");
		}
		System.out.println("=======================================================");
		// DeSelect All and verify Total Number of Selected Option is Zero
		oSelect.deselectAll();
		System.out.println("Total Number of Selected Option after applying delectAll Methos is :"
				+ oSelect.getAllSelectedOptions().size());
		driver.close();
	}

	public static void main(String[] args) throws InterruptedException {
		MultiDropListWithScroll multiDropListWithScroll = new MultiDropListWithScroll();
		multiDropListWithScroll.setUp("http://automationbykrishna.com/");
		multiDropListWithScroll.selectOptionFromDropList();

	}

}
