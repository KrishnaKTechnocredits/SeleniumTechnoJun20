package ajit;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ajit.base.PredefinedActions;

public class CodingExam2 extends PredefinedActions {

	WebDriver driver;
	Actions actions;
	WebDriverWait wait;

	void setUp(String url) {
		driver = start(url);
		if (driver.getTitle().equals(
				"Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com"))
			System.out.println(" Title Matched : User Successfully Navigated to SnapDeal Website");
		else
			System.out.println(" Title MisMatched : Navigation to SnapDeal Website Failed");

		wait = new WebDriverWait(driver, 25);
	}

	void SignInSnapDeal() throws InterruptedException {
		actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")))
				.build().perform();
		driver.findElement(By.xpath("//a[text()='login']")).click();

		driver.switchTo().frame(0);
		// driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
		System.out.println("Successfully Switched to 1st Frame");
		// Thread.sleep(5000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='login-register-common']//div[@id='fbUserLogin']")));
		driver.findElement(By.xpath("//div[@class='login-register-common']//div[@id='fbUserLogin']")).click();

		// Facebook Login
		String mainWindownHandle = driver.getWindowHandle();

		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String handle : allWindowHandles) {
			if ((driver.switchTo().window(handle).getTitle()).equals("Log in to Facebook | Facebook")) {
				System.out.println("Navigated to FB page");
				driver.switchTo().window(handle);
				driver.manage().window().maximize();

				driver.findElement(By.xpath("//input[@id='email']")).sendKeys("firkee");
				driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Facebook@1212");
				driver.findElement(By.xpath("//button[@id='loginbutton']")).click();
				System.out.println("final Step1");

			} else
				System.out.println("Faile to navihgate");
		}

		driver.switchTo().window(mainWindownHandle);
		driver.switchTo().frame(0);
		System.out.println("Switched to main Window Handle");
		// back to Sanpdeal Page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='j_number']")));
		driver.findElement(By.xpath("//input[@id='j_number']")).sendKeys("7299212566");
		driver.findElement(By.xpath("//input[@id='j_password']")).sendKeys("Ajit@1212");
		driver.findElement(By.xpath("//button[@id='userSignup']")).click();
		System.out.println("Performed all Steps-Click on Continue button");
		System.out.println("Didn't Perform step 8 and 11");

	}

	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) throws InterruptedException {
		CodingExam2 codingExam2 = new CodingExam2();
		codingExam2.setUp("https://www.snapdeal.com/");
		codingExam2.SignInSnapDeal();
		codingExam2.tearDown();

	}

}
