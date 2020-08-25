package amita;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import amita.base.PredefinedActions;

public class Exam1WebTableURLValidation extends PredefinedActions {
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	void validateURL() throws InterruptedException {
		int numberOfRow = driver.findElements(By.xpath("//table/tbody/tr")).size();
		for (int index = 1; index <= numberOfRow; index++) {
			String companyName = driver.findElement(By.xpath("//tbody/tr[" + index + "]/td[2]")).getText();
			String expectedTitle = driver.findElement(By.xpath("//tbody/tr[" + index + "]/td[4]")).getText();
			WebElement link = driver.findElement(By.xpath("//tbody/tr[" + index + "]/td[3]/a"));
			String name = driver.findElement(By.xpath("//tbody/tr[" + index + "]/td[1]")).getText();
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
			if (!link.getAttribute("href").equals("")) {
				link.click();
				if (driver.getTitle().equals(expectedTitle)) {
					System.out.println("Webpage title matched with : "+companyName);
				} else {
					System.out.println("Webpage title is not matched with : "+companyName);
				}
			} else {
				System.out.println(companyName+"Link is missing for -->"+name);
			}
			driver.navigate().back();
		}
	}

	public static void main(String[] args) {
		Exam1WebTableURLValidation webTableValidation = new Exam1WebTableURLValidation();
		webTableValidation.setUp("file:///D:/JAVA-JUNE20/TechnoGitProject/SeleniumTechnoJun20/resources/forms/Exam-1.html");
		try {
			webTableValidation.validateURL();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
