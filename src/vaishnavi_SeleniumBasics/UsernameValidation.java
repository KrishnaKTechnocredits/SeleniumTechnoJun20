package vaishnavi_SeleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//UserName validation from a table

public class UsernameValidation {

	void verifyUsername(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
		try {
			driver.findElement(By.xpath("//a[text()='Demo Tables']")).click();
			int tablerows = driver.findElements(By.xpath("//table[@id='table1']//tbody/tr")).size();

			for (int index = 1; index <= tablerows; index++) {
				String firstName = driver.findElement(By.xpath("//table[@id='table1']//tbody/tr[" + index + "]/td[2]")).getText();
				String lastName = driver.findElement(By.xpath("//table[@id='table1']//tbody/tr[" + index + "]/td[3]")).getText();
				String userName = driver.findElement(By.xpath("//table[@id='table1']//tbody/tr[" + index + "]/td[4]")).getText();
				String expectedUserName = (firstName.charAt(0) + lastName).toLowerCase();

				if (userName.equals(expectedUserName)) {
					System.out.println("Test Pass: UserName verified for: " + firstName + " " + lastName);
				} else {
					System.out.println("Test Fail: UserName wrong for: " + firstName + " " + lastName);
				}
			}
		} catch (NoSuchElementException ne) {
			ne.printStackTrace();
		} finally {
			driver.close();
		}
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com/");
		driver.manage().window().maximize();
		new UsernameValidation().verifyUsername(driver);
	}

}
