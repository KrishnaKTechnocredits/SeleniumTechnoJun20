package kartikey;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import kartikey.base.PredDefindActions;

public class SnapDealTest extends PredDefindActions {
	WebDriver driver;
	void setup(String url) {
		driver= start(url);
	}
	void tearDown() {
		driver.close();
	}
	void waitexplicityly(String path) {
		WebDriverWait wait = new WebDriverWait(driver, 25);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(path)));
	}
	void snapdealTestStart() throws InterruptedException {
		//Verify Title
		if(driver.getTitle().equals("Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com"))
			System.out.println("Title is verified");
		//Mouse Hover on Signin
		Actions actions= new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//span[text()='Sign In']"))).build().perform();
		driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']/a")).click();
	//			
	//	
		
		
		driver.switchTo().frame(0);
		waitexplicityly("//div[@class='login-register-common']//div[@id='fbUserLogin']//span");
		driver.findElement(By.xpath("//div[@class='login-register-common']//div[@id='fbUserLogin']//span")).click();
		String currentwin= driver.getWindowHandle();
		System.out.println(currentwin);
		Set<String> totalWindows= driver.getWindowHandles();
		System.out.println(totalWindows.size());
		Iterator<String> itr= totalWindows.iterator();
		while(itr.hasNext()) {
			String newpoped=itr.next();
			if(!currentwin.equals(newpoped))
			{
				driver.switchTo().window(newpoped);
				waitexplicityly("//input[@id='email']");
				if(driver.getTitle().equals("Log in to Facebook | Facebook"))
					System.out.println("Title of FB is verified");
				else
					System.out.println("Title is not verified");
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys("styliconkartik007@rediff.com");
				driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Rishabh@0702");
				driver.findElement(By.xpath("//button[@id='loginbutton']")).click();
				System.out.println(newpoped);
			//	driver.close();
			}
		}
		Set<String> totalWindowsafter= driver.getWindowHandles();
		System.out.println(totalWindowsafter.size());
		System.out.println(driver.getWindowHandle());
		System.out.println(currentwin);
		driver.switchTo().window(currentwin);
	//	waitexplicityly("//input[@id='j_number']");
	//	waitexplicityly("//iframe"); explicit wait is not working geting timeout error every time
		Thread.sleep(25000);
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//input[@id='j_number']")).sendKeys("9711673869");
		driver.findElement(By.xpath("//div[@class='loginCheckbox keepLoginSignUp']//label")).click();
				
	}
	public static void main(String[] args) throws InterruptedException {
		SnapDealTest snapDealTest= new SnapDealTest();
		String url="https://www.snapdeal.com/";
		snapDealTest.setup(url);
		snapDealTest.snapdealTestStart();
		
	}

}
