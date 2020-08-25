package rachana.CoadingExam;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import rachana.base.PredefinedActions;

public class Exam1 extends PredefinedActions {

	WebDriver driver;

	void setUp() {
		driver = start(
				"file:///G:/Technocredits/TechnoGitSeleniumProject/SeleniumTechnoJun20/resources/forms/Exam-1.html");
	}

	void verifymissinglink() {

		List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
		for (int i = 1; i <= rows.size(); i++) {
			WebElement linkElement = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[3]/a"));
			String attributeValue = linkElement.getAttribute("href");
			if (attributeValue.length() == 0) {
				System.out.println("Missing link is: " + linkElement.getText());
				System.out.println("Name of Missing Link:"
						+ driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]")).getText());
				System.out.println("Company name of Missing link: "
						+ driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[2]")).getText());
			} else {
				// verify page title
				String expectedTitle = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[4]")).getText();
				String companyName = driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[2]")).getText();
				linkElement.click();
				String actualTitle = driver.getTitle();
				if(!actualTitle.equals(expectedTitle)){
					System.out.println("Page Title is not verfied for the company Name: "+companyName);
				}
				driver.navigate().back();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Exam1 exam1 = new Exam1();
		exam1.setUp();
		exam1.verifymissinglink();
	}
}
