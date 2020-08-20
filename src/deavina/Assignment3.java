package deavina;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignment3 {

	private static WebDriver initializeDriver(String url) {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : " + os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}

	private boolean pageTitleVerification(WebDriver driver) {
		boolean flag = driver.getTitle().equals("Sign up for Facebook | Facebook") ? true : false;
		return flag;
	}

	void fbsignUp(WebDriver driver) throws InterruptedException {
		if (pageTitleVerification(driver)) {
			System.out.println("TEST PASS -> User Successfully navigated to FB SignUp page. Current page Title - "
					+ driver.getTitle());
			Thread.sleep(2000);
			// Set Values for Web elements
			driver.findElement(By.xpath("//input[@id='u_0_m']")).sendKeys("Deavina");
			driver.findElement(By.name("lastname")).sendKeys("Sharma");
			driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("911435fdghjhj88");
			driver.findElement(By.id("password_step_input")).sendKeys("ABCXYZ");

			// Select Class methods - selectByVisibleText(), selectByIndex(),
			// selectByValue()..

			new Select(driver.findElement(By.xpath("//select[@id='month']"))).selectByVisibleText("Jan");

			new Select(driver.findElement(By.xpath("//select[@name='birthday_day']"))).selectByIndex(25);

			new Select(driver.findElement(By.xpath("//select[@name='birthday_year']"))).selectByValue("1989");

			driver.findElement(By.xpath("//input[@value='1']")).click();

			driver.findElement(By.xpath("//button[@name='websubmit']")).click();

		} else {
			System.out
					.println("\"TEST FAIL -> Page navigation did not work. Current page Title - " + driver.getTitle());
		}

	}

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = initializeDriver("https://www.facebook.com/r.php");
		Assignment3 assignment3 = new Assignment3();
		assignment3.fbsignUp(driver);
		//driver.quit();

	}
}
