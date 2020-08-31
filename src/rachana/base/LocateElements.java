package rachana.base;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocateElements extends PredefinedActions{

	WebDriver driver;
	public LocateElements(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement findElementBy(String locator, String value) {

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
	
}
