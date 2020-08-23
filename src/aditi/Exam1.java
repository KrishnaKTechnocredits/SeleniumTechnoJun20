package aditi;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import aditi.base.PredefinedActions;

public class Exam1 extends PredefinedActions {
	WebDriver driver;

	void driverSetup(String url) {
		driver = start(url);
		driver.manage().window().maximize();
	}

	void linkvalidation() throws InterruptedException {
		List<WebElement> list = driver.findElements(By.xpath("//a"));
		for (int index = 1; index <= list.size(); index++) {
			String name = driver.findElement(By.xpath("//tbody/tr[" + index + "]/td[1]")).getText();
			String companyName = driver.findElement(By.xpath("//tbody/tr[" + index + "]/td[2]")).getText();
			WebElement link = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]/a"));
			try {
				if (link.getAttribute("href").isEmpty() || link.getAttribute("href").length() == 0) {
					System.out.println("\nMissing Link -> " + link.getText() + "  name -> " + name
							+ "  Company name -> " + companyName);
				} else {
					String expectedTitle = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[4]"))
							.getText();
					driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]")).click();
					String actualTitle = driver.getTitle();
					driver.navigate().back();
					Thread.sleep(2000);
					if (expectedTitle.equals(actualTitle)) {
						System.out.println("Test Pass - Company Name: " + companyName);
					} else
						System.out.println("Test Fail - Company Name: " + companyName);

				}
			} catch (NullPointerException nullPointerException) {
				System.out.println("\nhref attribute is missing for -> " + link.getText());
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Exam1 exam1 = new Exam1();
		exam1.driverSetup(
				"file:///Users/apple/Documents/TechnoGitProject/SeleniumTechnoJun20/resources/forms/Exam-1.html");
		exam1.linkvalidation();
	}

}
