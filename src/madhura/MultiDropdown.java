package madhura;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import madhura.base.PredefinedActions;

/*Selenium Assignment-5 :  20th Aug 2020
Go to AutomationByKrishna website -> Basic Elements -> MultiDropdown
1) Print total number of options.
2) Select all even numbers.
3) Print all selected options.
4) Print all deselected options.
5) Using deselectAll() method, deselect all the options.
6) Now verify total number of selected options is zero [hint : getAllSelectedOptions()]*/
public class MultiDropdown extends PredefinedActions {
	WebDriver driver;

	void setup(String url) {
		driver = start(url);
	}

	void actionsOnOptions() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Basic Elements']")).click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",
				driver.findElement(By.xpath("//select[@class='form-control']")));

		Select oselect = new Select(driver.findElement(By.xpath("//select[@class='form-control']")));

		List<WebElement> optionsList = oselect.getOptions();
		System.out.println("Total number of options : " + optionsList.size());

		for (WebElement option : optionsList) {
			if (Integer.parseInt(option.getText()) % 2 == 0)
				oselect.selectByVisibleText(option.getText());
		}

		System.out.println("All Selected options : ");
		for (WebElement ele : optionsList) {
			if (ele.isSelected()) {
				System.out.println(ele.getText());
			}
		}

		System.out.println("All deselected options : ");
		for (WebElement ele : optionsList) {
			if (!ele.isSelected()) {
				System.out.println(ele.getText());
			}
		}

		oselect.deselectAll();

		if (oselect.getAllSelectedOptions().isEmpty()) {
			System.out.println("Deselect all options --> Passed");
		} else
			System.out.println("Deselect all options --> Failed");

		driver.quit();
	}

	public static void main(String[] args) throws InterruptedException {
		MultiDropdown multiDropdown = new MultiDropdown();
		multiDropdown.setup("http://automationbykrishna.com/");
		multiDropdown.actionsOnOptions();
	}
}