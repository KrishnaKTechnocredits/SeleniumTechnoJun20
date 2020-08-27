package vaishnavi_SeleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import vaishnavi_Base.PredefinedAction;

public class IFrame extends PredefinedAction {
	private WebDriver driver;

	void setUp() {
		driver = start();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	void iframeDemo() {
		try {
			driver.findElement(By.linkText("IFrame Demo")).click();
			// Switching to First frame
			driver.switchTo().frame("site1");
			driver.findElement(By.xpath("//a[@id='dropdownButton']")).click();
			driver.findElement(By.xpath("//a[@href='/projects']")).click();
			String headstr = driver.findElement(By.xpath("//section[@class='hero']/h1")).getText();
			System.out.println("Projects heading from frame 1 is: " + headstr);

			// Switching back to main page
			driver.switchTo().defaultContent();

			// Switching to third frame
			driver.switchTo().frame(2);
			driver.findElement(By.xpath("//a[text()='License']")).click();

			driver.switchTo().defaultContent();
			driver.findElement(By.xpath("//a[text()='Automation By Krishna']")).click();

			// Verifying if we are on Home page
			String text = driver.findElement(By.xpath("//div[@id='indexBody']")).getText();
			if (text.equals("This is Maulik."))
				System.out.println("You are navigated to home page and visible text is: " + text);
			else
				System.out.println("Test Fail");
		} catch (NoSuchElementException ne) {
			ne.printStackTrace();
		} finally {
			driver.close();
		}
	}

	public static void main(String[] args) {
		IFrame iFrame = new IFrame();
		iFrame.setUp();
		iFrame.iframeDemo();
	}

}
