/*  1. User should successfully navigated to snapdeal website.
	2. Snapdeal page title should be "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com"
	3. User should be able to see "LogIn" option.
	4. "Login/Sign Up On Snapdeal" form should be open with 'Facebook' option.
    5. Facebook login page should be open in new window with page title "Log in to Facebook | Facebook".
	6. User should be successfully entered Email or phone number in field.
	7. User should be successfully entered password in field and click on Confirm.
	8. If 'confirm' button is displayed click on it or Page should directly redirected to the main window 'Sign Up' form.User is able to see his/her/other Facebook emailID, name and DOB fileds with facebook details and 'Keep me logged in' checkbox should be checked by default."
	9. User should successfully entered Mobile number in field.
	10. User should successfully entered Password in field.
	11. User should unchecked "Keep me logged in" checkbox successfully.*/

package raj;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import raj.selenium.base.PredefinedActions;

public class SeleniumExam2 extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;

	void setup() {
		driver = start("https://www.snapdeal.com/");
	}

	void loginSnapDeal() {
		String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		String actualTitle = driver.getTitle();

		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Title of snapdeal matched : Test Pass");
		} else {
			System.out.println("Title of snapdeal doesn't matched : Test Fail");
		}

		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//div[@class = 'accountInner']/span[text() ='Sign In']"))).build().perform();

		driver.findElement(By.xpath("//span[@class = 'accountBtn btn rippleWhite']/a[text() ='login']")).click();

		driver.switchTo().frame("iframeLogin");
	}

	void loginFacebook() {
		driver.findElement(By.xpath("//div[@class='social-button fbLogin col-xs-12']//span[text()='Facebook']")).click();

		String mainWindow = driver.getWindowHandle();
		Set<String> facebookWindow = driver.getWindowHandles();
		Iterator<String> itr = facebookWindow.iterator();

		while(itr.hasNext()) {
			String fbWindow = itr.next();
			if(!fbWindow.equals(mainWindow)) {
				driver.switchTo().window(fbWindow);
				System.out.println(driver.getTitle().equals("Log in to Facebook | Facebook") ? driver.getTitle() : "Title mismatch");
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abcd@gmail.com");
				driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("pwdxyz");
				driver.findElement(By.xpath("//button[@id='loginbutton']")).click();				
			}
		}
		driver.switchTo().window(mainWindow);
	}

	void loginInformation() {

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='j_username_new']")));

		System.out.println("EmailID : " + driver.findElement(By.xpath("//input[@id='j_username_new']")).getAttribute("value"));		
		System.out.println("User Name : " + driver.findElement(By.xpath("//input[@id='j_name']")).getAttribute("value"));
		System.out.println("DOB : " + driver.findElement(By.xpath("//input[@id='j_dob']")).getAttribute("value"));

		driver.findElement(By.xpath("//input[@id='j_number']")).sendKeys("1234567890");
		driver.findElement(By.xpath("//input[@id='j_password']")).sendKeys("pwdxyz");

		System.out.println(driver.findElement(By.xpath("//input[@id='keepLoginSignUp']")).isSelected());
		driver.findElement(By.xpath("//input[@id='keepLoginSignUp']/..//label")).click();
		System.out.println((driver.findElement(By.xpath("//input[@id='keepLoginSignUp']")).isSelected() == false) ? "Successfully unselected" : "Not able to unselect");
	}

	void closeBrowser() {
		driver.quit();
	}

	public static void main(String[] args) {
		SeleniumExam2 codingExam2 = new SeleniumExam2();
		codingExam2.setup();
		codingExam2.loginSnapDeal();
		codingExam2.loginFacebook();
		codingExam2.loginInformation();
		codingExam2.closeBrowser();
	}

}
