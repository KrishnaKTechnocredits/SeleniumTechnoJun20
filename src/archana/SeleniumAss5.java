package archana;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SeleniumAss5 extends PredefinedActions {

	void handleMultiDropdown() throws InterruptedException {
		driver.findElement(By.xpath("//a[@id='basicelements']")).click();
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.className("form-control")));
		// Select miltiDropdown
		WebElement multiSelect = driver.findElement(By.xpath("//select[@class='form-control']"));
		Select dropdownSelect = new Select(multiSelect);
		List<WebElement> listOfElement = dropdownSelect.getOptions();
		// Print total number of options
		System.out.println("Total number of Options-->" + listOfElement.size());
		// Print Selected Options
		System.out.println("Selected even number Options-->");
		for (WebElement element : listOfElement) {
			if (Integer.parseInt(element.getText()) % 2 == 0) {
				dropdownSelect.selectByVisibleText(element.getText());
				System.out.println(element.getText());
			}
		}
		// Deselect all options
		System.out.println("all Deselected Options-->");
		List<WebElement> selectedElement = dropdownSelect.getAllSelectedOptions();
		listOfElement.removeAll(selectedElement);
		for (WebElement element : listOfElement)
			System.out.println(element.getText());
		// verify selected options
		dropdownSelect.deselectAll();
		if (dropdownSelect.getAllSelectedOptions().size() == 0)
			System.out.println("All options are Deselected");
		else
			System.out.println("Total number of selected options -->" + dropdownSelect.getAllSelectedOptions().size());
	}

	public static void main(String[] args) {
		driver = start("http://automationbykrishna.com");
		try {
			new SeleniumAss5().handleMultiDropdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			driver.close();
		}
	}
}