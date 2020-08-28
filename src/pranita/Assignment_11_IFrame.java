package pranita;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pranita.basic.PredefinedFunctions;

public class Assignment_11_IFrame extends PredefinedFunctions {
	WebDriver driver;
	
	void setUp() {
		driver = start();
	}
	
	void tearDown() {
		driver.close();
	}
	
	void handleIframe() {
		driver.findElement(By.xpath("//a[@id='iframes']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		driver.switchTo().frame("site1");
		WebElement buttonDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='MENU +']")));
		buttonDropdown.click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		
		WebElement e1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='hero']/h1")));
		String actualText = e1.getText();
		String expectedText = "Selenium Projects";
		if(actualText.equals(expectedText)) {
			System.out.println("User successfully switch to selenium project page");
		}
		else {
			System.out.println("user is not on correct page");
		}
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(2);
		
		driver.findElement(By.xpath("//div[@class='menu']//li/ul/li[2]/a[text()='License']")).click();
		
		driver.switchTo().defaultContent();
		
		driver.findElement(By.linkText("Automation By Krishna")).click();
		
		WebElement e2= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='indexBody']")));
		String actualMainPageText = e2.getText();
		String expectedMainPagetext = "This is Maulik.";
		
		if(actualMainPageText.equals(expectedMainPagetext)) {
			System.out.println("User successfully navigated to http://automationbykrishna.com link");
			System.out.println("Test case passed");
		}
		else {
			System.out.println("user is not on correct page");
		}
	}
	
	public static void main(String[] args) {
		Assignment_11_IFrame assignment_11_IFrame = new Assignment_11_IFrame();
		assignment_11_IFrame.setUp();
		assignment_11_IFrame.handleIframe();
		assignment_11_IFrame.tearDown();
		
	}

}
