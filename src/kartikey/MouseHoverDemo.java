package kartikey;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import kartikey.base.PredDefindActions;

public class MouseHoverDemo extends PredDefindActions {
	WebDriver driver;

	void setup(String url) {
		driver = start(url);
	}

	@AfterMethod
	void tearDown() {
		driver.close();
	}

	@BeforeMethod
	void getUrl() {
		String url = "https://demos.telerik.com/kendo-ui/dragdrop/index";
		setup(url);
	}

	void waitexplicitly(String path) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(path)));
	}

	void mousehover(String path) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(path))).build().perform();
	}

	void clickOnElement(String path) {
		driver.findElement(By.xpath(path)).click();
	}
	@Test
	void mouseHoverItemsList() {
		waitexplicitly("//button[text()='Frameworks ']");
		mousehover("//button[text()='Frameworks ']");		
		List<WebElement> listOfProjects= driver.findElements(By.xpath("//li[@class='TK-Menu-Item'][2]//a"));
		System.out.println(listOfProjects.size());
		for(WebElement element:listOfProjects) {
			System.out.println(element.getText());
		}
	}
}
