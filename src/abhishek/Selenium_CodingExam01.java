package abhishek;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import abhishek.base.PredefinedActions;

public class Selenium_CodingExam01 extends PredefinedActions {
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);

	}

	void validatelink() throws InterruptedException {
		int tableRows = driver.findElements(By.xpath("//table/tbody/tr")).size();
		System.out.println(tableRows);

		for (int index = 1; index <= tableRows; index++) {
			WebElement link = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]/a"));
			String companyName = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[2]")).getText();

			if (link.getAttribute("href").equals("")) {
				System.out.println(companyName + " company missing link");
			} else {
				String tableTitle = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[4]")).getText();
				link.click();
				Thread.sleep(2000);
				if (driver.getTitle().equals(tableTitle)) {
					System.out.println("company title mached " + companyName);
				} else
					System.out.println("company title not mached  " + companyName);
			}
			driver.navigate().back();
			Thread.sleep(2000);
		}
	}

	void tearBreak() {
		driver.close();
	}

	public static void main(String[] args) throws InterruptedException {
		Selenium_CodingExam01 selenium_CodingExam01 = new Selenium_CodingExam01();
		selenium_CodingExam01.setUp(
				"file:///Users/adityashivankar/Documents/TechnoGitProject/SeleniumTechnoJun20/resources/forms/Exam-1.html");
		selenium_CodingExam01.validatelink();
		selenium_CodingExam01.tearBreak();
	}
}
