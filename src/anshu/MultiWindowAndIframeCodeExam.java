package anshu;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import anshu.base.PredefinedProperty;

public class MultiWindowAndIframeCodeExam extends PredefinedProperty {
	WebDriver driver;

	void setUp() {
		driver = start("https://www.snapdeal.com/");
	}

	void tearDown() {
		driver.quit();
	}

	void navigateToSnapdeal() {
		// Check snapdeal title
		String actualTitle = driver.getTitle();
		String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		if (expectedTitle.equals(actualTitle)) {
			System.out.println(" 1. Got Title : " + actualTitle);
		}

		// mover hover on login
		WebDriverWait wait = new WebDriverWait(driver, 15);
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//span[text()='Sign In']"));
		System.out.println(" 2. User are able to see Sign in Option");
		actions.moveToElement(element).build().perform();
		driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));

		// facebook switch
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Facebook']")))).click();
		String currentTab = driver.getWindowHandle();

		Set<String> multiWindow = driver.getWindowHandles();
		Iterator<String> itr = multiWindow.iterator();
		while (itr.hasNext()) {
			String currentWindow = itr.next();
			if (!currentTab.equals(currentWindow)) {
				driver.switchTo().window(currentWindow);
				if (driver.getTitle().equals("Log in to Facebook | Facebook")) {
					System.out.println("User successfully navigated to " + driver.getTitle());
				} else
					System.out.println("Test Case fail");

			}
		}
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//input[@placeholder='Email address or phone number']"))))
				.sendKeys("akant641@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Facebookpassword@1");
		driver.findElement(By.xpath("  //button[@name='login']")).click();
	}

	public static void main(String[] args) {
		MultiWindowAndIframeCodeExam examCode2 = new MultiWindowAndIframeCodeExam();
		examCode2.setUp();
		examCode2.navigateToSnapdeal();
		examCode2.tearDown();
	}
}
