package archana;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CodingExam2 extends PredefinedActions {
	@BeforeClass
	void steUp() {
		start("https://www.snapdeal.com/");
	}

	@AfterClass
	void tearDown() {
		driver.close();
	}

	@Test(priority=0)
	void snapdealTitleVerifivcation() {
		String title = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		if (driver.getTitle().equals(title))
			System.out.println("snapdeal page titled is matched with Expected title.");
		else
			System.out.println("Page titled is not corrcet");
	}

	@Test(priority=1)
	void SnapdealLoginFunction() {
		driver.findElement(By.xpath("//span[text()='Sign In']")).click();
		WebElement login = driver.findElement(By.xpath("//a[@href='https://www.snapdeal.com/login']"));
		if (login.isDisplayed()) {
			System.out.println("Login option is displayed");
			login.click();
		} else
			System.out.println("Login option is not displayed");
		
		driver.switchTo().frame("loginIframe");
		driver.findElement(By.xpath("//span[text()='Facebook']")).click();
		String mainWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		while (itr.hasNext()) {
			String currentWindow = itr.next();
			if (!currentWindow.equals(mainWindow)) {
				driver.switchTo().window(currentWindow);
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys("nawalearchana0@gmail.com");
				driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Password#1");
				driver.findElement(By.xpath("//button[@id='loginbutton']")).click();
			}
		}
		driver.switchTo().window(mainWindow);
	}
	@Test(priority=2)
	void signupPageVerification() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='j_username_new']")));
		System.out.println(
				"EmailID : " + driver.findElement(By.xpath("//input[@id='j_username_new']")).getAttribute("value"));
		System.out
				.println("User Name : " + driver.findElement(By.xpath("//input[@id='j_name']")).getAttribute("value"));
		driver.findElement(By.xpath("//input[@id='j_number']")).sendKeys("902446798");
		driver.findElement(By.xpath("//input[@id='j_password']")).sendKeys("Password1");
		driver.findElement(By.xpath("//div[@class='loginCheckbox keepLoginSignUp']")).click();
	}
}
