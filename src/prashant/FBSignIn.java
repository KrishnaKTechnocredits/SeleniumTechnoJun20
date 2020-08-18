package prashant;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FBSignIn {
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		
		//Facebook opening ad verification
		driver.get("https://www.facebook.com/r.php");
		if (driver.getTitle().equals("Sign up for Facebook | Facebook")){
			System.out.println("You are on facebook page");
			}
			else {
				System.out.println("Check your url");
			}
		
		
		//Sending details
		Thread.sleep(1000);
		driver.findElement(By.name("firstname")).sendKeys("ABC");
		driver.findElement(By.name("lastname")).sendKeys("XYZ");
		driver.findElement(By.name("reg_email__")).sendKeys("lkj@gmail.com");
		driver.findElement(By.name("reg_email_confirmation__")).sendKeys("lkj@gmail.com");
		driver.findElement(By.name("reg_passwd__")).sendKeys("hhhhh");
		
		System.out.println("You provided all details");
		
		//Selecting date
		WebElement date = driver.findElement(By.name("birthday_day"));
		Select dselect = new Select(date);
		dselect.selectByIndex(18);
		System.out.println("You provided date");
		
		//selecting month
		WebElement month = driver.findElement(By.name("birthday_month"));
		Select mselect = new Select(month);
		mselect.selectByVisibleText("Oct");
		System.out.println("You provided month");
		
		//selecting year
		WebElement year = driver.findElement(By.name("birthday_year"));
		Select yselect = new Select(year);
		yselect.selectByValue("1988");
		System.out.println("You provided Year");
			
		//radio button slection using xpath
		driver.findElement(By.xpath("//div//span//span//input[@value='2']")).click();
		Thread.sleep(5000);
		driver.findElement(By.name("websubmit")).submit();
		
		System.out.println("Form submitted successfully");
		
		//browser closed
		driver.close();
	}

}
