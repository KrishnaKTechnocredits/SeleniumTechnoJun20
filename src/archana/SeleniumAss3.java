package archana;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumAss3 {
	static WebDriver driver;

	void launchBrowser(String url) {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : " + os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}

	void faceBookSignUp() {
		launchBrowser("https://www.facebook.com/r.php");
		// facebook page title verification
		if (driver.getTitle().equals("Sign up for Facebook | Facebook"))
			System.out.println("Pass--Facebook Signup page loaded sucessfully");
		else
			System.out.println("Fail--Facebook Signup page not loaded-Incorrect page ");
		// enter Details
		driver.findElement(By.xpath("//input[@name=\"firstname\"]")).sendKeys("Archa");
		driver.findElement(By.xpath("//input[@name=\"lastname\"]")).sendKeys("Nawale");
		driver.findElement(By.xpath("//input[@name=\"reg_email__\"]")).sendKeys("905541562");
		driver.findElement(By.xpath("//input[@name=\"reg_passwd__\"]")).sendKeys("Password%1");
		// Select Date
		Select daySelect = new Select(driver.findElement(By.xpath("//select[@name=\"birthday_day\"]")));
		daySelect.selectByValue("3");
		Select monthSelect = new Select(driver.findElement(By.xpath("//select[@name=\"birthday_month\"]")));
		monthSelect.selectByIndex(2);
		Select yearSelect = new Select(driver.findElement(By.xpath("//select[@name=\"birthday_year\"]")));
		yearSelect.selectByVisibleText("1998");
		// select gender
		driver.findElement(By.xpath("//input[@name=\"sex\"]")).click();
		// clicked on signup
		driver.findElement(By.xpath("//button[@id=\"u_0_14\"]")).click();
	}

	public static void main(String[] args) {
		SeleniumAss3 assignment3 = new SeleniumAss3();
		assignment3.faceBookSignUp();
		driver.quit();
	}
}