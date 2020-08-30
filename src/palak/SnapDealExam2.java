package palak;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import palak.base.PredefinedActions;

public class SnapDealExam2 extends PredefinedActions{
	WebDriver driver;
	void setUp(String url) {
		driver = start(url);
	}
	
	void signInUsingFB() {
		String expectedTitle="Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		System.out.println(driver.getTitle().equals(expectedTitle)?"Title is correct":"Title not correct");
		WebElement signInElement = driver.findElement(By.xpath("//div[@class='accountInner']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(signInElement).build().perform();
		WebElement loginElement =driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']/a"));
		loginElement.click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
		driver.findElement(By.xpath("//div[@class='iframeSignin']/div[@class='userAuthIcons']/div/div[6]/div/div[2]")).click();
		
		String expectedFBTitle="Log in to Facebook | Facebook";
		String mainTab = driver.getWindowHandle();
		Set<String> multiTab= driver.getWindowHandles();
		Iterator<String> itr = multiTab.iterator();
		while (itr.hasNext()) {
			String currentTab = itr.next();
			if (!currentTab.equals(mainTab)) {
				driver.switchTo().window(currentTab);
				System.out.println(driver.getTitle().equals(expectedFBTitle)?"Title is correct":"Title not correct");
				WebElement email = driver.findElement(By.name("email"));
				email.sendKeys("Palaksoni1907@gmail.com");
				WebElement password = driver.findElement(By.name("pass"));
				password.sendKeys("Facebook@444");
				driver.findElement(By.name("login")).click();
			}
			
		}
		driver.switchTo().window(mainTab);
		System.out.println(driver.getTitle());
		
		//driver.close();
	}
	
	public static void main(String[] args) {
		SnapDealExam2 snapDealExam2 = new SnapDealExam2();
		snapDealExam2.setUp("https://www.snapdeal.com/");
		snapDealExam2.signInUsingFB();
	}

}
