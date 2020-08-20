package anshu;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import anshu.base.PredefinedProperty;

public class SelectMultiDropDownOption extends PredefinedProperty {

	void PrintSelectedOption(WebDriver driver) throws InterruptedException {
		try {
			driver.findElement(By.xpath("//a[text()='Basic Elements']")).click();
			Thread.sleep(3000);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();",
					driver.findElement(By.xpath("//select[@class='form-control']")));

			WebElement multiSelect = driver.findElement(By.xpath("//select[@class='form-control']"));
			Select oselect = new Select(multiSelect);
			Thread.sleep(3000);

			// Getting count for total number of options
			List<WebElement> listofElements = oselect.getOptions();
			System.out.println('\n' + " 1). Total Number of Options: " + listofElements.size());

			// Select all even numbers from options and also Print all selected options
			System.out.println('\n' + " 2). Here is all selected even number present in this option");
			for (WebElement number : listofElements) {
				if (Integer.parseInt(number.getText()) % 2 == 0) {
					oselect.selectByVisibleText(number.getText());
					System.out.println("*" + number.getText());
				}
			}

			// Print all deselcted option
			System.out.println('\n' + " 3). Here is all dselected number present in this option");
			for (WebElement number : listofElements) {
				if (Integer.parseInt(number.getText()) % 2 != 0) {
					oselect.selectByVisibleText(number.getText());
					System.out.println("*" + number.getText());
				}
			}

			// Using deselectAll() method, deselect all the options.
			oselect.deselectAll();
			System.out.println('\n' + " 4).  All Options are deselected ");

			// Verfify total number of selected option is zero
			System.out.println('\n' + " 5).  Verfify all deselcted option ");
			if (oselect.getAllSelectedOptions().size() == 0) {
				System.out.println("TestPass : All items are deselected.");
			} else {
				System.out.println("TestFail : All items are not deselected");
			}
		} catch (NoSuchElementException ne) {

		} finally {
			driver.close();
		}
	}

	public static void main(String[] args) {
		SelectMultiDropDownOption selMultipleOption = new SelectMultiDropDownOption();
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationbykrishna.com");
		try {
			selMultipleOption.PrintSelectedOption(driver);
		} catch (InterruptedException ie) {

		}

	}
}
