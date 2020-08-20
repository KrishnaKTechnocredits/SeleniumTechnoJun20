package rachana;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AccessFacebookWebElement {

	// Verify page title is “Sign up for Facebook | Facebook”.
	static void validatePageTitle(WebDriver driver) {
		
		String expectedpageTitle = "Sign up for Facebook | Facebook";
		if (driver.getTitle().equals(expectedpageTitle)) {
			System.out.println("Pass:Navigation successfull:Page Title as expected");
		} else {
			System.out.println("Fail:Navigation Failed:Page Title is not as expected");
		}
	}

	// fill all entries on form
	static void fillFacebookSignupForm(WebDriver driver) {

		driver.findElement(By.name("firstname")).sendKeys("Rachana");
		driver.findElement(By.name("lastname")).sendKeys("Ghayal");
		driver.findElement(By.name("reg_email__")).sendKeys("788898989hjhj88");
		driver.findElement(By.name("reg_passwd__")).sendKeys("l5g8k234545");

		// select value from dropdown
		WebElement day = driver.findElement(By.id("day"));
		WebElement month = driver.findElement(By.id("month"));
		WebElement year = driver.findElement(By.id("year"));

		Select dobSelect = new Select(day);
		dobSelect.selectByVisibleText("16");
		new Select(month).selectByValue("1");
		new Select(year).selectByVisibleText("1991");

		// select gender radio button
		driver.findElement(By.xpath("//input[@id='u_0_4']")).click();
		// Click on SignUp button.
		driver.findElement(By.xpath("//div[@class='_1lch']/button[@name='websubmit']")).click();
		
	}

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", ".//resources//windows//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Navigate to facebook sign in page using url https://www.facebook.com/r.php
		driver.get("https://www.facebook.com/r.php");
		validatePageTitle(driver);
		fillFacebookSignupForm(driver);
		driver.close();
	}

}
