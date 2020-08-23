package viresh.codingExam1;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import viresh.base.PredefinedActions;

public class WebTableValidations extends PredefinedActions {

	WebDriver driver;
	String compWithMissingLink = null;

	void setUp() {
		driver = start("file:///C:/Users/Viresh.jangam/OneDrive%20-%20Wolters%20Kluwer/Desktop/Viresh/TechnoCredits/TechnoGitProject/javatechnojun20/SeleniumTechnoJun20/resources/forms/Exam-1.html");
	}

	// gives the company names whose expected and actual pagetitles are not matching
	void compareTitle() {
		int totalRows = driver.findElements(By.xpath("//tbody/tr")).size();
		ArrayList<String> alist1 = new ArrayList<>();
		ArrayList<String> alist2 = new ArrayList<>();
		for (int index = 1; index <= totalRows; index++) {
			String expectedTitle = driver.findElement(By.xpath("//tbody/tr[" + index + "]/td[4]")).getText();
			String comName = driver.findElement(By.xpath("//tbody/tr[" + index + "]/td[2]")).getText();
			WebElement compLink = driver.findElement(By.xpath("//tbody/tr[" + index + "]/td[3]/a"));
			String href = compLink.getAttribute("href");
			compLink.click();
			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
			if (!href.equals("")) {
				if (!(driver.getTitle().equals(expectedTitle))) {
					alist1.add(comName);
					System.out.println(comName + ":");
					System.out.println("  Actual Title  : " + driver.getTitle());
					System.out.println("  Expected Title: " + expectedTitle);
				} else {
					alist2.add(comName);
				}
				driver.navigate().back();
			} else
				compWithMissingLink = comName;
		}
		System.out.println();
		System.out.println("Company whose pagetitles are not matching: " + alist1.toString());
		System.out.println("Company whose pagetitles are matching    : " + alist2.toString());
	}

	// displays company name whose link is missing.
	void findMissingLink() {
		System.out.println("Company with missing link                : " + compWithMissingLink);
	}

	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) {
		WebTableValidations wTval = new WebTableValidations();
		wTval.setUp();
		wTval.compareTitle();
		wTval.findMissingLink();
		wTval.tearDown();
	}
}
