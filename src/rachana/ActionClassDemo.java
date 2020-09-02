package rachana;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rachana.base.PredefinedActions;
import rachana.navigation.HomePageMenuNavigation;

public class ActionClassDemo extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	Actions action;

	void setUp() {
		driver = start();
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='collapse navbar-collapse']")));
	}

	void navigateToMenu() {
		HomePageMenuNavigation menu = new HomePageMenuNavigation(driver);
		menu.navigateToHomePageMenu("Basic Elements").click();
	}

	void pageScrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header[text()=' Textarea ']")));
		js.executeScript("arguments[0].scrollIntoView();",
				driver.findElement(By.xpath("//header[text()=' Textarea ']")));
	}

	void doubleClickOnLinkAndVerify() {
		System.out.println("-Double Click Functionality Demo-");
		String expectedResult = "You successfully double clicked it";
		action = new Actions(driver);
		action.doubleClick(driver.findElement(By.xpath("//a[@ondblclick='doubleClick()']"))).build().perform();
		Alert alert = driver.switchTo().alert();
		String actualResult = alert.getText();
		alert.accept();
		System.out.println(expectedResult.equals(actualResult) ? "successfully double clicked: Pass"
				: "Unsuccessfull to double click");
	}

	void verifyDragAndDrop() {
		System.out.println("-Drag and Drop Functionality Demo-");
		action = new Actions(driver);
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='draggable']")));

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();",
				driver.findElement(By.xpath("//div[@class='box-col']/h4[text()='Axis']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='draggable']")));

		WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement target = driver.findElement(By.xpath("//div[@id='droptarget']"));
		action.dragAndDrop(source, target).perform();
		String expectedmsg = "You did great!";
		String actualmsg = target.getText();
		System.out.println(
				actualmsg.equals(expectedmsg) ? "Success in Drag and drop:Pass" : "Failure in drag and drop: fail");
	}

	void verifymousehover() {
		System.out.println("-MouseHover functionality Demo-");
		action = new Actions(driver);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//ul[@class='TK-Context-Menu TK-Menu']/li[2]")));
		WebElement menuFramework = driver.findElement(By.xpath("//ul[@class='TK-Context-Menu TK-Menu']/li[2]"));
		action.moveToElement(menuFramework).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//ul[@class='TK-Dropdown TK-Dropdown--Gray TK-Dropdown--Full TK-Dropdown--Mobile']/li")));
		int menuFrameworkOptions = driver.findElements(By.xpath("//ul[@class='TK-Context-Menu TK-Menu']/li[2]/ul/li"))
				.size();
		if (menuFrameworkOptions > 0) {
			System.out.println("Total options visible in menu on mousehover  :" + menuFrameworkOptions + ": Pass");
		} else {
			System.out.println("Total options visible in menu on mousehover  :" + menuFrameworkOptions + ": Fail");
		}
	}

	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) {
		ActionClassDemo actionclassdemo = new ActionClassDemo();
		actionclassdemo.setUp();
		actionclassdemo.navigateToMenu();
		actionclassdemo.pageScrollDown();
		actionclassdemo.doubleClickOnLinkAndVerify();
		actionclassdemo.verifyDragAndDrop();
		actionclassdemo.verifymousehover();
		actionclassdemo.tearDown();

	}

}
