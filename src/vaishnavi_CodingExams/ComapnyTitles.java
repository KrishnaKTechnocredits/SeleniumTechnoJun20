package vaishnavi_CodingExams;

/* 1. Verify title of every link
 * 2. Find Missing Link
 */

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ComapnyTitles {

	void verifyCompanyTitles(WebDriver driver, List<WebElement> listOfLinks) {
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
		try {
			int tablerows = driver.findElements(By.xpath("//table/tbody/tr")).size();
			for (int index = 1; index <= tablerows; index++) {
				WebElement linkPath = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]"));
				String text = linkPath.getText(); 
				String title = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[4]")).getText();
				if (driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]/a")).getAttribute("href").length() != 0) {
					linkPath.click();
					if (driver.getTitle().equals(title)) {
						System.out.println("Title Verified for: " + text);
					} else {
						System.out.println("Test Failed : Title is not correct for:  " + text);
					}
					driver.navigate().back();
				} else {
					System.out.println("Missing link is: "
							+ driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]")).getText());
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
		driver.get("file:///C:/Users/vaish/Desktop/TechnoGitProject/SeleniumTechnoJun20/resources/forms/Exam-1.html");
		driver.manage().window().maximize();
		List<WebElement> listOfLinks = driver.findElements(By.xpath("//a"));
		new ComapnyTitles().verifyCompanyTitles(driver, listOfLinks);
	}

}
