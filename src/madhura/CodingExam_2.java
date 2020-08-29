package madhura;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import madhura.base.PredefinedActions;

public class CodingExam_2 extends PredefinedActions {

	@Test
	void snapDealLogin() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
		String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);

		WebElement element = driver
				.findElement(By.xpath("//div[@class='myAccountTab accountHeaderClass col-xs-13 reset-padding']"));

		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

		driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']")).click();
		driver.switchTo().frame("iframeLogin");
		driver.findElement(By.xpath("//div[@class='login-register-common']/div[1]/div[2]")).click();

		String mainWin = driver.getWindowHandle();
		Set<String> multiWin = driver.getWindowHandles();
		Iterator<String> itr = multiWin.iterator();

		String fbTitleExp = "Log in to Facebook | Facebook";
		String currentTitle = "";
		while (itr.hasNext()) {
			String currentBrowser = itr.next();
			if (!currentBrowser.equals(mainWin)) {
				driver.switchTo().window(currentBrowser);
				currentTitle = driver.getTitle();
			}
		}
		Assert.assertEquals(currentTitle, fbTitleExp);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("madhurah02@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("facebook@123");
		driver.findElement(By.xpath("//button")).click();
		driver.switchTo().window(mainWin);

		driver.quit();
	}
}