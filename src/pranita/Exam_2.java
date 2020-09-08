package pranita;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pranita.basic.PredefinedFunctions;

public class Exam_2 extends PredefinedFunctions {
	WebDriver driver;
	WebDriverWait wait;
	
	void setUp(String url) {
		driver = start(url);
	}
	void tearDown() {
		driver.close();
	}
	void signUpSnapDealUsingFB() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//Step-2
		String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
        String actualTile= driver.getTitle();
        if(actualTile.equals(expectedTitle)) {
        	System.out.println("SnapDeal pageTitle is verified: "+expectedTitle);	
        }
        else {
        	System.out.println("PageTitle not verified");
        }
        //Step-3
        WebElement element = driver.findElement(By.xpath("//div[@class='accountInner']/span[@class='accountUserName col-xs-12 reset-padding']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
		
		driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']/a")).click();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));

		driver.findElement(By.xpath("//div[@class='social-button fbLogin col-xs-12']//span[text()='Facebook']")).click();

		String mainWindow = driver.getWindowHandle();
		Set<String> facebookWindow = driver.getWindowHandles();
		Iterator<String> itr = facebookWindow.iterator();

		while(itr.hasNext()) {
			String fbWindow = itr.next();
			if(!fbWindow.equals(mainWindow)) {
				driver.switchTo().window(fbWindow);
				System.out.println(driver.getTitle().equals("Log in to Facebook | Facebook") ? driver.getTitle() : "Title mismatch");
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys("pranita.mini@gmail.com");
				driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("ccccc");
				driver.findElement(By.xpath("//button[@id='loginbutton']")).click();				
			}
		}
		driver.switchTo().window(mainWindow);
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='j_username_new']")));
		
	}
	
	public static void main(String[] args) {
		Exam_2 exam_2 = new Exam_2();
		exam_2.setUp("https://www.snapdeal.com/");
		exam_2.signUpSnapDealUsingFB();
		
	}

}
