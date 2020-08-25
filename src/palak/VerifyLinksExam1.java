package palak;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import palak.base.PredefinedActions;

public class VerifyLinksExam1 extends PredefinedActions {
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	void verifyLinks() {
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		int totalCompanyRows = driver.findElements(By.xpath("//table/tbody/tr/td[3]")).size();

		for (int index = 1; index <= totalCompanyRows; index++) {
			String expectedTitle = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[4]")).getText();
			String companyName = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[2]")).getText();
			String name = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[1]")).getText();

			if (driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]/a")).getAttribute("href")
					.equals("")) {
				System.out.println("Link is Missing \nName : " + name + "\nCompany Name : " + companyName);
			} else {
				driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]")).click();

				if (expectedTitle.equals(driver.getTitle())) {
					/*
					 * System.out.println("Title Match for Company " + companyName);
					 */
				} else {

					System.out.println("Title Not Match for Company Name : " + companyName);
				}
			}

			driver.navigate().back();
		}
	}

	public static void main(String[] args) {
		VerifyLinksExam1 verifyLinksExam1 = new VerifyLinksExam1();
		verifyLinksExam1
				.setUp("H://TechnoCredits/Projects/Techno Git Project/SeleniumTechnoJun20/resources/forms/Exam-1.html");
		verifyLinksExam1.verifyLinks();
	}

}
