package rachana.navigation;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import rachana.base.PredefinedActions;

public class HomePageMenuNavigation extends PredefinedActions {
	WebDriver driver;

	public HomePageMenuNavigation(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement navigateToHomePageMenu(String menuTabName) {

		switch (menuTabName) {
		case "Sing-On":
			return driver.findElement(By.xpath("//a[contains(text(), 'Sing-On')]"));
		case "Registration":
			return driver.findElement(By.xpath("//a[@id='registration2']"));
		case "Demo Tables":
			return driver.findElement(By.xpath("//a[@id='demotable']"));
		case "Basic Elements":
			return driver.findElement(By.xpath("//a[@id='basicelements']"));
		case "IFrame Demo":
			return driver.findElement(By.xpath("//a[@id='iframes']"));
		default:
			throw new NoSuchElementException();
		}

	}

}
