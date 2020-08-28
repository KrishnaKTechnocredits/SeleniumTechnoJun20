package viresh.assignment11;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import viresh.utility.SetUp;

public class ActionsExercise extends SetUp {

	WebDriver driver;

	void dragDropDemo() {
		driver = setUp("https://demos.telerik.com/kendo-ui/dragdrop/index");
		driver.findElement(By.xpath("//a[text()='Area']")).click();

		Actions actions = new Actions(driver);

		WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement target = driver.findElement(By.xpath("//div[@id='droptarget']/div[1]"));
		String expectedStr = "You did great!";

		actions.dragAndDrop(source, target).build().perform();

		String actualStr = driver.findElement(By.xpath("//div[@id='droptarget']/div[1]")).getText();
		System.out.println("Test case to check Drag & Drop functinality>> ");
		System.out.println(actualStr.equals(expectedStr) ? "  - Test Pass: " + actualStr : "  - Test Fail");
		tearDown();
	}

	void doubleClickDemo() {
		driver = setUp();
		driver.findElement(By.xpath("//a[text()='Basic Elements']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Double-click on me']")));

		WebElement doubleClickLink = driver.findElement(By.xpath("//a[text()='Double-click on me']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", doubleClickLink);

		String expectedAlert = "You successfully double clicked it";

		Actions actions = new Actions(driver);
		actions.doubleClick(doubleClickLink).build().perform();

		Alert alert = driver.switchTo().alert();
		String actualAlert = alert.getText();
		alert.accept();
		System.out.println("Test case to double click on a link and capture the alert message >>");
		System.out.println(actualAlert.equals(expectedAlert) ? "  - Test Pass: " + actualAlert : "  - Test Fail");
		tearDown();
	}

	void mouseHoverDemo() {
		driver = setUp("https://www.amazon.in/");

		Actions actions = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id= 'nav-link-accountList']")));

		actions.moveToElement(driver.findElement(By.xpath("//span[text()='Hello, Sign in']"))).build().perform();

		driver.findElement(By.xpath("//span[text()= 'Your Account']")).click();
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[text()='Track, return, or buy things again']")));
		// driver.switchTo().defaultContent();
		String title = driver.getTitle();
		System.out.println("Test case to perform mouse hover >>");
		System.out.println(title.equals("Your Account") ? "  - Test Pass: Page Title is: " + title : "  - Test Fail");
		tearDown();
	}

	public static void main(String[] args) {
		ActionsExercise exer = new ActionsExercise();
		exer.dragDropDemo();
		System.out.println("");
		exer.doubleClickDemo();
		System.out.println();
		exer.mouseHoverDemo();
	}
}
