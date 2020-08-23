package mahesh.Exam;

import mahesh.base.PredefinedActions;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CodingExam1 extends PredefinedActions{
	WebDriver driver;
	
	void setUp(String url) {
		driver = start(url);
	}
	
	void linkValidation() {
		int countOfRows = driver.findElements(By.xpath("//tbody/tr")).size();
		for (int index =1; index<=countOfRows;index++) {
			driver.manage().timeouts().implicitlyWait(5000,TimeUnit.MILLISECONDS);
			String webpageTitle = driver.findElement(By.xpath("//tbody/tr["+index+"]/td[4]")).getText();
			String companyName = driver.findElement(By.xpath("//tbody/tr["+index+"]/td[2]")).getText();
			String name = driver.findElement(By.xpath("//tbody/tr["+index+"]/td[1]")).getText();
			WebElement linkElement = driver.findElement(By.xpath("//tbody/tr["+index+"]/td[3]/a"));
			if (!linkElement.getAttribute("href").equals("")) {
				linkElement.click();
				if (!driver.getTitle().equals(webpageTitle)) {
					System.out.println("Title is not matching for company: " + companyName);
				}
				driver.navigate().back();
			}else {
				System.out.println("link is missing for company: " + companyName + " and name: " + name);
			}
				
		}
	}
	
	public static void main(String[] args) {
		CodingExam1 codingExam1 = new CodingExam1();
		codingExam1.setUp("file:///D:/Technocredit/Project/TechnoGitProject/SeleniumTechnoJun20/resources/forms/Exam-1.html");
		codingExam1.linkValidation();
		codingExam1.driver.close();
	}
}
