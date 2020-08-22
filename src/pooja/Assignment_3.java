package pooja;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignment_3 {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		//Navigate to facebook sign in page
		driver.navigate().to("https://www.facebook.com/r.php");
		
		//verify page title is Sign up for Facebook | Facebook
	    if(	driver.getTitle().equals("Sign up for Facebook | Facebook"))
	    		System.out.println("Test 1 pass");
	    else {
			System.out.println("Test 1 fail");
		}
        //enter details
	    driver.findElement(By.id("u_0_n")).sendKeys("Pooja");
	    driver.findElement(By.id("u_0_p")).sendKeys("Raut");
	    driver.findElement(By.id("u_0_s")).sendKeys("843692hsdk76");
	    driver.findElement(By.id("password_step_input")).sendKeys("Pooja");
	    WebElement ele =driver.findElement(By.id("day"));
	    Select sel=new Select(ele);
	    sel.selectByValue("11");
	    
	    WebElement ele2 =driver.findElement(By.id("month"));
	    Select sel2=new Select(ele2);
	    sel2.selectByIndex(3);
	    
	    WebElement ele3 =driver.findElement(By.id("year"));
	    Select sel3=new Select(ele3);
	    sel3.selectByValue("1994");
	    driver.findElement(By.xpath("//input[@name='sex' and @value='1']")).click();
	   
	  driver.findElement(By.id("u_0_14")).click();
	
	    
	}

}
