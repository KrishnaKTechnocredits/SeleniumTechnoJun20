package technoCredits.basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import technoCredits.basics.base.PredefinedActions;

public class ActionsEx1 extends PredefinedActions {

	WebDriver driver;

	void setUp() {
		driver = start("https://demos.telerik.com/kendo-ui/dragdrop/area");
	}

	void dragAndDrop() {

		//scrollToElement();
		// WebElement srcEle = driver.findElement(By.id("draggable"));
		// WebElement desEle = driver.findElement(By.className("test1"));

		WebElement srcEle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement desEle = driver.findElement(By.xpath("//div[@id='droptarget']/div[1]"));

		Actions actions = new Actions(driver);
		actions.dragAndDrop(srcEle, desEle).build().perform();

		String expMsg = "You did great!";
		String actMsg = driver.findElement(By.className("test1")).getText();

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
		ActionsEx1 actionex1 = new ActionsEx1();
		actionex1.setUp();
		actionex1.dragAndDrop();

	}
}
