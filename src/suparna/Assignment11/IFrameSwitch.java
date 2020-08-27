package suparna.Assignment11;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import suparna.basics.base.PredefineAction;
public class IFrameSwitch extends PredefineAction {
	public void switchIFrame() {
		driver.findElement(By.xpath("//a[@id='iframes']")).click();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.switchTo().frame("site1");
		driver.findElement(By.xpath("//a[@id='dropdownButton']")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String expecetdResult1 = "Selenium Projects";
		String actualResult1 = driver.findElement(By.xpath("//h1[text()='Selenium Projects']")).getText();
		System.out.println("Expeceted Result : " + expecetdResult1);
		System.out.println("Actual Result :" + actualResult1);
		if (actualResult1.equals(expecetdResult1))
			System.out.println("Test case Pass :");
		else
			System.out.println("Test case Fail :");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[3]")));
		driver.findElement(By.xpath("//li[@class='menuheader']//li[2]//a[text()='License']")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[text()='Automation By Krishna']")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String actualResult2 = driver.findElement(By.xpath("//div[@class='wrapper']")).getText();
		String expecetdResult2 = "This is Maulik.";
		System.out.println("Expeceted Result " + expecetdResult2);
		System.out.println("Actual Result :" + actualResult2);
		if (actualResult2.equals(expecetdResult2))
			System.out.println("Test case Pass :");
		else
			System.out.println("Test case Fail :");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IFrameSwitch iFrameSwitch = new IFrameSwitch();
		iFrameSwitch.setDriver();
		iFrameSwitch.switchIFrame();
		driver.close();
	}

}
