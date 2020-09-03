package kartikey;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import kartikey.base.PredDefindActions;

public class DoubleClick extends PredDefindActions {
	WebDriver driver;

	void setup(String url) {
		driver = start(url);
	}

//	@AfterTest
	void tearDown() {
		driver.close();
	}

	@BeforeTest
	void getUrl() {
		String url = "http://www.automationbykrishna.com";
		setup(url);
	}

	void waitexplicitly(String path) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(path)));
	}

	void doubleclick(String path) {
		Actions action = new Actions(driver);
		action.doubleClick(driver.findElement(By.xpath(path))).build().perform();
	}

	void clickOnElement(String path) {
		driver.findElement(By.xpath(path)).click();
	}

	String getAlertMsgtext() {
		return driver.switchTo().alert().getText();
	}
	void alertAccept() {
		driver.switchTo().alert().accept();
	}
	@Test
	void doubleClickDemo() {
		clickOnElement("//a[text()='Basic Elements']");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//a[text()='Double-click on me']")));
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		waitexplicitly("//a[text()='Double-click on me']");
		doubleclick("//a[text()='Double-click on me']");
		System.out.println(getAlertMsgtext());
		alertAccept();

	}
}
