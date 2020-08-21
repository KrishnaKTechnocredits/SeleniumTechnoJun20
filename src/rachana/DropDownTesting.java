package rachana;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DropDownTesting {
	WebDriver driver;

	 void navigateToUrl(String url) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}

	 void selectMenuOption() {

		findElementBy("xpath","//a[@id='basicelements']").click();
	}

	WebElement findElementBy(String locator, String value) {

		switch (locator) {
		case "id":
			return driver.findElement(By.id(value));
		case "tagName":
			return driver.findElement(By.tagName(value));
		case "xpath":
			return driver.findElement(By.xpath(value));
		case "className":
			return driver.findElement(By.className(value));
		default:
			throw new NoSuchElementException("Element passed is not valid.");
		}
	}

	void pageScrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", findElementBy("xpath","//select[1]"));
	}

	void testMultiDropDownFunctionality() {
		WebElement multiselectdropdown = findElementBy("xpath","//select[@class='form-control']");
		Select selectElement = new Select(multiselectdropdown);

		// print total number of options.
		System.out.println(
				"Total number of options available in multiselect dropdown are :" + selectElement.getOptions().size());

		// Select all even numbers.
		List<WebElement> optionslist = selectElement.getOptions();

		for (WebElement ele : optionslist) {
			String options = ele.getText();
			if ((Integer.parseInt(options)) % 2 == 0) {
				selectElement.selectByVisibleText(options);
			}
		}
		// Print all selected options.
		List<WebElement> selectedlist = selectElement.getAllSelectedOptions();
		System.out.print("All Selected options are: ");
		for (WebElement ele : selectedlist) {
			System.out.print(ele.getText() + " , ");
		}

		// Print all deselected options.
		System.out.print("\nAll deselected optios are: ");
		for (WebElement ele : optionslist) {
			if (!ele.isSelected()) {
				System.out.print(ele.getText() + " , ");
			}
		}

		// Using deselectAll() method, deselect all the options.
		selectElement.deselectAll();

		// Now verify total number of selected options is zero [hint :
		// getAllSelectedOptions()]
		if (selectElement.getAllSelectedOptions().size() == 0) {
			System.out.print("\nAll options are deselected has been verified :Pass");
		} else {
			System.out.println("\nAll options are not deselected has been verified : Fail");
		}
	}

	void tearDown() {
		driver.close();
	}
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", ".//resources//windows//chromedriver.exe");
		DropDownTesting dropdowntesting = new DropDownTesting();
		String url="http://automationbykrishna.com/";
		dropdowntesting.navigateToUrl(url);
		Thread.sleep(2000);
		dropdowntesting.selectMenuOption();
		Thread.sleep(4000);
		dropdowntesting.pageScrollDown();
		dropdowntesting.testMultiDropDownFunctionality();
		Thread.sleep(2000);
		dropdowntesting.tearDown();
	}

}
