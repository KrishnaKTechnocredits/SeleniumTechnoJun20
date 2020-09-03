package pooja;

import java.sql.Driver;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WindowHandling_Assignment_13 {
	WebDriver driver;
	@Test
	void start() {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///C:/Users/Omkar/Downloads/New%20Window%20Open.html");
	}
	@Test
	void windowHandlingDemo()
	{   for(int i=1;i<=4;i++) {
		driver.findElement(By.xpath("//button["+i+"]")).click();
		String mainwin=driver.getWindowHandle();
		Set <String>mult=driver.getWindowHandles();
		Iterator<String> itr=mult.iterator();
		while(itr.hasNext()) {
			String curent=itr.next();
			if(!curent.equals(mainwin))
			{
				driver.switchTo().window(curent);
				driver.close();
				driver.switchTo().window(mainwin);
			}
		}
	}
		
		
		
		
	//	driver.switchTo().window();
	}
}
