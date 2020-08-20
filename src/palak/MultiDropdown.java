/*Selenium Assignment-5 : 20th Aug 2020

Go to AutomationByKrishna website -> Basic Elements -> MultiDropdown
1) Print total number of options.
2) Select all even numbers.
3) Print all selected options.
4) Print all deselected options.
5) Using deselectAll() method, deselect all the options.
6) Now verify total number of selected options is zero [hint : getAllSelectedOptions()]*/
package palak;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import palak.base.PredefinedActions;

public class MultiDropdown extends PredefinedActions {
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	void printAlloption() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Basic Elements']")).click();
		Thread.sleep(3000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//select[2]")));

		Select oSelect = new Select(driver.findElement(By.xpath("//select[2]")));
		List<WebElement> optionList = oSelect.getOptions();

		System.out.println("Total Number of Options in Dropdown : " + optionList.size());

		System.out.println("All Selected Options : ");
		for (WebElement webElement : optionList) {
			int elementNumber = Integer.parseInt(webElement.getText());
			if (elementNumber % 2 == 0) {
				oSelect.selectByVisibleText(String.valueOf(elementNumber));
				System.out.println(webElement.getText());
			}
		}

		// Print all deselected options
		System.out.println("All Deselected Options : ");
		for (WebElement webElement : optionList) {
			if (!webElement.isSelected()) {
				System.out.println(webElement.getText());
			}
		}
		oSelect.deselectAll();

		// verify total number of selected options
		if (oSelect.getAllSelectedOptions().isEmpty())
			System.out.println("Verified Total No of Selected option is : " + oSelect.getAllSelectedOptions().size());
		else
			System.out.println("All the options are not deselected in dropdown");
	}

	public static void main(String[] args) throws InterruptedException {
		MultiDropdown multiDropdown = new MultiDropdown();
		multiDropdown.setUp("http://automationbykrishna.com/");
		multiDropdown.printAlloption();
	}
}
