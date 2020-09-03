package aditi;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import aditi.base.PredefinedActions;

public class Exam2 extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	String userName = "ABC", passWord = "XYZ", profileName = "ABC";

	void setUp(String url) {
		driver = start(url);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 15);
	}

	void snapDeallogin() {
		try {
			String expectedPageTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
			String mainWindow = driver.getWindowHandle();
			String actualTitle = driver.getTitle();
			System.out.println(actualTitle);
			// Assert.assertEquals(expectedPageTitle, actualTitle);
			if (expectedPageTitle.equals(actualTitle))
				System.out.println("Test Pass - Page title matchs" + actualTitle);
			Actions actions = new Actions(driver);
			actions.moveToElement(
					driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']"))).build()
					.perform();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'login')]")))
					.click();
			driver.switchTo().frame("iframeLogin");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='fbUserLogin']"))).click();
			System.out.println(driver.getTitle());
			Set<String> multiTab = driver.getWindowHandles();
			Iterator<String> itr = multiTab.iterator();
			while (itr.hasNext()) {
				String faceBookWindow = itr.next();
				if (!faceBookWindow.equals(mainWindow)) {
					driver.switchTo().window(faceBookWindow);
					if (driver.getTitle().equals("Log into Facebook | Facebook"))
						faceBookLogin();
				}
			}
			driver.switchTo().window(mainWindow);

			validateSignUpIFrame();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}
	}

	void faceBookLogin() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(userName);
		driver.findElement(By.id("pass")).sendKeys(passWord);
		driver.findElement(By.xpath("//button[@id='loginbutton']")).click();
	}

	void validateSignUpIFrame() {
		// Assert.assertEquals(driver.findElement(By.xpath("//input[@id='j_username_new']")),
		// userName);
		// Switched to signup frame
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
		String currentUserName = driver.findElement(By.xpath("//input[@id='j_username_new']")).getAttribute("value");
		System.out.println(
				currentUserName.equals(userName) ? "Pass - User Name Matches" : "Fail - User name do not match");
		System.out.println(
				(driver.findElement(By.xpath("//input[@id='j_name']")).getAttribute("value").equals(profileName))
						? "Pass - Name matches to profile name"
						: "Fail - Name doesn't match");
		driver.findElement(By.id("j_number")).sendKeys("1234567890");
		driver.findElement(By.id("j_password")).sendKeys("ABC");

		System.out.println(driver.findElement(By.xpath("//input[@id='keepLoginSignUp']")).isSelected()
				? "Checkbox already selected"
				: "Checkbox not selected");
		driver.findElement(By.xpath("//input[@id='keepLoginSignUp']/..//label")).click();
		System.out.println(!(driver.findElement(By.xpath("//input[@id='keepLoginSignUp']")).isSelected())
				? "Pass - CheckBox unselected"
				: "Fail - Unable to unselect");
	}

	public static void main(String[] args) {
		Exam2 exam2 = new Exam2();
		exam2.setUp("https://www.snapdeal.com/");
		exam2.snapDeallogin();
	}
}
