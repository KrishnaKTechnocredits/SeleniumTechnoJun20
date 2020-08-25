package ajit;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ajit.base.PredefinedActions;

public class CodingExam1 extends PredefinedActions {
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MICROSECONDS);
	}

	void validateLinkAndCompareTitle() {

		int tableRows = driver.findElements(By.xpath("//table/tbody/tr")).size();
		for (int index = 1; index <= tableRows; index++) {
			WebElement companyLink = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]/a"));
			String companyName = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[2]")).getText();

			if (companyLink.getAttribute("href").equals("")) {
				String empName = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[1]")).getText();
				System.out.println(
						"Link is Missing for company " + companyName + " which has Name: " + empName + "  in the Column Name");
			} else {
				String tableTitle = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[4]")).getText();
				companyLink.click();
				if (!driver.getTitle().equals(tableTitle))
					System.out.println(companyName + " page title match : FAILED");
				else
					System.out.println(companyName + " page title match : PASSED");
				driver.navigate().back();
			}
		}
		driver.close();
	}

	public static void main(String[] args) {
		CodingExam1 codingExam1 = new CodingExam1();
		codingExam1.setUp("D:/JAVA_LEARNING/Projects/TechnoGitProject/Exam-1.html");
		codingExam1.validateLinkAndCompareTitle();

	}

}
