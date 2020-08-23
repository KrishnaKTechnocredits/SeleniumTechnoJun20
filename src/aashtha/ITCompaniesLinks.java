package aashtha;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import aashtha.base.*;

public class ITCompaniesLinks extends PredefinedActions{
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	void tearDown() {
		driver.close();
	}
	
	void verifyTable() throws InterruptedException {
		int totalRows = driver.findElements(By.xpath("//table[1]/tbody/tr")).size();
		for(int index = 1; index <= totalRows; index++) {
			String companyName = driver.findElement(By.xpath("//table[1]/tbody/tr["+index+"]/td[2]")).getText();
			String expectedTitle = driver.findElement(By.xpath("//table[1]/tbody/tr["+index+"]/td[4]")).getText();
			WebElement companyLink = driver.findElement(By.xpath("//table[1]/tbody/tr["+index+"]/td[3]/a"));
			if(!companyLink.getAttribute("href").equals("")) {
				companyLink.click();
				Thread.sleep(2000);
				if(driver.getTitle().equals(expectedTitle)) {
					System.out.println(companyName + " - page title MATCHED");
				}else {
					System.out.println(companyName + " - page title NOT matched");
				}
			}else {
				System.out.println("\n" + driver.findElement(By.xpath("//table[1]/tbody/tr["+index+"]/td[1]")).getText() + " - link missing");
			}
			driver.navigate().back();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ITCompaniesLinks iTCompaniesLinks = new ITCompaniesLinks();
		iTCompaniesLinks.setUp("file:///D:/JavaBasics_JUN20/TechnoGitProject/seleniumtechnojun20/resources/forms/Exam-1.html");
		iTCompaniesLinks.verifyTable();
		iTCompaniesLinks.tearDown();
	}

}
