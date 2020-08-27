package anshu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import anshu.base.PredefinedProperty;

public class AutomationOnIFrame extends PredefinedProperty {
	private WebDriver driver;

	void setUp() {
		driver = start();
	}

	void tearDown() {
		driver.close();
	}

	void navigateToIframe() {
		driver.findElement(By.linkText("IFrame Demo")).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@name='site1']")));

		// 1.) Switch to first iframe
		driver.switchTo().frame(0);
		System.out.println("1.) Successfully switch to 1st IFrame.");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text() ='MENU +']")));
		driver.findElement(By.xpath("//a[text() ='MENU +']")).click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//section//h1[text()='Selenium Projects']")));
		String headerText = driver.findElement(By.xpath("//section//h1[text()='Selenium Projects']")).getText();
		System.out.println("2.) Got Heading in 1st Iframe on click on Projects. :- " + headerText);

		// 2.) Switch back to main page

		driver.switchTo().defaultContent();
		driver.switchTo().frame(2);
		System.out.println("3.) Successfully Switch to  3rd Iframe");
		driver.findElement(By.xpath("//li//a[text() ='License']")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//img[@alt='The Apache Software Foundation']")));
		System.out.println("4.) Successfully click on Licence link in 3rd IFrame. ");
		driver.switchTo().defaultContent();
		

		// 3.) get text on Main default page.

		driver.findElement(By.xpath("//a[text()='Automation By Krishna']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='indexBody']")));

		WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='indexBody']")));
		String actualMainPageText = text.getText();
		String expectedMainPagetext = "This is Maulik.";

		if (actualMainPageText.equals(expectedMainPagetext)) {
			System.out.println("5.)Successfully navigate to http://automationbykrishna.com link");
			System.out.println();
			System.out.println("All test case are passed");
		} else {
			System.out.println("User does not on correct page.");
		}
	}

	public static void main(String[] args) {
		AutomationOnIFrame auotomateIFrame = new AutomationOnIFrame();
		auotomateIFrame.setUp();
		auotomateIFrame.navigateToIframe();
		auotomateIFrame.tearDown();
	}
}
