package kartikey;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FacebookLoginByXpath {
	
	 private static WebDriver start(String url) {
		String os=System.getProperty("os.name").toLowerCase();
		String path= os.contains("windows")? ".//resources//windows//chromedriver.exe":os.contains("mac")?".//resources//chromedriver": null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver= new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		return driver;
	}
	 void facebookLogin(WebDriver driver) {
		 if(driver.getTitle().equals("Sign up for Facebook | Facebook"))
			 System.out.println("Title is verified");
		 else
			 System.out.println("Title is not verified");
		 
		 driver.findElement(By.xpath("//input[@id='u_0_n']")).sendKeys("Kartikey");
		 driver.findElement(By.xpath("//input[@id='u_0_p']")).sendKeys("Dev");
		 driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("KD@gmail.com");
		 driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).sendKeys("KD@gmail.com");
		 driver.findElement(By.xpath("//input[@autocomplete='new-password']")).sendKeys("Kartikey@123");
		 // select class to select dropdown
		 
		 Select selectDay= new Select(driver.findElement(By.xpath("//select[@id='day']")));
		 selectDay.selectByVisibleText("30");
		 
		 Select selectMonth= new Select(driver.findElement(By.xpath("//select[@title='Month']")));
		 selectMonth.selectByValue("9");
		 
		 Select selectYear= new Select(driver.findElement(By.xpath("//select[@id='year']")));
		 selectYear.selectByIndex(27);
	
		 // Alternate to select
		 List<WebElement> year= selectYear.getOptions();
		 WebElement yearToSelect= null;
		 for(WebElement element:year) {
			 if(element.getText().equals("1993")) {
				 yearToSelect=element;
				 break;
			 }			 
		 }
		 yearToSelect.click();
		 
		 driver.findElement(By.xpath("//input[@id='u_0_5']")).click();
		 driver.close();
	 }
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Provide the url");
		String url= scanner.next();
		FacebookLoginByXpath facebookLoginByXpath= new FacebookLoginByXpath();
		facebookLoginByXpath.facebookLogin(FacebookLoginByXpath.start(url));
	}

}
