/*Assignment - 16 : 6th Sep'2020

1) Find Unique Office location
2) Find Unique Position

URL : https://editor.datatables.net/examples/extensions/excel	*/

package ajit.testScripts;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ajit.base.PredefinedActions;

public class ValidateUniqueOfficeLocationAndPosition extends PredefinedActions {

	WebDriver driver;
	WebDriverWait wait;
	String exptdText = "paginate_button next";

	@BeforeMethod
	public void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
		wait = new WebDriverWait(driver, 15);
	}

	@Test(priority = 1)
	public void findUniqueOfficeLoction() {

		String actualText = "";
		Set<String> setOfOfficeLoc = new LinkedHashSet<String>();

		do {
			List<WebElement> list = driver
					.findElements(By.xpath("//table[@id='example']//tbody//tr[@role='row']/td[5]"));
			for (WebElement word : list) {
				String text = word.getText();
				if (!setOfOfficeLoc.contains(text)) {
					setOfOfficeLoc.add(text);
				}
			}
			WebElement element = driver.findElement(By.xpath("//a[@id='example_next']"));
			actualText = element.getAttribute("class");
			if (actualText.equals(exptdText)) {
				element.click();
				wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath("//table[@id='example']//tbody//tr[@role='row']"))));
			}

		} while (actualText.equals(exptdText));

		System.out.println("Total Unique Location: " + setOfOfficeLoc.size());
		System.out.println(setOfOfficeLoc);
	}

	@Test(priority = 2)
	public void findUniqueOfficePosition() {

		String actualText = "";
		Set<String> setOfOfficePosition = new LinkedHashSet<String>();

		do {
			List<WebElement> list = driver
					.findElements(By.xpath("//table[@id='example']//tbody//tr[@role='row']/td[4]"));
			for (WebElement word : list) {
				String text = word.getText();
				if (!setOfOfficePosition.contains(text)) {
					setOfOfficePosition.add(text);
				}
			}
			WebElement element = driver.findElement(By.xpath("//a[@id='example_next']"));
			actualText = element.getAttribute("class");
			if (actualText.equals(exptdText)) {
				element.click();
				wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath("//table[@id='example']//tbody//tr[@role='row']"))));
			}

		} while (actualText.equals(exptdText));
		System.out.println("Total Unique Position: " + setOfOfficePosition.size());
		System.out.println(setOfOfficePosition);
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
