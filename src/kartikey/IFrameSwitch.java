package kartikey;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import kartikey.base.PredDefindActions;

public class IFrameSwitch extends PredDefindActions{
	
	WebDriver driver;
	void startup(String url) {
		driver= start(url);
	}
	void tearDown(){
		driver.close();
	}
	void waitExplicitly(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 25);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));	
	}
	
	void frameSwitch() {
		driver.findElement(By.id("iframes")).click();
		waitExplicitly("//iframe");
		driver.switchTo().frame(0);// first frame switch
		driver.findElement(By.xpath("//a[@id='dropdownButton']")).click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		waitExplicitly("//section[@class='hero']/h1");
		System.out.println(driver.findElement(By.xpath("//section[@class='hero']/h1")).getText());
		//driver.switchTo().parentFrame();// checking with parent frame
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='site3']")));
		driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//a[text()='Automation By Krishna']")).click();
		System.out.println(driver.findElement(By.xpath("//div[@id='indexBody']")).getText());		
	}
	public static void main(String[] args) {
		IFrameSwitch iFrameSwitch= new IFrameSwitch();
		String url="http://www.automationbykrishna.com";
		iFrameSwitch.startup(url);
		iFrameSwitch.frameSwitch();
		iFrameSwitch.tearDown();
	}
	

}
