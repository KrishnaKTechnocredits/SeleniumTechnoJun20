package viresh.assignment3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FBLocatorTechniques {

	WebDriver start() {
		System.setProperty("webdriver.chrome.driver", ".//resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	void locateElements() throws InterruptedException {
		WebDriver driver = start();
		driver.get("https://www.facebook.com/r.php");
		System.out.println("** Page Title ** : " + driver.getTitle());
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Techno");
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Credits");
		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("9860817a7b");
		driver.findElement(By.xpath("//input[@id='password_step_input']")).sendKeys("abc@123");
		Select oSelect = new Select(driver.findElement(By.name("birthday_day")));
		oSelect.selectByValue("29");
		Select oSelect1 = new Select(driver.findElement(By.name("birthday_month")));
		oSelect1.selectByVisibleText("Feb");
		Select oSelect2 = new Select(driver.findElement(By.name("birthday_year")));
		oSelect2.selectByIndex(10);
		driver.findElement(By.xpath("//span[2]/input[@name='sex']")).click();
		driver.findElement(By.name("websubmit")).click();
		Thread.sleep(5000);
		driver.close();
	}

	public static void main(String[] args) throws InterruptedException {
		new FBLocatorTechniques().locateElements();
	}
}
