package vaishnavi_CodingExams;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import vaishnavi_Base.PredefinedAction;

public class SnapDealValidation extends PredefinedAction {
	WebDriver driver;

	void setUp() {
		driver = start("https://www.snapdeal.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	void verifyBasicDetails() {
		try {
			String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
			// Verifying title of main page
			String actual = driver.getTitle();
			if (actual.equals(expectedTitle))
				System.out.println("Title is successfully Verified for Main Page" + driver.getTitle());
			else
				System.out.println("Title verification failed");

			// Perform mouse hover
			Actions actions = new Actions(driver);
			WebDriverWait wait = new WebDriverWait(driver, 5);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='accountInner']"))); // Explicit wait																													 
			actions.moveToElement(driver.findElement(By.xpath("//div[@class='accountInner']"))).build().perform();

			WebElement login = driver.findElement(By.xpath("//a[@href=\"https://www.snapdeal.com/login\"]")); // User should be able to see facebook login
																												
			if (login.getText().equals("LOGIN"))
				login.click();
			else
				System.out.println("User is unabl to see login option");

			// Switch to another frame to login on facebook
			driver.switchTo().frame("iframeLogin");
			driver.findElement(By.id("fbUserLogin")).click();

			// Code to handle multiple windows
			String mainWindow = driver.getWindowHandle();
			Set<String> multiWindow = driver.getWindowHandles();

			// To iterate the set
			Iterator<String> itr = multiWindow.iterator();
			while (itr.hasNext()) {
				String currentBrowser = itr.next();
				if (!(currentBrowser.equals(mainWindow))) {
					driver.switchTo().window(currentBrowser);
					if (driver.getTitle().equals("Log in to Facebook | Facebook"))
						System.out.println("Title Verified for: " + driver.getTitle());
					else
						System.out.println("Title is Wrong for Facebook");

					driver.findElement(By.id("email")).sendKeys("ABC");
					driver.findElement(By.id("pass")).sendKeys("XYZ");
					driver.findElement(By.id("loginbutton")).click();

				}
			}
		} catch (NoSuchElementException ne) {
			ne.printStackTrace();
		} finally {
			driver.close();
		}
	}

	public static void main(String[] args) {
		SnapDealValidation snapDealValidation = new SnapDealValidation();
		snapDealValidation.setUp();
		snapDealValidation.verifyBasicDetails();
	}

}
