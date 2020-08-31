package pooja;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.org.apache.xerces.internal.util.DraconianErrorHandler;

public class Coding_Exam_3 {
	WebDriver driver;

	void Snapdeal_login() {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		driver = new ChromeDriver();

		// navigate to snapdeal wesite
		driver.navigate().to("https://www.snapdeal.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// verify page title
		String ActualTitle = driver.getTitle();
		String expextedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		if (ActualTitle.equals(expextedTitle)) {
			System.out.println("Test for page title is pass");
		} else {
			System.out.println("Test for page title is fail");
		}

		// mouse hover on sign in option
		Actions actions = new Actions(driver);
		WebElement src = driver.findElement(By.xpath("//div[@class='accountInner']"));
		actions.moveToElement(src).build().perform();

		// click on login option
		driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']")).click();
	}

	// Verify User is able to Login/Sign In using mobile number.
	@Test
	void LoginUsing_MobileNo(){
		Snapdeal_login();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));

		WebElement mobNo =driver.findElement(By.xpath("//input[@id='userName']"));
		mobNo.isEnabled();
		mobNo.sendKeys("7387691776");
		driver.findElement(By.xpath("//button[@id='checkUser']")).click();
		WebElement pass=driver.findElement(By.xpath("//input[@id='j_password_login_uc']"));
		pass.isEnabled();
		pass.sendKeys("Pooja9009");
		driver.findElement(By.xpath("//div[@class='loginCheckbox keepLoginUC']")).click();
		driver.findElement(By.id("submitLoginUC")).click();

	}
	// Verify User is able to Login/Sign In using EmailId.
	@Test
	void LoginUsing_EmailId(){
		Snapdeal_login();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
        String expected=driver.findElement(By.xpath("//div[@id='googleUserLogin']//span[text()='Google'][1]")).getText();
        String actual="Google";
        Assert.assertEquals(expected, actual);
		WebElement email=driver.findElement(By.xpath("//input[@id='userName']"));
		email.isEnabled();
		email.sendKeys("er.poojaraut@gmail.com");
		driver.findElement(By.xpath("//button[@id='checkUser']")).click();
		WebElement pass=driver.findElement(By.xpath("//input[@id='j_password_login_uc']"));
		pass.isEnabled();
		pass.sendKeys("Pooja9009");
				
		driver.findElement(By.xpath("//div[@class='loginCheckbox keepLoginUC']")).click();
		driver.findElement(By.id("submitLoginUC")).click();
		
	}
}
