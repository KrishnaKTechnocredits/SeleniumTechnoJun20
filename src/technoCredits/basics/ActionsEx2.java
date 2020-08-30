package technoCredits.basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import technoCredits.basics.base.PredefinedActions;

public class ActionsEx2 extends PredefinedActions {

	WebDriver driver;

	void setUp() {
		driver = start();
		driver.findElement(By.linkText("Basic Elements")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	void doubleClickOnElement() {

		WebElement element = driver.findElement(By.xpath("//div[@class='col-lg-12']/div/a"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);

		Actions actions = new Actions(driver);
		actions.doubleClick(element).build().perform();

		String expMsg = "You successfully double clicked it";
		String actMsg = driver.switchTo().alert().getText();

		if (expMsg.equals(actMsg)) {
			System.out.println("Test pass");
		} else {
			System.out.println("Test Fail");
		}

	}

	void terminateBrowser() {
		driver.close();
	}

	public static void main(String[] args) {
		ActionsEx2 actionex1 = new ActionsEx2();
		actionex1.setUp();
		actionex1.doubleClickOnElement();

	}
}
