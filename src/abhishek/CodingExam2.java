package abhishek;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abhishek.base.PredefinedActions;

public class CodingExam2 extends PredefinedActions {
	WebDriver driver;

	void setUp(String url) throws InterruptedException {
		driver = start(url);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		String Expectedtitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		if (Expectedtitle.equals(driver.getTitle()))
			System.out.println("user is on snapdeal page");
		else
			System.out.println("user is on snapdeal page");

		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")))
				.build().perform();
		driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']")).click();

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));

		
		String mainWindow = driver.getWindowHandle();

		driver.findElement(By.xpath("//div[@class='social-button fbLogin col-xs-12']//span[text()='Facebook']"))
				.click();

		Set<String> newWindow = driver.getWindowHandles();
		Iterator<String> itr = newWindow.iterator();

		while (itr.hasNext()) {
			String tab = itr.next();
			if (!tab.equals(mainWindow)) {
				driver.switchTo().window(tab);
				if (driver.getTitle().equals("Log in to Facebook | Facebook")) {
					System.out.println("You are successfully landed on Facebook page");
				} else
					System.out.println("Test Case fail");

				driver.findElement(By.xpath("//input[@autocomplete='username']"))
						.sendKeys("abhishek.satyam09@yahoo.com");
				driver.findElement(By.xpath("//input[@autocomplete='current-password']")).sendKeys("94*****877");
				driver.findElement(By.xpath("//button[@id='loginbutton']")).click();

			}
		}
		driver.switchTo().window(mainWindow);

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='j_username_new']")));

		System.out.println("Facebook ID is : "
				+ driver.findElement(By.xpath("//input[@id='j_username_new']")).getAttribute("value"));
		System.out.println(
				"User Name  is: " + driver.findElement(By.xpath("//input[@id='j_name']")).getAttribute("value"));
		System.out.println(
				"Date of Birth  is : " + driver.findElement(By.xpath("//input[@id='j_dob']")).getAttribute("value"));

		driver.findElement(By.xpath("//input[@id='j_number']")).sendKeys("965****0723");
		driver.findElement(By.xpath("//input[@id='j_password']")).sendKeys("******");

		if (driver.findElement(By.xpath("//input[@id='keepLoginSignUp']")).isSelected()) {
			driver.findElement(By.xpath("//input[@id='keepLoginSignUp']/..//label")).click();
		}
	}

	void teardown() {
		driver.close();
	}

	public static void main(String[] args) throws InterruptedException {
		CodingExam2 codingExam2 = new CodingExam2();
		codingExam2.setUp("https://www.snapdeal.com");
		 codingExam2.teardown();
	}
}
