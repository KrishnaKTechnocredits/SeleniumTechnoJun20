package kartikey;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import kartikey.base.PredDefindActions;

public class SnapdealTest234and5 extends PredDefindActions{
	WebDriver driver;
	@BeforeMethod
	void getUrl() {
		setup("https://www.snapdeal.com/");
	}
	
	void setup(String url) {
		driver=start(url);
	}
	@AfterMethod
	void tearDown() {
		driver.close();
	}
	
	void waitexplicily(String path) {
		WebDriverWait wait = new WebDriverWait(driver, 25);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(path)));
	}
	
	void sendkeysToElment(String path, String data) {
		driver.findElement(By.xpath(path)).sendKeys(data);
	}
	void clickOnElement(String path) {
		driver.findElement(By.xpath(path)).click();
	}
	boolean visibilityOfElement(String path) {
		return driver.findElement(By.xpath(path)).isDisplayed();
	}
	void mouseHover(String path) {
		Actions actions= new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath(path))).build().perform();
	}
	//@Test
	void loginusingMobileNumFirstTimeLogin() {
		
		mouseHover("//div[@class='accountInner']");
		clickOnElement("//a[text()='login']");	
		driver.switchTo().frame(0);
		waitexplicily("//input[@id='userName']");
		sendkeysToElment("//input[@id='userName']", "8791237417");
		clickOnElement("//button[@id='checkUser']");
		waitexplicily("//input[@id='j_name']");
		if(driver.findElement(By.xpath("//input[@id='j_password']")).isDisplayed())
			System.out.println("Password Testfield is visible");
		else
			System.out.println("Password text field is not visible");
		
		sendkeysToElment("//input[@id='j_username_new']", "Kartikeydev@gmail.com");
		sendkeysToElment("//input[@id='j_name']", "KD");
		sendkeysToElment("//input[@id='j_password']", "Kartikey@1");
		clickOnElement("//div[@class='loginCheckbox keepLoginSignUp']");
		clickOnElement("//button[@id='userSignup']");
		System.out.println(driver.getTitle());
		System.out.println(driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")).getText());
		
		
	}
	//@Test
	void loginTestWithMobileOrEmail() {
		mouseHover("//div[@class='accountInner']");
		clickOnElement("//a[text()='login']");	
		driver.switchTo().frame(0);
		waitexplicily("//input[@id='userName']");
		sendkeysToElment("//input[@id='userName']", "8791237417");
		clickOnElement("//button[@id='checkUser']");
		
		waitexplicily("//input[@id='j_password_login_uc']");
		sendkeysToElment("//input[@id='j_password_login_uc']", "Kartikey@1");
		
		if(driver.findElement(By.xpath("//button[@id='sendOtpUC']")).isDisplayed())
			System.out.println("Login without password is visible");
		else
			System.out.println("Login without password is not visible");	
		clickOnElement("//button[@id='submitLoginUC']");
		waitexplicily("//span[@class='accountUserName col-xs-12 reset-padding']");
		System.out.println(driver.getTitle());
		System.out.println(driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")).getText());		
	}
	@Test
	void emptyCartForNewUser () {
		mouseHover("//div[@class='accountInner']");
		clickOnElement("//a[text()='login']");	
		driver.switchTo().frame(0);
		waitexplicily("//input[@id='userName']");
		sendkeysToElment("//input[@id='userName']", "Kartikeydev@gmail.com");
		clickOnElement("//button[@id='checkUser']");
		
		waitexplicily("//input[@id='j_password_login_uc']");
		sendkeysToElment("//input[@id='j_password_login_uc']", "Kartikey@1");
		
		if(driver.findElement(By.xpath("//button[@id='sendOtpUC']")).isDisplayed())
			System.out.println("Login without password is visible");
		else
			System.out.println("Login without password is not visible");	
		clickOnElement("//button[@id='submitLoginUC']");
		
		waitexplicily("//span[@class='accountUserName col-xs-12 reset-padding']");
		System.out.println(driver.getTitle());
		System.out.println(driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")).getText());	
		
		mouseHover("//div[@class='accountInner']");
		clickOnElement("//a[text()='Orders']");
		/*waitexplicily("//div[@class='accountInner']//div[@class='dropdownAccount']//div[@class='accountList']//li//a[1]");
		//MouseHover Elements
		List<WebElement> mousehoverList= driver.findElements(By.xpath("//div[@class='accountInner']//div[@class='dropdownAccount']//div[@class='accountList']//li//a"));
		for(WebElement element:mousehoverList)
			System.out.println(element.getText());
		
		for(WebElement element:mousehoverList) {
			if(element.getAttribute("Value").equals("https://www.snapdeal.com/myorders"))
				element.click();
		}*/
		//Navigate to Home page
		waitexplicily("//span[text()='Home']");
		clickOnElement("//span[text()='Home']");
		waitexplicily("//input[@id='inputValEnter']");
		if(driver.getTitle().equals("Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com"))
			System.out.println("After Order history Title is verified");
		else
			System.out.println("After Order history Title is not verified");
		
		//Cart is clicked
		clickOnElement("//span[text()='Cart']");
		waitexplicily("//h3[text()='Shopping Cart is empty!']");
		// Shopping Cart is empty
		System.out.println(driver.findElement(By.xpath("//h3[text()='Shopping Cart is empty!']")).getText());
		System.out.println("Total no. of items Under BROWSW CATEGORY IS "+driver.findElements(By.xpath("//div[@class='cart-categories-list-wrapper row']//a")).size());
		
		if(driver.findElement(By.xpath("//a[text()='START SHOPPING NOW']")).isDisplayed())
			System.out.println("Start Shopping Now is verified");
		
		//Return to HomePage
		clickOnElement("//div[@class='cart-heading clearfix']//i");
		
		if(driver.getTitle().equals("Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com"))
			System.out.println("After Cart Title is verified");
		else
			System.out.println("After Cart Title is not verified");	
	}

}
