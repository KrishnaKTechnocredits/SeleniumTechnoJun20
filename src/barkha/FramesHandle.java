package barkha;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import barkha_base.PredefinedFunctions;

public class FramesHandle extends PredefinedFunctions {

	WebDriver driver;
	WebDriverWait wait;

	void setUp() {
		driver = start();
	}

	void navigateToField() {
		driver.findElement(By.xpath("//a[text()='IFrame Demo']")).click();

		// Explicit wait (loading IFrame page)
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//iframe[@name='site1']")));
	}

	void handleIframes() {
		//switch to frame(0) and wait untill Menu+ dropdown is visible
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='site1']")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@id='dropdownButton']")));
		driver.findElement(By.xpath("//a[@id='dropdownButton']")).click();
		
		//wait until Selenium Projects field is visible after clicking on Project
		driver.findElement(By.xpath("//a[@href='/projects']")).click();
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='hero']/h1")));  //will give header
		
		//validate correct page opening after clicking on Project link
		String actualText = element.getText();
		if (actualText.equals("Selenium Projects"))
			System.out.println("Test Case PASS: Correct page is opening after clicking on Projects.");
		else
			System.out.println("Test Case FAIL");
		
		//switch to page
		driver.switchTo().parentFrame();    //When there is no parent frame, it will switch to parent Page

		//switch to frame(2)
		driver.switchTo().frame(2).findElement(By.xpath("//a[text()='License']")).click();
		
		//switch to page
		driver.switchTo().defaultContent();
		
		//click on link
		driver.findElement(By.xpath("//a[@href='http://automationbykrishna.com']")).click();
		
		//get text after clicking on link and validate
		String text = driver.findElement(By.xpath("//div[@id='indexBody']")).getText();
		

		if (text.equals("This is Maulik.")) {
			System.out.println("Test Case PASS: You are navigated to Automation By Krishna Page.");
		} else
			System.out.println("Test Case FAIL");
	}
	
	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) {
		FramesHandle frames = new FramesHandle();
		frames.setUp();
		frames.navigateToField();
		frames.handleIframes();
		frames.tearDown();
	}
}	