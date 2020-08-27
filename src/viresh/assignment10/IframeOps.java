package viresh.assignment10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import viresh.utility.SetUp;

public class IframeOps extends SetUp{
	
	WebDriver driver;
	void switchingIframe() {
		driver= setUp();
		WebDriverWait wait= new WebDriverWait(driver, 20);
		driver.findElement(By.xpath("//a[text()='IFrame Demo']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@name='site1']")));
		driver.switchTo().frame(0);
		System.out.println("Switched to first frame");
		driver.findElement(By.xpath("//a[text()='MENU +']")).click();
		driver.findElement(By.xpath("//header//a[text()='Projects']")).click();
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//section[@class='hero']/h1")));
		String text= driver.findElement(By.xpath("//section[@class='hero']/h1")).getText();
		System.out.println(text);
		driver.switchTo().defaultContent();
		System.out.println("Switched out of first frame.");
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//iframe[@src='http://ant.apache.org/']")));
		driver.switchTo().frame(2);
		System.out.println("Switched to 3rd frame.");
		driver.findElement(By.xpath("//li/a[text()='License']")).click();
		System.out.println("Clicked on License link.");
		driver.switchTo().parentFrame();
		System.out.println("Switched to Parent Frame.");
		driver.findElement(By.xpath("//a[text()='Automation By Krishna']")).click();
		System.out.println("Switched to homepage.");
		System.out.println(driver.findElement(By.xpath("//div[@class='wrapper']")).getText());
	}
	
	public static void main(String[] args) {
		IframeOps ifrm= new IframeOps();
		ifrm.switchingIframe();
		ifrm.tearDown();
	}
}
