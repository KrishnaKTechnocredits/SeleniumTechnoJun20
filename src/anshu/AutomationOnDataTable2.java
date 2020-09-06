package anshu;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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

public class AutomationOnDataTable2 extends PredefinedProperty {

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

	@Test(priority = 1)
	public void findNoOfEmployeeByLocation() {

		String actualText = "";
		String exptdText = "paginate_button next";
		Map<String, Integer> officeMap = new LinkedHashMap<String, Integer>();
		Set<String> officeSet = new LinkedHashSet();
		// Iterator<String> itr = officeSet.iterator();

		do {
			List<WebElement> list = driver
					.findElements(By.xpath("//table[@id='example']//tbody//tr[@role='row']/td[5]"));
			for (WebElement word : list) {
				String text = word.getText();
				if (officeMap.containsKey(text)) {
					officeMap.put(text, officeMap.get(text) + 1);
				} else {
					officeMap.put(text, 1);
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

		officeSet = officeMap.keySet();
		for (String employee : officeSet) {
			System.out.println(officeMap.get(employee) + " employees are reported to this " + employee);
		}

	}
	
	@Test(priority = 2)
	public void findNoOfEmployeeByPosition() {

		String actualText = "";
		String exptdText = "paginate_button next";
		Map<String, Integer> positionMap = new LinkedHashMap<String, Integer>();
		Set<String> PositionSet = new LinkedHashSet();
		

		do {
			List<WebElement> list = driver
					.findElements(By.xpath("//table[@id='example']//tbody//tr[@role='row']/td[4]"));
			for (WebElement word : list) {
				String text = word.getText();
				if (positionMap.containsKey(text)) {
					positionMap.put(text, positionMap.get(text) + 1);
				} else {
					positionMap.put(text, 1);
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

		PositionSet = positionMap.keySet();
		for (String employee : PositionSet) {
			System.out.println(positionMap.get(employee) + " employees are reported to this " + employee);
		}
		System.out.println(positionMap);

	}
	
	

}
