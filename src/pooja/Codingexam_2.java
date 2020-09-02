package pooja;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Codingexam_2 {
	void snapdeal_login()
	{
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		//navigate to snapdeal wesite
		driver.navigate().to("https://www.snapdeal.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//verify page title
		String ActualTitle =driver.getTitle();
		String expextedTitle="Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		if(ActualTitle.equals(expextedTitle))
		{
			System.out.println("Test for page title is pass");
		}
		else {
			System.out.println("Test for page title is fail");
		}
		
		//mouse hover on sign in option
		Actions actions=new Actions(driver);
		WebElement src=driver.findElement(By.xpath("//div[@class='accountInner']"));
		actions.moveToElement(src).build().perform();
		
		//click on login option
		driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
		driver.findElement(By.xpath("//div[@class='login-register-common']/div[@class='loginSocialBtn col-xs-24 reset-padding marT20 marB20']/div[@id='fbUserLogin']/span[text()='Facebook']")).click();
		String mainWin=driver.getWindowHandle();
		Set <String>mult=driver.getWindowHandles();
		Iterator<String> itr=mult.iterator();
		while(itr.hasNext()) {
			String curent=itr.next();
			if(!curent.equals(mainWin))
			{
				driver.switchTo().window(curent);
			}
			
		}
		
		
		driver.findElement(By.xpath("//div[@id='email_container']/input")).sendKeys("poojagatade9009@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("pooja9009");
		driver.findElement(By.xpath("//button[@id='loginbutton']")).click();
		
	}
	
	public static void main(String[] args) {
		Codingexam_2 codingexam_2=new Codingexam_2();
		codingexam_2.snapdeal_login();
	
	}
}
