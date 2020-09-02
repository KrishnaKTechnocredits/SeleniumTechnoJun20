package anup.TestNGDemo;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import anup.basics.PredefinedActions;

public class SnapDealValidation extends PredefinedActions {

	WebDriver driver;

	@BeforeMethod
	void Setup() {
		driver = start("https://www.snapdeal.com/");
	}

	@AfterMethod
	void tearDown() {
		driver.close();
	}

	@Test
	public void snapdealValidation() {

		// User should successfully navigated to snapdeal website.
		// 2. Snapdeal page title should be "Online Shopping Site India - Shop
		// Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com"

		String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle); // Validating the Title against the ActualTitle

		String mainWindow = driver.getWindowHandle();

		// 3. User should be able to see "LogIn" option.
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//span[text() = 'Sign In']"));
		actions.moveToElement(element).build().perform(); // Mouse hovering
		driver.findElement(By.xpath("//span[@class = 'accountBtn btn rippleWhite']")).click();

		WebDriverWait wait = new WebDriverWait(driver, 20); // Explicit wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//iFrame[@src='https://www.snapdeal.com:443/iframeLogin#https://www.snapdeal.com/']")));

		driver.switchTo().frame(driver.findElement(
				By.xpath("//iFrame[@src='https://www.snapdeal.com:443/iframeLogin#https://www.snapdeal.com/']")));
		driver.findElement(By.xpath("/html/body/div/div/div/div[6]/div/div[2]")).click();

		Set<String> multiTab = driver.getWindowHandles();
		Iterator<String> itr = multiTab.iterator();
		while (itr.hasNext()) {
			String currentTab = itr.next();
			if (!currentTab.equals(mainWindow)) {
				driver.switchTo().window(currentTab);
				System.out.println(" Title : " + driver.getTitle());
			}
		}
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("sahoo.anupkumar@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.switchTo().window(mainWindow);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Anup Kumar Sahoo']")));
		WebElement logoutelement = driver.findElement(By.xpath("//span[text() = 'Anup Kumar Sahoo']"));
		actions.moveToElement(logoutelement).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Anup Kumar Sahoo']")));
		driver.findElement(By.xpath("//a[text() = 'Logout']")).click();
	}
}
