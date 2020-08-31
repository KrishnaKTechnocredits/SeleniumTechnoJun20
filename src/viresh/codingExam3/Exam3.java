package viresh.codingExam3;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import viresh.utility.SetUp;

public class Exam3 extends SetUp {

	WebDriver driver;
	WebElement signIn, login;
	Actions action;
	String expectedTitle, expectedOrderURL;
	ArrayList<String> expectedList, actualList;
	WebDriverWait wait;
	
	@BeforeClass
	public void browserStart() {
		driver = setUp("https://www.snapdeal.com/");
		expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle.equals(expectedTitle) ? "On Snapdeal HomePage: " + actualTitle
				: "Not on Snapdeal HomePage");
		Assert.assertEquals(expectedTitle, actualTitle);
	}
	
	void mouseHover(WebElement target) {
		action = new Actions(driver);
		action.moveToElement(target).build().perform();
	}

	void cart(WebElement element) {
		element.click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MICROSECONDS);
		String expectedCartStatus = "Shopping Cart is empty!";

		String cartStatus= driver.findElement(By.xpath("//h3[text()='Shopping Cart is empty!']")).getText(); //div[@class= 'cart-heading clearfix']/h3
		System.out.println(" >> " + expectedCartStatus);
		System.out.println(cartStatus.equals(expectedCartStatus) ? cartStatus : "cart status does not match with expected status.");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Browse Categories']")));
		String bCategories = driver.findElement(By.xpath("//p[@class='text-center browse-category-text']")).getText(); 
		System.out.println(" >> We are under: " + bCategories);

		int browserCatogories = driver.findElements(By.xpath("//div[@class= 'cart-categories-list-wrapper row']/ul/li")).size();

		System.out.println(browserCatogories == 20 ? "Browser Categories equals 20. " : " Browser Categories NOT equals 20");
		Assert.assertEquals(browserCatogories, 20);

		driver.findElement(By.xpath("//a[text()='START SHOPPING NOW']")).click();

		System.out.println(driver.getTitle().equals(expectedTitle) ? "Navigated from Cart to Main page"
				: "Problem navigating from Cart to Main Page");
		Assert.assertEquals(driver.getTitle(), expectedTitle);
	}

	@Test
	public void cartIsEmpty() { //TC5
		signIn = driver.findElement(By.xpath("//span[text()='Sign In']"));
		mouseHover(signIn);
		login = driver.findElement(By.xpath("//a[text()='login']"));
		login.click();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MICROSECONDS);
		driver.switchTo().frame("loginIframe");
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MICROSECONDS);
		driver.findElement(By.xpath("//input[@id= 'userName']")).sendKeys("9028253825");
		driver.findElement(By.xpath("//button[@id='checkUser']")).click();
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Viresh Jangam']")));
		System.out.println("URL after login: " + driver.getCurrentUrl());
		mouseHover(driver.findElement(By.xpath("//span[text()='Viresh Jangam']")));
		int userOptionCount = driver.findElements(By.xpath("//div[@class='dropdownAccount']/div/ul/li")).size();
		System.out.println(" >> " + userOptionCount);
		actualList = new ArrayList<>();
		for (int index = 1; index <= userOptionCount; index++) {
			String option = driver.findElement(By.xpath("//div[@class='dropdownAccount']/div/ul/li[" + index + "]"))
					.getText();
			actualList.add(option);
		}

		System.out.println(" >> Actual List: " + actualList); // temp

		expectedList = new ArrayList<>();
		expectedList.add("Orders");
		expectedList.add("Shortlist");
		expectedList.add("SD Cash Rs. 0");
		expectedList.add("E-Gift Voucher");
		expectedList.add("LOGOUT");

		System.out.println(" >> Expected list: " + expectedList); // temp

		//Assert.assertEquals(expectedList, actualList);
		System.out.println(expectedList.equals(actualList) ? " >> Options are MATCHING WITH: " + expectedList
				: "Options are NOT MATCHING WITH: " + expectedList);
		driver.findElement(By.xpath("//div[@class='dropdownAccount']/div/ul/li/a[text()= 'Orders']")).click();
		expectedOrderURL = "https://www.snapdeal.com/myorders";
		driver.getCurrentUrl();
		//Assert.assertEquals(driver.getCurrentUrl(), expectedOrderURL);
		System.out.println(driver.getCurrentUrl().equals(expectedOrderURL) ? " >> Navigated to My Orders page. "
				: "Problem navigating to My Orders Page.");
		driver.findElement(By.xpath("//span[text()='Home']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Viresh Jangam']")));
		//Assert.assertEquals(driver.getTitle(), expectedTitle);
		System.out.println(driver.getTitle().equals(expectedTitle) ? " >> Landed on Home Page" + expectedTitle
				: "Not Landed on Home Page");
		driver.findElement(By.xpath("//span[text()='Cart']")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MICROSECONDS);
		cart(driver.findElement(By.xpath("//span[text()='Cart']")));
	}
	
	@Test
	public void noOrderAvailable() { //TC4
		Assert.assertEquals(expectedList, actualList);
		mouseHover(driver.findElement(By.xpath("//span[text()='Viresh Jangam']")));
		driver.findElement(By.xpath("//div[@class='dropdownAccount']/div/ul/li[1]")).click();
		
		Assert.assertEquals(driver.getTitle(), "Order History");
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='NO ORDERS AVAILABLE']")).getText(), "NO ORDERS AVAILABLE");
		
		Assert.assertEquals(driver.getCurrentUrl(), expectedOrderURL);
		driver.findElement(By.xpath("//span[text()='Home']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Viresh Jangam']")));
		Assert.assertEquals(driver.getTitle(), expectedTitle);
		cart(driver.findElement(By.xpath("//span[text()='Cart']")));
	}

	@AfterClass
	public void wrapUp() {
		tearDown();
	}
}
