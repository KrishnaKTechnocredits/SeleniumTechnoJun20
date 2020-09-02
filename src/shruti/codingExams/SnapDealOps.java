package shruti.codingExams;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class SnapDealOps {
	
	@Test
	void snapDealLogin(){
		System.setProperty("webdriver.chrome.driver",".\\Resources\\windows\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.snapdeal.com/?utm_term=e,snapdeal&gclid=Cj0KCQjw1qL6BRCmARIsADV9JtapbHWnsQ9dlvE1DTK8_aURGaCabXEeFvpshHYpB6IxVq0Fpo5geCAaAjDKEALw_wcB&utm_campaign=BrandCat_new&utm_source=earth_brand_new&utm_medium=brand%20term,&utm_content=homepage");
		driver.manage().window().maximize();
		if(driver.getTitle().equals("Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com")){
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[@class='accountInner']"))).build().perform();
		driver.findElement(By.xpath("//div/span/a[text()='login']")).click();
		driver.switchTo().frame(driver.findElement(By.id("loginIframe")));
		driver.findElement(By.xpath("//div[@class='userAuth-card']/div/div/div/span[1][text()='Facebook']")).click();
		
		//Switch to Facebook Window
		String mainWindow = driver.getWindowHandle();
		Set<String> fbwin = driver.getWindowHandles();
		Iterator<String> itr = fbwin.iterator();
		while(itr.hasNext()){
			String currentBrowser = itr.next();
			if(!currentBrowser.equals(fbwin))
			//if(driver.getTitle().equals("Log in to Facebook | Facebook"))
			driver.switchTo().window(currentBrowser);
		}
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("Shrutidubey@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("*****");
		driver.findElement(By.id("loginbutton")).click();
	//	driver.findElement(By.name("[name='__CONFIRM__']")).click();
		//driver.switchTo().window(mainWindow);
		
		
	}
	}
}
// could not perform step 8,9,10 due to the issue discussed call/Skype