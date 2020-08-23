package aavruti;

import aavruti.base.PredefinedActions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CodingExam1 extends PredefinedActions{

	WebDriver driver;
	
	void launchBrowser() {
		driver = start("G:\\TechnoGitProject\\SeleniumTechnoJun20\\resources\\forms\\Exam-1.html");
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
	}
	
	void validateLinks() {
		
		int tableRows = driver.findElements(By.xpath("//table/tbody/tr")).size();
		for(int index=1;index<=tableRows;index++) {
			WebElement linkName = driver.findElement(By.xpath("//table/tbody/tr[" + index +"]/td[3]/a"));
			String companyName = driver.findElement(By.xpath("//table/tbody/tr[" + index +"]/td[2]")).getText();			
			
			if(linkName.getAttribute("href").equals("")) {
				String empName = driver.findElement(By.xpath("//table/tbody/tr[" + index +"]/td[1]")).getText();
				System.out.println(empName + " of Company "+ companyName +" is Missing Link.");
			}
			else {
				String tableTitle = driver.findElement(By.xpath("//table/tbody/tr[" + index +"]/td[4]")).getText();
				linkName.click();				
				if(!driver.getTitle().equals(tableTitle))
					System.out.println(companyName + " title is not getting matched.");
				else
					System.out.println(companyName + " title is getting matched.");
				driver.navigate().back();
			}
		}
	}
	
	void closeBrowser() {
		driver.close();
	}
	
	public static void main(String[] args) {
		CodingExam1 codingExam1 = new CodingExam1();
		codingExam1.launchBrowser();
		codingExam1.validateLinks();
		codingExam1.closeBrowser();
	}
}