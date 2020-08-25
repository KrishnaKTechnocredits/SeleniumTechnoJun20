package madhura;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium_Exam_1 {

	void verifyLinkTitle() {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///E:/TechnoGitProject/SeleniumTechnoJun20/resources/forms/Exam-1.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);

		int totalRows = driver.findElements(By.xpath("//table[1]/tbody/tr")).size();
		System.out.println(totalRows);

		for (int index = 1; index <= totalRows; index++) {
			WebElement element = driver.findElement(By.xpath("//table[1]/tbody/tr[" + index + "]/td[3]/a"));
			try {
				if (element.getAttribute("href").equals("")) {
					System.out.println("\nMissing link -> " + element.getText());
				}
			} catch (NullPointerException ne) {

			}
		}
		// System.out.println(links);

		for (int index = 1; index <= totalRows; index++) {
			String coName = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[2]")).getText();
			String col4 = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[4]")).getText();
			driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]")).click();

			if (col4.equals(driver.getTitle())) {
				System.out.println("Link title verification --> Passed");
			} else {
				System.out.println("Link title verification --> Failed for " + coName);
			}
			
			driver.navigate().back();
		}

		/*
		 * for (int index = 1; index <= totalRows; index++) {
		 * driver.findElement(By.xpath("//table[1]/tbody/tr[" + index +
		 * "]/td[3]")).click(); String col3 = driver.getTitle(); String col4 =
		 * driver.findElement(By.xpath("//table[1]/tbody/tr[" + index +
		 * "]/td[4]")).getText(); if (col3.equals(col4)) {
		 * System.out.println("Link title verification --> Passed"); } else {
		 * System.out.println("Link title verification --> Failed"); }
		 * 
		 * }
		 */
		driver.quit();
	}

	public static void main(String[] args) {
		Selenium_Exam_1 selenium_Exam_1 = new Selenium_Exam_1();
		selenium_Exam_1.verifyLinkTitle();

	}
}