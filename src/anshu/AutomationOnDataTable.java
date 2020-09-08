package anshu;

import java.util.Iterator;
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

import anshu.base.PredefinedProperty;

public class AutomationOnDataTable extends PredefinedProperty {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
		wait = new WebDriverWait(driver, 15);
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

	@Test(priority=1)
	public void validationOnLocation() {
		
		String actualText = "";
		String exptdText = "paginate_button next";
		Set<String> officeSet = new LinkedHashSet<String>();

		do {
			List<WebElement> list = driver
					.findElements(By.xpath("//table[@id='example']//tbody//tr[@role='row']/td[5]"));
			for (WebElement word : list) {
			String text = word.getText();
				if (!officeSet.contains(text)) {
					officeSet.add(text);
				}
			}
			WebElement element = driver.findElement(By.xpath("//a[@id='example_next']"));
			actualText = element.getAttribute("class");
			if (actualText.equals("paginate_button next")) {
				element.click();
				wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath("//table[@id='example']//tbody//tr[@role='row']"))));
			}

		} while (actualText.equals("paginate_button next"));

		System.out.println(officeSet);
	}
	
	
	@Test(priority=2)
	public void validationOnPosition() {
	
		String actualText = "";
		Set<String> positionSet = new LinkedHashSet<String>();

		do {
			List<WebElement> list = driver
					.findElements(By.xpath("//table[@id='example']//tbody//tr[@role='row']/td[4]"));
			for (WebElement word : list) {
				String text = word.getText();
				if (!positionSet.contains(text)) {
					positionSet.add(text);
				}
			}
			WebElement element = driver.findElement(By.xpath("//a[@id='example_next']"));
			actualText = element.getAttribute("class");
			if (actualText.equals("paginate_button next")) {
				element.click();
				wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath("//table[@id='example']//tbody//tr[@role='row']"))));
			}

		} while (actualText.equals("paginate_button next"));

		System.out.println(positionSet);
	}
	
	
}
