package nikhil;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import nikhil.base.PreDefinedActions;

public class SnapdealRegistration extends PreDefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	
	void setUp(String url) {
		driver = start(url);
		wait = new WebDriverWait(driver,15);
	}
	
	void tearDown() {
		driver.close();
	}
	
	void register() throws InterruptedException {
		String expectedSnapdealTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		if(expectedSnapdealTitle.equals(driver.getTitle())) {
			System.out.println("Step 1 : Snapdeal page title verified : PASS --> "+driver.getTitle());
		}else {
			System.out.println("Step 1 : Snapdeal page title NOT verified : FAIL");
		}
		String mainWindow = driver.getWindowHandle();
		
		Actions actions = new Actions(driver);
	
		//Hovering over Sign-in
		actions.moveToElement(driver.findElement(By.xpath("//div[@class='accountInner']"))).build().perform();
		
		//Clicking login button
		driver.findElement(By.xpath("//a[text()='login']")).click();
		
		//Switching to iFrame for logging-in options
		driver.switchTo().frame("loginIframe");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header[text()='login/sign up on snapdeal']")));
		driver.findElement(By.xpath("//div[@class='login-register-common']/div/div[@id='fbUserLogin']")).click();
		
		//Choosing login using FB option
		Set<String> popUps = driver.getWindowHandles();
		Iterator<String> itr = popUps.iterator();
		while(itr.hasNext()) {
			String fbWindow = itr.next();
			if(!fbWindow.equals(mainWindow)) {
				driver.switchTo().window(fbWindow);
				if(driver.getTitle().equals("Log in to Facebook | Facebook")) {
					System.out.println("Step 2 : Facebook page title verified : PASS --> "+driver.getTitle());
				}
				else {
					System.out.println("Step 2 : Facebook page title NOT verified : FAIL");
				}
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys("nikhil.nam@gmail.com");
				driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("mypillu123");
				driver.findElement(By.xpath("//button[@id='loginbutton']")).click();
			}
		}
		driver.switchTo().window(mainWindow);
		
		//Input verification for FB data
		driver.switchTo().frame("loginIframe");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='j_username_new']")));
		//Printing the data
		System.out.println("Step 8:-");
		System.out.println("Email ID - "+driver.findElement(By.xpath("//input[@id='j_username_new']")).getAttribute("value"));
		System.out.println("Customer name - "+driver.findElement(By.xpath("//input[@id='j_name']")).getAttribute("value"));
		driver.findElement(By.xpath("//input[@id='j_number']")).sendKeys("134567890");
		System.out.println("Sent 1234567890 to Mobile No. field.");
		driver.findElement(By.xpath("//input[@id='j_password']")).sendKeys("hello123");
		System.out.println("Sent password to Password field.");
		driver.findElement(By.xpath("//label[@for='keepLoginSignUp']")).click();
		System.out.println("Keep Me Logged In checkbox unselected.");
	}
	
	public static void main(String[] args) throws InterruptedException {
		SnapdealRegistration snapdealRegistration = new SnapdealRegistration();
		snapdealRegistration.setUp("https://www.snapdeal.com/");
		snapdealRegistration.register();
		snapdealRegistration.tearDown();
	}
}
