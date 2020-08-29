package sonal;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Exam2 {
	// Coding Exam 2--Snapdeal validations

	// Initiate webdriver
	public WebDriver Start(String url) {

		System.setProperty("webdriver.chrome.driver", "E:/NewWorkSpace/SeleniumBasics1/chromedriver.exe"); // Method
																											// to
																											// initiate
																											// web
																											// driver
																											// and
																											// launch
																											// url

		WebDriver driver = new ChromeDriver();

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		return driver;
	}

	public void startSnapdeal() throws InterruptedException {
		WebDriver driver = Start("https://www.snapdeal.com/");

		String expTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		String actTitle = driver.getTitle();

		// verify titke of snapdeal
		if (actTitle.equals(expTitle)) {
			System.out.println("Title of Snapdeal is matching with expected title");
		} else
			System.out.println("Test is fail as title of Snapdeal is not matching");

		WebElement element = driver.findElement(By.xpath("//div[@class='accountInner']"));

		// action class for mousehover
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

		String actText = driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']/a")).getText();

		String expText = "LOGIN";

		// Verify Login button is present on mousehover on Sign In
		if (actText.equals(expText)) {
			System.out.println("Button " + expText + " is present on mouse hover to SignIn");

		} else
			System.out.println("Mouse hover failed");

		driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']/a")).click();
		Thread.sleep(6000);

		// Navigate to iframe

		driver.switchTo().frame("loginIframe");

		WebElement element1 = driver.findElement(By.xpath(
				"//div[@class='userAuth-card']/div[@class='login-register-common']/div[@class='loginSocialBtn col-xs-24 reset-padding marT20 marB20']/div[@id='fbUserLogin']"));
		String expButtonText = "Facebook";
		String actButtontext = element1.getText();

		// Verify login with Facebook option is present
		if (actButtontext.equals(expButtonText))
			System.out.println("Login with facebook Option is present");
		else
			System.out.println("login with facebook Option is not present");

		// Navigate to facebook
		element1.click();

		// Navigate to Facebook page
		String mainwin = driver.getWindowHandle();

		Set<String> newWin = driver.getWindowHandles();

		Iterator<String> itr = newWin.iterator();

		while (itr.hasNext()) {
			String browser = itr.next();

			if (!browser.equals(mainwin)) {
				driver.switchTo().window(browser);
				Thread.sleep(4000);
				driver.findElement(By.id("email")).sendKeys("inamdar.sonal@gmail.com"); // login to Facebook account
				driver.findElement(By.id("pass")).sendKeys("rani275");
				driver.findElement(By.id("loginbutton")).click();

				Thread.sleep(4000);

			}
		}
		
		//driver.switchTo().window(mainwin);
		
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
			
			//System.out.println(driver.getCurrentUrl());

			// Check if the checkbox is selected. If yes, uncheck it

			if (driver.findElement(By.xpath("//input[@id='keepLoginSignUp']")).isSelected()) {
				System.out.println("Checkbox on sign in page is checked");
				driver.findElement(By.xpath("//input[@id='keepLoginSignUp']")).click();
			}

			else
				System.out.println("Checkbox on sign in page is unchecked");

		

		driver.close();

	}

	public static void main(String[] args) throws InterruptedException {

		Exam2 e2 = new Exam2();

		e2.startSnapdeal();

	}

}

