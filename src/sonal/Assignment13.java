package sonal;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment13 {

	
		WebDriver driver;
		public  WebDriver Start(String url){
			
			System.setProperty("webdriver.chrome.driver","E:/NewWorkSpace/SeleniumBasics1/chromedriver.exe"); // Method to initiate web driver and launch url
			
			WebDriver driver = new ChromeDriver();
			
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
			driver.manage().window().maximize();
			
			return driver;
		}
		
		public void windowhandler() throws InterruptedException
		{
			driver = Start("file:///C:/Users/Dell/Desktop/New%20Window%20Open.html");
			
			Thread.sleep(3000);			
			String mainWin = driver.getWindowHandle();
			
			
			for (int i=1; i<=4;i++){
			driver.findElement(By.xpath("//button["+i+"]")).click();
			
			
			
			Thread.sleep(3000);
					
			Set <String> windowsList = driver.getWindowHandles();
			
			
			Iterator <String> itr = windowsList.iterator();
			while(itr.hasNext())
			{
				String browserSession = itr.next();
				if(!browserSession.equals(mainWin))
					{
						driver.switchTo().window(browserSession);
						Thread.sleep(4000);
						
						System.out.println(driver.getTitle());
						driver.close();
						
					}
				
			}
			driver.switchTo().window(mainWin);
			Thread.sleep(3000);
			}
		}
		public static void main(String[] args) throws InterruptedException {
			
			Assignment13 a1 = new Assignment13();
			a1.windowhandler();
		}

	}



