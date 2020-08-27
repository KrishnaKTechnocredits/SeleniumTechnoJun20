package abhishek;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abhishek.base.PredefinedActions;

public class Iframe extends PredefinedActions {

	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	void seleniumIframe() {
		driver.findElement(By.linkText("IFrame Demo")).click();
		
		driver.switchTo().frame("site1");
		driver.findElement(By.xpath("//a[text()='MENU +']"));

		driver.findElement(By.xpath("//a[text()='MENU +']")).click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		String actualTitle = driver.findElement(By.xpath("//h1[text()='Selenium Projects']")).getText();

		if (actualTitle.equals("Selenium Projects"))
			System.out.println("user is on Selenium Projects page");
		else
			System.out.println("user is not on Selenium Projects page'");

		driver.switchTo().parentFrame();
		driver.switchTo().frame(2);
		driver.findElement(By.xpath("//html/body/div[4]/div/ul/li/ul/li/a[text()='License']")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[text()='Automation By Krishna']")).click();
		String actualText = driver.findElement(By.xpath("//div[@class='wrapper']")).getText();
		// System.out.println(actualText);

		if (actualText.equals("This is Maulik."))
			System.out.println("Test passed : " + actualText);
		else
			System.out.println("Test failed :" + actualText);
	}

	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) {
		Iframe iframe = new Iframe();
		iframe.setUp("http://automationbykrishna.com/");
		iframe.seleniumIframe();
		iframe.tearDown();
	}
}
