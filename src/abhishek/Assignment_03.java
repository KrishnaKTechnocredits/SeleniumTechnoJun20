package abhishek;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignment_03 {

	public void FacebookSignUpValidation() throws InterruptedException {

		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : " + os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;

		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		String expectedtitle = "Facebook – log in or sign up";
		String currenttitle = driver.getTitle();
		System.out.println(driver.getTitle());

		// verify Title is “Facebook – log in or sign up”.
		if (currenttitle.equals(expectedtitle))
			System.out.println("you are on the signup page");
		else
			System.out.println("you are not on the signup page");

		driver.findElement(By.id("u_0_2")).click();

		Thread.sleep(5000);
		// fill the facebook signup form
		driver.findElement(By.name("firstname")).sendKeys("abhishek");

		driver.findElement(By.name("lastname")).sendKeys("singh");
		driver.findElement(By.name("reg_email__")).sendKeys("567890189");
		driver.findElement(By.xpath("//input[@autocomplete='new-password']")).sendKeys("9650870770889");
		// select a day
		Select selectDay = new Select(driver.findElement(By.xpath("//select[@name='birthday_day']")));
		selectDay.selectByValue("17");
		// select a month
		Select selectMonth = new Select(driver.findElement(By.xpath("//select[@aria-label='Month']")));
		selectMonth.selectByIndex(12);
		// select a year
		Select selectYear = new Select(driver.findElement(By.xpath("//select[@title='Year']")));
		selectYear.selectByVisibleText("1990");
		//select a gender
		driver.findElement(By.xpath("//input[@type='radio' and @value='2']")).click();

		driver.findElement(By.xpath("//button[@name='websubmit']")).click();
		
		driver.close();
	}

	public static void main(String[] args) throws InterruptedException {
		new Assignment_03().FacebookSignUpValidation();
	}
}
		
