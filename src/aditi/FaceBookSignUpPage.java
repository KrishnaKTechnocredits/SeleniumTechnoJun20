package aditi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FaceBookSignUpPage {

	private static WebDriver start(String url) {
		System.setProperty("webdriver.chrome.driver", "./resources/mac/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}

	private boolean pageTitleVerification(WebDriver driver) {
		boolean flag = driver.getTitle().equals("Sign Up for Facebook | Facebook") ? true : false;
		return flag;
	}

	void signUp(WebDriver driver) throws InterruptedException {
		if (pageTitleVerification(driver)) {
			System.out.println("TEST PASS -> User Successfully navigated to FB SignUp page. Current page Title - "
					+ driver.getTitle());
			Thread.sleep(2000);
			// Set Values for Web elements
			driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Aditi");
			driver.findElement(By.name("lastname")).sendKeys("Gajipara");
			driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("788898989hjhj88");
			driver.findElement(By.id("password_step_input")).sendKeys("ABCXYZ");

			// Select Class methods - selectByVisibleText(), selectByIndex(),
			// selectByValue()..
			Select monthSelect = new Select(driver.findElement(By.xpath("//select[@id='month']")));
			monthSelect.selectByVisibleText("Dec");
			Select daySelect = new Select(driver.findElement(By.xpath("//select[@name='birthday_day']")));
			daySelect.selectByIndex(25);
			Select yearSelect = new Select(driver.findElement(By.xpath("//select[@name='birthday_year']")));
			yearSelect.selectByValue("1990");

			// Gender Radio Button..
			driver.findElement(By.xpath("//input[@value='1']")).click();

			// SignUp Button..
			driver.findElement(By.xpath("//button[@name='websubmit']")).click();

		} else {
			System.out
					.println("\"TEST FAIL -> Page navigation did not work. Current page Title - " + driver.getTitle());
		}

	}

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = start("https://www.facebook.com/r.php");
		FaceBookSignUpPage faceBookSignUpPage = new FaceBookSignUpPage();
		faceBookSignUpPage.signUp(driver);
		driver.quit();

	}
}
