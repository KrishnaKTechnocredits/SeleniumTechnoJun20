package aashtha;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import aashtha.base.*;

public class SnapdealLogin extends PredefinedActions {
	WebDriver driver;
	Actions actions;
	WebDriverWait wait;
	String mainWindow;

	// opens the Chrome
	void setUp(String url) {
		driver = start(url);
		wait = new WebDriverWait(driver, 30);
	}

	// closes the browser window
	void tearDown() {
		driver.close();
	}

	// Verifies : Snapdeal page title
	void verifyPageTitle() {
		String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		String actualTitle = driver.getTitle();
		if (actualTitle.equals(expectedTitle))
			System.out.println("Page title is correct as expected");
		else
			System.out.println("Page title is not correct");
	}

	// Clicks on FB button and verifies the FB page title
	void verifyFBPageTitle() {
		mainWindow = driver.getWindowHandle();
		actions = new Actions(driver);
		WebElement signIn = driver.findElement(By.xpath("//div[@class='accountInner']"));
		actions.moveToElement(signIn).build().perform();
		driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
		driver.findElement(By.xpath("//div[@id='fbUserLogin']")).click();
		Set<String> multiWindow = driver.getWindowHandles();
		Iterator<String> itr = multiWindow.iterator();
		while (itr.hasNext()) {
			String currentBrowser = itr.next();
			if (!currentBrowser.equals(mainWindow)) {
				driver.switchTo().window(currentBrowser);
			}
		}
		String expectedTitle = "Log in to Facebook | Facebook";
		String actualTitle = driver.getTitle();
		if (actualTitle.equals(expectedTitle))
			System.out.println("Facebook Page title is correct as expected");
		else
			System.out.println("Facebook Page title is not correct");
	}

	// Logs in into FB and verifies the details filled in by default and unchecks
	// the keepLoginSignUp checkbox
	void loginOnFacebook() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("jaiswal.aastha1691@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Sushma9491");
		driver.findElement(By.xpath("//button[@id='loginbutton']")).click();
		driver.switchTo().window(mainWindow);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='j_username_new']")));
		String email = driver.findElement(By.xpath("//input[@id='j_username_new']")).getAttribute("value");
		String name = driver.findElement(By.xpath("//input[@id='j_name']")).getAttribute("value");
		String dob = driver.findElement(By.xpath("//input[@id='j_dob']")).getAttribute("value");
		if (email.equals("jaiswal.aastha1691@gmail.com") && name.equals("Aastha Jaiswal")
				&& driver.findElement(By.xpath("//input[@id='keepLoginSignUp']")).isSelected()
				&& dob.equals("29/08/1995"))
			System.out.println("Textbox Name and Email are filled in correctly. Keep me logged in checkbox is checked");
		else
			System.out.println("details displayed are not correct");
		driver.findElement(By.xpath("//input[@id='j_number']")).sendKeys("9876543213");
		driver.findElement(By.xpath("//input[@id='j_password']")).sendKeys("aastha");
		driver.findElement(By.xpath("//label[@for='keepLoginSignUp']")).click();
		if (!driver.findElement(By.xpath("//input[@id='keepLoginSignUp']")).isSelected())
			System.out.println("Keep me logged in checkbox is unchecked successfully");
		else
			System.out.println("Could not uncheck Keep me logged in checkbox");
	}

	public static void main(String[] args) {
		SnapdealLogin snapdealLogin = new SnapdealLogin();
		snapdealLogin.setUp("https://www.snapdeal.com/");
		snapdealLogin.verifyPageTitle();
		snapdealLogin.verifyFBPageTitle();
		snapdealLogin.loginOnFacebook();
		snapdealLogin.tearDown();
	}
}
