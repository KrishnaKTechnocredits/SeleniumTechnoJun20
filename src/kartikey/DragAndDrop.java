package kartikey;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import kartikey.base.PredDefindActions;

public class DragAndDrop extends PredDefindActions {
	WebDriver driver;
	void setup(String url) {
		driver=start(url);
	}
	@AfterTest
	void tearDown() {
		driver.close();
	}
	@BeforeTest
	void getUrl() {
		String url="https://demos.telerik.com/kendo-ui/dragdrop/index";
		setup(url);
	}

	void waitexplicitly(String path) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(path)));
	}
	@Test
	void dragDropDemo() {
	waitexplicitly("//div[@id='draggable']");
	WebElement source=	driver.findElement(By.xpath("//div[@id='draggable']"));
	WebElement target=	driver.findElement(By.xpath("//div[@id='droptarget']"));
	
	Actions actions = new Actions(driver);
	actions.dragAndDrop(source, target).build().perform();
	System.out.println(target.getText());		
	}

}
