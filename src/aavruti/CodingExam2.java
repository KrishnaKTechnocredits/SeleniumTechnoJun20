package aavruti;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import aavruti.base.PredefinedActions;

public class CodingExam2 extends PredefinedActions{
	
	WebDriver driver;
	WebDriverWait wait;

	//launch Browser
	void launchBrowser() {
		driver = start("https://www.snapdeal.com/");
		wait = new WebDriverWait(driver,15);
	}
	
	/*1. User should successfully navigated to snapdeal website.
	2. Snapdeal page title should be "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com"
	3. User should be able to see "LogIn" option.
	4. "Login/Sign Up On Snapdeal" form should be open with 'Facebook' option.*/
	void snapDealLogin() {
		String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		System.out.println(driver.getTitle().equals(expectedTitle) ? "Titles Matched" : "Titles doesnot matched");
		
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//div[@class='accountInner']/span[@class='accountUserName col-xs-12 reset-padding']"))).build().perform();
		
		driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']/a")).click();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header[text()='login/sign up on snapdeal']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='social-button fbLogin col-xs-12']//span[text()='Facebook']")));
	}
	
	/*5. Facebook login page should be open in new window with page title "Log in to Facebook | Facebook".
	6. User should be successfully entered Email or phone number in field.
	7. User should be successfully entered password in field and click on Confirm.*/
	void facebookLogin() {
		driver.findElement(By.xpath("//div[@class='social-button fbLogin col-xs-12']//span[text()='Facebook']")).click();
		
		String mainWindow = driver.getWindowHandle();
		Set<String> facebookWindow = driver.getWindowHandles();
		Iterator<String> itr = facebookWindow.iterator();
		
		while(itr.hasNext()) {
			String fbWindow = itr.next();
			if(!fbWindow.equals(mainWindow)) {
				driver.switchTo().window(fbWindow);
				System.out.println(driver.getTitle().equals("Log in to Facebook | Facebook") ? driver.getTitle() : "Title mismatch");
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys("aavrutid@yahoo.com");
				driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("avdssdf");
				driver.findElement(By.xpath("//button[@id='loginbutton']")).click();				
			}
		}
		driver.switchTo().window(mainWindow);
	}
	
	/* "8. If 'confirm' button is displayed click on it or Page should directly redirected to the main window 'Sign Up' form.
		User is able to see his/her/other Facebook emailID, name and DOB fileds with facebook details and 'Keep me logged in' checkbox should be checked by default."
		9. User should successfully entered Mobile number in field.
		10. User should successfully entered Password in field.
		11. User should unchecked "Keep me logged in" checkbox successfully.*/
	void loginInfo() {
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='j_username_new']")));
		
		System.out.println("EmailID : " + driver.findElement(By.xpath("//input[@id='j_username_new']")).getAttribute("value"));		
		System.out.println("User Name : " + driver.findElement(By.xpath("//input[@id='j_name']")).getAttribute("value"));
		System.out.println("DOB : " + driver.findElement(By.xpath("//input[@id='j_dob']")).getAttribute("value"));
		
		driver.findElement(By.xpath("//input[@id='j_number']")).sendKeys("8485033276");
		driver.findElement(By.xpath("//input[@id='j_password']")).sendKeys("abcdefg");
		
		System.out.println(driver.findElement(By.xpath("//input[@id='keepLoginSignUp']")).isSelected());
		driver.findElement(By.xpath("//input[@id='keepLoginSignUp']/..//label")).click();
		System.out.println((driver.findElement(By.xpath("//input[@id='keepLoginSignUp']")).isSelected() == false) ? "Successfully unselected" : "Not able to unselect");
	}
	
	public static void main(String[] args) {
		CodingExam2 codingExam2 = new CodingExam2();
		
		codingExam2.launchBrowser();
		codingExam2.snapDealLogin();
		codingExam2.facebookLogin();
		codingExam2.loginInfo();
		codingExam2.closeBrowser();
	}
	
	//close browser
	void closeBrowser() {
		driver.quit();
	}
}
