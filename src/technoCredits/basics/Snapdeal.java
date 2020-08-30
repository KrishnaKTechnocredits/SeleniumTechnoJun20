package technoCredits.basics;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Snapdeal {

	public static void main(String[] args) throws InterruptedException {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : "+ os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;

		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.snapdeal.com/");
		System.out.println("Snapdeal website open successfully.");
		System.out.println(driver.getTitle());
		Thread.sleep(2000);

		
		Actions action = new Actions(driver);
		WebElement target = driver.findElement(By.xpath("//span[text()='Sign In']"));
		action.moveToElement(target).perform();
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='login']")).click();
		System.out.println("Clicked on login.");
		Thread.sleep(1000);
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
		System.out.println("Switched to frame.");
		Thread.sleep(1000);
		
		String MainWindow=driver.getWindowHandle();		
		
		driver.findElement(By.xpath("//div[@id='fbUserLogin']")).click();
		System.out.println("Clicked on fb login.");
		Thread.sleep(2000);
		
        // To handle all new opened window.				
        Set<String> s1=driver.getWindowHandles();		
        Iterator<String> i1=s1.iterator();		
        		
        while(i1.hasNext())			
        {		
            String ChildWindow=i1.next();		
            		
            if(!MainWindow.equalsIgnoreCase(ChildWindow))			
            {    		
            	System.out.println("Switch to window.");
                    // Switching to Child window
                    driver.switchTo().window(ChildWindow);	 
                    Thread.sleep(2000);
                    System.out.println(driver.getTitle());
                    driver.findElement(By.id("email")).sendKeys("maulik_it232@yahoo.com");                			
                    driver.findElement(By.id("pass")).sendKeys("Globant@01");
                    driver.findElement(By.id("loginbutton")).click();
                    System.out.println("Login button clicked");
                    Thread.sleep(2000);
//                    driver.findElement(By.name("__CONFIRM__")).click();
                    System.out.println("Clicked on confirm button.");
                    Thread.sleep(2000);             
			
            }		
        }		
        // Switching to Parent window i.e Main Window.
            driver.switchTo().window(MainWindow);
            System.out.println("Switch to main window.");
            Thread.sleep(2000);
            System.out.println("AFter wait");
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
    		System.out.println("Switched to frame.");
    		Thread.sleep(1000);
    		
    		if(driver.findElement(By.xpath("//p[text()='Create an account on Snapdeal']")).isDisplayed())
    			System.out.println("Yes");
    		else
    			System.out.println("No");
    		Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@id='j_number']")).sendKeys("9561690681");
            System.out.println("phn number added.");
            driver.findElement(By.xpath("//input[@id='j_password']")).sendKeys("snapdeal");
            System.out.println("password added.");
            driver.findElement(By.xpath("//label[@for='keepLoginSignUp']")).click();
            System.out.println("Unchecked checkbox");
            
    }
//		driver.quit();
}
