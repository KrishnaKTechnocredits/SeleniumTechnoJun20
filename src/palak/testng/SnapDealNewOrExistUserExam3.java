package palak.testng;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import palak.base.PredefinedActions;

public class SnapDealNewOrExistUserExam3 extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	Actions actions;
	String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";

	@BeforeClass
	void setUp() {
		driver = start("https://www.snapdeal.com/");
	}

	@Test(dataProvider = "loginDataProvider")
	public void LoginViaExistOrNewUser(String userName, String pwd, String loggedInUserName , String NewOrExisting) {

		Assert.assertEquals(driver.getTitle(), expectedTitle);

		WebElement signInElement = driver.findElement(By.xpath("//div[@class='accountInner']"));
		actions = new Actions(driver);
		actions.moveToElement(signInElement).build().perform();
		WebElement loginElement = driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']/a"));

		String expectedLoginText = "LOGIN";
		String LoginText = loginElement.getText();
		Assert.assertEquals(LoginText, expectedLoginText);

		loginElement.click();
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeLogin"));

		//Entering Username ,Password and click on Login Button & Validate Page Title
		driver.findElement(By.xpath("//div[@class='iframeSignin']/div[@class='userAuthIcons']/div/div[6]/form/div/input[@id='userName']")).sendKeys(userName);
		driver.findElement(By.xpath("//button[@id='checkUser']")).click();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//form[@id='loginUC']/input[@name='j_password']"))));

		WebElement password = driver.findElement(By.xpath("//form[@id='loginUC']/input[@name='j_password']"));
		WebElement loginButton = driver.findElement(By.xpath("//button[@id='submitLoginUC']"));

		password.sendKeys(pwd);
		loginButton.click();
		Assert.assertEquals(driver.getTitle(), expectedTitle);

		//Validate Logged In User 
		String actualLoggedInUserNameText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']"))).getText();
		actions.moveToElement(driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']"))).build().perform();

		Assert.assertEquals(actualLoggedInUserNameText, loggedInUserName);

		//Validate Appearing list
		String[] dropDownValues = { "Orders", "Shortlist", "SD Cash Rs. 0", "E-Gift Voucher", "LOGOUT" };
		List<WebElement> valuesList = driver.findElements(By.xpath("//div[@class='dropdownAccount']//a"));

		for (int index = 0; index < valuesList.size(); index++) {
			Assert.assertEquals(valuesList.get(index).getText(), dropDownValues[index]);
		}

		// Click on Orders
		driver.findElement(By.xpath("//div[@class='dropdownAccount']//a[text()='Orders']")).click();

		// Validate MY ORDERS Heading Page
		String expectedMyOrderText = "MY ORDERS";
		WebElement myOrderElement = driver.findElement(By.xpath("//span[@class='accountOrder']"));
		Assert.assertEquals(myOrderElement.getText(), expectedMyOrderText);

		// Validate Page Title
		String expectedTitleText = "Order History";
		Assert.assertEquals(driver.getTitle(), expectedTitleText);

		// Validate Page URL
		String expectedPageUrl = "https://www.snapdeal.com/myorders";
		Assert.assertEquals(driver.getCurrentUrl(), expectedPageUrl);

	
		if (NewOrExisting.equals("Yes")) {
			WebElement noOrderElement = driver.findElement(By.xpath("//div[@class='orderData']/div"));
			Assert.assertEquals(noOrderElement.getText(),"NO ORDERS AVAILABLE");
		} 
		else {
			// Validate Total orders (Qty.2)
			List<WebElement> totalOrderList = driver.findElements(By.xpath("//div[@class='outerOrderDetails']/div[@class='orderId']"));
			for (int index = 0; index < totalOrderList.size(); index++) {
				Assert.assertTrue(totalOrderList.get(index).isDisplayed());
			}
		}

		// Click on Home option
		driver.findElement(By.xpath("//a[@class='bCrumbOmniTrack']/span[text()='Home']")).click();
		Assert.assertEquals(driver.getTitle(), expectedTitle);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='cartTextSpan']"))).click();

		String expectedCartEmptyText = "Shopping Cart is empty!";

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart-heading clearfix']/h3")));
		WebElement CartEmptyElement = driver.findElement(By.xpath("//div[@class='cart-heading clearfix']/h3"));
		Assert.assertEquals(CartEmptyElement.getText(), expectedCartEmptyText);

		// Validate Browsing Categories total items
		Assert.assertEquals(driver.findElements(By.xpath("//li[@class='col-xs-6']")).size(), 20);

		actions.moveToElement(driver.findElement(By.xpath("//a[@class='btn btn-xl rippleWhite cart-shopping-button col-xs-7']")));
		driver.findElement(By.xpath("//a[@class='btn btn-xl rippleWhite cart-shopping-button col-xs-7']")).click();

		Assert.assertEquals(driver.getTitle(), expectedTitle);

		// Logout for checking with new User
		actions.moveToElement(driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']"))).build().perform();
		driver.findElement(By.xpath("//a[@class='accountBtn rippleWhite sign logout-account']")).click();
	}

	@DataProvider(name = "loginDataProvider")
	public Object[][] logindata() {
		Object[][] data = new Object[2][4];

		data[0][0] = "8109575444";
		data[0][1] = "SnapDeal@1234";
		data[0][2] = "Palak Soni";
		data[0][3] = "No";

		data[1][0] = "9589019444";
		data[1][1] = "Kiran0560";
		data[1][2] = "Kiran Soni";
		data[1][3] = "Yes";

		return data;

	}
}
