/*Assignment 11
 *1. Navigate to http://automationbykrishna.com/
  2. Go to IFram Demo
  3. Switch to 1st Frame click on Menu 
  4. Click on Project and Verify Header1 
  5. Switch to 3rd Frame and click on License
  6. Switch to main page and click on Automation By Krishna link
  7. After navigating page Verify the Text */
package palak;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import palak.base.PredefinedActions;

public class IframeAssignment extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	void setUp(String url) {
		driver = start(url);
	}

	void navigationToMenu() {
		driver.findElement(By.linkText("IFrame Demo")).click();
	}

	void seleniumIframe() {
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("site1"));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//header[@class='header']//a[@id='dropdownButton']")))
				.click();
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header[@class='header']//a[@id='dropdownButton']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav//a[2]"))).click();

		String expectedText = "Selenium Projects";
		String actualText = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='hero']/h1")))
				.getText();
		if (actualText.equals(expectedText))
			System.out.println("User Successfully Navigate to " + actualText + " Page");
		else
			System.out.println("User not is correct page");

		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(2));
		WebElement license = driver.findElement(By.xpath("//ul//li[2]/a[text()='License']"));
		System.out.println("Clicked on " + license.getText() + " Link");

		driver.switchTo().defaultContent();

		driver.findElement(By.xpath("//a[text()='Automation By Krishna']")).click();
		String pageExpectedText = "This is Maulik.";
		System.out.println(driver.findElement(By.xpath("//div[@id='indexBody']")).getText().equals(pageExpectedText)
				? "User Successfully Navigate to : \"automationbykrishna.com\" and Text is : " + pageExpectedText
				: "User not navigated correctly ");
	}
	
	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) {
		IframeAssignment iframeAssignment = new IframeAssignment();
		iframeAssignment.setUp("http://automationbykrishna.com/");
		iframeAssignment.navigationToMenu();
		iframeAssignment.seleniumIframe();
		iframeAssignment.tearDown();
	}
}
