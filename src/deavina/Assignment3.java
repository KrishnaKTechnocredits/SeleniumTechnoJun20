package deavina;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignment3 {
	public static void main(String[] args) throws InterruptedException {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : " + os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/r.php");
		if ((driver.getTitle()).equals("Facebook – log in or sign up")) {
			System.out.println("Title of the page is Facebook-log in or sign up");
		}
		driver.findElement(By.name("firstname")).sendKeys("Deavina");
		driver.findElement(By.name("lastname")).sendKeys("Gupta");
		driver.findElement(By.name("reg_email__")).sendKeys("8jhh8988999");
		driver.findElement(By.name("reg_passwd__")).sendKeys("Jan@2020");
		new Select(driver.findElement(By.name("birthday_day"))).selectByValue("20");
		new Select(driver.findElement(By.name("birthday_month"))).selectByVisibleText("Jan");
		new Select(driver.findElement(By.name("birthday_year"))).selectByValue("1996");
		driver.findElement(By.name("sex")).click();
		driver.findElement(By.name("websubmit")).click();
		driver.close();
	}
}
