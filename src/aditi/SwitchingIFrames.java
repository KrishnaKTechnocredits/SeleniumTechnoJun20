package aditi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import aditi.base.PredefinedActions;

public class SwitchingIFrames extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	void setUp() {
		driver = start();
		wait = new WebDriverWait(driver, 10);
	}

	void navigateTo() {
		driver.findElement(By.linkText("IFrame Demo")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
	}

	void validateIFrame() {
		// Switch to iframe1
		driver.switchTo().frame("site1");
		System.out.println("User switched to iframe1 (Selenium Site)");

		driver.findElement(By.xpath("//a[text()='MENU +']")).click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		System.out.println("Text from iframe1 -> "
				+ wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='hero']/h1")))
						.getText());

		// Switch to main window
		driver.switchTo().defaultContent();

		// Switch to iframe3
		driver.switchTo().frame(2);
		// driver.findElement(By.xpath("//a[@href='https://www.apache.org/licenses/']")).click();
		driver.findElement(By.xpath("//a[text()='License']")).click();
		System.out.println("Switched to ifram-3 and clicked on License link");

		// Switch to main window
		driver.switchTo().parentFrame();
		driver.findElement(By.linkText("Automation By Krishna")).click();
		System.out.println("Navigated to Home Page and pageText is -> " + wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='indexBody']"))).getText());
	}

	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) {
		SwitchingIFrames switchingIFrames = new SwitchingIFrames();
		switchingIFrames.setUp();
		switchingIFrames.navigateTo();
		switchingIFrames.validateIFrame();
		switchingIFrames.tearDown();

	}
}
