package aditi.exam3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import aditi.base.PredefinedActions;

public class ShoppingCart extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	Actions actions;
	String userName = "abc@gmail.com", passWord = "ABC123";

	@AfterSuite
	void closeBrowser() {
		driver.close();
	}

	public void validatePageTitle(String expectedTitleOfPage, String actualTitle) {
		System.out.println(actualTitle);
		Assert.assertEquals(expectedTitleOfPage, actualTitle);
		if (expectedTitleOfPage.equals(actualTitle))
			System.out.println("Test Pass - Page title matches - Title -> " + actualTitle);
	}

	// Login Via FaceBook
	public void logInViaFaceBook() {
		String expectedHomePageTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		String mainWindow = driver.getWindowHandle();
		validatePageTitle(expectedHomePageTitle, driver.getTitle());
		Assert.assertEquals(driver.getTitle(), expectedHomePageTitle);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Sign In']")));
		actions.moveToElement(driver.findElement(By.xpath("//span[text()='Sign In']"))).build().perform();

		driver.findElement(By.xpath("//a[contains(text(),'login')]")).isDisplayed();
		driver.findElement(By.xpath("//a[contains(text(),'login')]")).click();
		driver.switchTo().frame("loginIframe");
		driver.findElement(By.xpath("//div[@id=\"fbUserLogin\"]/span[text()='Facebook'][1]")).isDisplayed();
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[@id=\"fbUserLogin\"]/span[text()='Facebook'][1]"))))
				.click();
		;
		Set<String> multiTab = driver.getWindowHandles();
		Iterator<String> itr = multiTab.iterator();
		while (itr.hasNext()) {
			String faceBookWindow = itr.next();
			if (!faceBookWindow.equals(mainWindow)) {
				driver.switchTo().window(faceBookWindow);
				if (driver.getTitle().equals("Log into Facebook | Facebook")) {
					driver.findElement(By.xpath("//input[@id='email']")).sendKeys(userName);
					driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(passWord);
					driver.findElement(By.xpath("//button[@id='loginbutton']")).click();
				}
			}
		}
		driver.switchTo().window(mainWindow);
	}

	public void validateUserAccountOptions() {
		logInViaFaceBook();
		actions.moveToElement(driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")))
				.build().perform();
		String[] expectedOptionTextList = { "Orders", "Shortlist", "SD Cash", "E-Gift Voucher", "LOGOUT" };
		List<WebElement> actualOptionTextList = driver
				.findElements(By.xpath("//div[@class='dropdownAccount']/div/ul/li/a/i"));
		System.out.println(actualOptionTextList.size());
		for (int index = 0; index < actualOptionTextList.size(); index++) {
			Assert.assertEquals(actualOptionTextList.get(index).getText(), expectedOptionTextList[index]);
		}
	}

	@Test
	public void validateOrders() {
		logInViaFaceBook();
		validateUserAccountOptions();
		driver.findElement(By.xpath("//span[text()='Cart']")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//h3[text()='Shopping Cart is empty!']")));
		Assert.assertEquals(driver.findElement(By.xpath("//div//h3[text()='Shopping Cart is empty!']")).getText(),
				"Shopping Cart is empty!");

		// Under 'BROWSE CATEGORIES' total 20 items should be present.
		Assert.assertEquals(
				driver.findElements(By.xpath("//div[@class='cart-categories-list-wrapper row']//li")).size(), 20);

		// Start Shopping Now Option
		driver.findElement(By.xpath("//div[@class='dropdownAccount']/div/ul/li[1]")).click();

		// Check Orders
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='dropdownAccount']/div/ul/li/a[@href='https://www.snapdeal.com/myorders']")));
		// Validating page title, url and for new user - there are no orders available
		Assert.assertEquals(driver.getTitle(), "Order History");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.snapdeal.com/myorders");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='accountOrder']")), "MY ORDERS");
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='NO ORDERS AVAILABLE']")).getText(),
				"NO ORDERS AVAILABLE");

		// Existing user - Print orderID
		List<WebElement> orderList = driver.findElements(By.xpath("//div[@class='orderId']"));
		System.out.println("Number of order/s placed by User: " + orderList.size() + " and order ID:");
		for (WebElement order : orderList) {
			System.out.println(order.getText());
		}
	}
}
