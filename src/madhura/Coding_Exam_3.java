package madhura;

import java.util.ArrayList;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import madhura.base.PredefinedActions;

public class Coding_Exam_3 extends PredefinedActions {
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	@BeforeMethod
	void snapDealTitle() {
		setUp("https://www.snapdeal.com/");

		String expSDealTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		String snapDealTitle = driver.getTitle();
		Assert.assertEquals(snapDealTitle, expSDealTitle);
		System.out.println("SnapDeal title verified");
	}

	void openLoginForm() {

		WebElement element = driver
				.findElement(By.xpath("//div[@class='myAccountTab accountHeaderClass col-xs-13 reset-padding']"));

		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		WebElement loginOption = driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']"));
		loginOption.isDisplayed();
		loginOption.click();
		driver.switchTo().frame("iframeLogin");
		WebElement mobNumField = driver.findElement(By.xpath("//input[@id='userName']"));
		WebElement continueButton = driver.findElement(By.xpath("//button[@id='checkUser']"));
		mobNumField.isDisplayed();
		continueButton.isDisplayed();
		driver.findElement(By.xpath("//div[@class='login-register-common']/div[1]/div[2]")).isDisplayed(); // FB login
																											// button
		driver.findElement(By.xpath("//div[@class='login-register-common']/div[1]/div[3]")).isDisplayed(); // gmail
																											// loginbutton
	}

	@Test(priority = 1)
	void loginUsingMobileNumber() {
		openLoginForm();
		WebDriverWait wait = new WebDriverWait(driver, 15);

		driver.findElement(By.xpath("//input[@id='userName']")).sendKeys("70*****408");
		driver.findElement(By.xpath("//button[@id='checkUser']")).click();
		WebElement passField = driver.findElement(By.xpath("//input[@id='j_password_login_uc']"));
		WebElement checkbox = driver.findElement(By.xpath("//div[@class='loginCheckbox keepLoginUC']"));
		WebElement loginButton = driver.findElement(By.xpath("//button[@id='submitLoginUC']"));

		passField.isDisplayed();
		checkbox.isDisplayed();
		loginButton.isDisplayed();
		driver.findElement(By.xpath("//button[@id='sendOtpUC']")).isDisplayed();
		driver.findElement(By.xpath("//div[@class='screen1']/div/div[2]")).isDisplayed();
		driver.findElement(By.xpath("//div[@class='screen1']/div/div[3]")).isDisplayed();

		passField.sendKeys("s********23");
		checkbox.click();
		loginButton.click();
		Assert.assertEquals(driver.getTitle(),
				"Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com");

		String name = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")))
				.getText();

		if (name.contains("Madhura")) {
			System.out.println("User logged in successfully");
		}
	}

	@Test(priority = 2)
	void loginUsingEmail() {
		openLoginForm();
		WebDriverWait wait = new WebDriverWait(driver, 15);

		driver.findElement(By.xpath("//input[@id='userName']")).sendKeys("pra***********@gmail.com");
		driver.findElement(By.xpath("//button[@id='checkUser']")).click();
		WebElement passField = driver.findElement(By.xpath("//input[@id='j_password_login_uc']"));
		WebElement checkbox = driver.findElement(By.xpath("//div[@class='loginCheckbox keepLoginUC']"));
		WebElement loginButton = driver.findElement(By.xpath("//button[@id='submitLoginUC']"));

		passField.isDisplayed();
		checkbox.isDisplayed();
		loginButton.isDisplayed();
		driver.findElement(By.xpath("//button[@id='sendOtpUC']")).isDisplayed();
		driver.findElement(By.xpath("//div[@class='screen1']/div/div[2]")).isDisplayed();
		driver.findElement(By.xpath("//div[@class='screen1']/div/div[3]")).isDisplayed();

		passField.sendKeys("s**********");
		checkbox.click();
		loginButton.click();
		Assert.assertEquals(driver.getTitle(),
				"Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com");

		String name = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")))
				.getText();

		if (name.contains("Prasad")) {
			System.out.println("User logged in successfully");
		}
	}

	@Test(priority = 3)
	void userWithoutPurchaseHistory() {
		loginUsingMobileNumber();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = driver
				.findElement(By.xpath("//div[@class='myAccountTab accountHeaderClass col-xs-13 reset-padding']"));

		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		ArrayList<String> expectedList = new ArrayList<>();
		expectedList.add("Orders");
		expectedList.add("Shortlist");
		expectedList.add("SD Cash Rs. 0");
		expectedList.add("E-Gift Voucher");
		expectedList.add("LOGOUT");
		// System.out.println(expectedList);
		ArrayList<String> optionsList = new ArrayList<>();
		for (int index = 1; index <= 5; index++) {
			String listElement = driver.findElement(By.xpath("//div[@class='dropdownAccount']//li[" + index + "]/a"))
					.getText();
			optionsList.add(listElement);
		}
		// System.out.println(optionsList);
		if (expectedList.equals(optionsList)) {
			System.out.println("User options Verified");
		}

		driver.findElement(By.xpath("//div[@class='dropdownAccount']//li[1]/a")).click();
		String expectHeading = "MY ORDERS";
		String expectUrl = "https://www.snapdeal.com/myorders";
		String textMsg = "NO ORDERS AVAILABLE";
		if ((driver.findElement(By.xpath("//span[@class='accountOrder']")).getText()).equals(expectHeading)
				&& driver.findElement(By.xpath("//div[@class='noOrdersAvail']")).getText().equals(textMsg)
				&& driver.getCurrentUrl().equals(expectUrl)) {
			System.out.println("Page URL , Heading and Messege verified");
		}
		// navigate to home thruogh breadcrum
		driver.findElement(By.xpath("//div[@class='lfloat containerBreadcrumb']/a/span[1]")).click();
		if (driver.getTitle().equals(
				"Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com")) {
			System.out.println("Successfully Redirected to Dashboard");
		}
		// Click on shopping cart
		driver.findElement(By.xpath("//div[@class='cartContainer col-xs-11 reset-padding']")).click();
		String emptyCartMsg = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart-heading clearfix']")))
				.getText();
		if (emptyCartMsg.equals("Shopping Cart is empty!")) {
			System.out.println("Cart empty msg verified");
		}

		int count = 0;
		List<WebElement> options = driver
				.findElements(By.xpath("//div[@class='cart-categories-list-wrapper row']//li"));
		Iterator<WebElement> itr = options.iterator();

		while (itr.hasNext()) {
			count++;
			itr.next();
		}
		System.out.println("Total items present under browse category : " + count);
		WebElement startShopping = driver
				.findElement(By.xpath("//a[@class='btn btn-xl rippleWhite cart-shopping-button col-xs-7']"));
		startShopping.isDisplayed();
		startShopping.click();
		if (driver.getTitle().equals(
				"Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com")) {
			System.out.println("Successfully Redirected to Dashboard");
		}
	}

	@Test(priority = 4)
	void userWithPurchaseHistory() {
		loginUsingEmail();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = driver
				.findElement(By.xpath("//div[@class='myAccountTab accountHeaderClass col-xs-13 reset-padding']"));

		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		ArrayList<String> expectedList = new ArrayList<>();
		expectedList.add("Orders");
		expectedList.add("Shortlist");
		expectedList.add("SD Cash Rs. 0");
		expectedList.add("E-Gift Voucher");
		expectedList.add("LOGOUT");

		ArrayList<String> optionsList = new ArrayList<>();
		for (int index = 1; index <= 5; index++) {
			String listElement = driver.findElement(By.xpath("//div[@class='dropdownAccount']//li[" + index + "]/a"))
					.getText();
			optionsList.add(listElement);
		}
		if (expectedList.equals(optionsList)) {
			System.out.println("User options Verified");
		}
		driver.findElement(By.xpath("//div[@class='dropdownAccount']//li[1]/a")).click();
		String expectHeading = "MY ORDERS";
		String expectedTitle = "Order History";
		String expUrl = "https://www.snapdeal.com/myorders";

		if ((driver.findElement(By.xpath("//span[@class='accountOrder']")).getText()).equals(expectHeading)
				&& driver.getTitle().equals(expectedTitle) && driver.getCurrentUrl().equals(expUrl)) {
			System.out.println("Page URL , Heading and Title verified");
		}

		List<WebElement> orderId = driver.findElements(By.xpath("//div[@class='orderId']"));
		Iterator<WebElement> itr = orderId.iterator();
		int orderIdCnt = 0;
		while (itr.hasNext()) {
			orderIdCnt++;
			itr.next();
		}
		System.out.println("Total order IDs visible : " + orderIdCnt);

		// navigate to home thruogh breadcrum
		driver.findElement(By.xpath("//div[@class='lfloat containerBreadcrumb']/a/span[1]")).click();
		if (driver.getTitle().equals(
				"Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com")) {
			System.out.println("Successfully Redirected to Dashboard");
		}
		// Click on shopping cart
		driver.findElement(By.xpath("//div[@class='cartContainer col-xs-11 reset-padding']")).click();
		String emptyCartMsg = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart-heading clearfix']")))
				.getText();
		if (emptyCartMsg.equals("Shopping Cart is empty!")) {
			System.out.println("Cart empty msg verified");
		}

		int count = 0;

		List<WebElement> options = driver
				.findElements(By.xpath("//div[@class='cart-categories-list-wrapper row']//li"));
		Iterator<WebElement> iterator = options.iterator();

		while (iterator.hasNext()) {
			count++;
			iterator.next();
		}
		System.out.println("Total items present under browse category : " + count);
		WebElement startShopping = driver
				.findElement(By.xpath("//a[@class='btn btn-xl rippleWhite cart-shopping-button col-xs-7']"));
		startShopping.isDisplayed();
		startShopping.click();
		if (driver.getTitle().equals(
				"Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com")) {
			System.out.println("Successfully Redirected to Dashboard");
		}
	}

	@AfterMethod
	void tearDown() {
		driver.quit();
	}
}