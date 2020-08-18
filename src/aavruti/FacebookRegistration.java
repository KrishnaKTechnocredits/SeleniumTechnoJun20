package aavruti;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

	void fbRegistrationForm(WebDriver driver){
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

			driver.findElement(By.xpath("//a[@id='gender-help']")).click();
			System.out.println(driver.findElement(By.xpath("//div[@aria-label='Explanation tooltip for gender options during registration']/..//div/div")).getText().equals("You can change who sees your gender on your profile later. Select Custom to choose another gender, or if you'd rather not say.") ? "Gender tooltip data is correct" : "Gender tooltip data is incorrect");
			driver.findElement(By.xpath("//a[@class='_42ft _4jy0 layerCancel uiOverlayButton _4jy3 _4jy1 selected _51sy']")).click();
			
			driver.findElement(By.xpath("//input[@name='sex']/..//label[text()='Female']")).click();
			System.out.println(driver.findElement(By.xpath("//label[text()='Female']/..//input[@name='sex']")).isSelected() 
					? "Gender Female selected successfully!!" : "Gender Female not selected!!");
			
			driver.findElement(By.xpath("//input[@name='sex']/..//label[text()='Male']")).click();
			System.out.println(driver.findElement(By.xpath("//label[text()='Male']/..//input[@name='sex']")).isSelected() 
					? "Gender Male selected successfully!!" : "Gender Male not selected!!");
			
			driver.findElement(By.xpath("//input[@name='sex']/..//label[text()='Custom']")).click();
			Select selectPronoun = new Select(driver.findElement(By.name("preferred_pronoun")));
			selectPronoun.selectByVisibleText("She: \"Wish her a happy birthday!\"");
			driver.findElement(By.name("custom_gender")).sendKeys("Custom Gender");	
			System.out.println(driver.findElement(By.xpath("//label[text()='Custom']/..//input[@name='sex']")).isSelected() 
					? "Gender Custom selected successfully!!" : "Gender Custom not selected!!");
			
			System.out.println((selectDate.getFirstSelectedOption().getText().equals("14") && selectYear.getFirstSelectedOption().getText().equals("1992")) 
					? "Date Of Birth is selected correctly" : "Date Of Birth is not correctly selected");

			driver.findElement(By.name("websubmit")).click();
		}
		else {
			System.out.println("Url not reloaded correctly");
		}
		
		driver.quit();
	}

	public static void main(String[] args) {
		new FacebookRegistration().fbRegistrationForm(webDriverPathSetup());
	}
}
