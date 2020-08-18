package sonal;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignment3 {
	
	public static void main(String[] args){
	
	/*String os = System.getProperty("os.name").toLowerCase();
	System.out.println("os : "+os);
	String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
			: os.contains("mac") ? "./resources/mac/chromedriver" : null;*/

	System.setProperty("webdriver.chrome.driver", "E:/NewWorkSpace/SeleniumBasics1/chromedriver.exe");
		
	WebDriver driver  = new ChromeDriver();
	
	driver.manage().window().maximize();
	
	driver.get("https://www.facebook.com/r.php");
	
	String acttitle = driver.getTitle();
	String exptitle = "Sign up for Facebook | Facebook";
	
	if(acttitle.equals(exptitle))  //verification of title
		System.out.println("Test is pass titles are matching");
	else
		System.out.println("Test is fail, titles are not matching");
	
	driver.findElement(By.name("firstname")).sendKeys("Avntika");
	driver.findElement(By.name("lastname")).sendKeys("Pai");
	driver.findElement(By.id("u_0_s")).sendKeys("995676767hh68");//enter invalid mobile number
	driver.findElement(By.id("password_step_input")).sendKeys("@gkhg676");
	
	WebElement Daydd = driver.findElement(By.id("day")); //steps for locating dropdown and selecting option from Day
	Select option = new Select(Daydd);
	option.selectByVisibleText("2");
	
	WebElement Monthdd = driver.findElement(By.id("month")); //Steps for selection of month
	Select optionm = new Select(Monthdd);
	optionm.selectByIndex(3);
	
	WebElement Yeardd = driver.findElement(By.id("year"));
	Select optiony = new Select(Yeardd);
	optiony.selectByValue("2000");
	
	driver.findElement(By.xpath("//div[@id='u_0_z']//span[@class='_5k_3']/span[@class='_5k_2 _5dba']/input[@value='1']")).click();//Click on female radio button option
	
	driver.findElement(By.name("websubmit")).click(); //Click on Sign up button
	
	driver.close();
	


}
}

