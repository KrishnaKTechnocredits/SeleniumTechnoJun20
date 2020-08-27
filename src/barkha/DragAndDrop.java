package barkha;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import barkha_base.PredefinedFunctions;

public class DragAndDrop extends PredefinedFunctions {

	WebDriver driver;
	WebDriverWait wait;

	void setUP(String URL) {
		driver = start(URL);
	}

	void navigateToPage() {
		driver.findElement(By.xpath("//a[text()='Events']")).click();

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[@id='onetrust-accept-btn-handler' and text()='Accept Cookies']"))).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='droptarget']")));
	}

	void dragAndDropFunction() {
		WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement destination = driver.findElement(By.xpath("//div[@id='droptarget']"));

		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, destination).build().perform();

		String actualText = destination.getText();
		
		if (actualText.equals("You did great!"))
			System.out.println("Test Case PASS: Source successfully dragged into the target.");
		else
			System.out.println("Test Case FAIL.");
	}

	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) {
		DragAndDrop dragAndDrop = new DragAndDrop();
		dragAndDrop.setUP("https://demos.telerik.com/kendo-ui/dragdrop/index");
		dragAndDrop.navigateToPage();
		dragAndDrop.dragAndDropFunction();
		dragAndDrop.tearDown();
	}
}