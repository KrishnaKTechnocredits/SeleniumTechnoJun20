/* Selenium Assignment-5 :  20th Aug 2020
Go to AutomationByKrishna website -> Basic Elements -> MultiDropdown
1) Print total number of options.
2) Select all even numbers.
3) Print all selected options.
4) Print all deselected options.
5) Using deselectAll() method, deselect all the options.
6) Now verify total number of selected options is zero [hint : getAllSelectedOptions()] */

package raj;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import raj.selenium.base.PredefinedActions;

public class AutomateMultiDropdown extends PredefinedActions {
	static WebDriver driver;

	void setup(String url) {
		driver = start(url);
	}

	void multiDropdownOptionsByScrolling(WebDriver driver) throws InterruptedException {
		try {
			driver.findElement(By.xpath("//*[text() = \"Basic Elements\"]")).click();
			Thread.sleep(3000);

			// Declare JavascriptExecutor to scroll down page and find multidropdown
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//select[2]")));

			WebElement multiSelect = driver.findElement(By.xpath("//select[2]"));
			Select mSelect = new Select(multiSelect);

			// Total number of options count
			List<WebElement> list = mSelect.getOptions();
			System.out.println("Total Number of Options in Dropdown list is : " + list.size());

			// Select all even numbers from options and also Print all selected options
			System.out.println("All Selected Options are as belows : ");
			for (WebElement element : list) {
				if (Integer.parseInt(element.getText()) % 2 == 0) {
					element.click();
					System.out.println(element.getText());
				} 
			}

			// Print Deselected options 
			System.out.println("Deselected Options are as below : ");
			for (WebElement element : list) {
				if (Integer.parseInt(element.getText()) % 2 != 0) 
					System.out.println(element.getText());
			}

			// De-select all the selected options
			mSelect.deselectAll();

			// Verify using getAllSelectedOptions if select count is zero
			if (mSelect.getAllSelectedOptions().size() == 0)
				System.out.println("All Options are deselected...!! ");
			else
				System.out.println("All Options are not deselected...!!");
		} catch (NoSuchElementException ns) {
			ns.printStackTrace();
		} finally {
			driver.close();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		AutomateMultiDropdown multiDropdown = new AutomateMultiDropdown();
		multiDropdown.setup("http://automationbykrishna.com/");
		multiDropdown.multiDropdownOptionsByScrolling(driver);
	}

}
