package suparna;
/*
 * Selenium Assignment -3 : 18th Aug 2020

1. Navigate to facebook sign in page using url https://www.facebook.com/r.php
2. Verify page title is “Sign up for Facebook | Facebook”.
3. Enter Firstname, surname.
4. Enter wrong mobile number (like 788898989hjhj88)
5. Enter any combination of password
6. Using Select class select Date, Month and Year using sequentially selectByValue(), selectByIndex() and selectByVisibleText() 
7. Select gender from given radio button options.
8. Click on SignUp button.
(No need to validate error occurred for not entering a valid phone number.)
 */
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FBSingUP {

	private String path;

	FBSingUP() {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : " + os);
		path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("Webdriver.chrome.driver", path);

	}

	private void fbSingUP() {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/r.php");
		if (driver.getCurrentUrl().equals("https://www.facebook.com/"))
			System.out.println("Test case to  open FB sign in page pass ");
		else
			System.out.println("Test case to  open FB sign in page fail");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException IE) {
			IE.printStackTrace();
		}

		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Suparna");
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Nikam");
		driver.findElement(By.xpath("//input[@aria-label = 'Mobile number or email address']"))
				.sendKeys("patil.suparna@yahoo.com");
		driver.findElement(By.xpath("//input[@name='reg_passwd__']")).sendKeys("******");

		Select birthDateDropDown = new Select(driver.findElement(By.id("day")));
		birthDateDropDown.selectByIndex(16);

		Select birthMonthDropDown = new Select(driver.findElement(By.name("birthday_month")));
		birthMonthDropDown.selectByValue("12");

		Select birthYearDropDown = new Select(driver.findElement(By.id("year")));
		birthYearDropDown.selectByVisibleText("2000");

		driver.findElement(By.xpath("//span/input[@value='1']")).click();

		if (!(driver.findElement(By.xpath("//input[@name='firstname']")).getAttribute("value").isEmpty())
				&& (!(driver.findElement(By.xpath("//input[@name='lastname']")).getAttribute("value").isEmpty()))
				&& birthDateDropDown.getFirstSelectedOption().getText().equals("16")
				&& birthMonthDropDown.getFirstSelectedOption().getText().equals("Dec")
				&& birthYearDropDown.getFirstSelectedOption().getText().equals("2000")
				&& driver.findElement(By.xpath("//span/input[@value='2']")).isSelected()) {
			System.out.println("Assigned value to all filds  ");
		} else
			System.out.println("Feilds validation failed");
		driver.findElement(By.xpath("//button[@name='websubmit']")).click();
		driver.quit();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FBSingUP fbPage = new FBSingUP();
		fbPage.fbSingUP();
		

	}

}
