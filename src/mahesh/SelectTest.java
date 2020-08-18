package mahesh;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectTest {
	
	void signUp(WebDriver driver) {
		driver.findElement(By.name("firstname")).sendKeys("Mahesh");
		driver.findElement(By.name("lastname")).sendKeys("Kumavat");
		driver.findElement(By.name("reg_email__")).sendKeys("8521478523");
		driver.findElement(By.id("password_step_input")).sendKeys("Pass@123");
		new Select(driver.findElement(By.id("day"))).selectByValue("20");
		new Select(driver.findElement(By.id("month"))).selectByIndex(8);
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1988");
		driver.findElement(By.xpath("//span/input[@id='u_0_5' and @value='2']")).click();
//		driver.findElement(By.xpath("//input[@id='u_0_4']")).click();
//		driver.findElement(By.xpath("//span/input[@id='u_0_6']")).click();
		driver.findElement(By.name("websubmit")).click();					//Submit Details
	}
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/r.php");
		if (driver.getTitle().equals("Sign up for Facebook | Facebook")){
			SelectTest selectTest = new SelectTest();
			selectTest.signUp(driver);
		}else {
			System.out.println("Unable to open Facebook Signup page");
		}	
	}
}
