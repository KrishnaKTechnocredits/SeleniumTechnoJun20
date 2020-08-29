package viresh.codingExam2;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import viresh.utility.SetUp;


public class SnapdealSignUp extends SetUp {

	WebDriver driver;

	void validateSignUp() {
		driver = setUp("https://www.snapdeal.com/");
		String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		String actualTitle = driver.getTitle();
		WebDriverWait wait = new WebDriverWait(driver, 5);

		System.out.println(
				actualTitle.equals(expectedTitle) ? "Test Pass: Title Matches!" : "Test Fail: Title didnt Match!");

		WebElement signIn = driver.findElement(By.xpath("//span[text()='Sign In']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(signIn).build().perform();
		WebElement target = driver.findElement(By.xpath("//a[text()='login']"));
		String actualTarget = target.getText();
		System.out.println(actualTarget.equals("login") ? "Test Pass: Login option available" + actualTarget
				: "Test Fail: Login option is not available");
		driver.findElement(By.xpath("//a[text()='login']")).click();

		driver.switchTo().frame("loginIframe");
		String FBbutton = driver
				.findElement(By.xpath("//div[@class='userAuth-card']/div/div/div[@id='fbUserLogin']/span")).getText();
		System.out.println(FBbutton.equals("Facebook") ? "Test Pass: clicked on Facebook button" : "Test Fail");

		String mainWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//div[@class='userAuth-card']/div/div/div[@id='fbUserLogin']")).click();

		Set<String> multiWin = driver.getWindowHandles();
		Iterator<String> itr = multiWin.iterator();
		while (itr.hasNext()) {
			String newTab = itr.next();
			newTab = itr.next();
			if (!newTab.equals(mainWindow)) {
				driver.switchTo().window(newTab);
				System.out.println("Switched to new Window >>");
				System.out.println("  - title >> " + driver.getTitle());
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
			driver.findElement(By.xpath("//input[@id='email']")).sendKeys("a.b@facebook.com");
			driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("ab123");
			driver.findElement(By.xpath("//button")).click();
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
			System.out.println("Logged in to SnapDeal account: " + actualTitle);
		}
		tearDown();
	}

	public static void main(String[] args) {
		new SnapdealSignUp().validateSignUp();
	}
}
