package shruti;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/*
 Go to AutomationByKrishna website -> Basic Elements -> MultiDropdown
 1) Print total number of options.
 2) Select all even numbers.
 3) Print all selected options.
 4) Print all deselected options.
 5) Using deselectAll() method, deselect all the options.
 6) Now verify total number of selected options is zero [hint : getAllSelectedOptions()]
 */
public class MultiSelectDropdown {

	void dropdownOperation() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				".//resources//windows//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com/");
		driver.findElement(By.xpath("//a[@id='basicelements']")).click();
		Thread.sleep(3000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver
				.findElement(By
						.xpath("//select[@class='form-control m-bot15']")));

		WebElement element = driver.findElement(By
				.xpath("//select[@class='form-control m-bot15']"));
		Select oselect = new Select(element);
		List<WebElement> dropdownValues = oselect.getOptions();
		System.out.println("Total number of options: " + dropdownValues.size());

		// Print Even/Selected Values
		System.out
				.println("Even number from list are selected and mentioned below: ");
		for (WebElement currentvalue : dropdownValues) {
			if (Integer.parseInt(currentvalue.getText()) % 2 == 0) {
				oselect.selectByVisibleText(currentvalue.getText());
				System.out.println(currentvalue.getText());
			}
		}
		System.out
				.println("odd number from list are deselected and mentioned below:");
		for (WebElement currentvalue : dropdownValues) {
			if (Integer.parseInt(currentvalue.getText()) % 2 != 0) {
				oselect.selectByVisibleText(currentvalue.getText());
				System.out.println(currentvalue.getText());
			}
		}
		// Using deselectAll() method, deselect all the options.
		try{
		oselect.deselectAll();
		System.out.println("All options are delected");
		}
		catch(UnsupportedOperationException e){
			System.out.println("Not able to deselect options");
		}

		// total number of items selected is zero
		if (oselect.getAllSelectedOptions().size() == 0)
			System.out.println("All items are deselected");
		else
			System.out.println("Some items are still selected");
	}

	public static void main(String[] args) {
		MultiSelectDropdown Assignment5 = new MultiSelectDropdown();
		try {
			Assignment5.dropdownOperation();
		} catch (InterruptedException e) {
			System.out.println("Something went wrong");
		}

	}
}
