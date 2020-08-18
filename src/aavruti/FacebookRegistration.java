package aavruti;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class FacebookRegistration {

	static WebDriver webDriverPathSetup() {
		String osName = System.getProperty("os.name").toLowerCase();
		System.out.println(osName);
		String path = osName.contains("windows") ? "./resources/windows/chromedriver.exe"
				: osName.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	void fbRegistrationForm(WebDriver driver) {
		driver.get("https://www.facebook.com/r.php");
		try {
			Thread.sleep(1000);
		}
		catch(InterruptedException ie){
			ie.printStackTrace();
		}

		if(driver.getTitle().equals("Sign up for Facebook | Facebook")){
			driver.findElement(By.name("firstname")).sendKeys("Aavruti");
			driver.findElement(By.name("lastname")).sendKeys("Dalmia");
			driver.findElement(By.name("reg_email__")).sendKeys("234234324sfsdf234234");
			driver.findElement(By.name("reg_passwd__")).sendKeys("Don't Ask");

			System.out.println(driver.getPageSource().contains("Date of birth") ? "Page contains Date Of Birth" : "Page doesnot contains Date Of Birth");
			Select selectDate = new Select(driver.findElement(By.xpath("//select[@title='Day']")));
			selectDate.selectByValue("14");
			Select selectMonth = new Select(driver.findElement(By.xpath("//select[@title='Month']")));
			selectMonth.selectByIndex(2);
			Select selectYear = new Select(driver.findElement(By.xpath("//select[@title='Year']")));
			selectYear.selectByVisibleText("1992");

			WebElement gender;

			if(driver.findElement(By.xpath("//input[@name='sex' and @value='1']/..//label")).getText().equalsIgnoreCase("Female")) {
				gender = driver.findElement(By.xpath("//input[@name='sex' and @value='1']"));
				gender.click();
			}
			else if(driver.findElement(By.xpath("//input[@name='sex' and @value='2']/..//label")).getText().equalsIgnoreCase("Female")) {
				gender = driver.findElement(By.xpath("//input[@name='sex' and @value='2']"));
				gender.click();
			}
			else {
				gender = driver.findElement(By.xpath("//input[@name='sex' and @value='3']"));
				gender.click();
			}

			System.out.println(gender.isSelected() ? "Gender selected successfully!!" : "Gender incorrectly selected!!");
			System.out.println((selectDate.getFirstSelectedOption().getText().equals("14") && selectYear.getFirstSelectedOption().getText().equals("1992")) 
					? "Date Of Birth is selected correctly" : "Date Of Birth is not correctly selected");

			driver.findElement(By.name("websubmit")).click();
		}
		else {
			System.out.println("Url not reloaded correctly");
		}
	}

	public static void main(String[] args) {
		new FacebookRegistration().fbRegistrationForm(webDriverPathSetup());
	}
}
