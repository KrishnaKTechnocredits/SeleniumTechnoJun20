package aashtha;

import aashtha.base.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestIframes extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	void setUp(String url) {
		driver = start(url);
		wait = new WebDriverWait(driver, 20);
	}

	void tearDown() {
		driver.close();
	}

	void navigateToIframeDemo() {
		driver.findElement(By.id("iframes")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
	}

	void verifyIframes() {
		driver.switchTo().frame("site1");
		driver.findElement(By.xpath("//a[@id='dropdownButton']")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//nav[@id='navbar']")));
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		System.out.println("Text from frame 1 -> " + (wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Selenium Projects']"))))
						.getText());
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='site3']")));
		driver.findElement(By.xpath("//li[2]/a[text()='License']")).click();
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//img[@alt='The Apache Software Foundation']")));
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("Automation By Krishna")).click();
		System.out.println("Switched to homepage and the text is -> "
				+ (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='indexBody']"))))
						.getText());
	}

	public static void main(String[] args) {
		TestIframes testIframes = new TestIframes();
		testIframes.setUp("http://automationbykrishna.com/");
		testIframes.navigateToIframeDemo();
		testIframes.verifyIframes();
		testIframes.tearDown();
	}

}
