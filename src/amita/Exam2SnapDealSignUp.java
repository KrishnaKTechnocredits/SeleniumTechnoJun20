package amita;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amita.base.PredefinedActions;

public class Exam2SnapDealSignUp extends PredefinedActions {

	WebDriver driver;
	WebDriverWait wait;

	void setUp() {
		driver = start("https://www.snapdeal.com/");
		wait = new WebDriverWait(driver, 15);
	}

	/*
	 * 1. User should successfully navigated to snapdeal website. 2. Snapdeal page
	 * title should be
	 * "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com"
	 * 3. User should be able to see "LogIn" option. 4. "Login/Sign Up On Snapdeal"
	 * form should be open with 'Facebook' option.
	 */
	void signUpSnapDeal() {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		System.out.println(driver.getTitle().equals(
				"Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com")
						? "Test PASS : Navigated to Snapdeal.com"
						: "Test FAIL : Navigation failed");

		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']"));
		actions.moveToElement(element).build().perform();
		driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iFrame[@id='loginIframe']")));
		driver.switchTo().frame(driver.findElement(By.xpath("//iFrame[@id='loginIframe']")));
		driver.findElement(By.xpath("//div[@class='userAuth-card']/div[6]/div/div[2]")).click();
	}

	/*
	 * 5. Facebook login page should be open in new window with page title
	 * "Log in to Facebook | Facebook". 6. User should be successfully entered Email
	 * or phone number in field. 7. User should be successfully entered password in
	 * field and click on Confirm.
	 */
	void facebookLogin() {

		String mainWindow = driver.getWindowHandle();
		Set<String> multiWindow = driver.getWindowHandles();
		Iterator<String> itr = multiWindow.iterator();
		while (itr.hasNext()) {
			String currentWindow = itr.next();
			if (!currentWindow.equals(mainWindow)) {
				driver.switchTo().window(currentWindow);
				System.out.println("-->  Title is : " + driver.getTitle());
			}
		}
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("aliaalisha224@gmail.com");
		driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@id='loginbutton']")).click();

		driver.switchTo().window(mainWindow);
	}
	/*
	 * "8. If 'confirm' button is displayed click on it or Page should directly
	 * redirected to the main window 'Sign Up' form. User is able to see
	 * his/her/other Facebook emailID, name and DOB fileds with facebook details and
	 * 'Keep me logged in' checkbox should be checked by default." 9. User should
	 * successfully entered Mobile number in field. 10. User should successfully
	 * entered Password in field. 11. User should unchecked "Keep me logged in"
	 * checkbox successfully.
	 */

	void signUpFacebook() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@id='loginIframe']")));
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='j_username_new']")));

		System.out.println("Enter Email-ID : "
				+ driver.findElement(By.xpath("//input[@id='j_username_new']")).getAttribute("value"));
		System.out
				.println("User Name : " + driver.findElement(By.xpath("//input[@id='j_name']")).getAttribute("value"));
		System.out.println("DOB : " + driver.findElement(By.xpath("//input[@id='j_dob']")).getAttribute("value"));

		driver.findElement(By.xpath("//input[@id='j_number']")).sendKeys("1234567890");
		driver.findElement(By.xpath("//input[@id='j_password']")).sendKeys("blue10star");

		System.out.println(driver.findElement(By.xpath("//input[@id='keepLoginSignUp']")).isSelected()
				? "Checkbox already selected"
				: "Checkbox not selected");
		driver.findElement(By.xpath("//label[@for='keepLoginSignUp']")).click();
		if (!driver.findElement(By.xpath("//label[@for='keepLoginSignUp']")).isSelected()) {
			System.out.println("unchecked 'Keep me logged in' checkbox successfully.");
		}
	}

	void closeBrowser() {
		driver.quit();
	}

	public static void main(String[] args) {
		Exam2SnapDealSignUp exam2SnapDealSignUp = new Exam2SnapDealSignUp();
		exam2SnapDealSignUp.setUp();
		exam2SnapDealSignUp.signUpSnapDeal();
		exam2SnapDealSignUp.facebookLogin();
		exam2SnapDealSignUp.signUpFacebook();
		exam2SnapDealSignUp.closeBrowser();
	}
}
