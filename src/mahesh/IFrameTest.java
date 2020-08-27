package mahesh;

import mahesh.base.PredefinedActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IFrameTest extends PredefinedActions{
	WebDriver driver;
	
	void setUp() {
		driver = start();
	}
	
	void validateIFrame() {
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='iframes']"))).click();
		WebElement seleniumFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@src='http://www.seleniumhq.org']")));
		driver.switchTo().frame(seleniumFrame);
		driver.findElement(By.xpath("//a[@id='dropdownButton']")).click();
		driver.findElement(By.xpath("//a[@href='/projects']")).click();
		System.out.println(driver.findElement(By.xpath("//section[@class='hero']/h1")).getText());
		driver.switchTo().defaultContent();
		driver.manage().window().maximize();
		WebElement apacheFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@src='http://ant.apache.org/']")));
		driver.switchTo().frame(apacheFrame);
		driver.findElement(By.xpath("//a[@href='https://www.apache.org/licenses/']")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("Automation By Krishna")).click();
		System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='indexBody']"))).getText());
	}
	
	public static void main(String[] args) {
		IFrameTest iFrameTest = new IFrameTest();
		iFrameTest.setUp();
		iFrameTest.validateIFrame();
		iFrameTest.driver.close();
	}

}
